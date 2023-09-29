package CRUD;

import at.favre.lib.crypto.bcrypt.BCrypt;
import model.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class NhanVienCRUD {
    private Connection con;
    private Statement stmt;
    public NhanVienCRUD(){
        try{
            MyConnection mycon=new MyConnection();
            con=mycon.getConection();
            stmt=con.createStatement();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }                    
    }
    
    public boolean login(String username, String password) {
    String query = "SELECT Password FROM nhanvien WHERE username = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String hashedPassword = resultSet.getString("password");
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), hashedPassword);
                    if (result.verified) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    
    public boolean update(NhanVien nv){
        try {
            String query = "";
            if(nv.getPassword() == null) 
                query = String.format("UPDATE nhanvien SET  TenNV = '%s', DiaChi = '%s', Sdt = '%s', GioiTinh = '%s', NgaySinh = '%s', CMND_CCCD = '%s' WHERE username = '%s'",
                                    nv.getTenNV(), nv.getDiaChi(), nv.getSdt(), nv.getGioiTinh(),nv.getNgaySinh()
                                    , nv.getCMND_CCCD(), nv.getUsername());
            else
                query = String.format("UPDATE nhanvien SET password = '%s' ,TenNV = '%s', DiaChi = '%s', Sdt = '%s', GioiTinh = '%s', NgaySinh = '%s', CMND_CCCD = '%s' WHERE username = '%s'",
                                    nv.getPassword() , nv.getTenNV(), nv.getDiaChi(), nv.getSdt(), nv.getGioiTinh(),nv.getNgaySinh()
                                    , nv.getCMND_CCCD(), nv.getUsername());
            stmt.executeUpdate(query);
            return true;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return false;
        }
    }
    
    public boolean ChangePassword(String queryCheck, String query, String oldPass){
        try{
                String pass;
                PreparedStatement statement = con.prepareStatement(queryCheck);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    pass = resultSet.getString("Password");
                    BCrypt.Result result = BCrypt.verifyer().verify(oldPass.toCharArray(), pass);
                    if(!result.verified){
                        JOptionPane.showMessageDialog(null, "Mật khẩu cũ không hợp lệ");
                        return false;
                    }
                }
                stmt.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Đổi mật khẩu thành công");
                return true;
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
                return false;
        }        
    }
    
    public NhanVien getUser(String username) {
        String query = "SELECT * FROM nhanvien WHERE username = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(resultSet.getString("MaNV"));
                    nv.setTenNV(resultSet.getString("TenNV"));
                    nv.setDiaChi(resultSet.getString("DiaChi"));
                    nv.setSdt(resultSet.getString("Sdt"));
                    nv.setGioiTinh(resultSet.getString("GioiTinh"));
                    nv.setNgaySinh(resultSet.getDate("NgaySinh"));
                    nv.setCMND_CCCD(resultSet.getString("CMND_CCCD"));
                    nv.setUsername(resultSet.getString("username"));
                    nv.setPassword(resultSet.getString("password"));
                    nv.setVoHieuHoa(resultSet.getBoolean("VoHieuHoa"));
                    nv.setLaQuanLy(resultSet.getBoolean("LaQuanLy"));
                    return nv;
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return null; // Return null if the user doesn't exist or an error occurs
    }
    
    public boolean taoNhanVien(String str){
        try{
            stmt.executeUpdate(str);
            JOptionPane.showMessageDialog(null, "Thêm nhân viên mới thành công");
            return true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,ex);
            System.out.println(ex.toString());
            return false;
        }
    }
    
    public int Update(String str){
        try {
             int i=stmt.executeUpdate(str);
             return i;
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
            return -1;
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
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> nhanVienList = new ArrayList<>();
        String query = "SELECT * FROM nhanvien";
        ResultSet rs = Query(query);

        try {
            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString("MaNV"));
                nhanVien.setTenNV(rs.getString("TenNV"));
                nhanVien.setDiaChi(rs.getString("DiaChi"));
                nhanVien.setSdt(rs.getString("Sdt"));
                nhanVien.setGioiTinh(rs.getString("GioiTinh"));
                nhanVien.setNgaySinh(rs.getDate("NgaySinh"));
                nhanVien.setCMND_CCCD(rs.getString("CMND_CCCD"));
                nhanVien.setUsername(rs.getString("username"));
                nhanVienList.add(nhanVien);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    return nhanVienList;
}

    public void deleteUser(String nhanVienID) {
        String query = "DELETE FROM nhanvien WHERE MaNV = ?";

        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, nhanVienID);
            statement.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
}
