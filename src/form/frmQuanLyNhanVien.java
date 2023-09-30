/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import CRUD.NhanVienCRUD;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.NhanVien;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author WitherDragon
 */
public class frmQuanLyNhanVien extends javax.swing.JFrame {
    private NhanVien nv;
    private List<NhanVien> nhanViens;
    private NhanVienCRUD nhanVienCRUD;
    private ButtonColumn buttonColumn;
    public frmQuanLyNhanVien() {
        initComponents();
          
        nhanVienCRUD=new NhanVienCRUD();
        mergeRadioButton();
        renderTableNhanVien();
    }
    private void renderTableNhanVien(){
        nhanViens=nhanVienCRUD.getAllNhanVien();
        loadTable();
        tbNhanVien.setRowSelectionAllowed(false);
        clickLastColumnCell();
        tbNhanVien.setRowHeight(40);
        Icon deleteIcon = new  ImageIcon("C:\\Users\\Administrator\\Desktop\\LTMang\\KTGiuaKy\\VanPhongPhamBaToCom\\src\\icons\\delete.png");
        buttonColumn=new ButtonColumn(tbNhanVien, tbNhanVien.getColumnCount() - 1,deleteIcon);
    }
    private void createTableRowClick(){
        tbNhanVien.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = tbNhanVien.getSelectedRow();
                if(selectedRow < 0 || selectedRow == 6)
                    return;
                if (selectedRow >= 0) {
                    NhanVien selectedNhanVien = nhanViens.get(selectedRow);
                    txtTenNhanVien.setText(selectedNhanVien.getTenNV());
                    txtDiaChi.setText(selectedNhanVien.getDiaChi());
                    txtSDT.setText(selectedNhanVien.getSdt());
                    dtpNgaySinh.setDate(selectedNhanVien.getNgaySinh());
                    txtCMND.setText(selectedNhanVien.getCMND_CCCD());
                    txtTenDangNhap.setText(selectedNhanVien.getUsername());
                    loadGender(selectedNhanVien.getGioiTinh());

                }
            }
        });
     }
    private void refresh(){
        txtTenDangNhap.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtTenNhanVien.setText("");
        txtPassword.setText("");
        txtReenterPassword.setText("");
        txtCMND.setText("");
        dtpNgaySinh.setDate(null);
        rbNam.setSelected(false);
        rbNu.setSelected(false);
        rbKhac.setSelected(false);
        nhanViens=nhanVienCRUD.getAllNhanVien();
        loadTable();
        
    }
    private void clickLastColumnCell(){
        // Add a MouseListener to the table
        tbNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tbNhanVien.rowAtPoint(e.getPoint());
                int column = tbNhanVien.columnAtPoint(e.getPoint());
                if (column == tbNhanVien.getColumnCount() - 1) {
                    
                    int selectedRow = tbNhanVien.convertRowIndexToModel(row);
                    Object idNhanVien = tbNhanVien.getModel().getValueAt(selectedRow, 0);
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc muốn xoá nhân viên này? ",
                            "Xác nhận",
                            JOptionPane.YES_NO_OPTION);

                    if (result == JOptionPane.YES_OPTION)
                    {
                            nhanVienCRUD.deleteUser(idNhanVien.toString());
                            JOptionPane.showMessageDialog(null, "Xoá nhân viên thành công!");
                            refresh();
                    }
                
                }

            }
        });
    }
    private void mergeRadioButton(){
        btgGender.add(rbNam);
        btgGender.add(rbKhac);
        btgGender.add(rbNu);
    }
    public void loadGender(String gender){
        if("Nam".equals(gender)) {
            rbNam.setSelected(true);
        }
        else if("Nữ".equals(gender)){
            rbNu.setSelected(true);
        }
        else{
            rbKhac.setSelected(true);
        }
    }
            
    private void loadTable() {

        DefaultTableModel model = (DefaultTableModel) tbNhanVien.getModel();
        model.setRowCount(0); 
        for (NhanVien nhanVien : nhanViens) {
            String maNV = nhanVien.getMaNV();
            String tenNV = nhanVien.getTenNV();
            String diaChi = nhanVien.getDiaChi();
            String sdt = nhanVien.getSdt();
            String gioiTinh = nhanVien.getGioiTinh();
            Date ngaySinh = nhanVien.getNgaySinh();
            String cmndCCCD = nhanVien.getCMND_CCCD();

            model.addRow(new Object[]{maNV, tenNV,sdt, diaChi,ngaySinh,  gioiTinh , cmndCCCD});
        }
        createTableRowClick();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btgGender = new javax.swing.ButtonGroup();
        txtCMND = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbNhanVien = new javax.swing.JTable();
        txtSDT = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rbNam = new javax.swing.JRadioButton();
        rbNu = new javax.swing.JRadioButton();
        rbKhac = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dtpNgaySinh = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        txtTenNhanVien = new javax.swing.JTextField();
        txtTenDangNhap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtReenterPassword = new javax.swing.JPasswordField();
        txtPassword = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        cbPasswordShowHide = new javax.swing.JCheckBox();
        btnSua = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Tên nhân viên");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Địa chỉ");

        tbNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Tên nhân viên", "Số điện thoại", "Địa chỉ", "Ngày sinh", "Giới tính", "CMND", "Xoá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbNhanVien);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại");

        rbNam.setText("Nam");

        rbNu.setText("Nữ");

        rbKhac.setText("Khác");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Giới tính");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Ngày sinh");

        dtpNgaySinh.setDateFormatString("dd/MM/yyyy");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("CMND_CCCD");

        txtTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNhanVienActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tên đăng nhập");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Mật khẩu");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Nhập lại mật khẩu");

        cbPasswordShowHide.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbPasswordShowHide.setText("Hiện mật khẩu");

        btnSua.setText("Sửa nhân viên");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm mới NV");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnHuy.setIcon(new javax.swing.ImageIcon("C:\\Users\\Administrator\\Downloads\\transparent-arrow-icon-return-icon-5f8796d7ba8fc3.7847272616027214957642.jpg")); // NOI18N
        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        jMenu1.setText("Menu");

        jMenuItem2.setText("Phân quyền");
        jMenu1.add(jMenuItem2);

        jMenuItem1.setText("Đăng xuất");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(436, 436, 436)
                        .addComponent(cbPasswordShowHide, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(txtSDT, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(jLabel8)
                            .addComponent(dtpNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)
                            .addComponent(txtPassword))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(rbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(63, 63, 63)
                                        .addComponent(rbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addGap(18, 30, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel6)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel2)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtReenterPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jLabel9))
                                                .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addGap(166, 166, 166))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(9, 9, 9)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbNam)
                            .addComponent(rbNu)
                            .addComponent(rbKhac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCMND)
                    .addComponent(dtpNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtReenterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPasswordShowHide)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNhanVienActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNhanVienActionPerformed
    private String genderChose(){
        if(rbNam.isSelected()) {
            return "Nam";
        }
        else if(rbNu.isSelected()){
            return "Nữ";
        }
        else if(rbKhac.isSelected()){
            return "Khác";
        }
        return "";
    }
    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        String password = new String(txtPassword.getPassword());
