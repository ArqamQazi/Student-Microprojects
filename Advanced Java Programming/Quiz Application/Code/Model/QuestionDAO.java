package Model;

import java.sql.*;
import java.util.ArrayList;

public class QuestionDAO {
    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "123";

    public static ArrayList<QuestionModel> getQuestionsFromDB() {
        ArrayList<QuestionModel> questions = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Load Oracle JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish the connection
            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Create a statement
            statement = connection.createStatement();

            // SQL query to get all questions
            String query = "SELECT id, question_text, option1, option2, option3, option4, correct_option FROM questions";

            // Execute the query and get the result set
            resultSet = statement.executeQuery(query);

            // Iterate through the result set and populate the questions list
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String questionText = resultSet.getString("question_text");
                String option1 = resultSet.getString("option1");
                String option2 = resultSet.getString("option2");
                String option3 = resultSet.getString("option3");
                String option4 = resultSet.getString("option4");
                int correctOption = resultSet.getInt("correct_option");

                // Add the question to the list
                questions.add(new QuestionModel(questionText, option1, option2, option3, option4, correctOption));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found. Add the ojdbc jar to your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return questions;
    }

}
