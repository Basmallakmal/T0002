package com.aladziviesoft.data.model;

public class ListTaawunModel {
    String Nama, JumlahUang, Tanggal, Status;

    public ListTaawunModel(String nama, String jumlahUang, String tanggal, String status) {
        Nama = nama;
        JumlahUang = jumlahUang;
        Tanggal = tanggal;
        Status = status;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getJumlahUang() {
        return JumlahUang;
    }

    public void setJumlahUang(String jumlahUang) {
        JumlahUang = jumlahUang;
    }

    public String getTanggal() {
        return Tanggal;
    }

    public void setTanggal(String tanggal) {
        Tanggal = tanggal;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
