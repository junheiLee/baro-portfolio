package com.baro.portfolio.web.controller;

import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.ProjectsInfo;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.UserInfo;
import com.baro.portfolio.web.validation.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserValidator userValidator;
    private final UserService userService;

//    @InitBinder
//    public void init(WebDataBinder dataBinder) {
//        dataBinder.addValidators(userValidator);
//    }

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
        model.addAttribute("userInfo", userInfo);
//        ProjectsInfo projectsInfo =
        return "users/portFolio";
    }
}
