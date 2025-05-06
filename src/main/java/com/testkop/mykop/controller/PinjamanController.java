package com.testkop.mykop.controller;

import com.testkop.mykop.model.Pinjaman;
import com.testkop.mykop.repository.PinjamanRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PinjamanController {
  private PinjamanRepository pinjamanRepository;

  public PinjamanController(PinjamanRepository pinjamanRepository) {
    this.pinjamanRepository = pinjamanRepository;
  }

  @GetMapping("/api/pinjaman")
  public List<Pinjaman> getPinjaman() {
    return pinjamanRepository.findAll();
  }

}
