package com.baro.portfolio.service;

import com.baro.portfolio.domain.User;
import com.baro.portfolio.repository.itf.UserRepository;
import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.AccountInfo;
import com.baro.portfolio.web.dto.SignInDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Optional<AccountInfo> signIn(SignInDto dto) {

        Optional<User> userOptional
                = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();
        AccountInfo accountInfo = new AccountInfo(user.getSeq(), user.getEmail());

        return Optional.of(accountInfo);

    }

    @Override
    public UserInfo findBySeq(int seq) {

        Optional<User> userOptional = userRepository.findBySeq(seq);
        User user = userOptional.orElseThrow();

        return UserInfo.builder()
                .seq(seq)
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .introduce(user.getIntroduce())
                .build();
    }
}
