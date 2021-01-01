package szachy;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Path;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import szachy.pieces.*;

import java.util.Random;

public class Main extends Application {
    private static final String WINDOW_TITLE = "Szachy";
    private static final String ICON_PATH = "/assets/icon.svg";

    @Override
    public void start(Stage stage) {
        SvgImageLoaderFactory.install();

        stage.setTitle(WINDOW_TITLE);
        stage.setResizable(false);
        stage.getIcons().add(new Image(ICON_PATH));

        GameState state = new GameState();
        ControlPanel panel = new ControlPanel(state);
        HBox root = new HBox();
        root.getChildren().addAll(state.getChessBoard(), panel);

        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
