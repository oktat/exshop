package controllers;

import models.api.AuthResponse;
import models.api.AuthService;
import views.LoginView;

public class LoginController {
    LoginView loginView;
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
        this.loginView.getLoginButton()
        .setOnAction(e -> onClickLoginButton());
    }
    private void onClickLoginButton() {
        System.out.println("Azonosítás");
        String user = this.loginView.getUserField().getText();
        String pass = this.loginView.getPassField().getText();

        AuthService authService = new AuthService();
        authService.login2(user, pass);
        
        // AuthResponse vmi = authService.login(user, pass);
        // System.out.println(vmi.getToken());
        
    }
}
