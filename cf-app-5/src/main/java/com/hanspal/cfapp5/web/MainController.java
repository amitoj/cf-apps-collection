package com.hanspal.cfapp5.web;

import com.hanspal.cfapp5.service.TokenBeautifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URL;


//@RestController
@Controller
public class MainController {

    @Value("${ssoServiceUrl: placeholder}")
    private String ssoServiceUrl;

    @Value("${security.oauth2.client.clientId: client1}")
    private String clientId;

    @Autowired
    private TokenBeautifier tokenBeautifier;

    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("ssoServiceUrl", ssoServiceUrl);
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @GetMapping("/secured/show_token")
    public String authCode(Model model, HttpServletRequest request) {
        OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        log.info("auth = {}", auth);

        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
        log.info("details = {}", details);

        String tokenValue = details.getTokenValue();
        log.info("tokenValue = {}", tokenValue);

        if (tokenValue != null) {
            model.addAttribute("access_token", tokenBeautifier.formatJwtToken(tokenValue));
        }
        return "show_token";
    }

    @GetMapping(value = "/userlogout")
    public String logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        URL url = new URL(request.getRequestURL().toString());
        String urlStr = url.getProtocol() + "://" + url.getAuthority();
        return "redirect:" + ssoServiceUrl + "/logout.do?redirect=" + urlStr + "&clientId=" + clientId;
    }

}
