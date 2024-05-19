package com.baro.portfolio.service;

import com.baro.portfolio.repository.itf.UserRepository;
import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.UserCreateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public int signUp(UserCreateDto dto) {

        return userRepository.save(dto.toEntity());
    }
}
