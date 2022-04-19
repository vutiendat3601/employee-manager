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
            case "ky_su_list":
                List<KySu> kySuList = CanBoDAO.getKySuList();
                resp.setAttribute("ky_su_list", kySuList);
                break;
            case "nhan_vien_list":
                List<NhanVien> nhanVienList = CanBoDAO.getNhanVienList();
                resp.setAttribute("nhan_vien_list", nhanVienList);
                break;
            case "cong_nhan_list":
                List<CongNhan> congNhanList = CanBoDAO.getCongNhanList();
                resp.setAttribute("cong_nhan_list", congNhanList);
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
                resp.setAttribute("ky_su_list", CanBoDAO.getKySuList());
                break;
            case "nhan_vien":
                NhanVien nhanVien = new NhanVien();
                setBasicInformations(req, nhanVien);
                String congViec = req.getParameter("cong_viec");
                nhanVien.setCongViec(congViec);
                CanBoDAO.addNhanVien(nhanVien);
                resp.setAttribute("nhan_vien_list", CanBoDAO.getNhanVienList());
                break;
            case "cong_nhan":
                CongNhan congNhan = new CongNhan();
                setBasicInformations(req, congNhan);
                int bac = Integer.parseInt(req.getParameter("bac"));
                congNhan.setBac(bac);
                CanBoDAO.addCongNhan(congNhan);
                resp.setAttribute("cong_nhan_list", CanBoDAO.getCongNhanList());
                break;
        }
        return resp;
    }

    @Override
    public Response doPut(Request req) {
        Response resp = new Response();
        String canBoType = req.getParameter("can_bo_type");
        int selectedCanBoId = Integer.parseInt(req.getParameter("selected_can_bo_id"));
        String tmp = "";
        switch (canBoType) {
            case "ky_su":
                KySu kySu = CanBoDAO.getKySu(selectedCanBoId);
                setBasicInformations(req, kySu);
                tmp = req.getParameter("nghanh_dao_tao");
                String nghanhDaoTao = tmp.equals("") ? kySu.getNghanhDaoTao() : tmp;
                kySu.setNghanhDaoTao(nghanhDaoTao);
                CanBoDAO.updateKySu(kySu);
                resp.setAttribute("ky_su_list", CanBoDAO.getKySuList());
                break;
            case "nhan_vien":
                NhanVien nhanVien = CanBoDAO.getNhanVien(selectedCanBoId);
                setBasicInformations(req, nhanVien);
                tmp = req.getParameter("cong_viec");
                String congViec = tmp.equals("") ? nhanVien.getCongViec() : tmp;
                nhanVien.setCongViec(congViec);
                CanBoDAO.updateNhanVien(nhanVien);
                resp.setAttribute("nhan_vien_list", CanBoDAO.getNhanVienList());
                break;
            case "cong_nhan":
                CongNhan congNhan = CanBoDAO.getCongNhan(selectedCanBoId);
                setBasicInformations(req, congNhan);
                tmp = req.getParameter("bac");
                int bac = tmp.equals("") ? congNhan.getBac() : Integer.parseInt(tmp);
                congNhan.setBac(bac);
                CanBoDAO.updateCongNhan(congNhan);
                resp.setAttribute("cong_nhan_list", CanBoDAO.getCongNhanList());
                break;
        }
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

    @Override
    public Response doDelete(Request req) {
        int canBoId = Integer.parseInt(req.getParameter("selected_can_bo_id"));
        String canBoType = req.getParameter("can_bo_type");
        Response resp = new Response();
        switch (canBoType) {
            case "ky_su":
                CanBoDAO.deleteKySu(canBoId);
                List<KySu> kySuList = CanBoDAO.getKySuList();
                resp.setAttribute("ky_su_list", kySuList);
                break;
            case "nhan_vien":
                CanBoDAO.deleteNhanVien(canBoId);
                List<NhanVien> nhanVienList = CanBoDAO.getNhanVienList();
                resp.setAttribute("nhan_vien_list", nhanVienList);
                break;
            case "cong_nhan":
                CanBoDAO.deleteCongNhan(canBoId);
                List<CongNhan> congNhanList = CanBoDAO.getCongNhanList();
                resp.setAttribute("cong_nhan_list", congNhanList);
                break;
        }
        return resp;
    }

    public static HomeController getInstance() {
        if (homeController == null) {
            homeController = new HomeController();
        }
        return homeController;
    }

}
