/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import CRUD.NhanVienCRUD;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.NhanVien;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;


import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import ktgiuaky_qlvpp.CheckInput;
import ktgiuaky_qlvpp.KeyPressCheck;

/**
 *
 * @author WitherDragon
 */
public class frmQuanLyNhanVien extends javax.swing.JFrame {
    private NhanVien nv;
    private List<NhanVien> nhanViens;
    private NhanVienCRUD nhanVienCRUD;
    private ButtonColumn buttonColumn;
    private DocumentListener textChangeListener;
    private CheckInput checkInput;
    private KeyPressCheck keyPressCheck;
    
    public frmQuanLyNhanVien() {
        initComponents();          
        nhanVienCRUD=new NhanVienCRUD();
        checkInput = new CheckInput();
        keyPressCheck = new KeyPressCheck();
        mergeRadioButton();
        renderTableNhanVien();
        TextChangeEvent();
        CheckKeyPress();
        txtMaNV.setEnabled(false);
        rbNam.setSelected(true);
        btnThem.setEnabled(false);
        btnSua.setEnabled(false);
    }
    private void renderTableNhanVien(){
        nhanViens=nhanVienCRUD.getAllNhanVien();
        loadTable();
        tbNhanVien.setRowSelectionAllowed(false);
        clickLastColumnCell();
        tbNhanVien.setRowHeight(40);
        Icon deleteIcon = new  ImageIcon("C:\\HUTECH\\New\\Mang may tinh\\Giua ky\\VanPhongPhamBaToCom\\src\\icons\\delete.png");
        buttonColumn=new ButtonColumn(tbNhanVien, tbNhanVien.getColumnCount() - 1,deleteIcon);
    }
    
