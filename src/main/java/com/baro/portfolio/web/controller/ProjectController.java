package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Project;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.web.dto.ProjectRequestDto;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/projects")
@Controller
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/add")
    public String createForm(@ModelAttribute("projectRequestDto") ProjectRequestDto projectRequestDto) {
        return "projects/createForm";
    }

    @PostMapping("add")
    public String createProject(@Valid @ModelAttribute("projectRequestDto") ProjectRequestDto projectRequestDto,
                                BindingResult result) {

        if (projectRequestDto.getStart() != null && projectRequestDto.getEnd() != null && projectRequestDto.getStart().after(projectRequestDto.getEnd())) {
            result.reject("canNotExistDateSet", "시작 날짜는 종료 날짜보다 과거여야 합니다.");
        }

        if (result.hasErrors()) {
            return "projects/createForm";
        }

        int createdSeq = projectService.save(projectRequestDto);

        return "redirect:/projects/" + createdSeq;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{projectSeq}")
    public String readProject(@PathVariable int projectSeq) {

        //TODO
        // - 로그인한 사용자 확인, 비공개 포트폴리오면 contributor만 열람 가능
        log.info("readProject 호출");
        Project project = projectService.read(projectSeq);
        return "projects/detail";
    }
}
