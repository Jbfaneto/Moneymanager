package com.moneymanager.moneymanager.controller.authentication;

import com.moneymanager.moneymanager.controller.authentication.dto.LoginRequestDto;
import com.moneymanager.moneymanager.controller.authentication.dto.LoginResponseDto;
import com.moneymanager.moneymanager.controller.authentication.dto.ValidateRequestDto;
import com.moneymanager.moneymanager.controller.authentication.dto.ValidateResponseDto;
import com.moneymanager.moneymanager.controller.authentication.dto.mapper.LoginRequestToLoginServiceMapper;
import com.moneymanager.moneymanager.service.auth.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid final LoginRequestDto input){
        final var serviceInput = LoginRequestToLoginServiceMapper.build().apply(input);
        final var token = authService.login(serviceInput).token();
        final var response = new LoginResponseDto(token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/validate")
    public ResponseEntity<ValidateResponseDto> validade(@RequestBody @Valid final ValidateRequestDto input){
        //TODO: colocar no service
        final var serviceOutput = this.authService.validateToken(input.token());
        var isValid = false;

        if (!serviceOutput.isBlank()){
            isValid = true;
        }
        return ResponseEntity.ok(new ValidateResponseDto(isValid));
    }


}
