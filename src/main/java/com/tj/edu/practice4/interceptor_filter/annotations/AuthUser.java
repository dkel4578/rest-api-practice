package com.tj.edu.practice4.interceptor_filter.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ METHOD, FIELD, TYPE })
@Retention(RUNTIME)
public @interface AuthUser {

}
