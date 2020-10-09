package com.cms.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")
//                        .allowedOrigins("http://localhost:3000", "*")
//                        .allowedHeaders("*").allowCredentials(true).exposedHeaders("Authorization").maxAge(36000);
//            }
//        };
//    }

}