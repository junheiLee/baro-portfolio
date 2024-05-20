package com.baro.portfolio.repository;

import com.baro.portfolio.domain.User;
import com.baro.portfolio.repository.itf.UserRepository;
import com.baro.portfolio.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserMapper userMapper;

    @Override
    public int save(User user) {
        userMapper.save(user);
        return user.getSeq();
    }

    @Override
    public boolean existsByEmail(String email) {
        return userMapper.countByEmail(email) == 1;
    }

    @Override
    public boolean existsByNickname(String nickname) {
        return userMapper.countByNickname(nickname) == 1;
    }

    @Override
    public boolean existsByPhone(String phone) {
        return userMapper.countByPhone(phone) == 1;
    }
}
