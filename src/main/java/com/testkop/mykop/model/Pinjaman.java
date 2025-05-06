package com.testkop.mykop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pinjaman {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String nama;
  private Double jumlahPinjaman;
  private double bunga;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getNama() {
    return nama;
  }

  public void setNama(String nama) {
    this.nama = nama;
  }

  public Double getJumlahPinjaman() {
    return jumlahPinjaman;
  }

  public void setJumlahPinjaman(Double jumlahPinjaman) {
    this.jumlahPinjaman = jumlahPinjaman;
  }

  public double getBunga() {
    return bunga;
  }

  public void setBunga(double bunga) {
    this.bunga = bunga;
  }
}
