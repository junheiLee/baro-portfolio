package com.baro.portfolio.web.controller;

import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.SignInDto;
import com.baro.portfolio.web.dto.result.AccountInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;


    @GetMapping("/sign-in")
    public String signInForm(@ModelAttribute("signInDto") SignInDto dto) {
        return "users/signInForm";
    }

    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute SignInDto dto,
                         BindingResult result, HttpServletRequest request) {

        if (result.hasErrors()) {
            return "users/signInForm";
        }

        Optional<AccountInfo> accountInfo = userService.signIn(dto);

        if (accountInfo.isEmpty()) {
            result.reject("loginFail", "아이디 혹은 비밀 번호가 일치하지 않습니다.");
            return "users/signInForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute("accountInfo", accountInfo.get());
        return "redirect:/";
    }
}
