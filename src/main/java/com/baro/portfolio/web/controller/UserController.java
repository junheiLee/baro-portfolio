package com.baro.portfolio.web.controller;

import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.argumentresolver.Current;
import com.baro.portfolio.web.dto.EditUserDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.result.AccountInfo;
import com.baro.portfolio.web.dto.result.UserInfo;
import com.baro.portfolio.web.validation.UserValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    //todo: 로그인 계정과 일치하는 seq만 수정 가능
    @GetMapping("/{userSeq}/edit")
    public String portFolio(Model model, @PathVariable int userSeq) {
        EditUserDto dto = userService.findEditUserBySeq(userSeq);
        model.addAttribute("editUserDto", dto);

        return "users/editForm";
    }

    @PostMapping("/{userSeq}/edit")
    public String edit(@Valid @ModelAttribute("editUserDto") EditUserDto dto,
                       BindingResult result, @PathVariable int userSeq,
                       @Current AccountInfo accountInfo) {

        log.info("editUserDto={}", dto.toString());

        if (result.hasErrors()) {
            return "users/editForm";
        }

        userService.updateBySeq(userSeq, dto);
        return "redirect:/users/" + userSeq;
    }
}
