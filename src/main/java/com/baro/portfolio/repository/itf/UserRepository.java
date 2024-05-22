package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.User;

import java.util.Optional;

public interface UserRepository {

    int save(User user);

    boolean existsByEmail(String email);

    boolean existsByNickname(String nickname);

    boolean existsByPhone(String phone);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findBySeq(int userSeq);

    void updateBySeq(int userSeq, User userParam);
}
