package com.dat.model;

public class NhanVien extends CanBo {
    private String congViec;

    public String getCongViec() {
        return congViec;
    }

    public void setCongViec(String congViec) {
        this.congViec = congViec;
    }

    @Override
    public void showInformation() {
        super.showInformation();
        System.out.format("%-22s", congViec);
    }
}
