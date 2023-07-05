package com.tj.edu.practice2.spring_ioc_practice;

import org.springframework.stereotype.Component;
import com.tj.edu.practice2.spring_ioc_practice.IEncoder;
import java.util.Base64;

@Component
public class Base64Encoder implements IEncoder{
    @Override
    public String encodeString(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }

    @Override
    public byte[] encodeByte(String message) {
        return Base64.getEncoder().encode(message.getBytes());
    }
}
