package com.baro.portfolio.service.itf;

import com.baro.portfolio.web.dto.SignUpDto;

public interface UserService {

    int signUp(SignUpDto dto);

    boolean isEmailDuplicated(String email);

    boolean isNicknameDuplicated(String nickname);

    boolean isPhoneDuplicated(String phone);
}
