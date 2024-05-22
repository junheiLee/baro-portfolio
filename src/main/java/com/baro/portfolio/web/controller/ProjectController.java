package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.exception.AuthorityException;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.web.argumentresolver.Current;
import com.baro.portfolio.web.dto.CreateProjectDto;
import com.baro.portfolio.web.dto.EditProjectDto;
import com.baro.portfolio.web.dto.ProjectDateDto;
import com.baro.portfolio.web.dto.result.ProjectInfo;
import com.baro.portfolio.web.dto.result.UserInfo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping
    public String projects(Model model) {

        List<ProjectInfo> projects = projectService.projects(null, true);
        model.addAttribute("projects", projects);

        return "projects/list";
    }

    @GetMapping("/my")
    public String myProjects(@Current Account account, Model model) {

        List<ProjectInfo> projects = projectService.projects(account.getSeq(), null);
        model.addAttribute("projects", projects);

        return "projects/list";
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

    @GetMapping("/{projectSeq}")
    public String readProject(@Current Account account, Model model, @PathVariable int projectSeq) {

        ProjectInfo projectInfo = projectService.read(projectSeq);

        if (isInaccessible(account.getSeq(), projectInfo)) {
            throw new AuthorityException("private 프로젝트입니다.");
        }
        model.addAttribute("project", projectInfo);
        return "projects/detail";
    }

    private static boolean isInaccessible(int accountSeq, ProjectInfo projectInfo) {
        return !projectInfo.isPublic() && !projectInfo.getContributorsSeq().contains(accountSeq);
    }

    @GetMapping("/{projectSeq}/edit")
    public String editForm(@Current Account account,
                           Model model, @PathVariable int projectSeq) {

        if (isStranger(account, projectSeq)) {
            throw new AuthorityException("본인이 참여한 프로젝트만 수정할 수 있습니다.");
        }

        EditProjectDto dto = projectService.read(account.getSeq(), projectSeq);
        model.addAttribute("editProjectDto", dto);

        return "projects/editForm";
    }

    @PostMapping("/{projectSeq}/edit")
    public String edit(@Current Account account,
                       @Valid @ModelAttribute EditProjectDto editProjectDto,
                       BindingResult result, @PathVariable int projectSeq) {

        if (isStranger(account, projectSeq)) {
            throw new AuthorityException("본인이 참여한 프로젝트만 수정할 수 있습니다.");
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

    @PostMapping("/{projectSeq}/delete")
    public String delete(@Current Account account,
                         @PathVariable int projectSeq) {

        if (isStranger(account, projectSeq)) {
            throw new AuthorityException("본인이 참여한 프로젝트만 삭제할 수 있습니다.");
        }

        projectService.delete(account.getSeq(), projectSeq);
        return "redirect:/";
    }

    private boolean isStranger(Account account, int projectSeq) {

        return !projectService.findContributors(projectSeq)
                .stream().map(UserInfo::getSeq).toList()
                .contains(account.getSeq());
    }


}
