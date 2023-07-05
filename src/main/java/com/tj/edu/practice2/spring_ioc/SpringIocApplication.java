package com.tj.edu.practice2.spring_ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringIocApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringIocApplication.class, args);
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();

        String url = "https://www.naver.com";

        Encoder encoderUrl = ac.getBean("urlEncode", Encoder.class);
        Encoder encoderBase64 = ac.getBean("base64Encode", Encoder.class);

        String resultStr = encoderUrl.encodeString(url);
        byte[] resultByte = encoderUrl.encodeByte(url);

        System.out.println(resultStr);
        System.out.println(encoderBase64.encodeString(url));
        System.out.println("spring-ioc연습 끝");
    }
}

@Configuration(enforceUniqueMethods = false)
class AppConfig {

    @Bean
    public Encoder base64Encode(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }

    @Bean
    public Encoder urlEncode(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }
}
