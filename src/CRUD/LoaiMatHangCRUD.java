/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.LoaiMatHang;


/**
 *
 * @author Light
 */
public class LoaiMatHangCRUD {
    private Connection con;
    private Statement stmt;

    public LoaiMatHangCRUD() {
        try{
            MyConnection mycon = new MyConnection();
            con = mycon.getConection();
            stmt = con.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }                    
    }
    
    public ResultSet Query(String srt){
         try{
             ResultSet rs=stmt.executeQuery(srt);
             return rs;
         }catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
            return null ;
        }
    }
    public boolean addLoaiMatHang(LoaiMatHang lmh){
        String sql = String.format("INSERT INTO loaimathang VALUES ('%s', '%s','%s')",lmh.getMaLoaiHang(),lmh.getTenLoai(),0);
        try{
            stmt.executeUpdate(sql);
            
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
    public List<LoaiMatHang> getAll() {
        List<LoaiMatHang> list = new ArrayList<>();
        String query = "SELECT * FROM loaimathang";
        ResultSet rs = Query(query);
        try {
            while (rs.next()) {
                LoaiMatHang loaiMatHang = new LoaiMatHang();
                loaiMatHang.setMaLoaiHang(rs.getString("MaLoaiHang"));
                loaiMatHang.setTenLoai(rs.getString("TenLoai"));
                loaiMatHang.setVoHieuHoa(rs.getBoolean("VoHieuHoa"));
                list.add(loaiMatHang);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public List<LoaiMatHang> getAllExceptVoHieuHoa() {
        List<LoaiMatHang> list = new ArrayList<>();
        String query = "SELECT * FROM loaimathang WHERE VoHieuHoa = false";
        ResultSet rs = Query(query);
        try {
            while (rs.next()) {
                LoaiMatHang loaiMatHang = new LoaiMatHang();
                loaiMatHang.setMaLoaiHang(rs.getString("MaLoaiHang"));
                loaiMatHang.setTenLoai(rs.getString("TenLoai"));
                loaiMatHang.setVoHieuHoa(rs.getBoolean("VoHieuHoa"));
                list.add(loaiMatHang);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public String getByMaLoaiMH(String maLoaiMH) {
        String tenLoaiMH = "";
        String query = "SELECT * FROM loaimathang WHERE MaLoaiHang = '" + maLoaiMH + "'";
        ResultSet rs = Query(query);
        try {
            while (rs.next()) {
                tenLoaiMH = rs.getString("TenLoai");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return tenLoaiMH;
    }
    public String getByTenLoaiMH(String tenLoaiMH) {
        String maLoaiMH = "";
        String query = "SELECT * FROM loaimathang WHERE TenLoai = '" + tenLoaiMH + "'";
        ResultSet rs = Query(query);
        try {
            while (rs.next()) {
                maLoaiMH = rs.getString("MaLoaiHang");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return maLoaiMH;
    }
    public boolean xoaLoaiMatHang(String loaiMatHangId) {
        try {
            String query =  String.format(
                "UPDATE loaimathang SET VoHieuHoa = 1 WHERE MaLoaiHang = '" + loaiMatHangId + "'");
            stmt.executeUpdate(query);
            return true;    
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
}
