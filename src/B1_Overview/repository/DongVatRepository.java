/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B1_Overview.repository;

import B1_Overview.entity.DongVat;
import B1_Overview.util.DBConnect;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author hangnt
 */
public class DongVatRepository {

    // DI (Depenendy Injection): Map, List
    // Get all => hien thi toan bo du lieu trong db
    public List<DongVat> getAll() {
        List<DongVat> lists = new ArrayList<>();
        // Code 
        // B1: Tao cau truy van (sql)
        String sql = """
                    SELECT ma, ten, tuoi, gioi_tinh
                    FROM QLDongVat.dbo.dong_vat;
                    """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DongVat dv = new DongVat(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getBoolean(4));
                lists.add(dv);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return lists;
    }

    public List<DongVat> searchByName(String name) {
        List<DongVat> lists = new ArrayList<>();
        // Code 
        // B1: Tao cau truy van (sql)
        String sql = """
                    SELECT ma, ten, tuoi, gioi_tinh
                    FROM QLDongVat.dbo.dong_vat
                     WHERE ten LIKE ?
                    """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)) {
            // ResultSet <=> 1 table => du lieu tra ra cua cau lenh SQL 
            // TAT CA CAC CAU QUERY BAT DAU BANG SELECT : executeQuery
            ps.setObject(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery(); // thuc thi cau len sql
            while (rs.next()) {
                DongVat dv = new DongVat(rs.getString(1), rs.getString(2),
                        rs.getInt(3), rs.getBoolean(4));
                lists.add(dv);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return lists;
    }

    // add , update, delete => so dong thanh cong 
    public Boolean addDongVat(DongVat dv) {
        int check = 0; // 0 => fail: khong co dong nao duoc add thanh cong
        // xu ly 
        String sql = """
                   INSERT INTO [dbo].[dong_vat]
                              ([ma]
                              ,[ten]
                              ,[tuoi]
                              ,[gioi_tinh])
                        VALUES
                              (?,?,?,?)
                    """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, dv.getMa());
            ps.setObject(2, dv.getTen());
            ps.setObject(3, dv.getTuoi());
            // boolean => is 
            // Boolean => get
            ps.setObject(4, dv.getGioiTinh());
            check = ps.executeUpdate(); // TRA RA SO BAN GHI DUOC ADD THANH CONG
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return check > 0; // true 
    }

    public Boolean updateDongVat(DongVat newDongVat, String ma) {
        int check = 0; // 0 => fail: khong co dong nao duoc add thanh cong
        // xu ly 
        String sql = """
                   UPDATE QLDongVat.dbo.dong_vat
                              SET ten= ? , tuoi= ?, gioi_tinh= ?
                              WHERE ma= ?;
                    """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, newDongVat.getTen());
            ps.setObject(2, newDongVat.getTuoi());
            ps.setObject(3, newDongVat.getGioiTinh());
            ps.setObject(4, ma);
            check = ps.executeUpdate(); // TRA RA SO BAN GHI DUOC ADD THANH CONG
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return check > 0; // true 
    }

    public Boolean deleteDongVat(String ma) {
        int check = 0; // 0 => fail: khong co dong nao duoc add thanh cong
        // xu ly 
        String sql = """
                  DELETE FROM QLDongVat.dbo.dong_vat
                   WHERE ma=?;
                    """;
        try (Connection con = DBConnect.getConnection();
                PreparedStatement ps = con.prepareCall(sql)) {
            ps.setObject(1, ma);
            check = ps.executeUpdate(); // TRA RA SO BAN GHI DUOC ADD THANH CONG
        } catch (Exception e) {
            e.printStackTrace(System.out);
            // Xy ly ngoai le 
        }
        return check > 0; // true 
    }

    public static void main(String[] args) {
        System.out.println(new DongVatRepository().getAll());
    }
}
