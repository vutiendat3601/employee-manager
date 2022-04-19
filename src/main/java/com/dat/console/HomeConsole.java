package com.dat.console;

import java.util.List;

import com.dat.App;
import com.dat.controller.HomeController;
import com.dat.model.CanBo;
import com.dat.model.CongNhan;
import com.dat.model.KySu;
import com.dat.model.NhanVien;
import com.dat.util.DBConnection;

public class HomeConsole {

    public static void printAxisX(char c, int times, boolean activeNewLine) {
        for (int i = 0; i < times; i++) {
            System.out.print(c);
        }
        if (activeNewLine) {
            System.out.println();
        }
    }

    public static void showMainMenu() {
        printAxisX('_', 50, true);
        System.out.format("|%-48s|\n", "1. Hien thi danh sach Can bo");
        System.out.format("|%-48s|\n", "2. Hien thi danh sach Ky su");
        System.out.format("|%-48s|\n", "3. Hien thi danh sach Nhan vien");
        System.out.format("|%-48s|\n", "4. Hien thi danh sach Cong nhan");
        System.out.format("|%-48s|\n", "5. Them moi Can bo");
        System.out.format("|%-48s|\n", "6. Update Can bo");
        System.out.format("|%-48s|\n", "7. Delete Can bo");
        System.out.format("|%-48s|\n", "8. Change Database");
        System.out.format("|%-48s|\n", "0. Thoat");
        System.out.print('|');
        printAxisX('_', 48, false);
        System.out.println('|');
        int choice = -1;
        do {
            System.out.print("choice = ");
            choice = Integer.parseInt(App.systemScanner.nextLine());
        } while (!(0 <= choice && choice <= 8));
        if (choice != 0) {
            processMainMenu(choice);
        }
    }

    public static void processMainMenu(int choice) {
        Request req = new Request();
        Response resp = null;
        List<CanBo> canBoList = null;
        switch (choice) {
            case 1:
                req.setMethod("get");
                req.addParameter("can_bo_table_name", "can_bo_list");
                resp = HomeController.getInstance().service(req);
                canBoList = (List<CanBo>) resp.getAttribute("can_bo_list");
                showCanBoList(canBoList);
                showMainMenu();
                break;
            case 2:
                req.setMethod("get");
                req.addParameter("can_bo_table_name", "ky_su_list");
                resp = HomeController.getInstance().service(req);
                List<KySu> kySuList = (List<KySu>) resp.getAttribute("ky_su_list");
                showKySuList(kySuList);
                showMainMenu();
                break;
            case 3:
                req.setMethod("get");
                req.addParameter("can_bo_table_name", "nhan_vien_list");
                resp = HomeController.getInstance().service(req);
                List<NhanVien> nhanVienList = (List<NhanVien>) resp.getAttribute("nhan_vien_list");
                showNhanVienList(nhanVienList);
                showMainMenu();
                break;
            case 4:
                req.setMethod("get");
                req.addParameter("can_bo_table_name", "cong_nhan_list");
                resp = HomeController.getInstance().service(req);
                List<CongNhan> congNhanList = (List<CongNhan>) resp.getAttribute("cong_nhan_list");
                showCongNhanList(congNhanList);
                showMainMenu();
                break;
            case 5:
                showAddCanBoMenu();
                break;
            case 6:
                showUpdateCanBoMenu();
                break;
            case 7:
                showDeleteCanBoMenu();
                break;
            case 8:
                printAxisX('_', 50, true);
                System.out.print("url = ");
                DBConnection.url = App.systemScanner.nextLine();
                System.out.print("user = ");
                DBConnection.user = App.systemScanner.nextLine();
                System.out.print("password = ");
                char[] passwordChars = App.systemConsole.readPassword();
                DBConnection.password = String.copyValueOf(passwordChars);
                showMainMenu();
                break;
        }
    }

    public static void showAddCanBoMenu() {
        printAxisX('_', 50, true);
        System.out.format("|%-48s|\n", "1. Them moi Ky su");
        System.out.format("|%-48s|\n", "2. Them moi Nhan vien");
        System.out.format("|%-48s|\n", "3. Them moi Cong nhan");
        System.out.format("|%-48s|\n", "0. Quay lai Main menu");
        System.out.print('|');
        printAxisX('_', 48, false);
        System.out.println('|');
        int choice = -1;
        do {
            System.out.print("choice = ");
            choice = Integer.parseInt(App.systemScanner.nextLine());
        } while (!(0 <= choice && choice <= 3));
        if (choice == 0) {
            showMainMenu();
        } else {
            processAddCanBoMenu(choice);
        }
    }

