/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package voters;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import sqlcommands.SQLConnector;
import utilities.PopupWindows;

/**
 *
 * @author Christian
 */
public class VicePresidentForm extends javax.swing.JFrame {

    /**
     * Creates new form PresidentForm
     */
    Connection conn;
    
    private void displayVicePresidents() {
        DefaultTableModel model = (DefaultTableModel) tblVPres.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            String sql = "SELECT candidate_no, name, partylist FROM tbl_candidates WHERE position = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Vice President");

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int candidateNo = rs.getInt("candidate_no");
                String name = rs.getString("name");
                String partylist = rs.getString("partylist");

                model.addRow(new Object[]{candidateNo, name, partylist});
            }
        } catch (SQLException e) {
            PopupWindows.errorMessage("Candidate Request Error - 0014", e.getMessage());
        }
    }
    
    private void setTextFields() {
        txtVPresNo.setText(Variables.VPresID);
        txtVPresName.setText(Variables.VPresName);
        txtVPresPartylist.setText(Variables.VPresPartylist);
    }
    
    private void tblVPresRowClicked() {
        int row = tblVPres.getSelectedRow();
        if (row >= 0) {
            // Get values from the table (for backup or validation)
            String id = tblVPres.getValueAt(row, 0).toString();
            String name = tblVPres.getValueAt(row, 1).toString();
            String partylist = tblVPres.getValueAt(row, 2).toString();

            // Set values to the Variables class
            Variables.VPresID = id;
            Variables.VPresName = name;
            Variables.VPresPartylist = partylist;

            // Now set the text fields using the variables from the Variables class
            setTextFields();
        }
    }



    public VicePresidentForm() {
        initComponents();
        
        conn = SQLConnector.connectToDB();
        
        displayVicePresidents();
        
        setTextFields();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblVPresError = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVPres = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtVPresNo = new javax.swing.JTextField();
        txtVPresName = new javax.swing.JTextField();
        txtVPresPartylist = new javax.swing.JTextField();
        btnVPresNext = new javax.swing.JButton();
        btnVPresBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblVPresError.setFont(new java.awt.Font("Poppins", 2, 12)); // NOI18N
        lblVPresError.setForeground(new java.awt.Color(247, 71, 71));
        lblVPresError.setText("          ");

        jLabel1.setFont(new java.awt.Font("Poppins", 1, 24)); // NOI18N
        jLabel1.setText("VICE PRESIDENT");

        tblVPres.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        tblVPres.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CANDIDATE NO.", "NAME", "PARTYLIST"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblVPres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVPresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVPres);
        if (tblVPres.getColumnModel().getColumnCount() > 0) {
            tblVPres.getColumnModel().getColumn(0).setMinWidth(120);
            tblVPres.getColumnModel().getColumn(0).setPreferredWidth(120);
            tblVPres.getColumnModel().getColumn(0).setMaxWidth(120);
            tblVPres.getColumnModel().getColumn(2).setMinWidth(150);
            tblVPres.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblVPres.getColumnModel().getColumn(2).setMaxWidth(150);
        }

        jLabel2.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel2.setText("SELECTED CANDIDATE");

        jLabel3.setText("Candidate No.:");

        jLabel4.setText("Name:");

        jLabel5.setText("Partlist:");

        txtVPresNo.setEditable(false);

        txtVPresName.setEditable(false);

        txtVPresPartylist.setEditable(false);

        btnVPresNext.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnVPresNext.setText("Next");
        btnVPresNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVPresNextActionPerformed(evt);
            }
        });

        btnVPresBack.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnVPresBack.setText("Back");
        btnVPresBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVPresBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtVPresNo, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtVPresPartylist, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVPresName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(110, 110, 110)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnVPresBack, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnVPresNext, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(lblVPresError)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblVPresError))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtVPresNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtVPresName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVPresBack))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtVPresPartylist, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVPresNext))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVPresNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVPresNextActionPerformed
        SenatorsForm senator = new SenatorsForm();
        
        senator.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVPresNextActionPerformed

    private void tblVPresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVPresMouseClicked
        tblVPresRowClicked();
    }//GEN-LAST:event_tblVPresMouseClicked

    private void btnVPresBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVPresBackActionPerformed
        PresidentForm pres = new PresidentForm();
        
        pres.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnVPresBackActionPerformed

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
            java.util.logging.Logger.getLogger(VicePresidentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VicePresidentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VicePresidentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VicePresidentForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VicePresidentForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVPresBack;
    private javax.swing.JButton btnVPresNext;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblVPresError;
    private javax.swing.JTable tblVPres;
    private javax.swing.JTextField txtVPresName;
    private javax.swing.JTextField txtVPresNo;
    private javax.swing.JTextField txtVPresPartylist;
    // End of variables declaration//GEN-END:variables
}
