package com.baro.portfolio.web.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInDto {

    @Size(max = 40)
    private String email;

    @Size(max = 20)
    private String password;
}
