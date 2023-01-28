package com.blog.controller;

import com.blog.domain.User;
import com.blog.domain.UserRole;
import com.blog.dto.ResponseDto;
import com.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.http.HttpSession;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
    private final UserService userService;

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        log.info("UserApuController : save 회원가입 호출됨");
        user.setRole(UserRole.ROLE_USER);
        log.info(String.valueOf(user));
        userService.회원가입(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


/**
 * 전통적인 방식
 */
//    @PostMapping("/api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//        log.info("UserApuController : login 호출됨");
//        User principalUser = userService.로그인(user);
//
//        if (principalUser != null) {
//            session.setAttribute("principal", principalUser);
//        }
//
//        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//    }
}
