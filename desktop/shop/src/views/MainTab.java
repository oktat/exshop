package views;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import views.singup.SignupView;

public class MainTab extends TabPane {

    Tab productPane;
    Tab loginPane;
    Tab signupPane;
    MainView mainView;
    LoginView loginView;
    SignupView signupView;

    public MainTab(MainView mainView, LoginView loginView, SignupView signupView) {
        this.mainView = mainView;
        this.loginView = loginView;
        this.signupView = signupView;
        this.initTabs();
        this.setTabPane();
    }
    private void initTabs() {
        this.productPane = new Tab("Termékek", this.mainView);
        this.loginPane = new Tab("Belépés", this.loginView);
        this.signupPane = new Tab("Regisztráció", this.signupView);
    }
    private void setTabPane() {
        this.getTabs().add(this.productPane);
        this.getTabs().add(this.loginPane);
        this.getTabs().add(this.signupPane);
    }
    
}
