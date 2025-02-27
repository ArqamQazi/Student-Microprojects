package Controller;

import Model.LoginDAO;
import Model.LoginModel;
import View.LoginPage;
import View.QuizView;

import javax.swing.*;

import java.util.ArrayList;

public class LoginController {
    private LoginPage loginView;
    private ArrayList<LoginModel> userList;

    public LoginController(LoginPage view) {
        this.loginView = view;
        this.userList = LoginDAO.getUsers();

        loginView.getLoginButton().addActionListener(e -> {
            String userName = loginView.getUsernameField().getText();
            String password = loginView.getPasswordField().getText();
            boolean flag = false;
            for (LoginModel loginModel : userList) {
                String currentUserName = loginModel.getUserName();
                String currentPassword = loginModel.getPassword();

                if (currentUserName.equals(userName) && currentPassword.equals(password)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                loginView.setVisible(false);
                JOptionPane.showMessageDialog(null, "Login Successful");
                QuizView quizView = new QuizView();
                System.out.println("quiz view initialized");
                QuizController quizController = new QuizController(quizView, userName);
                System.out.println("Quiz controller initialized");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

    }
}
