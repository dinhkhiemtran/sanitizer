package com.khiemtran.sanitizer.controller;

import com.khiemtran.sanitizer.model.request.UserRequest;
import com.khiemtran.sanitizer.model.response.UserResponse;
import com.khiemtran.sanitizer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/user")
  public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest) {
    UserRequest sanitizer = userRequest.sanitize(userRequest);
    UserResponse userResponse = userService.create(sanitizer);
    return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
  }
}
