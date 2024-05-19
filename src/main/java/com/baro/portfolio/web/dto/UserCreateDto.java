package com.baro.portfolio.web.dto;

import com.baro.portfolio.domain.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserCreateDto {

    @NotEmpty
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,20}$", message = "ㅎㄱㄿㅍ")
    private String password;

    @NotBlank
    @Size(min = 1, max = 20)
    private String nickname;

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    @NotBlank
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}")
    private String phone;

    @Size(max = 500)
    private String image;

    @Size(max = 400)
    private String introduce;

    public UserCreateDto() {

    }

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .name(name)
                .phone(phone)
                .image(image)
                .introduce(introduce)
                .build();
    }

}
