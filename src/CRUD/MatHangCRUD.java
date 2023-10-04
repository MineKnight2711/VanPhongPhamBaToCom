/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
            String query ="INSERT INTO mathang VALUES ('', ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, matHang.getTenMH());
            statement.setDouble(2, matHang.getGiaBan());
            statement.setString(3, matHang.getDvt());
            statement.setString(4, matHang.getMaLoaiMH());
            statement.setString(5, matHang.getMota());
            statement.setBoolean(6, false);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Thêm mặt hàng mới thành công");
            return true;    
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
    public MatHang findByMaMH(String maMH) {
        String query = "SELECT * FROM mathang WHERE MaMH = '" + maMH + "'";
        ResultSet rs = Query(query);
        MatHang matHang = new MatHang();
        try {
            while (rs.next()) {
                matHang.setMaMH(rs.getString("MaMH"));
                matHang.setTenMH(rs.getString("TenMH"));
                matHang.setGiaBan(rs.getDouble("GiaBan"));
                matHang.setDvt(rs.getString("Dvt"));
                matHang.setMaLoaiMH(rs.getString("MaLoaiHang"));
                matHang.setMota(rs.getString("Mota"));
                matHang.setVoHieuHoa(rs.getBoolean("VoHieuHoa"));
            }
            return matHang;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    public boolean updateMatHang(MatHang matHang) {
        try{
            String query = "UPDATE mathang SET TenMH = ?, GiaBan = ?, Dvt = ?, MaLoaiHang = ?, MoTa = ?, VoHieuHoa = ? WHERE MaMH = ?" ;
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, matHang.getTenMH());
            statement.setDouble(2, matHang.getGiaBan());
            statement.setString(3, matHang.getDvt());
            statement.setString(4, matHang.getMaLoaiMH());
            statement.setString(5, matHang.getMota());
            statement.setBoolean(6, matHang.isVoHieuHoa());
            statement.setString(7, matHang.getMaMH());
            statement.execute();
            JOptionPane.showMessageDialog(null, "Cập nhật mặt hàng mới thành công");
            return true;    
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
    public boolean voHieuHoaMatHang(MatHang matHang) {
        try{
            String query = String.format(
                "UPDATE mathang SET VoHieuHoa = 1 WHERE MaMH = '" + matHang.getMaMH() + "'");
            stmt.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Vô hiệu hoá mặt hàng thành công");
            return true;    
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
}
