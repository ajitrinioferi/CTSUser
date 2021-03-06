/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa.foreveryuser;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ajitrinioferi
 */
public class Restrict extends javax.swing.JFrame {

    /**
     * Creates new form Restrict
     */
    public Restrict() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        sidePanel = new javax.swing.JPanel();
        ico_Back = new javax.swing.JLabel();
        Label_title1 = new javax.swing.JLabel();
        Label_title = new javax.swing.JLabel();
        ico_restriction = new javax.swing.JLabel();
        btn_Enter = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(415, 305));
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sidePanel.setBackground(new java.awt.Color(255, 102, 0));
        sidePanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sidePanelMouseClicked(evt);
            }
        });

        ico_Back.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ico_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/icons8_back_30px.png"))); // NOI18N

        Label_title1.setBackground(new java.awt.Color(204, 204, 204));
        Label_title1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_title1.setForeground(new java.awt.Color(51, 51, 51));
        Label_title1.setText("Back");

        javax.swing.GroupLayout sidePanelLayout = new javax.swing.GroupLayout(sidePanel);
        sidePanel.setLayout(sidePanelLayout);
        sidePanelLayout.setHorizontalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ico_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Label_title1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(14, 14, 14))
        );
        sidePanelLayout.setVerticalGroup(
            sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sidePanelLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(sidePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ico_Back, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Label_title1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );

        bg.add(sidePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 140));

        Label_title.setBackground(new java.awt.Color(204, 204, 204));
        Label_title.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        Label_title.setForeground(new java.awt.Color(255, 102, 0));
        Label_title.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        Label_title.setText("Please Input Your Birthdate ");
        bg.add(Label_title, new org.netbeans.lib.awtextra.AbsoluteConstraints(141, 11, 220, 41));

        ico_restriction.setForeground(new java.awt.Color(255, 255, 255));
        ico_restriction.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ico_restriction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/icons8_age_30px_1.png"))); // NOI18N
        bg.add(ico_restriction, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 40, 41));

        btn_Enter.setBackground(new java.awt.Color(153, 255, 255));
        btn_Enter.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        btn_Enter.setForeground(new java.awt.Color(255, 102, 0));
        btn_Enter.setText("Enter");
        btn_Enter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 2, new java.awt.Color(255, 102, 0)));
        btn_Enter.setContentAreaFilled(false);
        btn_Enter.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btn_Enter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EnterActionPerformed(evt);
            }
        });
        bg.add(btn_Enter, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, 90));

        jDateChooser1.setBackground(new java.awt.Color(0, 0, 0));
        bg.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 260, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_EnterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EnterActionPerformed
        try {
            // TODO add your handling code here:
            SimpleDateFormat sdf;
            Date now = new Date();
            sdf = new SimpleDateFormat("dd-MM-yyyy");
            
            String birthDate = sdf.format(jDateChooser1.getDate());
            String nowDate = sdf.format(now);
            
            Date dateBirth = sdf.parse(birthDate);
            Date dateNow = sdf.parse(nowDate);
            
            long difference = dateNow.getTime() - dateBirth.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            int umur = (int) daysBetween/365;
            
            Ticketing y = new Ticketing(umur);
            y.setVisible(true);
            this.dispose();
        } catch (ParseException ex) {
            Logger.getLogger(Restrict.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Restrict.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_EnterActionPerformed

    private void sidePanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sidePanelMouseClicked
        // TODO add your handling code here:
        IndexForm x = new IndexForm();
        x.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_sidePanelMouseClicked

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
            java.util.logging.Logger.getLogger(Restrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Restrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Restrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Restrict.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Restrict().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Label_title;
    private javax.swing.JLabel Label_title1;
    private javax.swing.JPanel bg;
    private javax.swing.JButton btn_Enter;
    private javax.swing.JLabel ico_Back;
    private javax.swing.JLabel ico_restriction;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel sidePanel;
    // End of variables declaration//GEN-END:variables
}
