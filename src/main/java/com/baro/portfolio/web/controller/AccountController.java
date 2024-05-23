package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.dto.SignInDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.baro.portfolio.constant.ErrorEnum.LOGIN_FAIL;
import static com.baro.portfolio.constant.ModelConst.ACCOUNT;
import static com.baro.portfolio.constant.ModelConst.SIGN_IN_DTO;


/**
 * 로그인 로그아웃 관련 처리 컨트롤러
 */
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final UserService userService;

    @GetMapping("/sign-in")
    public String signInForm(@ModelAttribute SignInDto signInDto) {

        return "users/signInForm";
    }

    @PostMapping("/sign-in")
    public String signIn(@Valid @ModelAttribute SignInDto dto, BindingResult result,
                         @RequestParam(defaultValue = "/") String redirectUrl,
                         HttpServletRequest request) {

        if (result.hasErrors()) {
            return "users/signInForm";
        }

        Optional<Account> account = userService.findByEmailAndPassword(dto);

        if (account.isEmpty()) {
            result.reject(LOGIN_FAIL.getCode(), LOGIN_FAIL.getMessage());
            return "users/signInForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(ACCOUNT, account.get());

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/sign-out")
    public String signOut(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        return "redirect:/";
    }
}
