package com.baro.portfolio.web.controller;

import com.baro.portfolio.domain.Account;
import com.baro.portfolio.web.argumentresolver.Current;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static com.baro.portfolio.constant.ModelConst.ACCOUNT;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Current Account account, Model model) {

        if (account == null) {
            return "home";
        }
        model.addAttribute(ACCOUNT, account);
        return "forward:/users/" + account.getSeq();
    }

}
