package com.portfolio.controller;

import com.portfolio.ResponseDTO.APIResponse;
import com.portfolio.entity.User;
import com.portfolio.service.PortfolioService;
import com.portfolio.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")

public class PortfolioController {

    PortfolioService portfolioService;
    UserService userService;

    @PostMapping("/user")
    public ResponseEntity<APIResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createNewUser(user);

        APIResponse<User> response = APIResponse.<User>builder()
                .status("success")
                .message("User created successfully")
                .data(createdUser)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/user")
    public ResponseEntity<APIResponse<User>> updateUser(@RequestBody User user) throws Exception {
        User userResponse = userService.updateUserByEmail(user);
        APIResponse<User> response = APIResponse.<User>builder().data(userResponse).status("success").build();
        return ResponseEntity.ok(response);
    }
}
