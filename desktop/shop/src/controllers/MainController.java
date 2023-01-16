package controllers;

import views.LoginView;
import views.MainView;

public class MainController {
    MainView mainView;
    LoginView loginView;
    LoginController loginController;
    public MainController() {
        this.mainView = new MainView();
        loginView = new LoginView(mainView);
        this.loginController = new LoginController(loginView);
    }
    public MainView getMainView() {
        return this.mainView;
    }
    public LoginView getLoginView() {        
        return loginView;
    }
}
