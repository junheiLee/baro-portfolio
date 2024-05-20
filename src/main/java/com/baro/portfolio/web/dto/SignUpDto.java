package com.baro.portfolio.web.dto;

import com.baro.portfolio.domain.User;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class SignUpDto {

    private final static String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@!%*#?&.])[A-Za-z0-9$@!%*#?&.]{8,20}$";
    private final static String PHONE_PATTERN = "\\d{3}-\\d{3,4}-\\d{4}";

    @NotEmpty
    @Size(max = 40)
    @Email
    private String email;

    @NotBlank
    @Pattern(regexp = PASSWORD_PATTERN, message = "영문, 숫자, 특수 문자 포함 8 ~ 20자 사이의 비밀 번호를 입력해주세요.")
    private String password;

    @NotBlank
    @Size(min = 1, max = 20)
    private String nickname;

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    @NotBlank
    @Pattern(regexp = PHONE_PATTERN, message = "000-0000-0000 형식으로 입력해 주세요.")
    private String phone;

    @Size(max = 500)
    private String image;

    @Size(max = 400)
    private String introduce;

    public SignUpDto() {

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