    public static void processAddCanBoMenu(int choice) {
        printAxisX('_', 50, true);
        Request req = new Request();
        req.setMethod("post");
        Response resp = null;
        switch (choice) {
            case 1:
                System.out.println("* Them Ky su");
                inputBasicInformation(req);
                System.out.print("Nghanh dao tao = ");
                String nghanhDaoTao = App.systemScanner.nextLine();
                req.addParameter("nghanh_dao_tao", nghanhDaoTao);
                req.addParameter("can_bo_type", "ky_su");
                resp = HomeController.getInstance().service(req);
                List<KySu> kySuList = (List<KySu>) resp.getAttribute("ky_su_list");
                showKySuList(kySuList);
                break;
            case 2:
                System.out.println("* Them Nhan vien");
                inputBasicInformation(req);
                System.out.print("Cong viec = ");
                String congViec = App.systemScanner.nextLine();
                req.addParameter("cong_viec", congViec);
                req.addParameter("can_bo_type", "nhan_vien");
                resp = HomeController.getInstance().service(req);
                List<NhanVien> nhanVienList = (List<NhanVien>) resp.getAttribute("nhan_vien_list");
                showNhanVienList(nhanVienList);
                break;
            case 3:
                System.out.println("* Them Cong nhan");
                inputBasicInformation(req);
                System.out.print("Bac = ");
                String bac = App.systemScanner.nextLine();
                req.addParameter("bac", bac);
                req.addParameter("can_bo_type", "cong_nhan");
                resp = HomeController.getInstance().service(req);
                List<CongNhan> congNhanList = (List<CongNhan>) resp.getAttribute("cong_nhan_list");
                showCongNhanList(congNhanList);
                break;
        }
        showAddCanBoMenu();
    }

    public static void showUpdateCanBoMenu() {
        printAxisX('_', 50, true);
        System.out.format("|%-48s|\n", "1. Update Ky su");
        System.out.format("|%-48s|\n", "2. Update Nhan vien");
        System.out.format("|%-48s|\n", "3. Update Cong nhan");
        System.out.format("|%-48s|\n", "0. Quay lai Main menu");
        System.out.print('|');
        printAxisX('_', 48, false);
        System.out.println('|');
        int choice = -1;
        do {
            System.out.print("choice = ");
            choice = Integer.parseInt(App.systemScanner.nextLine());
        } while (!(0 <= choice && choice <= 3));
        if (choice == 0) {
            showMainMenu();
        } else {
            processUpdateCanBoMenu(choice);
        }
    }

    private static void processUpdateCanBoMenu(int choice) {
        Request req = new Request();
        req.setMethod("get");
        Response resp = new Response();
        int selectedCanBoId = -1;
        switch (choice) {
            case 1:
                req.addParameter("can_bo_table_name", "ky_su_list");
                resp = HomeController.getInstance().service(req);
                List<KySu> kySuList = (List<KySu>) resp.getAttribute("ky_su_list");
                showKySuList(kySuList);
                req.clearAllParameter();
                System.out.print("Type in an ID to Edit (Keep empty to back to Main menu) = ");
                req.setMethod("put");
                req.addParameter("can_bo_type", "ky_su");
                String tmp = App.systemScanner.nextLine();
                if (tmp.equals("")) {
                    showMainMenu();
                    return;
                }
                selectedCanBoId = Integer.parseInt(tmp);
                req.addParameter("selected_can_bo_id", String.valueOf(selectedCanBoId));
                System.out.println("* Keep empty will have no change");
                inputBasicInformation(req);
                System.out.print("Nghanh dao tao = ");
                String nghanhDaoTao = App.systemScanner.nextLine();
                req.addParameter("nghanh_dao_tao", nghanhDaoTao);
                resp = HomeController.getInstance().service(req);
                kySuList = (List<KySu>) resp.getAttribute("ky_su_list");
                showKySuList(kySuList);
                break;
            case 2:
                req.addParameter("can_bo_table_name", "nhan_vien_list");
                resp = HomeController.getInstance().service(req);
                List<NhanVien> nhanVienList = (List<NhanVien>) resp.getAttribute("nhan_vien_list");
                showNhanVienList(nhanVienList);
                req.clearAllParameter();
                System.out.print("Type in an ID to Edit (Keep empty to back to Main menu) = ");
                req.setMethod("put");
                req.addParameter("can_bo_type", "nhan_vien");
                tmp = App.systemScanner.nextLine();
                if (tmp.equals("")) {
                    showMainMenu();
                    return;
                }
                selectedCanBoId = Integer.parseInt(tmp);
                req.addParameter("selected_can_bo_id", String.valueOf(selectedCanBoId));
                System.out.println("* Keep empty will have no change");
                inputBasicInformation(req);
                System.out.print("Cong viec = ");
                String congViec = App.systemScanner.nextLine();
                req.addParameter("cong_viec", congViec);
                resp = HomeController.getInstance().service(req);
                nhanVienList = (List<NhanVien>) resp.getAttribute("nhan_vien_list");
                showNhanVienList(nhanVienList);
                break;
            case 3:
                req.addParameter("can_bo_table_name", "cong_nhan_list");
                resp = HomeController.getInstance().service(req);
                List<CongNhan> congNhanList = (List<CongNhan>) resp.getAttribute("cong_nhan_list");
                showCongNhanList(congNhanList);
                req.clearAllParameter();
                System.out.print("Type in an ID to Edit (Keep empty to back to Main menu) = ");
                req.setMethod("put");
                req.addParameter("can_bo_type", "cong_nhan");
                tmp = App.systemScanner.nextLine();
                if (tmp.equals("")) {
                    showMainMenu();
                    return;
                }
                selectedCanBoId = Integer.parseInt(tmp);
                req.addParameter("selected_can_bo_id", String.valueOf(selectedCanBoId));
                System.out.println("* Keep empty will have no change");
                inputBasicInformation(req);
                System.out.print("Bac = ");
                String bac = App.systemScanner.nextLine();
                req.addParameter("bac", bac);
                resp = HomeController.getInstance().service(req);
                congNhanList = (List<CongNhan>) resp.getAttribute("cong_nhan_list");
                showCongNhanList(congNhanList);
                break;
        }
        showUpdateCanBoMenu();
    }

