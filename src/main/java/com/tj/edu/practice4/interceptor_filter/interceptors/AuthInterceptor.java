package com.tj.edu.practice4.interceptor_filter.interceptors;

import com.tj.edu.practice4.interceptor_filter.annotations.AuthUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.lang.annotation.Annotation;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerInterceptor.super.preHandle(request, response, handler);
        System.out.println("AuthInterceptor preHandle 실행");

        //request URL 문자열을 parsing 해서 특정한 url 만 interceptor 후처리가 가능
        String requestURI = request.getRequestURI(); // /authapi/~~, /api/~~
        boolean isValidAccessAuthUserAnnotation = checkAccessValidAnnotation(handler, AuthUser.class);
        boolean isValidAccessAuthLogAnnotation = checkAccessValidAnnotation(handler, AuthUser.class);

        if(isValidAccessAuthLogAnnotation || isValidAccessAuthUserAnnotation){
            return true;
        }

        return false;
    }

    private boolean checkAccessValidAnnotation(Object handler, Class clazz){
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object authUserHandlerMethod = handlerMethod.getMethodAnnotation(clazz);
        Annotation annotation = handlerMethod.getBeanType().getAnnotation(clazz);

        if (authUserHandlerMethod != null || annotation != null) {
            System.out.println("어노테이션 체크 class이름: " + clazz.getName());
            return true;
        }
        // logic코드 예:전화번호 암호화
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);

        // logic코드 예:전화번호 복호화
        System.out.println("AuthInterceptor postHandle 실행");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);

        // logic코드 예: 전화번호 암복호화 완료된 것만 다른 db에 남기기
        System.out.println("AuthInterceptor afterCompletion 실행");
    }
}
