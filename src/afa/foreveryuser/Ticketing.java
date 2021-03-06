/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package afa.foreveryuser;

import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.prompt.PromptSupport;

/**
 *
 * @author ajitrinioferi
 */
public class Ticketing extends javax.swing.JFrame {

    private int umur;
    private String scheduleid;

    /**
     * Creates new form Ticketing
     */
    public Ticketing(int umur) throws SQLException {
        initComponents();
        this.umur = umur;
        GenerateFilmName(umur);
        PromptSupport.setPrompt("            Click here, and scan your card", TextField_CardNumber);

    }

    private void GenerateFilmName(int umur) throws SQLException {
        String qry = "SELECT DISTINCT\n"
                + "vmovieschedule.Title\n"
                + "FROM\n"
                + "vmovieschedule\n"
                + "WHERE\n"
                + "vmovieschedule.SeatsAvailable > 0 AND\n"
                + "TIMESTAMP(`MovieDate`,`Showtimes`) > NOW() AND\n"
                + "vmovieschedule.AgeRestriction <= " + Integer.toString(umur) + "";
        ResultSet rs = AFAUtils.Select(qry);
        while (rs.next()) {
            ComboBox_Movie.addItem(rs.getString("Title"));
        }
    }

    public <T> List<T> fromArrayToList(T[] a) {
        return Arrays.stream(a).collect(Collectors.toList());
    }

    private List<String> stringToList(String str) {
        String str_arr[] = str.split(",");
        List<String> al = new ArrayList<String>();
        al = fromArrayToList(str_arr);
        return al;
    }

