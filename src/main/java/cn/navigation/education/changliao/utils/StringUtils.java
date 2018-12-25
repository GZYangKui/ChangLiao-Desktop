package cn.navigation.education.changliao.utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
    //将字符串装换为MD5
    public static String toMd5(String msg){
        //指定加密算法
        MessageDigest md5= null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[]b = md5.digest(msg.getBytes());
        return new String(b);
    }
}
