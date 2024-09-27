/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B1_Overview.entity;

/**
 *
 * @author hangnt
 */
public class DongVat {

    private String ma;
    private String ten;
    private Integer tuoi;
    private Boolean gioiTinh;

    public DongVat() {
    }

    public DongVat(String ma, String ten, Integer tuoi, Boolean gioiTinh) {
        this.ma = ma;
        this.ten = ten;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getTuoi() {
        return tuoi;
    }

    public void setTuoi(Integer tuoi) {
        this.tuoi = tuoi;
    }

    public Boolean getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "DongVat{" + "ma=" + ma + ", ten=" + ten + ", tuoi=" + tuoi + ", gioiTinh=" + gioiTinh + '}';
    }

}
