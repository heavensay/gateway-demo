package com.gw.authserver.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证
 * @author lijianyu
 * @date 2018/10/17 15:42
 */
public class PasswordAuthenticationManager implements AuthenticationManager {

    private static List<GrantedAuthority> authorities = new ArrayList();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        if(authentication.getName() == ""){
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), authorities);
        }

        throw new RuntimeException("认证错误");
    }

}
