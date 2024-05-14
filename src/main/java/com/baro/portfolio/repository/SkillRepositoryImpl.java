package com.baro.portfolio.repository;

import com.baro.portfolio.domain.Skill;
import com.baro.portfolio.repository.itf.SkillRepository;
import com.baro.portfolio.repository.mapper.SkillMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SkillRepositoryImpl implements SkillRepository {

    private final SkillMapper skillMapper;

    @Autowired
    public SkillRepositoryImpl(SkillMapper skillMapper) {
        this.skillMapper = skillMapper;
    }

    @Override
    public void save(Skill skill) {
        skillMapper.save(skill);
    }

    @Override
    public Optional<Skill> findByName(String name) {
        return skillMapper.findByName(name);
    }

    @Override
    public Optional<Skill> findBySeq(Integer seq) {
        return skillMapper.findBySeq(seq);
    }
}