    private void TextChangeEvent(){
        textChangeListener = new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateTextFields();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateTextFields();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateTextFields();
            }
            private void updateTextFields(){
                if(txtMaNV.getText().isEmpty()){
                    btnSua.setEnabled(false);
                    txtTenDangNhap.setEnabled(true);
                    if(txtTenNhanVien.getText().isEmpty() || txtSDT.getText().isEmpty() || txtDiaChi.getText().isEmpty() || txtTenDangNhap.getText().isEmpty() ||
                    txtCMND.getText().isEmpty() || txtPassword.getText().isEmpty() || txtReenterPassword.getText().isEmpty()){
                        btnThem.setEnabled(false);
                        return;
                    }                        
                    else
                        btnThem.setEnabled(true);
                }
                else{
                    btnThem.setEnabled(false);
                    txtTenDangNhap.setEnabled(false);
                    if(txtTenNhanVien.getText().isEmpty() || txtSDT.getText().isEmpty() || txtDiaChi.getText().isEmpty() || txtCMND.getText().isEmpty()){                        
                            btnSua.setEnabled(false);   
                            return;
                    }
                    else{
                        if(!txtPassword.getText().isBlank()&& !txtReenterPassword.getText().isBlank()||
                           txtPassword.getText().isBlank() && txtReenterPassword.getText().isBlank()){
                            btnSua.setEnabled(true);
                            return;
                        }                            
                        else
                            btnSua.setEnabled(false);
                    }
                }
                    
            }
        };
        txtMaNV.getDocument().addDocumentListener(textChangeListener);
        txtTenDangNhap.getDocument().addDocumentListener(textChangeListener);
        txtTenNhanVien.getDocument().addDocumentListener(textChangeListener);
        txtSDT.getDocument().addDocumentListener(textChangeListener);
        txtDiaChi.getDocument().addDocumentListener(textChangeListener);
        txtCMND.getDocument().addDocumentListener(textChangeListener);
        txtPassword.getDocument().addDocumentListener(textChangeListener);
        txtReenterPassword.getDocument().addDocumentListener(textChangeListener);
    }
    
    private void CheckKeyPress(){
        txtTenNhanVien.addKeyListener(keyPressCheck.OnlyCharTextField());
        txtSDT.addKeyListener(keyPressCheck.OnlyNumberTextField());
        txtCMND.addKeyListener(keyPressCheck.OnlyNumberTextField());
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
                    txtMaNV.setText(selectedNhanVien.getMaNV());
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
        txtMaNV.setText("");
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
                            if(nhanVienCRUD.deleteUser(idNhanVien.toString())){
                                JOptionPane.showMessageDialog(null, "Xoá nhân viên thành công");
                                 refresh();
                            }
                            
                           
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
        txtMaNV = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        btnMenuPhanQuyen = new javax.swing.JMenuItem();
        btnMenuQuanLyMatHang = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Tên nhân viên");

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

        jLabel3.setText("Số điện thoại");

        rbNam.setText("Nam");

        rbNu.setText("Nữ");

        rbKhac.setText("Khác");

        jLabel4.setText("Giới tính");

        jLabel5.setText("Ngày sinh");

        dtpNgaySinh.setDateFormatString("dd/MM/yyyy");

        jLabel6.setText("CMND_CCCD");

        txtTenNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNhanVienActionPerformed(evt);
            }
        });

        jLabel7.setText("Tên đăng nhập");

        jLabel8.setText("Mật khẩu");

        jLabel9.setText("Nhập lại mật khẩu");

        cbPasswordShowHide.setText("Hiện mật khẩu");
        cbPasswordShowHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPasswordShowHideActionPerformed(evt);
            }
        });

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

        btnHuy.setText("Huỷ");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel10.setText("Mã nhân viên");

        jMenu1.setText("Menu");

        btnMenuPhanQuyen.setText("Phân quyền");
        btnMenuPhanQuyen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenuPhanQuyenActionPerformed(evt);
            }
        });
        jMenu1.add(btnMenuPhanQuyen);

        btnMenuQuanLyMatHang.setText("Quản lý mặt hàng");
        jMenu1.add(btnMenuQuanLyMatHang);

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(430, 430, 430)
                        .addComponent(cbPasswordShowHide, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(txtSDT)
                            .addComponent(jLabel8)
                            .addComponent(dtpNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenDangNhap)
                            .addComponent(txtPassword)
                            .addComponent(jLabel10)
                            .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                                        .addComponent(jLabel9)
                                        .addGap(0, 266, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtReenterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rbNam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(63, 63, 63)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel4)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(rbNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(rbKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(jLabel6)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel1)
                                            .addComponent(txtTenNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 393, Short.MAX_VALUE)))
                                    .addComponent(txtCMND, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 393, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(9, 9, 9)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addGap(4, 4, 4)
                        .addComponent(dtpNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel7))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbNam)
                            .addComponent(rbNu)
                            .addComponent(rbKhac))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCMND)
                    .addComponent(txtTenDangNhap, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtReenterPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPasswordShowHide)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        String password1 = new String(txtReenterPassword.getPassword());
        if(!checkInput.CheckBrithday(dtpNgaySinh.getDate()))
            return;
        if(!checkInput.CheckSDT(txtSDT.getText()))
            return;
        if(!checkInput.CheckCCCD(txtCMND.getText()))
            return;
        if(!checkInput.CheckPassword(password, password1))
            return;
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
        if(!checkInput.CheckBrithday(dtpNgaySinh.getDate()))
            return;
        if(!checkInput.CheckSDT(txtSDT.getText()))
            return;
        if(!checkInput.CheckCCCD(txtCMND.getText()))
            return;
        try{ 
            NhanVien nv = new NhanVien();
            if(!txtPassword.getText().isEmpty() ){
                String password = new String(txtPassword.getPassword());
                String password1 = new String(txtReenterPassword.getPassword());
                if(!checkInput.CheckPassword(password, password1))
                    return;
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

    private void cbPasswordShowHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPasswordShowHideActionPerformed
        // TODO add your handling code here:
        if(cbPasswordShowHide.isSelected()){
            txtPassword.setEchoChar((char) 0);
            txtReenterPassword.setEchoChar((char) 0);
        }
        
        else{                
            txtPassword.setEchoChar('\u2022');       
            txtReenterPassword.setEchoChar('\u2022');             
        }                                              
    }//GEN-LAST:event_cbPasswordShowHideActionPerformed

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaNVActionPerformed

    private void btnMenuPhanQuyenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenuPhanQuyenActionPerformed
        // TODO add your handling code here:
        frmPhanQuyen open = new frmPhanQuyen();
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnMenuPhanQuyenActionPerformed

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
    private javax.swing.JMenuItem btnMenuPhanQuyen;
    private javax.swing.JMenuItem btnMenuQuanLyMatHang;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JCheckBox cbPasswordShowHide;
    private com.toedter.calendar.JDateChooser dtpNgaySinh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbKhac;
    private javax.swing.JRadioButton rbNam;
    private javax.swing.JRadioButton rbNu;
    private javax.swing.JTable tbNhanVien;
    private javax.swing.JTextField txtCMND;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JPasswordField txtReenterPassword;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenDangNhap;
    private javax.swing.JTextField txtTenNhanVien;
    // End of variables declaration//GEN-END:variables
}
