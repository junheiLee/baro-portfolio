package com.baro.portfolio.service.itf;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.web.dto.EditUserDto;
import com.baro.portfolio.web.dto.SignInDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.result.UserInfo;

import java.util.Optional;

public interface UserService {

    void signUp(SignUpDto dto);

    boolean isEmailDuplicated(String email);

    boolean isNicknameDuplicated(String nickname);

    boolean isPhoneDuplicated(String phone);

    Optional<Account> signIn(SignInDto dto);

    UserInfo findBySeq(int userSeq);

    EditUserDto findEditUserInfoBySeq(int userSeq);

    void updateBySeq(int userSeq, EditUserDto dto);
}
