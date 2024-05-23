package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.exception.AuthorityException;
import com.baro.portfolio.service.itf.ProjectService;
import com.baro.portfolio.service.itf.UserService;
import com.baro.portfolio.web.argumentresolver.Current;
import com.baro.portfolio.web.dto.EditUserDto;
import com.baro.portfolio.web.dto.SignUpDto;
import com.baro.portfolio.web.dto.result.PortfolioProjectInfo;
import com.baro.portfolio.web.dto.result.UserInfo;
import com.baro.portfolio.web.validation.UserDuplicatedFieldValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.baro.portfolio.constant.ErrorEnum.STRANGER_EDIT_PROJECT;
import static com.baro.portfolio.constant.ModelConst.*;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDuplicatedFieldValidator userDuplicatedFieldValidator;
    private final UserService userService;
    private final ProjectService projectService;

    @InitBinder({SIGN_UP_DTO, EDIT_USER_DTO})
    public void init(WebDataBinder dataBinder) {
        dataBinder.addValidators(userDuplicatedFieldValidator);
    }

    @GetMapping("/sign-up")
    public String singUpForm(@ModelAttribute(SIGN_UP_DTO) SignUpDto signUpDto) {

        return "users/signUpForm";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute(SIGN_UP_DTO) SignUpDto signUpDto, BindingResult result) {

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

        model.addAttribute(USER, userInfo);
        model.addAttribute(PROJECT_LIST, projectInfos);

        return "users/portFolio";
    }

    @GetMapping("/{userSeq}/edit")
    public String editForm(Model model, @PathVariable int userSeq, @Current Account account) {

        if (account.getSeq() != userSeq) {

            throw new AuthorityException(STRANGER_EDIT_PROJECT.getMessage());
        }
        EditUserDto dto = userService.findEditUserInfoBySeq(userSeq);
        model.addAttribute(EDIT_USER_DTO, dto);

        return "users/editForm";
    }

    @PostMapping("/{userSeq}/edit")
    public String edit(@Valid @ModelAttribute(EDIT_USER_DTO) EditUserDto editUserDto,
                       BindingResult result, @PathVariable int userSeq,
                       @Current Account account) {

        if (account.getSeq() != userSeq) {
            throw new AuthorityException(STRANGER_EDIT_PROJECT.getMessage());
        }

        if (result.hasErrors()) {
            return "users/editForm";
        }
        userService.updateBySeq(userSeq, editUserDto);

        return "redirect:/users/" + userSeq;
    }
}
