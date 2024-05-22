package com.baro.portfolio.web.validation;

import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.EditUserDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.Unique;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {

    private final UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Unique.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (target instanceof SignUpDto) {
            validateSignUpDto((SignUpDto) target, errors);

        } else if (target instanceof EditUserDto) {
            validateEditUserDto((EditUserDto) target, errors);
        }

    }

    private void validateSignUpDto(SignUpDto target, Errors errors) {

        if (userService.isEmailDuplicated(target.getEmail())) {
            errors.rejectValue("email", "duplication", "이미 가입된 이메일 입니다.");
        }

        if (userService.isNicknameDuplicated(target.getNickname(), "")) {
            errors.rejectValue("nickname", "duplication", "이미 존재하는 닉네임 입니다.");
        }

        if (userService.isPhoneDuplicated(target.getPhone())) {
            errors.rejectValue("phone", "duplication", "이미 가입된 핸드폰 번호 입니다.");
        }
    }

    private void validateEditUserDto(EditUserDto target, Errors errors) {
        log.info("validator editUserDto={}", target.toString());

        if (userService.isNicknameDuplicated(target.getNickname(), target.getCurrentNickname())) {
            errors.rejectValue("nickname", "duplication", "이미 존재하는 닉네임 입니다.");
        }
    }
}
