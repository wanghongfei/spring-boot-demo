# Demo
SpringBoot Web项目, 包括Session集中存储、安全授权、缓存、异常处理机制、动态数据源、Service层单元测试

## Session集中式存储
### 应用场景
应用集群部署但无法做到完全无状态时，需要共享Session信息。

### 实现
在控制器中可以自由使用`HttpSession`对象, Spring Session会自动将Session同步到Redis中以解决集群部署时会话同步问题。默认连接 `127.0.0.1:3306`, 无密码, `application.properties`文件中的`spring.redis.*`属性可配置连接信息。若使用了Redis3.X新版本的集群功能，可通过`spring.redis.cluster.*`属性配置集群信息。`TitanSessionConfig`为Spring Session的配置类, 该类指定了Spring Session通过Lettuce客户端连接Redis, 使用Jackson序列化/反序列化Session数据，以及标识session的cookie名。

## 权限
### 应用场景
用户登录, 基于role-permission的授权机制

### 实现
项目使用的安全框架为Spring Security。与登录授权相关的类有:
- TitanAuthProvider: 登陆验证
- TitanWebSecurityConfig: 配置URL对应需要的权限信息
- DemoController#login: 用户登陆入口

## 缓存
### 应用场景
程序有时需要进行昂贵操作才能获取到一些数据(如远程调用、复杂SQL查询)，但这部分数据在较长时间内都不会发生变化, 此时使用本地内存缓存能有效提升性能。

### 实现
项目使用Spring Cache + Ehcache实现本地缓存, Spring Cache为缓存提供统一抽象，Ehcache是具体的缓存实现。相关配置有：
- TitanCacheConfig: 标注`@EnableCaching`注解
- resources/ehcache.xml: Ehcache配置文件
- application.properties中的`spring.cache.ehcache.config`属性: 指定Ehcache配置文件路径
使用时只需要在Service方法上添加`@Cachealbe("titan")`注解即可。将来可根据需要无缝切换成Redis, Mongodb等存储方式。

## 异常处理机制
异常处理可分为以下几类：
- 用户名不存在, 密码错误等与具体业务逻辑相关的可控业务异常。开发者在编码时可根据业务需要自定义异常，但必须继承自`TitanException`。该父异常为运行时异常，因此业务代码不会被大量的try-cache扰乱。Controller/Service方法抛出的异常会在`TitanRestExceptionHandler`中进行统一集中处理。
- 权限类异常。`TitanUnauthenticatedHandler`处理未登录用户访问受保护资源时应返回的错误信息，`TitanDeniedHandler`处理用户已登录但无权访问目标资源的错误信息。
- 其它异常。 Controller/Service层抛出的任何异常都会被`TitanRestExceptionHandler`处理, 还未到达Controller层就抛出的异常(如404, SpringMVC框架级的异常)会被`TitanErrorHandler`处理。

## 动态数据源
### 应用场景
数据库使用了主从结构, 写操作连接master库，读操作连接slave库，但又不想使用数据库中间件。

### 实现
可以在Service方法上添加`@DS("数据源名")`的方式动态切换数据源实现应用层控制读写分离。相关配置类有：
- DataSourceConfig: 读取`application.properties`配置信息，创建两个数据源
- DatSourceContextHolder: 通过`ThreadLocal`对象在当前线程保存数据源名
- DS: 注解定义
- DynamicDataSource: 动态数据源对象，通过继承Spring自带的`AbstractRoutingDataSource`实现
- DynamicDataSourceAspect: 拦截`@DS`注解切换数据源的切面代码
- MybatisTitanDbConfig: 配置Mybatis使用动态数据源, 即前面定义的`DynamicDataSource`

### Mysql表结构
demo中的DAO使用了以下schema:
```
CREATE TABLE IF NOT EXISTS `demo/demo-slave`.`member` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL DEFAULT '',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
```

## Service层单元测试
所有测试类要继承自`BaseTestClass`, test/resources目录下的`application.properties`中配置跑测试时需要的配置。样例：`MapperTest`


## 模块
- titan-common: 业务逻辑
- titan-web: Web应用(控制器, Servlet, 拦截器等与Web相关的代码)
- titan-job: 定时任务
