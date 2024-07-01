package com.dev_cbj.community.member.controller.api;

import com.dev_cbj.community.common.response.ResponseDataDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class MembershipApiController {

    @PostMapping("/api/login")
    public ResponseEntity<ResponseDataDTO<?>> login() {
        return null;
    }

    @PostMapping("/api/join")
    public ResponseEntity<ResponseDataDTO<?>> join() {
        return null;
    }

    @PostMapping("/api/findId")
    public ResponseEntity<ResponseDataDTO<?>> findId() {
        return null;
    }

    @PostMapping("/api/findPw")
    public ResponseEntity<ResponseDataDTO<?>> findPw() {
        return null;
    }
}
