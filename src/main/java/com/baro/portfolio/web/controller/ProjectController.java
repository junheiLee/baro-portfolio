package com.baro.portfolio.web.controller;

import com.baro.portfolio.web.dto.ProjectCreateRequestDto;
import com.baro.portfolio.service.itf.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/projects")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void createProject(@RequestBody ProjectCreateRequestDto dto){
        projectService.save(dto);
    }
}
