package szachy;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;

    @Override
    public void start(Stage stage) {
        stage.setTitle("Szachy");

        StackPane root = new StackPane();
        Button btn = new Button("Test");

        ButtonType retry = new ButtonType("Test1", ButtonBar.ButtonData.OK_DONE);
        ButtonType end = new ButtonType("Test2", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(
                Alert.AlertType.INFORMATION,
                "Test!",
                retry, end
        );


        btn.setOnAction(e -> alert.showAndWait());
        root.getChildren().add(btn);

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
