package com.baro.portfolio.repository.mapper;

import com.baro.portfolio.domain.Skill;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SkillMapper {

    void save(Skill skill);

    Optional<Skill> findByName(String name);

    Optional<Skill> findBySeq(Integer seq);
}