    private String GenerateTransaction(String scheduleID, boolean print) throws SQLException {

        String generatedColumns[] = {"TransID"};
        Transaction trans_data = new Transaction();
        Random rnd = new Random();
        int pin = rnd.nextInt(999999);
        // Set Seat Data
        List<String> al = new ArrayList<String>();
        al = stringToList(TextField_SeatSelected.getText());
        trans_data.setSeat(al);
        // Set Schedule ID, Ticket Status, Quantity, CardID, Total price
        trans_data.setScheduleid(scheduleID);
        trans_data.setTicketstatus(print);
        trans_data.setQty(trans_data.getSeat().size());
        trans_data.setCardnumber(Integer.parseInt(TextField_CardNumber.getText()));
        trans_data.setTotalprice(Integer.parseInt(TextField_TotalPrice.getText()));

        // Insert Transaction
        String query = " insert into transactions (ScheduleID, CardID, Quantity, TotalPrice, PIN )"
                + " values (?, ?, ?, ?, ?)";
        PreparedStatement preparedStmt = AFAUtils.conn.prepareStatement(query, generatedColumns);
        preparedStmt.setString(1, trans_data.getScheduleid());
        preparedStmt.setInt(2, trans_data.getCardnumber());
        preparedStmt.setInt(3, trans_data.getQty());
        preparedStmt.setInt(4, trans_data.getTotalprice());
        preparedStmt.setInt(5, pin);

        preparedStmt.execute();
        ResultSet rs = preparedStmt.getGeneratedKeys();
        if (rs.next()) {
            // Set Trans ID
            trans_data.setTransid(rs.getInt(1));
        }

        // Insert Ticket
        for (int i = 0; i < trans_data.getQty(); i++) {
            //System.out.println(al.get(i));
            query = "insert into tickets (TransID, ScheduleID, SeatNo)" + " values (?, ?, ?)";
            preparedStmt = AFAUtils.conn.prepareStatement(query);
            preparedStmt.setInt(1, trans_data.getTransid());
            preparedStmt.setString(2, trans_data.getScheduleid());
            preparedStmt.setString(3, trans_data.getSeat().get(i));
            preparedStmt.execute();

        }
        TransactionReceipt printTransaction = new TransactionReceipt();
        printTransaction.GenerateReceipt(String.valueOf(trans_data.getTransid()));
        if (trans_data.getTicketstatus()) {
            TicketReceipt printTicketReceipt = new TicketReceipt();
            printTicketReceipt.GenerateReceipt(String.valueOf(trans_data.getTransid()));
        }
        return String.valueOf(trans_data.getTransid());

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
        layouty = new javax.swing.JLabel();
        layoutx = new javax.swing.JLabel();
        ico_Back = new javax.swing.JLabel();
        Label_SelectMovie = new javax.swing.JLabel();
        ComboBox_Movie = new javax.swing.JComboBox<>();
        Label_Showtime = new javax.swing.JLabel();
        ComboBox_MovieDate = new javax.swing.JComboBox<>();
        Label_SeatSelected = new javax.swing.JLabel();
        TextField_Price = new javax.swing.JTextField();
        Label_TotalPrice = new javax.swing.JLabel();
        TextField_TotalPrice = new javax.swing.JTextField();
        Label_Balance = new javax.swing.JLabel();
        TextField_Balance = new javax.swing.JTextField();
        Label_CardNumber = new javax.swing.JLabel();
        TextField_CardNumber = new javax.swing.JTextField();
        Label_CardStatus = new javax.swing.JLabel();
        TextField_CardStatus = new javax.swing.JTextField();
        SelectSeatpanel = new javax.swing.JPanel();
        btn_Seat = new javax.swing.JButton();
        Label_Select = new javax.swing.JLabel();
        confirmpaymentpanel = new javax.swing.JPanel();
        btn_print = new javax.swing.JButton();
        Label_Confirm = new javax.swing.JLabel();
        checkbox1 = new java.awt.Checkbox();
        ComboBox_Showtime1 = new javax.swing.JComboBox<>();
        Label_Showtime2 = new javax.swing.JLabel();
        TextField_SeatSelected = new javax.swing.JTextField();
        TextField_SeatLeft = new javax.swing.JTextField();
        jXImageView1 = new org.jdesktop.swingx.JXImageView();
        Label_SeatSelected1 = new javax.swing.JLabel();
        Label_SeatSelected2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(348, 134));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(0, 0, 0));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        layouty.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        layouty.setForeground(new java.awt.Color(153, 153, 153));
        bg.add(layouty, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 300, 10, 20));

        layoutx.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        layoutx.setForeground(new java.awt.Color(153, 153, 153));
        bg.add(layoutx, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 480, 10, 20));

        ico_Back.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ico_Back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/icons8_back_52px.png"))); // NOI18N
        ico_Back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ico_BackMouseClicked(evt);
            }
        });
        bg.add(ico_Back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 450, 40, 41));

        Label_SelectMovie.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_SelectMovie.setForeground(new java.awt.Color(255, 102, 0));
        Label_SelectMovie.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_SelectMovie.setText("Select Movie");
        bg.add(Label_SelectMovie, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 10, 240, -1));

        ComboBox_Movie.setBackground(new java.awt.Color(0, 0, 0));
        ComboBox_Movie.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ComboBox_Movie.setMaximumRowCount(5);
        ComboBox_Movie.setToolTipText("");
        ComboBox_Movie.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 2, new java.awt.Color(0, 0, 0)));
        ComboBox_Movie.setOpaque(false);
        ComboBox_Movie.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBox_MovieItemStateChanged(evt);
            }
        });
        bg.add(ComboBox_Movie, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 30, 240, 27));

        Label_Showtime.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_Showtime.setForeground(new java.awt.Color(255, 102, 0));
        Label_Showtime.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Showtime.setText("Movie Date");
        bg.add(Label_Showtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(311, 70, 240, -1));

        ComboBox_MovieDate.setBackground(new java.awt.Color(0, 0, 0));
        ComboBox_MovieDate.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ComboBox_MovieDate.setMaximumRowCount(5);
        ComboBox_MovieDate.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 2, new java.awt.Color(0, 0, 0)));
        ComboBox_MovieDate.setOpaque(false);
        ComboBox_MovieDate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBox_MovieDateItemStateChanged(evt);
            }
        });
        bg.add(ComboBox_MovieDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 90, 240, 27));

        Label_SeatSelected.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_SeatSelected.setForeground(new java.awt.Color(255, 102, 0));
        Label_SeatSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_SeatSelected.setText("Seat left");
        bg.add(Label_SeatSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 190, 80, -1));

        TextField_Price.setEditable(false);
        TextField_Price.setBackground(new java.awt.Color(0, 0, 0));
        TextField_Price.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextField_Price.setForeground(new java.awt.Color(255, 255, 255));
        TextField_Price.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 102, 0)));
        TextField_Price.setOpaque(false);
        bg.add(TextField_Price, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 210, 80, 30));

        Label_TotalPrice.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_TotalPrice.setForeground(new java.awt.Color(255, 102, 0));
        Label_TotalPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_TotalPrice.setText("Card Balance");
        bg.add(Label_TotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, 240, -1));

        TextField_TotalPrice.setEditable(false);
        TextField_TotalPrice.setBackground(new java.awt.Color(0, 0, 0));
        TextField_TotalPrice.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextField_TotalPrice.setForeground(new java.awt.Color(255, 255, 255));
        TextField_TotalPrice.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 102, 0)));
        TextField_TotalPrice.setOpaque(false);
        bg.add(TextField_TotalPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, 240, 30));

        Label_Balance.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_Balance.setForeground(new java.awt.Color(255, 102, 0));
        Label_Balance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Balance.setText("Total Price");
        bg.add(Label_Balance, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 390, 240, -1));

        TextField_Balance.setEditable(false);
        TextField_Balance.setBackground(new java.awt.Color(0, 0, 0));
        TextField_Balance.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextField_Balance.setForeground(new java.awt.Color(255, 255, 255));
        TextField_Balance.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 102, 0)));
        TextField_Balance.setOpaque(false);
        bg.add(TextField_Balance, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 350, 240, 30));

        Label_CardNumber.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_CardNumber.setForeground(new java.awt.Color(255, 102, 0));
        Label_CardNumber.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_CardNumber.setText("Card Number");
        bg.add(Label_CardNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 330, 270, -1));

        TextField_CardNumber.setBackground(new java.awt.Color(0, 0, 0));
        TextField_CardNumber.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextField_CardNumber.setForeground(new java.awt.Color(255, 255, 255));
        TextField_CardNumber.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 102, 0)));
        TextField_CardNumber.setOpaque(false);
        TextField_CardNumber.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextField_CardNumberActionPerformed(evt);
            }
        });
        bg.add(TextField_CardNumber, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 270, 30));

        Label_CardStatus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_CardStatus.setForeground(new java.awt.Color(255, 102, 0));
        Label_CardStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_CardStatus.setText("Card Status");
        bg.add(Label_CardStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 270, -1));

        TextField_CardStatus.setEditable(false);
        TextField_CardStatus.setBackground(new java.awt.Color(0, 0, 0));
        TextField_CardStatus.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextField_CardStatus.setForeground(new java.awt.Color(255, 255, 255));
        TextField_CardStatus.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 102, 0)));
        TextField_CardStatus.setOpaque(false);
        bg.add(TextField_CardStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 270, 30));

        SelectSeatpanel.setBackground(new java.awt.Color(255, 102, 0));
        SelectSeatpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Seat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/icons8_sleeper_chair_52px_1.png"))); // NOI18N
        btn_Seat.setContentAreaFilled(false);
        btn_Seat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_SeatActionPerformed(evt);
            }
        });
        SelectSeatpanel.add(btn_Seat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 109, 70, -1));

        Label_Select.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Label_Select.setForeground(new java.awt.Color(51, 51, 51));
        Label_Select.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Select.setText("SELECT");
        SelectSeatpanel.add(Label_Select, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 176, 60, -1));

        bg.add(SelectSeatpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 11, 70, 315));

        confirmpaymentpanel.setBackground(new java.awt.Color(255, 102, 0));
        confirmpaymentpanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/user/icons8_print_52px_2.png"))); // NOI18N
        btn_print.setContentAreaFilled(false);
        btn_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_printActionPerformed(evt);
            }
        });
        confirmpaymentpanel.add(btn_print, new org.netbeans.lib.awtextra.AbsoluteConstraints(5, 11, 60, -1));

        Label_Confirm.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        Label_Confirm.setForeground(new java.awt.Color(51, 51, 51));
        Label_Confirm.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Confirm.setText("CONFIRM");
        confirmpaymentpanel.add(Label_Confirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 78, 60, -1));

        checkbox1.setFont(new java.awt.Font("Century Gothic", 1, 12)); // NOI18N
        checkbox1.setForeground(new java.awt.Color(51, 51, 51));
        checkbox1.setLabel(" PRINT");
        confirmpaymentpanel.add(checkbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 60, -1));

        bg.add(confirmpaymentpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 344, 70, 140));

        ComboBox_Showtime1.setBackground(new java.awt.Color(0, 0, 0));
        ComboBox_Showtime1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        ComboBox_Showtime1.setMaximumRowCount(5);
        ComboBox_Showtime1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 2, new java.awt.Color(0, 0, 0)));
        ComboBox_Showtime1.setOpaque(false);
        ComboBox_Showtime1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBox_Showtime1ItemStateChanged(evt);
            }
        });
        bg.add(ComboBox_Showtime1, new org.netbeans.lib.awtextra.AbsoluteConstraints(314, 150, 240, 27));

        Label_Showtime2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_Showtime2.setForeground(new java.awt.Color(255, 102, 0));
        Label_Showtime2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_Showtime2.setText("Showtime");
        bg.add(Label_Showtime2, new org.netbeans.lib.awtextra.AbsoluteConstraints(309, 130, 240, -1));

        TextField_SeatSelected.setEditable(false);
        TextField_SeatSelected.setBackground(new java.awt.Color(0, 0, 0));
        TextField_SeatSelected.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextField_SeatSelected.setForeground(new java.awt.Color(255, 255, 255));
        TextField_SeatSelected.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 102, 0)));
        TextField_SeatSelected.setOpaque(false);
        bg.add(TextField_SeatSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 240, 30));

        TextField_SeatLeft.setEditable(false);
        TextField_SeatLeft.setBackground(new java.awt.Color(0, 0, 0));
        TextField_SeatLeft.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        TextField_SeatLeft.setForeground(new java.awt.Color(255, 255, 255));
        TextField_SeatLeft.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(255, 102, 0)));
        TextField_SeatLeft.setOpaque(false);
        bg.add(TextField_SeatLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 210, 80, 30));

        javax.swing.GroupLayout jXImageView1Layout = new javax.swing.GroupLayout(jXImageView1);
        jXImageView1.setLayout(jXImageView1Layout);
        jXImageView1Layout.setHorizontalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jXImageView1Layout.setVerticalGroup(
            jXImageView1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );

        bg.add(jXImageView1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 270, 290));

        Label_SeatSelected1.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_SeatSelected1.setForeground(new java.awt.Color(255, 102, 0));
        Label_SeatSelected1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_SeatSelected1.setText("Selected Seat");
        bg.add(Label_SeatSelected1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, 180, -1));

        Label_SeatSelected2.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        Label_SeatSelected2.setForeground(new java.awt.Color(255, 102, 0));
        Label_SeatSelected2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Label_SeatSelected2.setText("Price");
        bg.add(Label_SeatSelected2, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 80, -1));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 1, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_printActionPerformed
        // TODO add your handling code here:
        int affectedRows = 0;
        List<String> al = new ArrayList<String>();
        al = stringToList(TextField_SeatSelected.getText());
        String query_seat = "";
        for (int counter = 0; counter < al.size(); counter++) {
            if (al.size() - counter == 1) {
                query_seat += String.format("SeatNo = '%s' ", al.get(counter));
            } else {
                query_seat += String.format("SeatNo = '%s' OR ", al.get(counter));
            }
        }
        String query = "SELECT COUNT(*) AS Res FROM `tickets` WHERE ScheduleID='" + scheduleid + "' AND (" + query_seat + ");";
        try {
            ResultSet rs = AFAUtils.Select(query);
            while (rs.next()) {
                affectedRows = rs.getInt("Res");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        }
        String trxid;
        if ((TextField_SeatSelected.getText().equals("")
                && TextField_CardStatus.getText().equals("Inactive")
                && Integer.parseInt(TextField_Balance.getText()) < Integer.parseInt(TextField_TotalPrice.getText()))
                || affectedRows > 0) {
            JOptionPane.showMessageDialog(null,
                    "Something went wrong \n" + "Please check your seat first, or check your card data or balance",
                    "Error",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                if (checkbox1.getState()) {
                    trxid = GenerateTransaction(scheduleid, true);
                } else {
                    trxid = GenerateTransaction(scheduleid, false);
                }
                JOptionPane.showMessageDialog(null,
                        "Transaction success, your transaction ID is : " + trxid,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                IndexForm index = new IndexForm();
                index.setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btn_printActionPerformed

    private void ComboBox_MovieDateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBox_MovieDateItemStateChanged
        ComboBox_Showtime1.removeAllItems();
        try {
            // TODO add your handling code here:
            String qry = "SELECT DISTINCT\n"
                    + "vmovieschedule.Price,\n"
                    + "vmovieschedule.Showtimes\n"
                    + "FROM\n"
                    + "vmovieschedule\n"
                    + "WHERE\n"
                    + "vmovieschedule.MovieDate = '" + ComboBox_MovieDate.getSelectedItem() + "' AND\n"
                    + "vmovieschedule.Title = '" + ComboBox_Movie.getSelectedItem() + "' AND\n"
                    + "TIMESTAMP(`MovieDate`,`Showtimes`) > NOW()";
            ResultSet rs = AFAUtils.Select(qry);
            while (rs.next()) {
                ComboBox_Showtime1.addItem(rs.getString("Showtimes"));
                TextField_Price.setText(rs.getString("Price"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ComboBox_MovieDateItemStateChanged

    private void ComboBox_Showtime1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBox_Showtime1ItemStateChanged
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String qry = "SELECT\n"
                    + "vmovieschedule.SeatsAvailable\n"
                    + "FROM\n"
                    + "vmovieschedule\n"
                    + "WHERE\n"
                    + "vmovieschedule.MovieDate = '" + ComboBox_MovieDate.getSelectedItem() + "' AND\n"
                    + "vmovieschedule.Showtimes = '" + ComboBox_Showtime1.getSelectedItem() + "'";
            ResultSet rs = AFAUtils.Select(qry);
            while (rs.next()) {
                TextField_SeatLeft.setText(rs.getString("SeatsAvailable") + " Seat Left");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_ComboBox_Showtime1ItemStateChanged

    private void ComboBox_MovieItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBox_MovieItemStateChanged
        // TODO add your handling code here:
        ComboBox_Showtime1.removeAllItems();
        ComboBox_MovieDate.removeAllItems();
        try {
            String qry = "SELECT DISTINCT\n"
                    + "vmovieschedule.MovieDate\n"
                    + "FROM\n"
                    + "vmovieschedule\n"
                    + "WHERE\n"
                    + "vmovieschedule.Title = '" + ComboBox_Movie.getSelectedItem() + "' AND\n"
                    + "TIMESTAMP(`MovieDate`,`Showtimes`) > NOW()";
            ResultSet rs = AFAUtils.Select(qry);
            while (rs.next()) {
                ComboBox_MovieDate.addItem(rs.getString("MovieDate"));
            }
            File file = new File("src/resources/thumbnails/" + ComboBox_Movie.getSelectedItem() + ".jpg");
            jXImageView1.setImage(file);
        } catch (IOException ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_ComboBox_MovieItemStateChanged

    private void TextField_CardNumberActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextField_CardNumberActionPerformed
        try {
            // TODO add your handling code here:
            String qry = "SELECT\n"
                    + "carddata.CardID,\n"
                    + "carddata.CardStatus,\n"
                    + "carddata.Balance\n"
                    + "FROM\n"
                    + "carddata\n"
                    + "WHERE\n"
                    + "carddata.CardID = '" + TextField_CardNumber.getText() + "'";
            ResultSet rs = AFAUtils.Select(qry);
            while (rs.next()) {
                TextField_CardStatus.setText(rs.getString("CardStatus"));
                TextField_Balance.setText(rs.getString("Balance"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TextField_CardNumberActionPerformed

    private void btn_SeatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_SeatActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            String qry = "SELECT\n"
                    + "vmovieschedule.ScheduleID\n"
                    + "FROM\n"
                    + "vmovieschedule\n"
                    + "WHERE\n"
                    + "vmovieschedule.Title = '" + ComboBox_Movie.getSelectedItem() + "' AND\n"
                    + "vmovieschedule.MovieDate = '" + ComboBox_MovieDate.getSelectedItem() + "' AND\n"
                    + "vmovieschedule.Showtimes = '" + ComboBox_Showtime1.getSelectedItem() + "'\n"
                    + "ORDER BY\n"
                    + "vmovieschedule.SeatsAvailable ASC\n"
                    + "LIMIT 1";
            ResultSet rs = AFAUtils.Select(qry);
            while (rs.next()) {
                scheduleid = rs.getString("ScheduleID");
            }
            SelectSeat ss = new SelectSeat(this, scheduleid);
            ss.setVisible(true);
            this.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(Ticketing.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_SeatActionPerformed

    private void ico_BackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ico_BackMouseClicked
        // TODO add your handling code here:
        this.dispose();
        Restrict back = new Restrict();
        back.setVisible(true);
    }//GEN-LAST:event_ico_BackMouseClicked

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
            java.util.logging.Logger.getLogger(Ticketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ticketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ticketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ticketing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> ComboBox_Movie;
    private javax.swing.JComboBox<String> ComboBox_MovieDate;
    private javax.swing.JComboBox<String> ComboBox_Showtime1;
    private javax.swing.JLabel Label_Balance;
    private javax.swing.JLabel Label_CardNumber;
    private javax.swing.JLabel Label_CardStatus;
    private javax.swing.JLabel Label_Confirm;
    private javax.swing.JLabel Label_SeatSelected;
    private javax.swing.JLabel Label_SeatSelected1;
    private javax.swing.JLabel Label_SeatSelected2;
    private javax.swing.JLabel Label_Select;
    private javax.swing.JLabel Label_SelectMovie;
    private javax.swing.JLabel Label_Showtime;
    private javax.swing.JLabel Label_Showtime2;
    private javax.swing.JLabel Label_TotalPrice;
    private javax.swing.JPanel SelectSeatpanel;
    private javax.swing.JTextField TextField_Balance;
    private javax.swing.JTextField TextField_CardNumber;
    private javax.swing.JTextField TextField_CardStatus;
    public javax.swing.JTextField TextField_Price;
    public javax.swing.JTextField TextField_SeatLeft;
    public javax.swing.JTextField TextField_SeatSelected;
    public javax.swing.JTextField TextField_TotalPrice;
    private javax.swing.JPanel bg;
    private javax.swing.JButton btn_Seat;
    private javax.swing.JButton btn_print;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JPanel confirmpaymentpanel;
    private javax.swing.JLabel ico_Back;
    private org.jdesktop.swingx.JXImageView jXImageView1;
    private javax.swing.JLabel layoutx;
    private javax.swing.JLabel layouty;
    // End of variables declaration//GEN-END:variables
}
