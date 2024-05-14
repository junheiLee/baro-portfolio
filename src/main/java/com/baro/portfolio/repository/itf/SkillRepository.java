package com.baro.portfolio.repository.itf;

import com.baro.portfolio.domain.Skill;

import java.util.Optional;

public interface SkillRepository {

    void save(Skill skill);

    Optional<Skill> findByName(String name);

    Optional<Skill> findBySeq(Integer seq);

}
