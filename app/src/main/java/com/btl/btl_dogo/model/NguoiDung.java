package com.btl.btl_dogo.model;

public class NguoiDung {
    private String maND,tenND,email,matKhau,soDienThoai,diaChi;

    public String getMaND() {
        return maND;
    }

    public NguoiDung(String maND, String tenND, String email, String matKhau, String soDienThoai, String diaChi) {
        this.maND = maND;
        this.tenND = tenND;
        this.email = email;
        this.matKhau = matKhau;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    public NguoiDung() {
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "maND='" + maND + '\'' +
                ", tenND='" + tenND + '\'' +
                ", email='" + email + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", soDienThoai='" + soDienThoai + '\'' +
                ", diaChi='" + diaChi + '\'' +
                '}';
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }

    public String getTenND() {
        return tenND;
    }

    public void setTenND(String tenND) {
        this.tenND = tenND;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
