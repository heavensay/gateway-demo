package com.gw.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected UserDetailsService userDetailsService(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        password 方案一：明文存储，用于测试，不能用于生产
//        String finalPassword = "123456";
//        password 方案二：用 BCrypt 对密码编码
//        String finalPassword = bCryptPasswordEncoder.encode("123456");
        // password 方案三：支持多种编码，通过密码的前缀区分编码方式
        String finalPassword = /*"{bcrypt}"+*/bCryptPasswordEncoder.encode("123456");//默认方式

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user_1").password(finalPassword).authorities("USER").build());
        manager.createUser(User.withUsername("user_2").password(finalPassword).authorities("USER").build());
        return manager;
    }

    // password 方案一：明文存储，用于测试，不能用于生产
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }

//     password 方案二：用 BCrypt 对密码编码
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    // password 方案三：支持多种编码，通过密码的前缀区分编码方式,推荐
//    @Bean
//    PasswordEncoder passwordEncoder(){
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }

    /**
     * 这一步的配置是必不可少的，否则SpringBoot会自动配置一个AuthenticationManager,覆盖掉内存中的用户
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager manager = super.authenticationManagerBean();
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                //通过formlogin方法登录
                .formLogin()
    //              并设置登录页面url为/api/user/login
    //                .loginPage("/api/user/login")
                    //登录请求处理url
    //                .loginProcessingUrl("/authentication/form")
                    //指定登录成功后跳转到/index页面
    //                .defaultSuccessUrl("/index")
                    //指定登录失败后跳转到/login?error页面
    //                .failureUrl("/login?error")
                    .permitAll()
                    .and()
                .authorizeRequests()
                    .antMatchers("/oauth/*,/hello/*").permitAll()
                    .anyRequest().authenticated();
                //使用自定义授权策略
//                .anyRequest().access("@mySecurity.check(authentication,request)");
    }
}