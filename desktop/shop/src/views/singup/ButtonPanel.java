package views.singup;

import java.awt.event.ActionEvent;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonPanel extends HBox {
    Button signupButton;
    public ButtonPanel() {
        this.initComponent();
        this.addComponent();
    }
    private void initComponent() {
        this.signupButton = new Button("Regisztrálás");
    }
    private void addComponent() {
        this.getChildren().add(this.signupButton);
    }
    public void setOnAction(EventHandler<javafx.event.ActionEvent> eventHandler) {
        this.signupButton.setOnAction(eventHandler);
    }
}
