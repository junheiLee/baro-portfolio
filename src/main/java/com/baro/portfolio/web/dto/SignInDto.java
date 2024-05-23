package com.baro.portfolio.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInDto {

    @NotBlank
    @Size(max = 40)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String password;
}
