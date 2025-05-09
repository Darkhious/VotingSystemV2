/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package login;

import admin.AdminForm;
import java.util.Arrays;
import javax.swing.JFrame;
import security.Hash;
import utilities.PopupWindows;
import utilities.RandomKeyGenerator;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sqlcommands.SQLConnector;
import voters.PresidentForm;
import voters.Variables;

/**
 *
 * @author Christian
 */
public class LoginForm extends javax.swing.JFrame {

    /**
     * Creates new form LoginForm
     */
    RandomKeyGenerator keyGen = new RandomKeyGenerator();
    AdminForm adminForm = new AdminForm();
    PresidentForm pres = new PresidentForm();
    
    private Connection conn;
    
    private String key = "";
    private String password = "";
    private String generatedKey = "";
    private String generatedPass = "";
    
    private void getCreds() {
        this.key = txtKey.getText();
        
        char[] rawPass = pskPass.getPassword();
        this.password = Hash.applyHash(new String(rawPass));
        Arrays.fill(rawPass, ' '); // clear sensitive data

    }
    
    private void generateCode() {
        // Generates random keys
        this.generatedKey = keyGen.randomizeKey(5);
        this.generatedPass = keyGen.randomizeKey(10);
        
        // For demo purposes only: shows the key and password
        System.out.println("KEY: " + this.generatedKey);
        System.out.println("PASSWORD: " + this.generatedPass);
        
        this.generatedPass = Hash.applyHash(this.generatedPass); // Hashes the password
    }
    
    private void updateProfiles() {
        int account_id = 0;
        
        try {
            String query = "SELECT * FROM tbl_voters";
            PreparedStatement IDStatement = conn.prepareStatement(query);
            ResultSet IDResult = IDStatement.executeQuery();
            
            while (IDResult.next()) {
                account_id = IDResult.getInt("account_id");
            
                generateCode();
                        
                String updateSql = "UPDATE tbl_voters SET voters_key = ?, password = ? WHERE account_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, this.generatedKey);
                updateStmt.setString(2, this.generatedPass);
                updateStmt.setInt(3, account_id);

                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated <= 0) {
                    PopupWindows.errorMessage("Key Generation Error - 0003", "Unable to update key codes for voters");
                }
            }
            
        } catch (SQLException e) {
            PopupWindows.errorMessage("Key Generation Error - 0002", "Unable to produce key codes for voters");
        }
    }
    
    public boolean isVoterEligible(int votersID) {
        try {
            String sql = "SELECT hasVoted FROM tbl_voters WHERE account_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, votersID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String status = rs.getString("hasVoted");
                if ("Yes".equalsIgnoreCase(status)) {
                    PopupWindows.dialogMessage("Voting Denied", "You have already voted.");
                    return false;
                } else {
                    return true;
                }
            } else {
                PopupWindows.dialogMessage("Voter Not Found", "Your voter ID does not exist.");
            }
        } catch (SQLException e) {
            PopupWindows.errorMessage("Eligibility Check Error", e.getMessage());
        }
        return false;
    }
    
    private void validateCreds() {
        getCreds();
        
        try {
            String query = "SELECT * FROM tbl_voters WHERE voters_key = ? AND password = ?";
            PreparedStatement pstm = conn.prepareStatement(query);
            
            pstm.setString(1, this.key);
            pstm.setString(2, this.password);
            
            ResultSet result = pstm.executeQuery();
            
            if (result.next()) {
                Variables.voters_id = result.getInt("account_id");
                
                if (Variables.isVoting) {
                    if (isVoterEligible(Variables.voters_id)) {
                        pres.setVisible(true);

                        this.dispose();
                    }
                } else {
                    PopupWindows.dialogMessage("Notice", "Voting has not yet started");
                }
            } else {
                if (this.key.equals("admin") && this.password.equals("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")) {
                    adminForm.setVisible(true);
                    
                    this.dispose();
                } else {
                    PopupWindows.dialogMessage("Invalid Credentials", "You may have entered the a wrong key or password");
                }
            }
            
        } catch (SQLException e) {
            PopupWindows.errorMessage("Validation Error - 0005", e.getMessage());
        }
        
    }
    
    public LoginForm() {
        initComponents();
        
        SQLConnector sqlConnector = new SQLConnector();
        
        conn = sqlConnector.connectToDB();
        
        updateProfiles();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtKey = new javax.swing.JTextField();
        pskPass = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        lblRegister = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rcs/logo.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel2.setText("Key:");

        jLabel3.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        jLabel3.setText("Password:");

        btnLogin.setFont(new java.awt.Font("Poppins", 1, 12)); // NOI18N
        btnLogin.setText("Log in");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        lblRegister.setFont(new java.awt.Font("Poppins", 2, 10)); // NOI18N
        lblRegister.setForeground(new java.awt.Color(26, 62, 189));
        lblRegister.setText("Don't have an account? Click here!");
        lblRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblRegisterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(94, 94, 94))
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegister)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pskPass, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtKey))))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(pskPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addComponent(btnLogin)
                .addGap(31, 31, 31))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void lblRegisterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblRegisterMouseClicked
        RegisterForm register = new RegisterForm();
        
        register.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        register.setVisible(true);
    }//GEN-LAST:event_lblRegisterMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        validateCreds();
    }//GEN-LAST:event_btnLoginActionPerformed

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
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblRegister;
    private javax.swing.JPasswordField pskPass;
    private javax.swing.JTextField txtKey;
    // End of variables declaration//GEN-END:variables
}
