package com.testkop.mykop.controller;

import com.testkop.mykop.dto.RegisterRequest;
import com.testkop.mykop.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/register")
  public String register(@RequestBody RegisterRequest registerRequest) {
    return authService.register(registerRequest);
  }
}
