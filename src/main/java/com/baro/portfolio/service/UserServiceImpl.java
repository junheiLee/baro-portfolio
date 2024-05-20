package com.baro.portfolio.service;

import com.baro.portfolio.repository.itf.UserRepository;
import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.SignUpDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public int signUp(SignUpDto dto) {

        return userRepository.save(dto.toEntity());
    }

    @Override
    public boolean isEmailDuplicated(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isNicknameDuplicated(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isPhoneDuplicated(String phone) {
        return userRepository.existsByPhone(phone);
    }
}
