package com.example.fgocalculation.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.example.fgocalculation.entity.UserEntity;
import com.example.fgocalculation.repository.UserRepository;
import com.example.fgocalculation.encoder.PasswordHasher;

@Configuration
public class FormAuthenticationProvider implements AuthenticationProvider {

    protected static Logger log = LoggerFactory.getLogger(FormAuthenticationProvider.class);

    @Autowired
    private UserRepository repository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        String name = auth.getName();
        String password = auth.getCredentials().toString();

        log.debug("name={}", name);
        log.debug("password={}", password);

        if ("".equals(name) || "".equals(password)) {
            throw new AuthenticationCredentialsNotFoundException("ログイン情報に不備があります。");
        }

        UserEntity entity = repository.findByEmail(name);
        if (entity == null) {
            throw new AuthenticationCredentialsNotFoundException("ログイン情報が存在しません。");
        }
        
        if (!(entity.getPassword().equals(new PasswordHasher().hasher(password)))) {
            throw new AuthenticationCredentialsNotFoundException("パスワードが一致しません。");
        }
        return new UsernamePasswordAuthenticationToken(entity, password, entity.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
