package Train.Reservation.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class CancelReservation extends JFrame implements ActionListener{
    JTextField pnrField;
    JTextField passengerNameField;
    JTextField passengerAadharField;
    JTextField trainNumberField;
    JTextField trainNameField;
    JTextField classTypeField;
    JTextField dateOfJourneyField;
    JTextField fromPlaceField;
    JTextField toDestinationField;
    JTextField priceField;
    JButton checkButton;
    JButton backButton;
    JButton cancelButton;
    int pnr;
    CancelReservation(){
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/wallpaper.gif"));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,1920,1080);
        add(label);

        JLabel reservationForm = new JLabel("Check Reservation");
        reservationForm.setFont(new java.awt.Font("serif", Font.BOLD, 40));
        reservationForm.setBounds(600, 30, 350, 35);
        reservationForm.setForeground(Color.white);
        label.add(reservationForm);

        JLabel pnrName = new JLabel("Enter PNR Number :");
        pnrName.setFont(new java.awt.Font("serif", Font.BOLD, 24));
        pnrName.setBounds(600, 140, 220, 27);
        pnrName.setForeground(Color.white);
        label.add(pnrName);
        pnrField = new JTextField();
        pnrField.setBounds(840, 140, 150, 27);
        pnrField.setBackground(new Color(16,108,115));
        pnrField.setForeground(Color.white);
        pnrField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(pnrField);

        checkButton = new JButton("Check");
        checkButton.setBounds(1020, 140, 100, 25);
        checkButton.setBackground(Color.BLACK);
        checkButton.setForeground(Color.white);
        checkButton.setFont(new Font("Tahoma", Font.PLAIN, 17));
        checkButton.addActionListener(this);
        label.add(checkButton);

        JLabel name = new JLabel("Name :");
        name.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        name.setBounds(350, 220, 150, 27);
        name.setForeground(Color.white);
        label.add(name);
        passengerNameField = new JTextField();
        passengerNameField.setBounds(470, 220, 200, 27);
        passengerNameField.setEditable(false);
        passengerNameField.setBackground(new Color(16,108,115));
        passengerNameField.setForeground(Color.white);
        passengerNameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(passengerNameField);

        JLabel aadhar = new JLabel("Aadhar :");
        aadhar.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        aadhar.setBounds(850, 220, 220, 27);
        aadhar.setForeground(Color.white);
        label.add(aadhar);
        passengerAadharField = new JTextField();
        passengerAadharField.setBounds(1000, 220, 200, 27);
        passengerAadharField.setEditable(false);
        passengerAadharField.setBackground(new Color(16,108,115));
        passengerAadharField.setForeground(Color.white);
        passengerAadharField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(passengerAadharField);

        JLabel trainNumber = new JLabel("Train No. :");
        trainNumber.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        trainNumber.setBounds(350, 310, 150, 27);
        trainNumber.setForeground(Color.white);
        label.add(trainNumber);

        trainNumberField = new JTextField();
        trainNumberField.setBounds(470, 310, 200, 27);
        trainNumberField.setEditable(false);
        trainNumberField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        trainNumberField.setForeground(Color.WHITE);
        trainNumberField.setBackground(new Color(16, 108, 115));
        label.add(trainNumberField);

        JLabel trainName = new JLabel("Train Name :");
        trainName.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        trainName.setBounds(850, 310, 150, 27);
        trainName.setForeground(Color.white);
        label.add(trainName);

        trainNameField = new JTextField();
        trainNameField.setBounds(1000, 310, 195, 27);
        trainNameField.setEditable(false);
        trainNameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        trainNameField.setForeground(Color.WHITE);
        trainNameField.setBackground(new Color(16, 108, 115));
        label.add(trainNameField);

        JLabel classType = new JLabel("Class Type :");
        classType.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        classType.setBounds(350, 400, 150, 27);
        classType.setForeground(Color.white);
        label.add(classType);

        classTypeField = new JTextField();
        classTypeField.setBounds(470, 400, 195, 27);
        classTypeField.setEditable(false);
        classTypeField.setFont(new Font("Tahoma", Font.PLAIN, 14));
        classTypeField.setForeground(Color.WHITE);
        classTypeField.setBackground(new Color(16, 108, 115));
        label.add(classTypeField);

        JLabel dateOfJourney = new JLabel("Date Of Journey :");
        dateOfJourney.setFont(new Font("serif", Font.BOLD, 19));
        dateOfJourney.setBounds(850, 400, 160, 27);
        dateOfJourney.setForeground(Color.white);
        label.add(dateOfJourney);

        dateOfJourneyField = new JTextField();
        dateOfJourneyField.setFont(new Font("serif", Font.BOLD, 15));
        dateOfJourneyField.setEditable(false);
        dateOfJourneyField.setBounds(1000, 400, 200, 27);
        dateOfJourneyField.setBackground(new Color(16,108,115));
        dateOfJourneyField.setForeground(Color.white);
        label.add(dateOfJourneyField);

        JLabel fromPlace = new JLabel("From Place :");
        fromPlace.setFont(new Font("serif", Font.BOLD, 20));
        fromPlace.setBounds(350, 490, 150, 27);
        fromPlace.setForeground(Color.white);
        label.add(fromPlace);

        fromPlaceField = new JTextField();
        fromPlaceField.setBounds(470, 490, 200, 27);
        fromPlaceField.setEditable(false);
        fromPlaceField.setBackground(new Color(16,108,115));
        fromPlaceField.setForeground(Color.white);
        fromPlaceField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(fromPlaceField);

        JLabel toDestination = new JLabel("To Destination :");
        toDestination.setFont(new Font("serif", Font.BOLD, 20));
        toDestination.setBounds(850, 490, 150, 27);
        toDestination.setForeground(Color.white);
        label.add(toDestination);

        toDestinationField = new JTextField();
        toDestinationField.setBounds(1000, 490, 200, 27);
        toDestinationField.setEditable(false);
        toDestinationField.setBackground(new Color(16,108,115));
        toDestinationField.setForeground(Color.white);
        toDestinationField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(toDestinationField);

        JLabel priceLabel = new JLabel("Fare Price:");
        priceLabel.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        priceLabel.setBounds(850, 580, 100, 27);
        priceLabel.setForeground(Color.white);
        label.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(1000, 580, 200, 27);
        priceField.setEditable(false);
        priceField.setBackground(new Color(16, 108, 115));
        priceField.setForeground(Color.WHITE);
        priceField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(priceField);


        backButton = new JButton("Back");
        backButton.setBounds(550, 700, 200, 35);
        backButton.setFont(new Font("Tahoma",Font.BOLD,14));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.BLACK);
        label.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Dashboard();
            }
        });

        cancelButton = new JButton("Cancel Reservation");
        cancelButton.setBounds(800, 700, 200, 35);
        cancelButton.setFont(new Font("Tahoma",Font.BOLD,14));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setBackground(Color.BLACK);
        label.add(cancelButton);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Con con = new Con();
                    if (JOptionPane.showConfirmDialog(null,"Do you want to cancel reservation?") == JOptionPane.OK_OPTION){
                        pnr =  Integer.parseInt(pnrField.getText());
                        con.statement.executeUpdate("DELETE from reservation where pnr = '" + pnr + "'");
                        JOptionPane.showMessageDialog(null, "Deleted Successfully");
                        setVisible(false);
                    }
                }catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        });

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
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        setSize(1920,1080);
        setVisible(true);
    }
    public static void main(String[] args) {
        new CancelReservation();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==checkButton){
            try {
                int pnr =  Integer.parseInt(pnrField.getText());
                String query = "select * from reservation where pnr = '" + pnr + "'";
                Con con = new Con();
                ResultSet resultSet = con.statement.executeQuery(query);
                while (resultSet.next()) {
                    passengerNameField.setText(resultSet.getString("name"));
                    passengerAadharField.setText(resultSet.getString("aadhar"));
                    trainNumberField.setText(resultSet.getString("trainNo"));
                    trainNameField.setText(resultSet.getString("trainName"));
                    classTypeField.setText(resultSet.getString("classType"));
                    dateOfJourneyField.setText(resultSet.getString("dateOfJourney"));
                    fromPlaceField.setText(resultSet.getString("fromPlace"));
                    toDestinationField.setText(resultSet.getString("toDestination"));
                    priceField.setText(resultSet.getString("farePrice"));
                }
            }catch (Exception ee){
                JOptionPane.showMessageDialog(null,"Enter PNR number");
                ee.printStackTrace();
            }
        }
    }
}
