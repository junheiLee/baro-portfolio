package com.baro.portfolio.web.controller;

import com.baro.portfolio.web.argumentresolver.Current;
import com.baro.portfolio.web.dto.result.AccountInfo;
import lombok.experimental.Accessors;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(@Current AccountInfo accountInfo, Model model) {

        if (accountInfo == null) {
            return "home";
        }

        model.addAttribute("account", accountInfo);
        return "forward:/users/" + accountInfo.getSeq();
    }

}
