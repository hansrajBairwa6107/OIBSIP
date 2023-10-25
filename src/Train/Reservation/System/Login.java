package Train.Reservation.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Login extends JFrame {
    JButton login;
    JButton createAccount;
    Login(){

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/wallpaper.gif")); // add icon
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,1920,1080);
        add(label);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,1920,1080);
        panel.setSize(1920,1080);
        add(panel);

        JLabel label1 = new JLabel("National Ticket Booking System");
        label1.setBounds(450,0,700,200);
        label1.setFont(new Font("Tahoma",Font.BOLD,40));
        label1.setForeground(Color.GREEN);
        label.add(label1);

        java.util.Date date = new Date();
        Calendar cal = Calendar.getInstance();
        JLabel dateLable = new JLabel(""+cal.getTime());
        dateLable.setBounds(1200, 8, 500, 50);
        dateLable.setFont(new Font("Tahoma", Font.BOLD, 15));
        dateLable.setForeground(Color.WHITE);
        label.add(dateLable);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Getting the current time and formatted it
                Calendar cal = Calendar.getInstance();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentTime = sdf.format(cal.getTime());

                // Updating the JLabel with the current time
                dateLable.setText(currentTime);
            }
        });
        timer.start();

        JPanel panel2 = new JPanel();
        panel2.setLayout(null);
        panel2.setBounds(380, 300,700,290);
        panel2.setBackground(Color.DARK_GRAY);
        label.add(panel2);

        JLabel userName = new JLabel("UserName");
        userName.setBounds(40,30,150,30);
        userName.setFont(new Font("Tahoma", Font.BOLD,20));
        userName.setForeground(Color.white);
        panel2.add(userName);

        JLabel password = new JLabel("Password");
        password.setBounds(40,100,150,30);
        password.setFont(new Font("Tahoma", Font.BOLD,20));
        password.setForeground(Color.white);
        panel2.add(password);

        JLabel doNotHaveAccount = new JLabel("Don't have an account?");
        doNotHaveAccount.setBounds(70,195,150,30);
        doNotHaveAccount.setFont(new Font("Tahoma", Font.BOLD,10));
        doNotHaveAccount.setForeground(Color.white);
        panel2.add(doNotHaveAccount);

        JTextField userField = new JTextField();
        userField.setBounds(250,30,260,30);
        userField.setForeground(Color.white);
        userField.setFont(new Font("Tahoma", Font.PLAIN,18));
        userField.setBackground(new Color(26,104,110));
        panel2.add(userField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(250,100,260,30);
        passwordField.setForeground(Color.white);
        passwordField.setFont(new Font("Tahoma", Font.PLAIN,15));
        passwordField.setBackground(new Color(26,104,110));
        panel2.add(passwordField);

        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/boss.png"));
        Image i2=i11.getImage().getScaledInstance(200,195, Image.SCALE_DEFAULT);
        ImageIcon imageIcon11 = new ImageIcon(i2);
        JLabel label2 = new JLabel(imageIcon11);
        label2.setBounds(1150,350,200,195);
        label.add(label2);

        JButton create_account = new JButton("Create Account");
        create_account.setBounds(40,220,200,30);
        create_account.setBackground(Color.BLACK);
        create_account.setForeground(Color.WHITE);
        panel2.add(create_account);
        create_account.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection con=null;
                PreparedStatement ps = null;
                try {
                    String username = userField.getText();
                    String password = new String(passwordField.getPassword());

                    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trainReservation","root","root");
                    String query = "INSERT INTO login (username, password) VALUES (?, ?)";
                    ps=con.prepareStatement(query);
                    ps.setString(1,username);
                    ps.setString(2,password);
                    int i=ps.executeUpdate();
                    String s = i > 0 ? "Success" : "Failed";

                    if (username.isEmpty()||password.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Please enter a username and password properly");
                        return;
                    } else if (password.length()<8){
                        JOptionPane.showMessageDialog(null,"Password must be at least 8 characters");
                        return;
                    }
                    if (s.equals("Success")){
                        JOptionPane.showMessageDialog(null, "Account created successfully");
                        userField.setText("");
                        passwordField.setText("");
                    } else if (s.equals("Failed")) {
                        JOptionPane.showMessageDialog(null, "Account creation failed");
                    }

                }catch (SQLException ee){
                    ee.printStackTrace();
                }
            }
        });

        JButton login = new JButton("Login");
        login.setBounds(400,220,200,30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        panel2.add(login);
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection connection = null;
                PreparedStatement preparedStatement = null;
                ResultSet resultSet = null;
                try {
                    String username = userField.getText();
                    String password = new String(passwordField.getPassword());

                    if (username.isEmpty()||password.isEmpty()){
                        JOptionPane.showMessageDialog(null,"Please enter a username and password properly");
                        return;
                    } else if (password.length()<8){
                        JOptionPane.showMessageDialog(null,"Password must be at least 8 characters");
                        return;
                    }

                    connection = DriverManager.getConnection("jdbc:mysql://localhost/trainReservation", "root", "root");
                    String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                    preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);
                    resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()){
                        setVisible(false);
                        new Dashboard();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"Login Failed");
                        JOptionPane.showMessageDialog(null,"Please enter correct username and password");
                    }

                }catch (SQLException ee) {
                    ee.printStackTrace();
                }
            }
        });
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setSize(1920,1080);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Login();
    }
}
