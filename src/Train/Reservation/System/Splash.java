package Train.Reservation.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Splash extends JFrame {
    Splash(){

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/train.jpg"));
        JLabel label1 = new JLabel(imageIcon); // we cannot directly use images or vid that's why we are using JLable
        label1.setBounds(0,0,1300,723);
        add(label1);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(0,0,1920,1080);
        panel.setSize(1920,1080);
        add(panel);

        JLabel label = new JLabel("ONLINE RESERVATION SYSTEM");
        label.setBounds(800, 730, 1000, 50); // Adjust the position and size of the label
        label.setFont(new Font("Tahoma", Font.BOLD, 40));
        label.setBackground(Color.BLACK);
        panel.add(label);

        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        JLabel dateLable = new JLabel(""+cal.getTime());
        dateLable.setBounds(1000, 0, 500, 50); // Adjust the position and size of the label
        dateLable.setFont(new Font("Tahoma", Font.BOLD, 15));
        dateLable.setBackground(Color.BLACK);

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
        label1.add(dateLable);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setSize(1920,1080);
        setVisible(true);

        try {
            Thread.sleep(3000);
            new Login();
            setVisible(false);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        new Splash();
    }
}
