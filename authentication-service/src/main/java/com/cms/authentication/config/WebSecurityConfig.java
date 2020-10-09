package com.cms.authentication.config;

import com.cms.authentication.service.UserAuthDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserAuthDetailsService userAuthDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userAuthDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                    .disable()
                    .authorizeRequests()
//                    .antMatchers("/api/v1/auth/register").hasAnyAuthority("ROLE_ADMIN", "ROLE_USER")
                    .requestMatchers(CorsUtils::isPreFlightRequest)
                    .permitAll()
                .antMatchers("/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .logout()
                    .logoutUrl("/api/v1/auth/logout")
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
//                    .logoutSuccessUrl("https://www.baeldung.com/spring-security-logout")
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .permitAll()
                .and()
                    .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                    .and()
//                .cors();
    }
}
