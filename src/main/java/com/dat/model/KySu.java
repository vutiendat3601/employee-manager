package com.dat.model;

public class KySu extends CanBo {
    private String nghanhDaoTao;

    public String getNghanhDaoTao() {
        return nghanhDaoTao;
    }

    public void setNghanhDaoTao(String nghanhDaoTao) {
        this.nghanhDaoTao = nghanhDaoTao;
    }

    @Override
    public void showInformation() {
        super.showInformation();
        System.out.format("%-22s", "Ky su " + nghanhDaoTao);
    }
}
