package com.baro.portfolio.repository;

import com.baro.portfolio.domain.User;
import com.baro.portfolio.repository.itf.UserRepository;
import com.baro.portfolio.repository.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userMapper.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<User> findBySeq(int seq) {
        return userMapper.findBySeq(seq);
    }

    @Override
    public void updateBySeq(int seq, User user) {
        userMapper.updateBySeq(seq, user);
    }
}
