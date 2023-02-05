package views.singup;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class CaptionPanel extends HBox {
    Label label;
    public CaptionPanel() {
        this.label = new Label("Új felhasználó felvétele");
        this.getChildren().add(this.label);
    }
    
}
