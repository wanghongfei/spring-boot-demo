package com.sina.demo.common.utils;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created by wanghongfei on 13/03/2017.
 */
public class PictureGenUtils {
    // 验证码图片的宽度。
    public static int width = 150;
    // 验证码图片的高度。
    public static int height = 30;

    // 两个字符之前的间隔
    public static int codeWidth = 15;



    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("pic.jpeg");
        new PictureGenUtils().genPic("ABCD8", fos);
        fos.close();
    }

    public static void genPic(String code, OutputStream out) throws IOException {
        int fontHeight = height;
        int codeY = height;

        Random random = new Random();

        // 定义图像buffer
        int wd = width / 2 + 10;
        BufferedImage buffImg = new BufferedImage(wd, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 将图像填充为白色
        g.setColor(getRandColor(220, 250));
        g.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("黑体", Font.BOLD, fontHeight - 6);
        // 设置字体。
        g.setFont(font);
        g.setColor(getRandColor(120,200));

        // 随机产生干扰点，使图象中的认证码不易被其它程序探测到。
        for (int i = 0; i < 500; i++){
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            g.drawOval(x, y, 0, 0);
        }

        for (int ix = 0 ; ix < code.length() ; ++ix) {
            // 字符的x坐标
            int x = (ix + 1) * codeWidth - 10;
            // 字符的y坐标
            int y = codeY - 5;

            // 上下漂移标志
            boolean offsetY = random.nextBoolean();

            if (offsetY) {
                y -= 2;
            } else {
                y += 2;
            }

            g.drawString(code.charAt(ix) + "", x, y);
        }

        ImageIO.write(buffImg, "jpeg", out);

    }

    /**
     * 产生随机颜色
     *
     * @param num1
     * @param num2
     * @return Color
     */
    private static Color getRandColor(int num1, int num2) {
        Random random = new Random();
        if (num1 > 255) {
            num1 = 255;
        }

        if (num2 > 255) {
            num2 = 255;
        }

        int r = num1 + random.nextInt(num2 - num1);
        int g = num1 + random.nextInt(num2 - num1);
        int b = num1 + random.nextInt(num2 - num1);

        return new Color(r, g, b);
    }
}
