package com.baro.portfolio.service;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.domain.User;
import com.baro.portfolio.exception.NotFoundException;
import com.baro.portfolio.repository.itf.UserRepository;
import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.EditUserDto;
import com.baro.portfolio.web.dto.SignInDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.result.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.baro.portfolio.constant.ErrorEnum.NOT_FOUND_USER;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void signUp(SignUpDto dto) {

        userRepository.save(dto.toEntity());
    }

    @Override
    public boolean isEmailDuplicated(String email) {

        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean isNicknameDuplicated(String nickname, String currentNickname) {

        if (currentNickname.equals(nickname)) {
            return false;
        }
        return userRepository.existsByNickname(nickname);
    }

    @Override
    public boolean isPhoneDuplicated(String phone) {

        return userRepository.existsByPhone(phone);
    }

    @Override
    public Optional<Account> findByEmailAndPassword(SignInDto dto) {

        Optional<User> userOptional
                = userRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());

        if (userOptional.isEmpty()) {
            return Optional.empty();
        }

        User user = userOptional.get();
        Account account = new Account(user.getSeq(), user.getEmail());

        return Optional.of(account);
    }

    @Override
    public UserInfo findBySeq(int seq) {

        User user = userRepository.findBySeq(seq)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER.getMessage()));

        return UserInfo.builder()
                .seq(seq)
                .name(user.getName())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .introduce(user.getIntroduce())
                .build();
    }

    @Override
    public EditUserDto findEditUserInfoBySeq(int userSeq) {

        User user = userRepository.findBySeq(userSeq)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_USER.getMessage()));

        return EditUserDto.fromEntity(user);
    }

    @Override
    public void updateBySeq(int userSeq, EditUserDto dto) {

        User user = dto.toEntity();
        userRepository.updateBySeq(userSeq, user);
    }
}
