package com.baro.portfolio.service.itf;

import com.baro.portfolio.web.dto.UserCreateDto;

public interface UserService {

    int signUp(UserCreateDto dto);
}
