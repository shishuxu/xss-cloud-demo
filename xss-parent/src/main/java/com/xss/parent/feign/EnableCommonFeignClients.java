//package com.xss.parent.feign;
//
//import org.springframework.cloud.openfeign.EnableFeignClients;
//
//import java.lang.annotation.*;
//
//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
//@Documented
//@EnableFeignClients
//public @interface EnableCommonFeignClients {
//    String[] value() default {};
//    //这里添加自己项目的包扫描路径
//    String[] basePackages() default {""};
//
//    Class<?>[] basePackageClasses() default {};
//
//    Class<?>[] defaultConfiguration() default {};
//
//    Class<?>[] clients() default {};
//}
