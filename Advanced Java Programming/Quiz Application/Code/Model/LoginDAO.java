package Model;

import java.sql.*;
import java.util.ArrayList;

public class LoginDAO {

    private static final String JDBC_URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String USERNAME = "system";
    private static final String PASSWORD = "123";

    public static ArrayList<LoginModel> getUsers() {
        ArrayList<LoginModel> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            statement = connection.createStatement();

            String query = "SELECT NAME, PASSWORD from users";

            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String userName = resultSet.getString("Name");
                String password = resultSet.getString("PASSWORD");

                users.add(new LoginModel(userName, password));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found. Add the ojdbc jar to your classpath.");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally  {
            try {
                if (resultSet != null) { resultSet.close(); }
                if (connection != null) {connection.close(); }
                if (statement != null) {statement.close(); }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }
}
