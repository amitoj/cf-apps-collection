package com.hanspal.cfapp5r.web;

import com.hanspal.cfapp5r.service.TokenBeautifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private TokenBeautifier tokenBeautifier;

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/secured/userinfo")
    public String authCode(Model model, OAuth2AuthenticationToken authentication) {
        OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
        OAuth2AuthorizedClient authorizedClient = getAuthorizedClient(authentication);

        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        model.addAttribute("raw_access_token", accessToken);
        model.addAttribute("formatted_access_token", tokenBeautifier.formatJwtToken(accessToken));

        String idToken = oidcUser.getIdToken().getTokenValue();
        model.addAttribute("raw_id_token", idToken);
        model.addAttribute("formatted_id_token", tokenBeautifier.formatJwtToken(idToken));

        model.addAttribute("userAttributes", oidcUser.getClaims());
        return "userinfo";
    }


    private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
        return this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(),
                authentication.getName());
    }
}
