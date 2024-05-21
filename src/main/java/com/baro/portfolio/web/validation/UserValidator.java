package com.baro.portfolio.web.validation;

import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpDto dto = (SignUpDto) target;

        if (userService.isEmailDuplicated(dto.getEmail())) {
            errors.rejectValue("email", "duplication", "이미 가입된 이메일 입니다.");
        }
        if (userService.isNicknameDuplicated(dto.getNickname())) {
            errors.rejectValue("nickname", "duplication", "이미 존재하는 닉네임 입니다.");
        }
        if (userService.isPhoneDuplicated(dto.getPhone())) {
            errors.rejectValue("phone", "duplication", "이미 가입된 핸드폰 번호 입니다.");
        }
    }
}
