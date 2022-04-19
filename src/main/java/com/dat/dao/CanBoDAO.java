package com.dat.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.dat.model.CanBo;
import com.dat.model.CongNhan;
import com.dat.model.KySu;
import com.dat.model.NhanVien;
import com.dat.util.DBConnection;

public class CanBoDAO {
    private static Connection conn;

    // READ
    public static boolean getBasicInformation(int canBoId, CanBo canBo) {
        boolean result = false;
        String sqlGetBasicInformation = "SELECT * FROM can_bo WHERE id = ?";
        Connection conn = getDBConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sqlGetBasicInformation);
            ps.setInt(1, canBoId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int tuoi = rs.getInt("tuoi");
                String gioiTinh = rs.getString("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                canBo.setId(id);
                canBo.setTen(ten);
                canBo.setTuoi(tuoi);
                canBo.setGioiTinh(gioiTinh);
                canBo.setDiaChi(diaChi);
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static KySu getKySu(int canBoId) {
        Connection conn = getDBConnection();
        KySu kySu = new KySu();
        boolean result = getBasicInformation(canBoId, kySu);
        if (!result) {
            return null;
        }
        try {
            String sqlGetKySu = "SELECT * FROM ky_su WHERE can_bo_id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlGetKySu);
            ps.setInt(1, canBoId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String nghanhDaoTao = rs.getString("nghanh_dao_tao");
            kySu.setNghanhDaoTao(nghanhDaoTao);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kySu;
    }

    public static NhanVien getNhanVien(int canBoId) {
        Connection conn = getDBConnection();
        NhanVien nhanVien = new NhanVien();
        boolean result = getBasicInformation(canBoId, nhanVien);
        if (!result) {
            return null;
        }
        try {
            String sqlGetNhanVien = "SELECT * FROM nhan_vien WHERE can_bo_id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlGetNhanVien);
            ps.setInt(1, canBoId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String congViec = rs.getString("cong_viec");
            nhanVien.setCongViec(congViec);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVien;
    }

    public static CongNhan getCongNhan(int canBoId) {
        Connection conn = getDBConnection();
        CongNhan congNhan = new CongNhan();
        boolean result = getBasicInformation(canBoId, congNhan);
        if (!result) {
            return null;
        }
        try {
            String sqlGetCongNhan = "SELECT * FROM cong_nhan WHERE can_bo_id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlGetCongNhan);
            ps.setInt(1, canBoId);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int bac = rs.getInt("bac");
            congNhan.setBac(bac);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return congNhan;
    }

    public static List<CanBo> getCanBoList() {
        List<CanBo> canBoList = new ArrayList<>();
        Connection conn = getDBConnection();
        String sqlGetCanBo = "SELECT * FROM can_bo";
        try (PreparedStatement ps = conn.prepareStatement(sqlGetCanBo)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CanBo canBo = new CanBo();
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int tuoi = rs.getInt("tuoi");
                String gioiTinh = rs.getString("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                canBo.setId(id);
                canBo.setTen(ten);
                canBo.setTuoi(tuoi);
                canBo.setGioiTinh(gioiTinh);
                canBo.setDiaChi(diaChi);
                canBoList.add(canBo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canBoList;
    }

    public static List<KySu> getKySuList() {
        Connection conn = getDBConnection();
        List<KySu> kySuList = new ArrayList<>();
        String sqlGetKySuList = "SELECT cb.id id, cb.ten ten, cb.tuoi tuoi, cb.gioi_tinh gioi_tinh, cb.dia_chi dia_chi, "
                + "ks.nghanh_dao_tao nghanh_dao_tao FROM can_bo cb INNER JOIN ky_su ks " +
                "ON cb.id = ks.can_bo_id";
        try (PreparedStatement ps = conn.prepareStatement(sqlGetKySuList)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                KySu kySu = new KySu();
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int tuoi = rs.getInt("tuoi");
                String gioiTinh = rs.getString("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                String nghanhDaoTao = rs.getString("nghanh_dao_tao");
                kySu.setId(id);
                kySu.setTen(ten);
                kySu.setTuoi(tuoi);
                kySu.setGioiTinh(gioiTinh);
                kySu.setDiaChi(diaChi);
                kySu.setNghanhDaoTao(nghanhDaoTao);
                kySuList.add(kySu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kySuList;
    }

    public static List<NhanVien> getNhanVienList() {
        Connection conn = getDBConnection();
        List<NhanVien> nhanVienList = new ArrayList<>();
        String sqlGetNhanVienList = "SELECT cb.id id, cb.ten ten, cb.tuoi tuoi, cb.gioi_tinh gioi_tinh, cb.dia_chi dia_chi, "
                + "nv.cong_viec cong_viec FROM can_bo cb INNER JOIN nhan_vien nv "
                + "ON cb.id = nv.can_bo_id";
        try (PreparedStatement ps = conn.prepareStatement(sqlGetNhanVienList)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int tuoi = rs.getInt("tuoi");
                String gioiTinh = rs.getString("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                String congViec = rs.getString("cong_viec");
                nhanVien.setId(id);
                nhanVien.setTen(ten);
                nhanVien.setTuoi(tuoi);
                nhanVien.setGioiTinh(gioiTinh);
                nhanVien.setDiaChi(diaChi);
                nhanVien.setCongViec(congViec);
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return nhanVienList;
    }

    public static List<CongNhan> getCongNhanList() {
        Connection conn = getDBConnection();
        List<CongNhan> congNhanList = new ArrayList<>();
        String sqlGetCongNhan = "SELECT cb.id id, cb.ten ten, cb.tuoi tuoi, cb.gioi_tinh gioi_tinh, cb.dia_chi dia_chi, "
                + "cn.bac bac FROM can_bo cb INNER JOIN cong_nhan cn "
                + "ON cb.id = cn.can_bo_id";
        try (PreparedStatement ps = conn.prepareStatement(sqlGetCongNhan)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CongNhan congNhan = new CongNhan();
                int id = rs.getInt("id");
                String ten = rs.getString("ten");
                int tuoi = rs.getInt("tuoi");
                String gioiTinh = rs.getString("gioi_tinh");
                String diaChi = rs.getString("dia_chi");
                int bac = rs.getInt("bac");
                congNhan.setId(id);
                congNhan.setTen(ten);
                congNhan.setTuoi(tuoi);
                congNhan.setGioiTinh(gioiTinh);
                congNhan.setDiaChi(diaChi);
                congNhan.setBac(bac);
                congNhanList.add(congNhan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return congNhanList;
    }

    // CREATE
    public static int addCanBo(CanBo canBo) {
        int canBoId = -1;
        Connection conn = getDBConnection();
        String sqlAddCanBo = "INSERT INTO can_bo(ten,tuoi,gioi_tinh,dia_chi) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlAddCanBo, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, canBo.getTen());
            ps.setInt(2, canBo.getTuoi());
            ps.setString(3, canBo.getGioiTinh());
            ps.setString(4, canBo.getDiaChi());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                canBoId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return canBoId;
    }

    public static void addKySu(KySu kySu) {
        int canBoId = addCanBo(kySu);
        Connection conn = getDBConnection();
        String sqlAddKySu = "INSERT INTO ky_su VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlAddKySu);
            ps.setInt(1, canBoId);
            ps.setString(2, kySu.getNghanhDaoTao());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addNhanVien(NhanVien nhanVien) {
        int canBoId = addCanBo(nhanVien);
        Connection conn = getDBConnection();
        String sqlAddKySu = "INSERT INTO nhan_vien VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlAddKySu);
            ps.setInt(1, canBoId);
            ps.setString(2, nhanVien.getCongViec());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addCongNhan(CongNhan congNhan) {
        int canBoId = addCanBo(congNhan);
        Connection conn = getDBConnection();
        String sqlAddKySu = "INSERT INTO cong_nhan VALUES(?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlAddKySu);
            ps.setInt(1, canBoId);
            ps.setInt(2, congNhan.getBac());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public static void updateCanBo(CanBo canBo) {
        Connection conn = getDBConnection();
        String sqlUpdateCanBo = "UPDATE can_bo SET ten = ?, tuoi = ?, gioi_tinh = ?, dia_chi = ? WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdateCanBo);
            ps.setString(1, canBo.getTen());
            ps.setInt(2, canBo.getTuoi());
            ps.setString(3, canBo.getGioiTinh());
            ps.setString(4, canBo.getDiaChi());
            ps.setInt(5, canBo.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateKySu(KySu kySu) {
        updateCanBo(kySu);
        Connection conn = getDBConnection();
        String sqlUpdateKySu = "UPDATE ky_su SET nghanh_dao_tao = ? WHERE can_bo_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdateKySu);
            ps.setString(1, kySu.getNghanhDaoTao());
            ps.setInt(2, kySu.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void updateNhanVien(NhanVien nhanVien) {
        updateCanBo(nhanVien);
        Connection conn = getDBConnection();
        String sqlUpdateNhanVien = "UPDATE nhan_vien SET cong_viec = ? WHERE can_bo_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdateNhanVien);
            ps.setString(1, nhanVien.getCongViec());
            ps.setInt(2, nhanVien.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCongNhan(CongNhan congNhan) {
        updateCanBo(congNhan);
        Connection conn = getDBConnection();
        String sqlUpdateNhanVien = "UPDATE cong_nhan SET bac = ? WHERE can_bo_id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sqlUpdateNhanVien);
            ps.setInt(1, congNhan.getBac());
            ps.setInt(2, congNhan.getId());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete 

    private static Connection getDBConnection() {
        if (conn == null) {
            conn = DBConnection.connect();
        }
        return conn;
    }
}
