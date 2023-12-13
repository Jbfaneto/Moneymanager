package com.moneymanager.moneymanager.controller.authentication.dto.mapper;

import com.moneymanager.moneymanager.controller.authentication.dto.LoginRequestDto;
import com.moneymanager.moneymanager.service.auth.dto.LoginServiceInputDto;

import java.util.function.Function;

public class LoginRequestToLoginServiceMapper implements Function<LoginRequestDto, LoginServiceInputDto> {
    public static LoginRequestToLoginServiceMapper build(){
        return new LoginRequestToLoginServiceMapper();
    }
    @Override
    public LoginServiceInputDto apply(LoginRequestDto loginRequestDto) {
        return new LoginServiceInputDto(loginRequestDto.email(), loginRequestDto.password());
    }

}
