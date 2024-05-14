package com.baro.portfolio.service.itf;

import com.baro.portfolio.domain.Skill;

public interface SkillService {

    void save(Skill skill);

    Integer findByName(String name);

    String findBySeq(Integer seq);

}
