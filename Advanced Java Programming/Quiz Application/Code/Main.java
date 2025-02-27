import View.*;
import Controller.*;

public class Main {
    public static void main(String[] args) {
        LoginPage loginView = new LoginPage();
        new LoginController(loginView);
    }
}