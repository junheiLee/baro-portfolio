package com.baro.portfolio.service;

import com.baro.portfolio.domain.Skill;
import com.baro.portfolio.service.itf.SkillService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class SkillServiceTest {

    @Autowired
    SkillService skillService;

    @Test
    void saveAndFind() {

        //given
        Skill skill = new Skill("test!!");

        //when
        skillService.save(skill);

        Integer findSeqByName = skillService.findByName("test!!");
        String findNameBySeq = skillService.findBySeq(findSeqByName);

        //then
        assertThat(findNameBySeq).isEqualTo(skill.getName());
    }
}
