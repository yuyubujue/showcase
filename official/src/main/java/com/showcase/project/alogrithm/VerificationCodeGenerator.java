package com.showcase.project.alogrithm;

import java.util.HashSet;
import java.util.Random;

public class VerificationCodeGenerator {
    public static String[] getRandomStringArray(int length,int size){
        String[] strs = new String[size];
        char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        HashSet<String> set = new HashSet<>();
        while (set.size() < size){
            sb.setLength(0);
            for (int i = 0; i < length; i++)
                sb.append(chars[random.nextInt(36)]);
            set.add(sb.toString());
        }
        int i= 0;
        for (String s : set)
            strs[i++] = s;
        return strs;
    }

    public static String getRandomVCode(){
        Random random = new Random();
        int size = 100;
        int arrive = random.nextInt(100);
        String[] rArray = getRandomStringArray(6,size);
        return rArray[arrive];
    }
}
