package com.dat.model;

public class CanBo {
    protected int id;
    protected String ten;
    protected int tuoi;
    protected String gioiTinh;
    protected String diaChi;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public void showInformation() {
        System.out.format("%-4d| %-30s| %-5d| %-10s| %-40s| ",id,ten , tuoi, gioiTinh, diaChi);
    }

    public CanBo convertToCanBo() {
        CanBo canBo = new  CanBo();
        canBo.setTen(ten);
        canBo.setTuoi(tuoi);
        canBo.setGioiTinh(gioiTinh);
        canBo.setDiaChi(diaChi);
        return canBo;
    }
}