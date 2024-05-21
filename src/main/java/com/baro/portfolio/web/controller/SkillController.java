package com.baro.portfolio.web.controller;

import com.baro.portfolio.service.itf.SkillService;
import org.springframework.beans.factory.annotation.Autowired;

public class SkillController {

    private final SkillService skillService;

    @Autowired
    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

}
