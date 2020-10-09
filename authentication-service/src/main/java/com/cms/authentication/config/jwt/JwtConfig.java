package com.cms.authentication.config.jwt;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class JwtConfig {
    @Value("#{${security.jwt.expiration}}")
    private int expiration;

    @Value("${security.jwt.secret}")
    private String secretKey;

}