package com.testkop.mykop.service;

import com.testkop.mykop.dto.LoginRequest;
import com.testkop.mykop.dto.RegisterRequest;
import com.testkop.mykop.model.User;
import com.testkop.mykop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AuthService {

  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder;

  public AuthService(UserRepository userRepository) {
    this.userRepository = userRepository;
    this.passwordEncoder = new BCryptPasswordEncoder();
  }

  public String register(RegisterRequest registerRequest) {
    if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
      return "Email sudah terdaftar";
    }

    User user = new User();
    user.setNama(registerRequest.getNama());
    user.setEmail(registerRequest.getEmail());
    user.setPassword(registerRequest.getPassword());
    userRepository.save(user);

    return "Pendaftaran berhasil";
  }
  public String login(LoginRequest request) {
    Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

    if (userOpt.isEmpty()) {
      return "Email tidak ditemukan";
    }

    User user = userOpt.get();
    if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
      return "Password salah";
    }
    return "Login berhasil";
  }
}
