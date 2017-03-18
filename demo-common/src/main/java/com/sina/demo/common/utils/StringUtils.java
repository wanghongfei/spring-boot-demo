package com.sina.demo.common.utils;

import java.util.Random;

/**
 * Created by wanghongfei on 13/03/2017.
 */
public class StringUtils {
    private static char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

    /**
     * 生成随机字符串, a-z, A-Z, 0-9
     * @param len
     * @return
     */
    public static String randomString(int len) {
        Random random = new Random();
        int max = codeSequence.length;

        StringBuilder sb = new StringBuilder(len);
        for (int ix = 0; ix < len; ++ix) {
            int index = random.nextInt(max);
            sb.append(codeSequence[index]);
        }

        return sb.toString();
    }
}
