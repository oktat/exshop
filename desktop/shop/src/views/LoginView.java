package views;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginView extends VBox {
    Label loginLabel;
    TextField userField;
    TextField passField;
    Button loginButton;
    
    public MainView mainView;

    public LoginView(MainView mainView) {
        this.mainView = mainView;
        this.initComponent();
        this.addComponent();
    }
    private void initComponent() {
        this.loginLabel = new Label("Shop belépés");
        this.userField = new TextField();
        this.passField = new TextField();
        this.loginButton = new Button("Belépés");

        this.userField.setPromptText("Felhasználónév");
        this.passField.setPromptText("Jelszó");
    }
    private void addComponent() {
        this.getChildren().add(this.loginLabel);
        this.getChildren().add(this.userField);
        this.getChildren().add(this.passField);
        this.getChildren().add(this.loginButton);
    }
    public Label getLoginLabel() {
        return loginLabel;
    }
    public TextField getUserField() {
        return userField;
    }
    public TextField getPassField() {
        return passField;
    }
    public Button getLoginButton() {
        return loginButton;
    }
    
}
