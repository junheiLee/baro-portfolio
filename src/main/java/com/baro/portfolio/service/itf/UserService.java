package com.baro.portfolio.service.itf;

import com.baro.portfolio.web.dto.AccountInfo;
import com.baro.portfolio.web.dto.SignInDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.UserInfo;

import java.util.Optional;

public interface UserService {

    int signUp(SignUpDto dto);

    boolean isEmailDuplicated(String email);

    boolean isNicknameDuplicated(String nickname);

    boolean isPhoneDuplicated(String phone);

    Optional<AccountInfo> signIn(SignInDto dto);

    UserInfo findBySeq(int seq);
}
