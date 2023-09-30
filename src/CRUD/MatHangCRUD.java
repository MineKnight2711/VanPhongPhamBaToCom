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
import model.MatHang;

/**
 *
 * @author WitherDragon
 */
public class MatHangCRUD {
    private Connection con;
    private Statement stmt;
    public MatHangCRUD(){
        try{
            MyConnection mycon=new MyConnection();
            con=mycon.getConection();
            stmt=con.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }                    
    }
    
    public ResultSet Query(String srt){
         try{
             ResultSet rs = stmt.executeQuery(srt);
             return rs;
         }catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex);
            return null ;
        }
    }
    
    public List<MatHang> getAll() {
        List<MatHang> list = new ArrayList<>();
        String query = "SELECT * FROM mathang";
        ResultSet rs = Query(query);
        try {
            while (rs.next()) {
                MatHang matHang = new MatHang();
                matHang.setMaMH(rs.getString("MaMH"));
                matHang.setTenMH(rs.getString("TenMH"));
                matHang.setGiaBan(rs.getDouble("GiaBan"));
                matHang.setDvt(rs.getString("Dvt"));
                matHang.setMaLoaiMH(rs.getString("MaLoaiHang"));
                matHang.setMota(rs.getString("Mota"));
                matHang.setVoHieuHoa(rs.getBoolean("VoHieuHoa"));
                list.add(matHang);
            }
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public boolean addData(MatHang matHang) throws SQLException {
        
        try{
            String query = String.format("INSERT INTO mathang VALUES ('%s', '%s', '%f', '%s', '%s', '%s', '%s')",
                                    matHang.getMaMH(), matHang.getTenMH(), matHang.getGiaBan(), matHang.getDvt(), 
                                    matHang.getMaLoaiMH(),matHang.getMota(), 0);
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Thêm mặt hàng mới thành công");
            return true;    
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
}
