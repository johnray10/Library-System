/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jayjomjohn
 */
public class UpdateMembers extends javax.swing.JFrame {

    Connection conn=null;
    ResultSet rs=null;
    PreparedStatement pst=null;
    DefaultTableModel model = new DefaultTableModel();
    
  
    
    public UpdateMembers() {
        initComponents();
        conn=JavaConnector.ConnectDb();
            
        ImageIcon ic = new ImageIcon(getClass().getResource("/Module/icons8_Library_100px.png"));
        this.setIconImage(ic.getImage());
        
         tblmembers();
    }
//==================================================================================================
 public void Members() {
      try{
            String sql = "SELECT * FROM members ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            Module.MainFrame.tablemembers.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){        
        }
 }            
//==================================================================================================    
 public void tblmembers() {
      try{
            String sql = "SELECT * FROM members ";
            pst=(PreparedStatement) conn.prepareStatement(sql);
            rs = pst.executeQuery();
            updatetable.setModel(net.proteanit.sql.DbUtils.resultSetToTableModel(rs));
        }
        catch(SQLException e){        
        }
 }  
//==================================================================================================
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        a4 = new javax.swing.JTextField();
        a1 = new javax.swing.JTextField();
        a2 = new javax.swing.JTextField();
        a3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        a5 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        a6 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        a8 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        a7 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        a9 = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Update Members");
        setMaximumSize(new java.awt.Dimension(985, 500));
        setMinimumSize(new java.awt.Dimension(985, 500));
        setPreferredSize(new java.awt.Dimension(985, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(985, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(java.awt.Color.lightGray, java.awt.Color.lightGray));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 990, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 30));

