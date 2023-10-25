package Train.Reservation.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Dashboard extends JFrame {
    Dashboard(){

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/wallpaper.gif"));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,1920,1080);
        add(label);

        JButton reserveButton = new JButton("Book Reservation");
        reserveButton.setBounds(425, 410, 290, 60);
        reserveButton.setFont(new Font("tahoma", Font.BOLD,20));
        reserveButton.setBackground(new Color(255, 98, 0));
        reserveButton.setForeground(Color.white);
        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Reservation();
            }
        });
        label.add(reserveButton);

        JButton cancelButton = new JButton("Check Reservation");
        cancelButton.setBounds(825, 410, 290, 60);
        cancelButton.setFont(new Font("tahoma", Font.BOLD,20));
        cancelButton.setBackground(new Color(255, 98, 0));
        cancelButton.setForeground(Color.white);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new CancelReservation();
            }
        });
        label.add(cancelButton);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        JLabel dateLable = new JLabel(""+cal.getTime());
        dateLable.setBounds(1200, 0, 500, 40);
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
        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1920, 1080);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Dashboard();
    }
}
