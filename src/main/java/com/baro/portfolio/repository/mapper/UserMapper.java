package com.baro.portfolio.repository.mapper;

import com.baro.portfolio.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void save(User user);

    Integer countByEmail(String email);

    Integer countByNickname(String nickname);

    Integer countByPhone(String phone);
}