    public static void showDeleteCanBoMenu() {
        printAxisX('_', 50, true);
        System.out.format("|%-48s|\n", "1. Delete Ky su");
        System.out.format("|%-48s|\n", "2. Delete Nhan vien");
        System.out.format("|%-48s|\n", "3. Delete Cong nhan");
        System.out.format("|%-48s|\n", "0. Quay lai Main menu");
        System.out.print('|');
        printAxisX('_', 48, false);
        System.out.println('|');
        int choice = -1;
        do {
            System.out.print("choice = ");
            choice = Integer.parseInt(App.systemScanner.nextLine());
        } while (!(0 <= choice && choice <= 3));
        if (choice == 0) {
            showMainMenu();
        } else {
            processDeleteCanBoMenu(choice);
        }
    }

    public static void processDeleteCanBoMenu(int choice) {
        Request req = new Request();
        Response resp = new Response();
        int selectedCanBoId = -1;
        String tmp = "";
        switch (choice) {
            case 1:
                req.setMethod("get");
                req.addParameter("can_bo_table_name", "ky_su_list");
                resp = HomeController.getInstance().service(req);
                List<KySu> kySuList = (List<KySu>) resp.getAttribute("ky_su_list");
                showKySuList(kySuList);
                req.clearAllParameter();
                printAxisX('_', 50, true);
                System.out.print("Type in an ID to Delete (Keep empty to back to Main menu) = ");
                req.addParameter("can_bo_type", "ky_su");
                tmp = App.systemScanner.nextLine();
                if (tmp.equals("")) {
                    showMainMenu();
                    return;
                }
                selectedCanBoId = Integer.parseInt(tmp);
                req.setMethod("delete");
                req.addParameter("selected_can_bo_id", String.valueOf(selectedCanBoId));
                resp = HomeController.getInstance().service(req);
                kySuList = (List<KySu>) resp.getAttribute("ky_su_list");
                showKySuList(kySuList);
                break;
            case 2:
                req.setMethod("get");
                req.addParameter("can_bo_table_name", "nhan_vien_list");
                resp = HomeController.getInstance().service(req);
                List<NhanVien> nhanVienList = (List<NhanVien>) resp.getAttribute("nhan_vien_list");
                showNhanVienList(nhanVienList);
                req.clearAllParameter();
                printAxisX('_', 50, true);
                System.out.print("Type in an ID to Delete (Keep empty to back to Main menu) = ");
                req.addParameter("can_bo_type", "nhan_vien");
                tmp = App.systemScanner.nextLine();
                if (tmp.equals("")) {
                    showMainMenu();
                    return;
                }
                selectedCanBoId = Integer.parseInt(tmp);
                req.setMethod("delete");
                req.addParameter("selected_can_bo_id", String.valueOf(selectedCanBoId));
                resp = HomeController.getInstance().service(req);
                nhanVienList = (List<NhanVien>) resp.getAttribute("nhan_vien_list");
                showNhanVienList(nhanVienList);
                break;
            case 3:
                req.setMethod("get");
                req.addParameter("can_bo_table_name", "cong_nhan_list");
                resp = HomeController.getInstance().service(req);
                List<CongNhan> congNhanList = (List<CongNhan>) resp.getAttribute("cong_nhan_list");
                showCongNhanList(congNhanList);
                req.clearAllParameter();
                printAxisX('_', 50, true);
                System.out.print("Type in an ID to Delete (Keep empty to back to Main menu) = ");
                req.addParameter("can_bo_type", "cong_nhan");
                tmp = App.systemScanner.nextLine();
                if (tmp.equals("")) {
                    showMainMenu();
                    return;
                }
                selectedCanBoId = Integer.parseInt(tmp);
                req.setMethod("delete");
                req.addParameter("selected_can_bo_id", String.valueOf(selectedCanBoId));
                resp = HomeController.getInstance().service(req);
                congNhanList = (List<CongNhan>) resp.getAttribute("cong_nhan_list");
                showCongNhanList(congNhanList);
                break;
        }
        showDeleteCanBoMenu();
    }

