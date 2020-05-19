package com.example.utildemo.util;


import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * 学习链接：
 *      https://baijiahao.baidu.com/s?id=1644892102150918183&wfr=spider&for=pc
 *      https://blog.csdn.net/qq_20545367/article/details/79538530
 * @author Song Xianzhuo
 * @version 1.0.0
 * @since 2020/05/09
 **/
public class Base64Utils {

    /**
     * base64的编码和解码，jdk已经实现，并给封装好了，可以直接使用，另外还有Commons Codec和Bouncy Castle，其实现过程与jdk类似
     * @param args
     * @throws UnsupportedEncodingException
     */

    public static void main(String[] args) throws UnsupportedEncodingException {
        String string = "宋献卓";
        // 编码
        String encode = Base64.getEncoder().encodeToString(string.getBytes("UTF-8"));
        System.out.println(encode);
        // 解码
        byte[] decode = Base64.getDecoder().decode(encode);
        System.out.println(new String(decode, "UTF-8"));

    }
}
