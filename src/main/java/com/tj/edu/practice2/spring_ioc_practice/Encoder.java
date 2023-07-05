package com.tj.edu.practice2.spring_ioc_practice;

public class Encoder implements IEncoder{
    private IEncoder iEncoder;
    //생성자
    public Encoder(IEncoder iEncoder){
        this.iEncoder=iEncoder;
    }

    @Override
    public String encodeString(String message) {
        return iEncoder.encodeString(message);
    }

    @Override
    public byte[] encodeByte(String message) {
        return iEncoder.encodeByte(message);
    }
}
