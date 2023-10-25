package Train.Reservation.System;

import javax.swing.*;
import java.awt.*;

public class FirstPage extends JFrame{
    FirstPage(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/wallpaper.gif"));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,1920,1080);
        add(label);

        JLabel name = new JLabel("Hansraj Bairwa");
        name.setFont(new java.awt.Font("serif", Font.BOLD, 60));
        name.setBounds(600, 300, 500, 85);
        name.setForeground(Color.white);
        label.add(name);

        setLayout(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1920, 1080);
        setVisible(true);

        try {
            Thread.sleep(3000);
            setVisible(false);
            new Splash();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FirstPage();
    }
}
