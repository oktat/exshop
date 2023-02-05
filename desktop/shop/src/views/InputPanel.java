package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InputPanel extends HBox {
    Label label;
    TextField field;
    public InputPanel() {
        this.initComponent();
        this.addComponent();
        this.setPanel();
    }
    private void initComponent() {
        this.label = new Label();
        this.field = new TextField();
        this.label.setMinWidth(100);
        this.label.setAlignment(Pos.BASELINE_RIGHT);
    }
    private void addComponent() {
        this.getChildren().add(this.label);
        this.getChildren().add(this.field);
    }
    private void setPanel() {
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setAlignment(Pos.CENTER);
        this.setSpacing(10);
    }
    public String getLabel() {
        return this.label.getText();
    }
    public void setLabel(String label) {
        this.label.setText(label);
    }
    public String getValue() {
        return this.field.getText();
    }
    public void setValue(String value) {
        this.field.setText(value);
    }
}
