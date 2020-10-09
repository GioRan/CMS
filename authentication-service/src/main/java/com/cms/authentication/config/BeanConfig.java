package com.cms.authentication.config;


import com.cms.authentication.controller.response.builder.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public ResponseBuilder responseBuilder(){
        return new ResponseBuilder();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
