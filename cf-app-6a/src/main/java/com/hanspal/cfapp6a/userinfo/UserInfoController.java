package com.hanspal.cfapp6a.userinfo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private AtomicInteger counter = new AtomicInteger();

    @GetMapping
    public UserInfo getUserInfo() {
        return new UserInfo(counter.incrementAndGet(), "Amitoj", "Hanspal");
    }

}
