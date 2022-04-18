package com.dat.controller;

import java.util.List;

import com.dat.console.Request;
import com.dat.console.Response;
import com.dat.dao.CanBoDAO;
import com.dat.model.CanBo;
import com.dat.model.CongNhan;
import com.dat.model.KySu;
import com.dat.model.NhanVien;

public class HomeController implements Controller {
    private static HomeController homeController;

    private HomeController() {
    }

    @Override
    public Response doGet(Request req) {
        String canboTableName = req.getParameter("can_bo_table_name");
        Response resp = new Response();
        switch (canboTableName) {
            case "can_bo_list":
                List<CanBo> canBoList = CanBoDAO.getCanBoList();
                resp.setAttribute("can_bo_list", canBoList);
                break;
            case "cong_nhan_list":
                List<CongNhan> congNhanList = CanBoDAO.getCongNhanList();
                resp.setAttribute("cong_nhan_list", congNhanList);
                break;
            case "nhan_vien_list":
                List<NhanVien> nhanVienList = CanBoDAO.getNhanVienList();
                resp.setAttribute("nhan_vien_list", nhanVienList);
                break;
            case "ky_su_list":
                List<KySu> kySuList = CanBoDAO.getKySuList();
                resp.setAttribute("ky_su_list", kySuList);
                break;
        }
        return resp;
    }

    @Override
    public Response doPost(Request req) {
        String canBoType = req.getParameter("can_bo_type");
        Response resp = new Response();
        switch (canBoType) {
            case "ky_su":
                KySu kySu = new KySu();
                setBasicInformations(req, kySu);
                String nghanhDaoTao = req.getParameter("nghanh_dao_tao");
                kySu.setNghanhDaoTao(nghanhDaoTao);
                CanBoDAO.addKySu(kySu);
                break;
            case "nhan_vien":
                NhanVien nhanVien = new NhanVien();
                setBasicInformations(req, nhanVien);
                String congViec = req.getParameter("cong_viec");
                nhanVien.setCongViec(congViec);
                CanBoDAO.addNhanVien(nhanVien);
                break;
            case "cong_nhan":
                CongNhan congNhan = new CongNhan();
                setBasicInformations(req, congNhan);
                int bac = Integer.parseInt(req.getParameter("bac"));
                congNhan.setBac(bac);
                CanBoDAO.addCongNhan(congNhan);
                break;
        }
        return resp;
    }

    @Override
    public Response doPut(Request req) {
        Response resp = new Response();
        String canBoType = req.getParameter("can_bo_type");
        int selectedCanBoId = Integer.parseInt(req.getParameter("selected_can_bo_id"));
        switch (canBoType) {
            case "ky_su":
                KySu kySu = CanBoDAO.getKySu(selectedCanBoId);
                setBasicInformations(req, kySu);
                String tmp = req.getParameter("nghanh_dao_tao");
                String nghanhDaoTao = tmp.equals("") ? kySu.getNghanhDaoTao() : tmp;
                kySu.setNghanhDaoTao(nghanhDaoTao);
                CanBoDAO.updateKySu(kySu);
                break;
            case "nhan_vien":
                break;
            case "cong_nhan":
                break;
        }
        resp.setAttribute("ky_su_list", CanBoDAO.getKySuList());
        return resp;
    }

    private void setBasicInformations(Request req, CanBo canBo) {
        String tmp = "";
        tmp = req.getParameter("ten");
        String ten = tmp.equals("") ? canBo.getTen() : tmp;

        tmp = req.getParameter("tuoi");
        int tuoi = tmp.equals("") ? canBo.getTuoi() : Integer.parseInt(tmp);

        tmp = req.getParameter("gioi_tinh");
        String gioiTinh = tmp.equals("") ? canBo.getGioiTinh() : tmp;

        tmp = req.getParameter("dia_chi");
        String diaChi = tmp.equals("") ? canBo.getDiaChi() : tmp;

        canBo.setTen(ten);
        canBo.setTuoi(tuoi);
        canBo.setGioiTinh(gioiTinh);
        canBo.setDiaChi(diaChi);
    }

    public static HomeController getInstance() {
        if (homeController == null) {
            homeController = new HomeController();
        }
        return homeController;
    }
}
