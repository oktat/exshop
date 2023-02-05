package controllers;

import views.LoginView;
import views.MainTab;
import views.MainView;
import views.singup.SignupView;

public class MainController {
    MainTab mainTab;
    public MainView mainView;
    public LoginView loginView;
    public SignupView signupView;    
    LoginController loginController;
    SignupController signupController;

    public MainController() {
        
        this.mainView = new MainView();
        this.loginView = new LoginView();
        this.signupView = new SignupView();
        this.mainTab = new MainTab(this.mainView, this.loginView, this.signupView);
        this.loginController = new LoginController(loginView);
        this.signupController = new SignupController(signupView);
    }
    public MainView getMainView() {
        return this.mainView;
    }
    public LoginView getLoginView() {        
        return loginView;
    }
    public MainTab getMainTab() {
        return this.mainTab;
    }
}
