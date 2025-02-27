package View;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ResultView extends JFrame {

    private JLabel scoreLabel;
    private JButton exitButton;
    Connection connection = null;
    Statement statement = null;
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "123";

    public ResultView(String name, int score) {
        setTitle("Quiz Application - Score");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(240, 248, 255)); // Light blueish background

        scoreLabel = new JLabel("Hello " + name + ", your score is: " + score);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(new Color(70, 130, 180)); // Steel Blue for text

        exitButton = new JButton("Exit");
        styleButton(exitButton);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(scoreLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(exitButton, gbc);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish the connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // SQL query to get all questions
            String query = "insert into scores values('" + name + "', " + score + ")";

            // Execute the query and get the result set
            statement.executeUpdate(query);

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found. Add the ojdbc jar to your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        add(panel);
        setVisible(true);
    }

    // Method to style the Exit button
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding inside button
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }


    public static void main(String[] args) {

        new ResultView("Arqam", 85);
    }
}
