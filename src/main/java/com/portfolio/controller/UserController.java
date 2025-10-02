package com.portfolio.controller;

import com.portfolio.ResponseDTO.APIResponse;
import com.portfolio.entity.User;
import com.portfolio.service.PortfolioService;
import com.portfolio.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

  PortfolioService portfolioService;
  UserService userService;

  @PostMapping("/user")
  public ResponseEntity<APIResponse<String>> createUser(@RequestBody User user) {
    User createdUser = userService.createNewUser(user);

    APIResponse<String> response =
        APIResponse.<String>builder()
            .status("success")
            .message("User created successfully")
            .build();

    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PostMapping("/login")
  public ResponseEntity<APIResponse<String>> userLogin(@RequestBody User user) {
    boolean verifiedUser = userService.userLogin(user);
    if (verifiedUser) {
      APIResponse<String> authResponse =
          APIResponse.<String>builder().status("Success").message("Authenticated").build();
      ResponseEntity<APIResponse<String>> response = new ResponseEntity<>(HttpStatus.OK);

      return ResponseEntity.status(HttpStatus.OK).body(authResponse);
    }

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new APIResponse<>());
  }

  @PutMapping("/user")
  public ResponseEntity<APIResponse<User>> updateUser(@RequestBody User user) throws Exception {
    User userResponse = userService.updateUserByEmail(user);
    APIResponse<User> response =
        APIResponse.<User>builder().data(userResponse).status("success").build();
    return ResponseEntity.ok(response);
  }
}
