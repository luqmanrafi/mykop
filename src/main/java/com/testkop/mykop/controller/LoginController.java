package com.testkop.mykop.controller;

import com.testkop.mykop.dto.LoginRequest;
import com.testkop.mykop.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
  private final AuthService authService;

  public LoginController(AuthService authService) {
    this.authService = authService;
  }
  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody LoginRequest request){
    String response = authService.login(request);
    if(response.equals("Login berhasil")){
      return ResponseEntity.ok(response);
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
  }
}
