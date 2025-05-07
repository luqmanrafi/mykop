package com.testkop.mykop;

import com.testkop.mykop.dto.LoginRequest;
import com.testkop.mykop.dto.RegisterRequest;
import com.testkop.mykop.model.User;
import com.testkop.mykop.repository.UserRepository;
import com.testkop.mykop.service.AuthService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
  private AuthService authService;
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    userRepository = Mockito.mock(UserRepository.class);
    authService = new AuthService(userRepository);
  }

  @Test
  void register_sukses(){
    RegisterRequest request = new RegisterRequest();
    request.setNama("luqman");
    request.setEmail("luqman@gmail.com");
    request.setPassword("123456");

    when(userRepository.findByEmail("luqman@gmail.com"))
        .thenReturn(Optional.empty());

    String result = authService.register(request);

    assertEquals("Pendaftaran berhasil", result);
    verify(userRepository, times(1)).save(any(User.class));
  }

  @Test
  void register_emailSudahDipakai(){
    RegisterRequest request = new RegisterRequest();
    request.setNama("luqman");
    request.setEmail("luqman@gmail.com");
    request.setPassword("123456");

    when(userRepository.findByEmail("luqman@gmail.com")).thenReturn(Optional.of(new User()));

    String result = authService.register(request);

    assertEquals("Email sudah terdaftar", result);
    verify(userRepository, times(0)).save(any(User.class));
  }

  @Test
  void login_sukses(){
    LoginRequest request = new LoginRequest();
    request.setEmail("luqman@gmail.com");
    request.setPassword("123456");

    User user = new User();
    user.setEmail("luqman@gmail.com");
    user.setPassword(new BCryptPasswordEncoder().encode("123456"));

    when(userRepository.findByEmail("luqman@gmail.com")).thenReturn(Optional.of(user));
    String result = authService.login(request);
    assertEquals("Login berhasil", result);
  }

  @Test
  void login_passwordSalah(){
    LoginRequest request = new LoginRequest();
    request.setEmail("luqman@gmail.com");
    request.setPassword("wrong");

    User user = new User();
    user.setEmail("luqman@gmail.com");
    user.setPassword(new BCryptPasswordEncoder().encode("correct"));
    when(userRepository.findByEmail("luqman@gmail.com")).thenReturn(Optional.of(user));

    String result = authService.login(request);
    assertEquals("Password salah", result);
  }

  @Test
  void login_emailTidakDitemukan(){
    LoginRequest request = new LoginRequest();
    request.setEmail("luqman@gmail.com");
    request.setPassword("123456");

    User user = new User();
    user.setEmail("luqman@gmail.com");
    user.setPassword(new BCryptPasswordEncoder().encode("123456"));
    when(userRepository.findByEmail("luqman@gmail.com")).thenReturn(Optional.empty());

    String result = authService.login(request);
    assertEquals("Email tidak ditemukan", result);
  }

}
