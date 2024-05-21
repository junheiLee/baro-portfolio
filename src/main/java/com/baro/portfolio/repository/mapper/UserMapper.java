package com.baro.portfolio.repository.mapper;

import com.baro.portfolio.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface UserMapper {

    void save(User user);

    Integer countByEmail(String email);

    Integer countByNickname(String nickname);

    Integer countByPhone(String phone);

    Optional<User> findByEmailAndPassword(String email, String password);

    Optional<User> findBySeq(int seq);

    void updateBySeq(int seq, User user);

}
