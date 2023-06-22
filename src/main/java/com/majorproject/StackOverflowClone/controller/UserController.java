package com.majorproject.StackOverflowClone.controller;

import com.majorproject.StackOverflowClone.model.User;
import com.majorproject.StackOverflowClone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/signup")
    public String addUser(@ModelAttribute User user) {
        userService.addUser(user);
        return "login";
    }

    @GetMapping("/users")
    public String users() {
        return "user";
    }

    @GetMapping("/oauth2/authorization/google")
    public String redirectToGoogleAuthorization() {
        return "redirect:/login/oauth2/code/google";
    }
}
