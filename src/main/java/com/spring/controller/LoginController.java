package com.spring.controller;

import com.spring.model.User;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.attribute.UserPrincipalNotFoundException;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;

    /*@PostMapping("/user")
    public String login(@RequestParam("email")String email){
        User user = userService.findUserByEmail(email);
        if(user == null){
            return "login failed";
        }
        return "Login successfully";
    }*/
}
