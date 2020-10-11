package com.cms.gateway.config;

import com.cms.gateway.rest.response.ResponseBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ResponseBuilder responseBuilder(){
        return new ResponseBuilder();
    }

    @Bean
    public ModelMapper modelMapper() {return new ModelMapper();}
}