//        String password1 = new String(.getPassword());
//        if(!inputCheck.CheckBrithday(dcBrithday.getDate()))
//            return;
//        if(!inputCheck.CheckSDT(txtPhone.getText()))
//            return;
//        if(!inputCheck.CheckEmail(txtEmail.getText()))
//            return;
//        if(!inputCheck.CheckPassword(password, password1))
//            return;
        try{ 
            password =  BCrypt.withDefaults().hashToString(12, password.toCharArray());
            Date selectedDate = dtpNgaySinh.getDate();
            java.sql.Date birthday = new java.sql.Date(selectedDate.getTime());
            nv = new NhanVien();
            nv.setMaNV("");
            nv.setTenNV(txtTenNhanVien.getText());
            nv.setDiaChi(txtDiaChi.getText());
            nv.setSdt(txtSDT.getText());
            nv.setGioiTinh(genderChose());
            nv.setNgaySinh(birthday);
            nv.setCMND_CCCD(txtCMND.getText());
            nv.setUsername(txtTenDangNhap.getText());
            nv.setPassword(password);
            
            String query = String.format("INSERT INTO nhanvien VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s','%s')",
                                    nv.getMaNV(), nv.getTenNV(), nv.getDiaChi(), nv.getSdt(), nv.getGioiTinh(),nv.getNgaySinh()
                                    , nv.getCMND_CCCD(), nv.getUsername(), nv.getPassword(),0,0);
            nhanVienCRUD.taoNhanVien(query);
            refresh();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, ex,"LOI",1);
        }      
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        refresh();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try{ 
            NhanVien nv = new NhanVien();
            if(!txtPassword.getText().isEmpty() ){
                String password = new String(txtPassword.getPassword());
                password =  BCrypt.withDefaults().hashToString(12, password.toCharArray());
                nv.setPassword(password);
            }                
            Date selectedDate = dtpNgaySinh.getDate();
            java.sql.Date birthday = new java.sql.Date(selectedDate.getTime());            
            nv.setTenNV(txtTenNhanVien.getText());
            nv.setDiaChi(txtDiaChi.getText());
            nv.setSdt(txtSDT.getText());
            nv.setGioiTinh(genderChose());
            nv.setNgaySinh(birthday);
            nv.setCMND_CCCD(txtCMND.getText());
            nv.setUsername(txtTenDangNhap.getText());            
            if(nhanVienCRUD.update(nv))
                JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
            else
                JOptionPane.showMessageDialog(null, "Cập nhật thất bại!","Lỗi",JOptionPane.ERROR_MESSAGE);
            refresh();
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(rootPane, ex,"LOI",1);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmQuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmQuanLyNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btgGender;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JCheckBox cbPasswordShowHide;
    private com.toedter.calendar.JDateChooser dtpNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbKhac;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtReenterPassword;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenDangNhap;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
