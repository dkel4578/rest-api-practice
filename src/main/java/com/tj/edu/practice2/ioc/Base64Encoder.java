package com.tj.edu.practice2.ioc;

import java.util.Base64;

import java.util.Base64.Encoder;

public class Base64Encoder implements IEncoder {

    @Override
    public String encodeString(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    @Override
    public byte[] encodeByte(String message) {
        Encoder encoder = Base64.getEncoder();
        byte[] encodedBytes = encoder.encode(message.getBytes());
        return encodedBytes;
    }
}
