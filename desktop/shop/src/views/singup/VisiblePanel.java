package views.singup;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class VisiblePanel extends HBox {
    CheckBox visibleCheckBox;
    Label passTextLabel;
    public VisiblePanel() {
        this.initComponent();
        this.setComponent();
        this.addComponent();
        this.setPadding(new Insets(10, 10, 10, 10));
    }
    private void initComponent() {
        this.visibleCheckBox = new CheckBox("A jelszó megjelenítése");
        this.passTextLabel = new Label();
    }
    private void setComponent() {
        this.passTextLabel.setPadding(new Insets(0, 0, 0, 10));
        this.passTextLabel.setAlignment(Pos.CENTER);
        this.passTextLabel.setVisible(false);
    }
    private void addComponent() {
        this.getChildren().add(this.visibleCheckBox);
        this.getChildren().add(this.passTextLabel);
    }
    public CheckBox getVisibleCheckBox() {
        return this.visibleCheckBox;
    }
    public Label getPassTextLabel() {
        return this.passTextLabel;
    }
    
}
