package com.tj.edu.practice2.ioc;

public class IocApplication {
    public static void main(String[] args) {
        //IoC (Inversion Of Controll(제어의 역전) -> 클래스 생성 주입에 관한 내용

        String url = "https://www.naver.com";
//        String result = new UrlEncoder().encodeString(url);
//        System.out.println("urlEncoder" + result);
        Encoder encoder = new Encoder(new UrlEncoder());
        String resultStr = encoder.encodeString(url);
        byte[] resultByte = encoder.encodeByte(url);

        System.out.println(resultStr);
        System.out.println(resultByte);
    }
}