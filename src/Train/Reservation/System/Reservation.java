package Train.Reservation.System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
public class Reservation extends JFrame implements ActionListener{
    private JTextField passengerNameField;
    private JTextField passengerAadharField;
    private JTextField fromPlaceField;
    private JTextField toDestinationField;
    private JComboBox<String> trainNumberComboBox;
    private JComboBox<String> trainNameComboBox;
    private JComboBox<String> classTypeComboBox;
    private JDateChooser dateChooser;
    private JTextField priceField;
    private Calendar currentCalendar;
    private Calendar selectedCalendar;
    private Map<String, String> trainNumberToName;
    private Map<String, String> trainNameToNumber;
    Reservation(){

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icons/wallpaper.gif"));
        JLabel label = new JLabel(imageIcon);
        label.setBounds(0,0,1920,1080);
        add(label);

        JLabel reservationForm = new JLabel("Reservation Form");
        reservationForm.setFont(new java.awt.Font("serif", Font.BOLD, 40));
        reservationForm.setBounds(600, 30, 350, 35);
        reservationForm.setForeground(Color.white);
        label.add(reservationForm);

        JLabel name = new JLabel("Name :");
        name.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        name.setBounds(350, 150, 150, 27);
        name.setForeground(Color.white);
        label.add(name);
        passengerNameField = new JTextField();
        passengerNameField.setBounds(470, 150, 200, 27);
        passengerNameField.setBackground(new Color(16,108,115));
        passengerNameField.setForeground(Color.white);
        passengerNameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(passengerNameField);

        JLabel aadhar = new JLabel("Aadhar :");
        aadhar.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        aadhar.setBounds(850, 150, 150, 27);
        aadhar.setForeground(Color.white);
        label.add(aadhar);
        passengerAadharField = new JTextField();
        passengerAadharField.setBounds(1000, 150, 200, 27);
        passengerAadharField.setBackground(new Color(16,108,115));
        passengerAadharField.setForeground(Color.white);
        passengerAadharField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(passengerAadharField);

        JLabel trainNumber = new JLabel("Train No. :");
        trainNumber.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        trainNumber.setBounds(350, 240, 150, 27);
        trainNumber.setForeground(Color.white);
        label.add(trainNumber);

        trainNumberToName = new HashMap<>();
        trainNumberToName.put("19263", "Dadi Express");
        trainNumberToName.put("20978", "Vande Bharat");
        trainNumberToName.put("19614", "Aii Amritsar Exp");
        trainNumberToName.put("22985", "Double Decker");
        trainNumberToName.put("22673", "Ypr Jp Exp");
        trainNumberToName.put("22547", "Hw Suhail Dev");
        trainNumberToName.put("19611", "Aii Ajmer Exp");

        trainNameToNumber = new HashMap<>();
        for (Map.Entry<String, String> entry : trainNumberToName.entrySet()) {
            trainNameToNumber.put(entry.getValue(), entry.getKey());
        }

        trainNumberComboBox = new JComboBox<>(trainNumberToName.keySet().toArray(new String[0]));
        trainNumberComboBox.setBounds(470, 240, 200, 27);
        trainNumberComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
        trainNumberComboBox.setForeground(Color.WHITE);
        trainNumberComboBox.setBackground(new Color(16, 108, 115));
        label.add(trainNumberComboBox);

        JLabel trainName = new JLabel("Train Name :");
        trainName.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        trainName.setBounds(850, 240, 150, 27);
        trainName.setForeground(Color.white);
        label.add(trainName);

        trainNameComboBox = new JComboBox<>(trainNumberToName.values().toArray(new String[0]));
        trainNameComboBox.setBounds(1000, 240, 195, 27);
        trainNameComboBox.setFont(new Font("Tahoma", Font.PLAIN, 17));
        trainNameComboBox.setForeground(Color.WHITE);
        trainNameComboBox.setBackground(new Color(16, 108, 115));
        label.add(trainNameComboBox);
        trainNumberComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedTrainNumber = trainNumberComboBox.getSelectedItem().toString();
                    String selectedTrainName = trainNumberToName.get(selectedTrainNumber);
                    trainNameComboBox.setSelectedItem(selectedTrainName);
                }
            }
        });
        trainNameComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String selectedTrainName = trainNameComboBox.getSelectedItem().toString();
                    String selectedTrainNumber = trainNameToNumber.get(selectedTrainName);
                    trainNumberComboBox.setSelectedItem(selectedTrainNumber);
                }
            }
        });

        JLabel classType = new JLabel("Class Type :");
        classType.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        classType.setBounds(350, 420, 150, 27);
        classType.setForeground(Color.white);
        label.add(classType);

        classTypeComboBox = new JComboBox(new String[] {"----Select Class Type-----","Air-Conditioned Executive Chair Class (EC)", "Air-Conditioned First Class (1AC)", "Air-Conditioned Two-Tier Class (2AC)", "Air-Conditioned Three-Tier Class (3AC)", "First Class (FC)", "AC Chair Class (CC)", "Sleeper Class (SL)", "Second Class (2S)", "Unreserved/General Class (2S)"});
        classTypeComboBox.setBounds(470, 420, 195, 27);
        classTypeComboBox.setFont(new Font("Tahoma", Font.PLAIN, 15));
        classTypeComboBox.setForeground(Color.WHITE);
        classTypeComboBox.setBackground(new Color(16, 108, 115));
        label.add(classTypeComboBox);

        Map<String, String> classTypeToPrice = new HashMap<>();
        classTypeToPrice.put("Air-Conditioned Executive Chair Class (EC)", "1500.0");
        classTypeToPrice.put("Air-Conditioned First Class (1AC)", "1200.0");
        classTypeToPrice.put("Air-Conditioned Two-Tier Class (2AC)", "1000.0");
        classTypeToPrice.put("Air-Conditioned Three-Tier Class (3AC)", "800.0");
        classTypeToPrice.put("First Class (FC)", "600.0");
        classTypeToPrice.put("AC Chair Class (CC)", "400.0");
        classTypeToPrice.put("Sleeper Class (SL)", "250.0");
        classTypeToPrice.put("Second Class (2S)", "150.0");
        classTypeToPrice.put("Unreserved/General Class (2S)", "50.0");
        classTypeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedClassType = (String) classTypeComboBox.getSelectedItem();
                // Check if the selectedClassType is not the default option
                if (!selectedClassType.equals("----Select Class Type-----")) {
                    // Get the price from the map
                    String price = classTypeToPrice.get(selectedClassType);
                    // Update the priceField with the selected price
                    priceField.setText(String.valueOf(price));
                } else {
                    // If the default option is selected, clear the priceField
                    priceField.setText("");
                }
            }
        });

        JLabel dateOfJourney = new JLabel("Date Of Journey :");
        dateOfJourney.setFont(new java.awt.Font("serif", Font.BOLD, 19));
        dateOfJourney.setBounds(850, 420, 160, 27);
        dateOfJourney.setForeground(Color.white);
        label.add(dateOfJourney);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(1000, 420, 200, 27);
        JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateChooser.getDateEditor();
        dateEditor.setFont(new Font("Tahoma", Font.PLAIN, 17));
        dateEditor.setEditable(false);
        dateEditor.setBackground(new Color(16, 108, 115));
        dateEditor.setEditable(false);
        Calendar currentDate = Calendar.getInstance();
        dateChooser.setDate(currentDate.getTime());
        label.add(dateChooser);
        dateChooser.getDateEditor().addPropertyChangeListener(e -> {
            if (e.getPropertyName().equals("date")) {
                Date selectedDate = dateChooser.getDate();
                selectedCalendar = Calendar.getInstance();
                selectedCalendar.setTime(selectedDate);

                currentCalendar = Calendar.getInstance();
                currentCalendar.setTime(new Date());
            }
        });

        JLabel fromPlace = new JLabel("From Place :");
        fromPlace.setFont(new Font("serif", Font.BOLD, 20));
        fromPlace.setBounds(350, 330, 150, 27);
        fromPlace.setForeground(Color.white);
        label.add(fromPlace);

        fromPlaceField = new JTextField();
        fromPlaceField.setBounds(470, 330, 200, 27);
        fromPlaceField.setBackground(new Color(16,108,115));
        fromPlaceField.setForeground(Color.white);
        fromPlaceField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(fromPlaceField);

        JLabel toDestination = new JLabel("To Destination :");
        toDestination.setFont(new Font("serif", Font.BOLD, 20));
        toDestination.setBounds(850, 330, 150, 27);
        toDestination.setForeground(Color.white);
        label.add(toDestination);

        toDestinationField = new JTextField();
        toDestinationField.setBounds(1000, 330, 200, 27);
        toDestinationField.setBackground(new Color(16,108,115));
        toDestinationField.setForeground(Color.white);
        toDestinationField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(toDestinationField);

        JLabel priceLabel = new JLabel("Fare Price:");
        priceLabel.setFont(new java.awt.Font("serif", Font.BOLD, 20));
        priceLabel.setBounds(850, 510, 100, 27);
        priceLabel.setForeground(Color.white);
        label.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(1000, 510, 200, 27);
        priceField.setEditable(false); // Make it read-only
        priceField.setBackground(new Color(16, 108, 115));
        priceField.setForeground(Color.WHITE);
        priceField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        label.add(priceField);

        JButton insertButton = new JButton("Insert");
        insertButton.setBounds(850, 650, 111, 33);
        insertButton.setFont(new Font("Tahoma",Font.BOLD,14));
        insertButton.setForeground(Color.WHITE);
        insertButton.setBackground(Color.BLACK);
        insertButton.addActionListener(this);
        label.add(insertButton);

        JButton backButton = new JButton("Back");
        backButton.setBounds(550, 650, 111, 33);
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

    @Override
    public void actionPerformed(ActionEvent e) {

        String name = passengerNameField.getText();
        String aadhar = passengerAadharField.getText();
        String trainNo = (String) trainNumberComboBox.getSelectedItem();
        String trainName = (String) trainNameComboBox.getSelectedItem();
        String classType = (String) classTypeComboBox.getSelectedItem();
        String fromPlace = fromPlaceField.getText();
        String toDestination = toDestinationField.getText();
        String farePrice = priceField.getText();

        if (name.isEmpty() || aadhar.isEmpty() || trainNo.isEmpty() || trainName.isEmpty() || classType.isEmpty() || fromPlace.isEmpty() || toDestination.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please Fill All Fields");
            return;
        }
        if (selectedCalendar == null) {
            JOptionPane.showMessageDialog(this, "Please select date.",
                    "Invalid Date", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date selectedDate = dateChooser.getDate();
        String dateText = selectedDate.toString();

        if (!Pattern.matches("\\d{12}", aadhar)){
            JOptionPane.showMessageDialog(null,
                    "Aadhar number must be 12 digits.",
                    "Invalid Aadhar Number",JOptionPane.ERROR_MESSAGE);
            return;
        }
        else if (selectedCalendar.before(selectedDate)) {

            JOptionPane.showMessageDialog(this, "Please select a valid future date.",
                    "Invalid Date", JOptionPane.ERROR_MESSAGE);
            dateChooser.setDate(currentCalendar.getTime());
            return;
        }
        try {
            Con connection = new Con();
            String query = "INSERT INTO reservation (name, aadhar, trainNo, trainName, classType, dateOfJourney, fromPlace, toDestination, farePrice) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, aadhar);
            preparedStatement.setString(3, trainNo);
            preparedStatement.setString(4, trainName);
            preparedStatement.setString(5, classType);
            preparedStatement.setString(6, dateText);
            preparedStatement.setString(7, fromPlace);
            preparedStatement.setString(8, toDestination);
            preparedStatement.setString(9, farePrice);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected == 1) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int pnr = generatedKeys.getInt(1);
                    String formattedPNR = String.format("%03d", pnr);
                    JOptionPane.showMessageDialog(null, "Reservation Successfully. Your PNR Number is: " + formattedPNR);
                }
            } else {
                System.err.println("Reservation failed.");
            }

        }catch (SQLException exception) {
            JOptionPane.showMessageDialog(null, "Something went wrong please try again.");
            exception.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new Reservation();
    }
}
