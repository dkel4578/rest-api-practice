package com.tj.edu.practice2.spring_ioc_practice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class SpringIocPracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringIocPracticeApplication.class, args);
        ApplicationContext ac = ApplicationContextProvider.getApplicationContext();

        String url = "https://www.naver.com";

        Encoder encoderUrl = ac.getBean("urlEncode", Encoder.class);
        Encoder encodeBase64 = ac.getBean("base64Encode", Encoder.class);

        System.out.println(encoderUrl.encodeString(url));
        System.out.println(encodeBase64.encodeString(url));

    }
}
@Configuration(enforceUniqueMethods = false)
class AppConfig{
    @Bean
    public Encoder urlEncode(UrlEncoder urlEncoder){
        return new Encoder(urlEncoder);
    }
    @Bean
    public Encoder base64Encode(Base64Encoder base64Encoder){
        return new Encoder(base64Encoder);
    }
}