        updatetable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        updatetable.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        updatetable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                updatetableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(updatetable);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 950, 150));

        a4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        a4.setForeground(new java.awt.Color(51, 102, 0));
        a4.setBorder(javax.swing.BorderFactory.createTitledBorder("Contact"));
        a4.setOpaque(false);
        jPanel1.add(a4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 200, 50));

        a1.setEditable(false);
        a1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        a1.setForeground(new java.awt.Color(204, 0, 0));
        a1.setBorder(javax.swing.BorderFactory.createTitledBorder("Member ID"));
        jPanel1.add(a1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 200, 50));

        a2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        a2.setForeground(new java.awt.Color(51, 102, 0));
        a2.setBorder(javax.swing.BorderFactory.createTitledBorder("Member Name"));
        a2.setOpaque(false);
        jPanel1.add(a2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 200, 50));

        a3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        a3.setForeground(new java.awt.Color(51, 102, 0));
        a3.setBorder(javax.swing.BorderFactory.createTitledBorder("Email"));
        a3.setOpaque(false);
        jPanel1.add(a3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 200, 50));

        jLabel8.setText("Gender:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 50, 40));

        a5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=SELECT=", "Male", "Female" }));
        jPanel1.add(a5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 140, 40));

        jLabel4.setText("Date Registered:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, 100, 40));

        a6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        a6.setOpaque(false);
        jPanel1.add(a6, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 60, 210, 40));

        jLabel5.setText("Member Type");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 160, 90, 40));

        a8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=SELECT=", "Student", "Proffesor", "Teacher", "Other" }));
        jPanel1.add(a8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 160, 210, 40));

        jLabel9.setText("Birthday");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 110, 100, 40));

        a7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        a7.setOpaque(false);
        jPanel1.add(a7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 110, 210, 40));

        jLabel7.setText("Address");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, 80, 40));

        a9.setColumns(20);
        a9.setRows(5);
        jScrollPane2.setViewportView(a9);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 210, -1));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Update"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setBackground(new java.awt.Color(0, 102, 51));
        jButton1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(204, 204, 204));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Edit_Row_35px.png"))); // NOI18N
        jButton1.setText("Update");
        jButton1.setToolTipText("");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 350, -1));

        jButton2.setBackground(new java.awt.Color(153, 0, 0));
        jButton2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Delete_35px.png"))); // NOI18N
        jButton2.setText("Delete");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 350, -1));

        jButton3.setBackground(new java.awt.Color(153, 153, 153));
        jButton3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/icons8_Eraser_35px.png"))); // NOI18N
        jButton3.setText("Clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 350, 40));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 60, 390, 230));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 500));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  
      int x = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Update Record?","Update",JOptionPane.YES_NO_OPTION);     
  if(x==0){
        if(!updatetable.getSelectionModel().isSelectionEmpty()){
      try { 
       int row =updatetable.getSelectedRow();
        String value = (updatetable.getModel().getValueAt(row, 0).toString());
        String sql ="UPDATE members SET memberid=?, "
                + "membername=?, "
                + "DateRegister=?, "
                + "Email=?, "
                + "Contact=?, "
                + "Gender=?, "
                + "Membertype=?,"
                + "Birthday=?, "
                + "Address=? where memberID="+value;
          pst=conn.prepareStatement(sql);
           
            pst.setString(1,a1.getText());
            pst.setString(2,a2.getText());
            pst.setString(3, ((JTextField)a6.getDateEditor().getUiComponent()).getText());
            pst.setString(4,a3.getText());
            pst.setString(5,a4.getText());
            pst.setString(6,a5.getSelectedItem().toString());
            pst.setString(7,a8.getSelectedItem().toString());
            pst.setString(8, ((JTextField)a7.getDateEditor().getUiComponent()).getText());
            pst.setString(9,a9.getText());         
            pst.executeUpdate(); 
            model.setRowCount(0);
            Members() ;
            tblmembers();
       
            JOptionPane.showMessageDialog(null, "Members information has been Updated!");
            this.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
  }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void updatetableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_updatetableMouseClicked
       
        try{
            int row = updatetable.getSelectedRow();
            String Tableclick=(updatetable.getModel().getValueAt(row, 0).toString());
            String sql = "SELECT * FROM members WHERE MemberID='"+Tableclick+"' ";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()) { 
                a1.setText(rs.getString("MemberID"));
                a2.setText(rs.getString("MemberName"));
                a3.setText(rs.getString("Email"));
                a4.setText(rs.getString("Contact"));
                a5.setSelectedItem(rs.getString("Gender"));
               ((JTextField)a6.getDateEditor().getUiComponent()).setText(rs.getString("DateRegister"));
               ((JTextField)a7.getDateEditor().getUiComponent()).setText(rs.getString("Birthday"));
               a8.setSelectedItem(rs.getString("Membertype"));
               a9.setText(rs.getString("Address"));                
            }
            
        }catch(Exception e) {            
            JOptionPane.showMessageDialog(rootPane, e);
        }
        
    }//GEN-LAST:event_updatetableMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       
  int x = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Delete Record?","Delete",JOptionPane.YES_NO_OPTION);     
  if(x==0){
    
        String sql ="DELETE FROM members WHERE memberID=?";
        try{
              pst=conn.prepareStatement(sql);
              pst.setString(1, a1.getText());
              pst.execute();
             model.setRowCount(0);
             Members() ;
             tblmembers();
             JOptionPane.showMessageDialog(rootPane,"Members Has Been Deleted", "Deleted", 2);
              
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);

        }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      Clear();
    }//GEN-LAST:event_jButton3ActionPerformed
//==================================================================================================
public void Clear() {
    a1.setText("");
    a2.setText("");
    a3.setText("");
    a4.setText("");
    a5.setSelectedIndex(0);
    ((JTextField)a6.getDateEditor().getUiComponent()).setText("");
    ((JTextField)a7.getDateEditor().getUiComponent()).setText("");
    a8.setSelectedIndex(0);
    a9.setText("");
}    
//==================================================================================================    
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
               /* if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;*/
                   UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UpdateMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UpdateMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UpdateMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UpdateMembers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UpdateMembers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField a1;
    private javax.swing.JTextField a2;
    private javax.swing.JTextField a3;
    private javax.swing.JTextField a4;
    private javax.swing.JComboBox<String> a5;
    private com.toedter.calendar.JDateChooser a6;
    private com.toedter.calendar.JDateChooser a7;
    private javax.swing.JComboBox<String> a8;
    private javax.swing.JTextArea a9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static final javax.swing.JTable updatetable = new javax.swing.JTable();
    // End of variables declaration//GEN-END:variables
}