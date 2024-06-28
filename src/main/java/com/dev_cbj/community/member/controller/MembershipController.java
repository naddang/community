package com.dev_cbj.community.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MembershipController {

    @GetMapping("/login")
    public String login() {
        return "membership/login";
    }

    @GetMapping("/join")
    public String join() {
        return "membership/join";
    }

    @GetMapping("/findId")
    public String findId() {
        return "membership/findId";
    }

    @GetMapping("/findPw")
    public String findPw() {
        return "membership/findPw";
    }
}
