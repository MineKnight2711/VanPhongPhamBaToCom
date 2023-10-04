/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package form;

import CRUD.LoaiMatHangCRUD;
import CRUD.MatHangCRUD;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import ktgiuaky_qlvpp.KeyPressCheck;
import model.LoaiMatHang;
import model.MatHang;
import model.NhanVienSession;

/**
 *
 * @author Light
 */
public class frmSanPham extends javax.swing.JFrame {
    private LoaiMatHangCRUD loaiMatHangCRUD;
    private MatHangCRUD matHangCRUD;
    private DocumentListener textChangeListener;
    private List<MatHang> listMatHang = new ArrayList<>();
    private List<LoaiMatHang> listLoaiMatHang = new ArrayList<>();
    private static NhanVienSession currentNhanVien;
    private KeyPressCheck keyCheck;
    /**
     * Creates new form FormSanPham
     * @param loginNhanVien
     */
    public frmSanPham(NhanVienSession loginNhanVien) {
        initComponents();
        loaiMatHangCRUD = new LoaiMatHangCRUD();
        matHangCRUD = new MatHangCRUD();
        frmSanPham.currentNhanVien=loginNhanVien;
        txtMoTa.setLineWrap(true);
        txtMoTa.setWrapStyleWord(true);
        keyCheck = new KeyPressCheck();
        CheckKeyPress();
        loadComboBox();
        textChange();
        refreshData();
        if(loginNhanVien.getLoggedInNhanVien().isLaQuanLy())
            btnReturn.setVisible(true);
        else
            btnReturn.setVisible(false);
        
    }
    private void loadComboBox(){
        listLoaiMatHang = loaiMatHangCRUD.getAllExceptVoHieuHoa();
        DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();
        for(LoaiMatHang loai : listLoaiMatHang){
            String maLoai = loai.getTenLoai();
            comboBoxModel.addElement(maLoai);
        }
        cmbLoai.setModel(comboBoxModel);
    }
    private void textChange() {
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
            private void updateTextFields()
            {
                if(txtMaMH.getText().isEmpty())
                {
                    btnEdit.setEnabled(false);
                    btnDelete.setEnabled(false);
                    if( txtTenMH.getText().isEmpty() || txtMoTa.getText().isEmpty() || txtGiaBan.getText().isEmpty())
                        btnAdd.setEnabled(false);                   
                    else
                        btnAdd.setEnabled(true);
                }  
                else 
                {
                    if(txtMaMH.getText().isEmpty()) 
                    {
                        btnEdit.setEnabled(false);
                        btnDelete.setEnabled(false);
                    }
                    else 
                    {
                        btnDelete.setEnabled(true);
                        if(txtTenMH.getText().isEmpty() || txtMoTa.getText().isEmpty() || txtGiaBan.getText().isEmpty())
                            btnEdit.setEnabled(false);
                        else
                            btnEdit.setEnabled(true);
                            
                    }
                }
            }
        };
        txtTenMH.getDocument().addDocumentListener(textChangeListener);
        txtMoTa.getDocument().addDocumentListener(textChangeListener);
        txtGiaBan.getDocument().addDocumentListener(textChangeListener);
    }
    
    private void CheckKeyPress(){
        txtGiaBan.addKeyListener(keyCheck.OnlyNumberTextField());
    } 
    
    private void loadVoHieuHoa(boolean isVoHieuHoa){
        if(isVoHieuHoa)
            cbVoHieuHoa.setSelected(true);
        else
            cbVoHieuHoa.setSelected(false);
    }
    private void createTableRowClick(){
        tblData.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {

                int selectedRow = tblData.getSelectedRow();
//                if(selectedRow < 0 || selectedRow == 6)
//                    return;
                if (selectedRow >= 0) {
                    MatHang selectedMatHang = listMatHang.get(selectedRow);
                    txtMaMH.setText(selectedMatHang.getMaMH());
                    txtTenMH.setText(selectedMatHang.getTenMH());
                    txtGiaBan.setText(Double.toString(selectedMatHang.getGiaBan()));
                    cmbLoai.setSelectedItem(selectedMatHang.getMaLoaiMH());
                    txtMoTa.setText(selectedMatHang.getMota());
                    cbmDvt.setSelectedItem(selectedMatHang.getDvt());
                    cbVoHieuHoa.setSelected(selectedMatHang.isVoHieuHoa());
                    loadVoHieuHoa(selectedMatHang.isVoHieuHoa());
                }
            }
        });
    }
    
    private void loadTable(List<MatHang> list) {
        tblData.setRowHeight(40);
        tblData.setRowSelectionAllowed(false);
        DefaultTableModel model = (DefaultTableModel) tblData.getModel();
        model.setRowCount(0); 
        int number = 1;
        for (MatHang matHang : list) {
            int stt = number;
            Boolean voHieuHoa = matHang.isVoHieuHoa();
            String maMH = matHang.getMaMH();
            String maLoai = loaiMatHangCRUD.getByMaLoaiMH(matHang.getMaLoaiMH());
            String tenMH = matHang.getTenMH();
            double giaBan = matHang.getGiaBan();
            String dvt = matHang.getDvt();
            String moTa = matHang.getMota();
            model.addRow(new Object[]{stt,voHieuHoa, maLoai,maMH, tenMH, giaBan, dvt , moTa});
            number ++;
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

        jPanel1 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnReturn = new javax.swing.JButton();
        btnCancel1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblData = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cmbLoai = new javax.swing.JComboBox<>();
        txtMaMH = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbmDvt = new javax.swing.JComboBox<>();
        txtTenMH = new javax.swing.JTextField();
        cbVoHieuHoa = new javax.swing.JCheckBox();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        txtGiaBan = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnEdit.setText("Sửa");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnCancel.setText("Bỏ qua");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnReturn.setText("Trở về");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        btnCancel1.setText("Quản lý loại mặt hàng");
        btnCancel1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancel1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(84, 84, 84)
                .addComponent(btnCancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel1)
                .addGap(32, 32, 32)
                .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnReturn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tblData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Vô hiệu", "Nhóm mặt hàng", "Mã mặt hàng", "Mặt hàng", "Giá bán", "DVT", "Mô tả"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblData);
        if (tblData.getColumnModel().getColumnCount() > 0) {
            tblData.getColumnModel().getColumn(7).setResizable(false);
        }

        jLabel1.setText("Thông tin mặt hàng");

        jLabel2.setText("Loại mặt hàng");

        jLabel3.setText("Mã mặt hàng");

        jLabel4.setText("Giá bán");

        txtMaMH.setEnabled(false);

        jLabel5.setText("Mặt hàng");

        jLabel6.setText("ĐVT");

        cbmDvt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "CÁI", "BẢNG", "CHIẾC", "CÂY", "TỜ" }));

        cbVoHieuHoa.setText("Vô hiệu hoá");

        jLabel7.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbLoai, 0, 105, Short.MAX_VALUE)
                                    .addComponent(txtMaMH))
                                .addGap(33, 33, 33)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(cbmDvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(26, 26, 26)
                                        .addComponent(cbVoHieuHoa, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 748, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1))
                .addContainerGap(189, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTenMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaMH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cbmDvt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbVoHieuHoa))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed

        MatHang matHang = new MatHang();
        matHang.setMaMH("");
        matHang.setTenMH(txtTenMH.getText());
        matHang.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        matHang.setDvt(cbmDvt.getSelectedItem().toString());
        matHang.setMaLoaiMH(loaiMatHangCRUD.getByTenLoaiMH(cmbLoai.getSelectedItem().toString()));
        matHang.setMota(txtMoTa.getText());
        matHang.setVoHieuHoa(cbVoHieuHoa.isSelected());
        try {
            matHangCRUD.addData(matHang);
            refreshData();
            JOptionPane.showMessageDialog(null, "Thêm mới mặt hàng thành công");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra:"+ex.toString(),"Lỗi",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAddActionPerformed
    private void refreshData(){
        txtMaMH.setText("");
        txtTenMH.setText("");
        txtMoTa.setText("");
        txtGiaBan.setText("");
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        if(!currentNhanVien.getLoggedInNhanVien().isLaQuanLy()){
            btnReturn.setVisible(false);
        }
        loadVoHieuHoa(false);
        listMatHang = matHangCRUD.getAll();
        loadTable(listMatHang);
    }
    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        refreshData();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        MatHang matHang = matHangCRUD.findByMaMH(txtMaMH.getText());
        matHang.setTenMH(txtTenMH.getText());
        matHang.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
        matHang.setDvt(cbmDvt.getSelectedItem().toString());
        matHang.setMaLoaiMH(loaiMatHangCRUD.getByTenLoaiMH(cmbLoai.getSelectedItem().toString()));
        matHang.setMota(txtMoTa.getText());
        matHang.setVoHieuHoa(cbVoHieuHoa.isSelected());
        matHangCRUD.updateMatHang(matHang);
        refreshData();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        MatHang matHang = matHangCRUD.findByMaMH(txtMaMH.getText());
        matHangCRUD.voHieuHoaMatHang(matHang);
        refreshData();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        frmQuanLyNhanVien qlnv=new frmQuanLyNhanVien(currentNhanVien);
        qlnv.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnReturnActionPerformed

    private void btnCancel1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancel1ActionPerformed
        // TODO add your handling code here:
        frmLoaiMatHang open = new frmLoaiMatHang(currentNhanVien);
        open.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancel1ActionPerformed

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
            java.util.logging.Logger.getLogger(frmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmSanPham(currentNhanVien).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCancel1;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnReturn;
    private javax.swing.JCheckBox cbVoHieuHoa;
    private javax.swing.JComboBox<String> cbmDvt;
    private javax.swing.JComboBox<String> cmbLoai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblData;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtMaMH;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenMH;
    // End of variables declaration//GEN-END:variables
}
