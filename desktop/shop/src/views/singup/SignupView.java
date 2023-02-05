package views.singup;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import views.PasswordPanel;

public class SignupView extends VBox {
    CaptionPanel captionPane;
    NamePanel namePanel;
    EmailPanel emailPanel;
    PassPanel passPanel;
    PasswordPanel passConfirmPanel;
    VisiblePanel visiblePanel;
    public ButtonPanel buttonPane;

    public SignupView() {
        this.initComponent();
        this.addComponent();
        this.setPanel();
    }
    private void initComponent() {
        this.captionPane = new CaptionPanel();
        this.namePanel = new NamePanel();        
        this.emailPanel = new EmailPanel();
        this.passPanel = new PassPanel();
        this.passConfirmPanel = new PasswordPanel();
        this.visiblePanel = new VisiblePanel();
        this.buttonPane = new ButtonPanel();
    }
    private void addComponent() {
        this.getChildren().add(this.captionPane);
        this.getChildren().add(this.namePanel);
        this.getChildren().add(this.emailPanel);
        this.getChildren().add(this.passPanel);
        this.getChildren().add(this.passConfirmPanel);
        this.getChildren().add(this.visiblePanel);
        this.getChildren().add(this.buttonPane);
        
    }
    private void setPanel() {
        this.setPadding(new Insets(10, 10, 10, 10));
    }
    public CaptionPanel getCaptionPane() {
        return captionPane;
    }
    public NamePanel getNamePanel() {
        return namePanel;
    }
    public EmailPanel getEmailPanel() {
        return emailPanel;
    }
    public PassPanel getPassPanel() {
        return passPanel;
    }
    public PasswordPanel getPassConfirmPanel() {
        return passConfirmPanel;
    }
    public VisiblePanel getVisiblePanel() {
        return this.visiblePanel;
    }
    public ButtonPanel getButtonPane() {
        return buttonPane;
    }
        
}
