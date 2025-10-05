package com.example.elaboratocookaroo.Common;

public class Methods {
    public static String CifrarioCesare(String s,int n) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++)
            sb.append((char)(((int)s.charAt(i)+n-97)%26+97));
        return sb.toString();
    }
    public static void SJ(Thread t){
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
