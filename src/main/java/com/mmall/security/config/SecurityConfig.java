package com.mmall.security.config;

import com.mmall.security.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity  // 启用 Web 安全配置
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/user/login.do", "/user/register.do", "/user/forget_check_answer.do", "/user/forget_reset_password.do").permitAll() // 登录、注册接口开放
                .antMatchers("/user/**").authenticated()
                .anyRequest().permitAll() // 其他接口允许访问
                .and()
                .csrf().disable()
                .formLogin().disable();

        // 添加 JwtAuthenticationFilter 到 Spring Security 过滤链中
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

//        return http.build();
    }
}


