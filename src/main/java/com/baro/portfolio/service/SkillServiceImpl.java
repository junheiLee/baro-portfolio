package com.baro.portfolio.service;

import com.baro.portfolio.domain.Skill;
import com.baro.portfolio.repository.itf.SkillRepository;
import com.baro.portfolio.service.itf.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public void save(Skill skill) {
        skillRepository.save(skill);
    }

    @Override
    public Integer findByName(String name) {

        Skill skill = skillRepository.findByName(name)
                .orElseGet(() -> new Skill(-1, "none"));

        return skill.getSeq();
    }

    @Override
    public String findBySeq(Integer seq) {

        Skill skill = skillRepository.findBySeq(seq)
                .orElseThrow(() -> new RuntimeException("exception handler 만들어야하나?"));

        return skill.getName();
    }
}
