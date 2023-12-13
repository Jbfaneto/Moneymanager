package com.moneymanager.moneymanager.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.moneymanager.moneymanager.domain.user.User;
import com.moneymanager.moneymanager.service.auth.dto.LoginServiceInputDto;
import com.moneymanager.moneymanager.service.auth.dto.LoginServiceOutputDto;
import com.moneymanager.moneymanager.service.exceptions.AuthenticationException;
import com.moneymanager.moneymanager.service.exceptions.LoginException;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {
    final User uniqueUser = User.with("jbfaneto@gmail.com", "joaoneto");

    final String TOKEN_SECRET = "123456";

    private final String TOKEN_ISSUER = "money.manager";

    public LoginServiceOutputDto login(final LoginServiceInputDto loginServiceInputDto) {
        final var user = User.with(loginServiceInputDto.email(), loginServiceInputDto.password());
        if (!uniqueUser.getEmail().equals(user.getEmail()) || !uniqueUser.getPassword().equals(user.getPassword())) {
            throw new LoginException("User or password not found");
        }
        final var token = this.createToken(user);

        return new LoginServiceOutputDto(token);
    }

    private String createToken(final User user) {
        try {
            final var ALgorithm = Algorithm.HMAC256(TOKEN_SECRET);
            final var token = JWT.create()
                    .withIssuer(TOKEN_ISSUER)
                    .withSubject(user.getEmail())
                    .withExpiresAt(Instant.now().plusSeconds(3600 * 4)) //plus 4 hours
                    .sign(ALgorithm);
            return token;
        } catch (IllegalArgumentException e) {
            throw new AuthenticationException(e.getMessage());
        } catch (JWTCreationException e) {
            throw new AuthenticationException(e.getMessage());
        }

    }

    public String validateToken(final String token) {
        try {
            final var algorithm = Algorithm.HMAC256(TOKEN_SECRET);
            final var subject = JWT.require(algorithm)
                    .withIssuer(TOKEN_ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
            return subject;
        } catch (Exception e){
            return "";
        }
    }
}
