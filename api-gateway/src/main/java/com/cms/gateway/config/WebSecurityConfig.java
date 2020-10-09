package com.cms.gateway.config;

import com.cms.gateway.service.UserAuthDetailsService;
import com.cms.gateway.config.jwt.JwtSecurityConfigurer;
import com.cms.gateway.config.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private static final String[]	AUTH_WHITELIST	= { "/wallet-service/**" };
    private static final String[]	AUTH_PUBLIC	= { "/eureka",
                                                    "/authentication-service/**",
                                                    "/swagger-resources/**",
                                                    "/swagger-ui.html",
                                                    "/v2/api-docs",
                                                    "/webjars/**"};

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring().antMatchers( "/authentication-service/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                    .authorizeRequests()
                    .requestMatchers(CorsUtils::isPreFlightRequest)
                .permitAll()
                    .antMatchers(AUTH_PUBLIC)
                .permitAll()
                    .antMatchers(AUTH_WHITELIST)
                    .authenticated()
                    .and()
                .httpBasic()
                    .and()
                .apply(new JwtSecurityConfigurer(jwtTokenProvider))
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .logout()
                    .logoutUrl("/authentication-service/api/v1/auth/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");

//                .cors();


//        http.headers().frameOptions().sameOrigin().httpStrictTransportSecurity().disable();
    }


}
