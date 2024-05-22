package com.baro.portfolio.repository.mapper;

import com.baro.portfolio.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProjectMapper {

    void addContributor(int userSeq, int projectSeq, String myPart);

    List<User> findUserByProjectSeq(int projectSeq);

    String findMyPart(int userSeq, int projectSeq);

    void updateMyPart(int userSeq, int projectSeq, String myPart);

    int remove(int userSeq, int projectSeq);
}
