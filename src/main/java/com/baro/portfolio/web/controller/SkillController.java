package com.baro.portfolio.web.controller;

import com.baro.portfolio.service.itf.SkillService;

public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

}
