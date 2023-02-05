import controllers.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public void start(Stage stage) {
        MainController mainController = new MainController();
        // Scene scene = new Scene(mainController.getMainView(), 400, 300);
        Scene scene = new Scene(mainController.getMainTab(), 400, 300);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws Exception {
        launch(args);
    }
}
