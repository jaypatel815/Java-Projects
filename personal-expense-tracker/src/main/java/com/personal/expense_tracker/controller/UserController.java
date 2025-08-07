package com.personal.expense_tracker.controller;

import com.personal.expense_tracker.dto.RegisterRequest;
import com.personal.expense_tracker.entity.User;
import com.personal.expense_tracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody RegisterRequest request) {
        User registeredUser = userService.register(request);
        return ResponseEntity.ok(registeredUser);
    }

}
