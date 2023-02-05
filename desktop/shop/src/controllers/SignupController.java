package controllers;

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import models.api.AuthService;
import views.singup.SignupView;

public class SignupController {
    CheckBox visibleCheckBox;
    TextField passField;
    Label passTextLabel;
    SignupView signupView;
    public SignupController(SignupView signupView) {
        this.signupView = signupView;

        visibleCheckBox = this.signupView.getVisiblePanel().getVisibleCheckBox();
        passField = this.signupView.getPassPanel().getField();
        passTextLabel = this.signupView.getVisiblePanel().getPassTextLabel();

        this.handleEvent();
    }
    private void handleEvent() {
        this.signupView.buttonPane.setOnAction(e -> {
            this.onClickButtonPanel();
        });
        this.signupView.getPassPanel().getField().setOnKeyTyped(e -> {
            this.onKeyTypedPassPanel();
        });
        this.signupView.getVisiblePanel().getVisibleCheckBox().setOnAction(e -> {
            this.onClickVisibleCheckBox();
        });
    }
    private void onClickVisibleCheckBox() {
        if(visibleCheckBox.isSelected()) {
            System.out.println("aaa");
            passTextLabel.setVisible(true);
            
        }else {
            System.out.println("bbb");
            passTextLabel.setVisible(false);
        }        
    }
    private void onKeyTypedPassPanel() {

        String pass = passField.getText();
        passTextLabel.setText(pass);
        if(visibleCheckBox.isSelected()) {
            System.out.println("jelölve");
        }else {
            System.out.println("nincs jelölve");
        }
    }
    private void onClickButtonPanel() {
        System.out.println("signup kezdődik");
        String name = signupView.getNamePanel().getValue();
        String email = signupView.getEmailPanel().getValue();
        String pass = signupView.getPassPanel().getValue();
        String passConfirmation = signupView.getPassConfirmPanel().getValue();
        if (pass.equals(passConfirmation)) {
            AuthService authService = new AuthService();
            authService.signup(name, email, pass);
            this.deleteFieldConent();
        }else {
            this.passwordNotSame();
        }
    }
    private void passwordNotSame() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Hiba");
        alert.setHeaderText("A hozzáadás sikertelen!");
        alert.setContentText("Hiba! A két jelszó nem egyezik!");
        alert.showAndWait();
    }
    private void deleteFieldConent() {
        signupView.getNamePanel().setValue("");
        signupView.getEmailPanel().setValue("");
        signupView.getPassPanel().setValue("");
        signupView.getPassConfirmPanel().setValue("");
    }
    
}
