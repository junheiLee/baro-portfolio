package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.User;

public interface UserRepository {

    int save(User user);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByPhone(String phone);

}