    private static void inputBasicInformation(Request req) {
        System.out.print("Ten = ");
        String ten = App.systemScanner.nextLine();
        System.out.print("Tuoi = ");
        String tuoi = App.systemScanner.nextLine();
        System.out.print("Gioi tinh = ");
        String gioiTinh = App.systemScanner.nextLine();
        System.out.print("Dia chi = ");
        String diaChi = App.systemScanner.nextLine();
        req.addParameter("ten", ten);
        req.addParameter("tuoi", tuoi);
        req.addParameter("gioi_tinh", gioiTinh);
        req.addParameter("dia_chi", diaChi);
    }

    public static void showCanBoList(List<CanBo> canBoList) {
        printAxisX('_', 100, true);
        System.out.format("| %-4s| %-30s| %-5s| %-10s| %-40s|\n", "ID", "Ten", "Tuoi", "Gioi tinh", "Dia chi");
        System.out.print('|');
        printAxisX('_', 98, false);
        System.out.println('|');
        canBoList.forEach(ks -> {
            System.out.print("| ");
            ks.showInformation();
            System.out.println();
        });
        System.out.print('|');
        printAxisX('_', 98, false);
        System.out.println('|');
    }

    public static void showKySuList(List<KySu> kySuList) {
        printAxisX('_', 124, true);
        System.out.format("| %-4s| %-30s| %-5s| %-10s| %-40s| %-22s|\n", "Id", "Ten", "Tuoi", "Gioi tinh", "Dia chi",
                "Nghanh dao tao");
        System.out.print('|');
        printAxisX('_', 122, false);
        System.out.println('|');
        kySuList.forEach(ks -> {
            System.out.print("| ");
            ks.showInformation();
            System.out.println('|');
        });
        System.out.print('|');
        printAxisX('_', 122, false);
        System.out.println('|');
    }

    public static void showNhanVienList(List<NhanVien> nhanVienList) {
        printAxisX('_', 124, true);
        System.out.format("| %-4s| %-30s| %-5s| %-10s| %-40s| %-22s|\n", "Id", "Ten", "Tuoi", "Gioi tinh", "Dia chi",
                "Cong viec");
        System.out.print('|');
        printAxisX('_', 122, false);
        System.out.println('|');
        nhanVienList.forEach(nv -> {
            System.out.print("| ");
            nv.showInformation();
            System.out.println('|');
        });
        System.out.print('|');
        printAxisX('_', 122, false);
        System.out.println('|');
    }

    public static void showCongNhanList(List<CongNhan> congNhanList) {
        printAxisX('_', 124, true);
        System.out.format("| %-4s| %-30s| %-5s| %-10s| %-40s| %-22s|\n", "Id", "Ten", "Tuoi", "Gioi tinh", "Dia chi",
                "Bac");
        System.out.print('|');
        printAxisX('_', 122, false);
        System.out.println('|');
        congNhanList.forEach(ks -> {
            System.out.print("| ");
            ks.showInformation();
            System.out.println('|');
        });
        System.out.print('|');
        printAxisX('_', 122, false);
        System.out.println('|');
    }
}
