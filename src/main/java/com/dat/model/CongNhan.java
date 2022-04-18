package com.dat.model;

public class CongNhan extends CanBo {
    private int bac;

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }

    @Override
    public void showInformation() {
        super.showInformation();
        System.out.format("%-22s", bac);
    }
}
