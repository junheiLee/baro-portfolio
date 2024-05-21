package com.baro.portfolio.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserProjectMapper {

    void addContributor(int userSeq, int projectSeq, String myPart);

    List<Integer> findUserByProjectSeq(int projectSeq);
}
