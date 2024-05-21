package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.web.argumentresolver.Current;
import com.baro.portfolio.web.dto.ProjectRequestDto;
import com.baro.portfolio.web.dto.result.ProjectInfo;
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
    public String createForm(@ModelAttribute ProjectRequestDto projectRequestDto) {
        return "projects/createForm";
    }

    @PostMapping("/add")
    public String createProject(@Valid @ModelAttribute ProjectRequestDto projectRequestDto,
                                BindingResult result, @Current Account account) {

        if (isImpossibleDateSet(projectRequestDto)) {
            result.reject("canNotExistDateSet", "시작 날짜는 종료 날짜보다 과거여야 합니다.");
        }

        if (result.hasErrors()) {
            return "projects/createForm";
        }

        int createdSeq = projectService.save(account.getSeq(), projectRequestDto);
        return "redirect:/projects/" + createdSeq;
    }

    private static boolean isImpossibleDateSet(ProjectRequestDto projectRequestDto) {
        return projectRequestDto.getStart() != null
                && projectRequestDto.getEnd() != null
                && projectRequestDto.getStart().after(projectRequestDto.getEnd());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{projectSeq}")
    public String readProject(@Current Account account, Model model, @PathVariable int projectSeq) {

        ProjectInfo projectInfo = projectService.read(projectSeq);

        if (isInaccessible(account.getSeq(), projectInfo)) {
            throw new RuntimeException("임시");
        }

        model.addAttribute("projectInfo", projectInfo);
        return "projects/detail";
    }

    private static boolean isInaccessible(int accountSeq, ProjectInfo projectInfo) {
        return !projectInfo.isPublic() && !projectInfo.getContributors().contains(accountSeq);
    }
}
