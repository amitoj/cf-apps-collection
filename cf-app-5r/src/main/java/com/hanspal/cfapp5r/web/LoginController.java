package com.hanspal.cfapp5r.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @RequestMapping("/custom_login")
    public String loginPage() {
        return "redirect:/oauth2/authorization/uaa";
    }

}
