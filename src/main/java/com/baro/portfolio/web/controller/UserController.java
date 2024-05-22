package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.argumentresolver.Current;
import com.baro.portfolio.web.dto.EditUserDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.result.PortfolioProjectInfo;
import com.baro.portfolio.web.dto.result.UserInfo;
import com.baro.portfolio.web.validation.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserValidator userValidator;
    private final UserService userService;
    private final ProjectService projectService;

    @InitBinder({"signUpDto", "editUserDto"})
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(userValidator);
    }

    @GetMapping("/sign-up")
    public String singUpForm(@ModelAttribute("signUpDto") SignUpDto signUpDto) {

        return "users/signUpForm";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute("signUpDto") SignUpDto signUpDto, BindingResult result) {

        if (result.hasErrors()) {
            return "users/signUpForm";
        }
        userService.signUp(signUpDto);

        return "redirect:/";
    }

    @GetMapping("/{userSeq}")
    public String portFolio(@PathVariable int userSeq, Model model) {

        UserInfo userInfo = userService.findBySeq(userSeq);
        List<PortfolioProjectInfo> projectInfos = projectService.portfolioProjects(userSeq);

        model.addAttribute("user", userInfo);
        model.addAttribute("projects", projectInfos);

        return "users/portFolio";
    }

    @GetMapping("/{userSeq}/edit")
    public String editForm(Model model, @PathVariable int userSeq, @Current Account account) {

        if (account.getSeq() != userSeq) {
            throw new RuntimeException("임시");
        }

        EditUserDto dto = userService.findEditUserInfoBySeq(userSeq);
        model.addAttribute("editUserDto", dto);

        return "users/editForm";
    }

    @PostMapping("/{userSeq}/edit")
    public String edit(@Valid @ModelAttribute("editUserDto") EditUserDto editUserDto,
                       BindingResult result, @PathVariable int userSeq,
                       @Current Account account) {

        if (account.getSeq() != userSeq) {
            throw new RuntimeException("임시");
        }

        if (result.hasErrors()) {
            return "users/editForm";
        }
        userService.updateBySeq(userSeq, editUserDto);

        return "redirect:/users/" + userSeq;
    }
}
