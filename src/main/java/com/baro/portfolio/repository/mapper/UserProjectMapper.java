package com.baro.portfolio.repository.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserProjectMapper {

    void addContributor(int userSeq, int projectSeq, String myPart);
}
