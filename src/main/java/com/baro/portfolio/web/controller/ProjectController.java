package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.service.UserServiceImpl;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.web.argumentresolver.Current;
import com.baro.portfolio.web.dto.CreateProjectDto;
import com.baro.portfolio.web.dto.EditProjectDto;
import com.baro.portfolio.web.dto.ProjectDateDto;
import com.baro.portfolio.web.dto.result.ProjectInfo;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/projects")
@Controller
public class ProjectController {

    private final ProjectService projectService;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public ProjectController(ProjectService projectService, UserServiceImpl userServiceImpl) {
        this.projectService = projectService;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/add")
    public String createForm(@ModelAttribute CreateProjectDto createProjectDto) {
        return "projects/createForm";
    }

    @PostMapping("/add")
    public String createProject(@Valid @ModelAttribute("createProjectDto") CreateProjectDto createProjectDto,
                                BindingResult result, @Current Account account) {

        if (isImpossibleDateSet(createProjectDto)) {
            result.reject("canNotExistDateSet", "시작 날짜는 종료 날짜보다 과거여야 합니다.");
        }

        if (result.hasErrors()) {
            return "projects/createForm";
        }

        int createdSeq = projectService.save(account.getSeq(), createProjectDto);
        return "redirect:/projects/" + createdSeq;
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

    @GetMapping("/{projectSeq}/edit")
    public String editForm(@Current Account account,
                           Model model, @PathVariable int projectSeq) {
        ProjectInfo projectInfo = projectService.read(projectSeq);

        if (!projectInfo.getContributors().contains(account.getSeq())) {
            throw new RuntimeException("임시");
        }


        EditProjectDto dto = new EditProjectDto();
        String myPart = projectService.findMyPart(account.getSeq(), projectSeq);

        dto.setMyPart(myPart).fromProjectInfo(projectInfo);
        model.addAttribute("editProjectDto", dto);

        return "projects/editForm";
    }

    @PostMapping("/{projectSeq}/edit")
    public String edit(@Current Account account,
                       @Valid @ModelAttribute EditProjectDto editProjectDto,
                       BindingResult result, @PathVariable int projectSeq) {

        List<Integer> currentContributors = projectService.findContributors(projectSeq);

        if (!currentContributors.contains(account.getSeq())) {
            throw new RuntimeException("임시");
        }

        if (isImpossibleDateSet(editProjectDto)) {
            result.reject("canNotExistDateSet", "시작 날짜는 종료 날짜보다 과거여야 합니다.");
        }

        if (result.hasErrors()) {
            return "projects/editForm";
        }

        projectService.update(account.getSeq(), projectSeq, editProjectDto);
        return "redirect:/projects/" + projectSeq;
    }

    private static boolean isImpossibleDateSet(ProjectDateDto projectDateDto) {
        return projectDateDto.getStart() != null
                && projectDateDto.getEnd() != null
                && projectDateDto.getStart().after(projectDateDto.getEnd());
    }
}
