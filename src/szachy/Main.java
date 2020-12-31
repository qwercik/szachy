package szachy;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import szachy.pieces.*;

import java.util.Random;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 620;

    @Override
    public void start(Stage stage) {
        SvgImageLoaderFactory.install();

        stage.setTitle("Szachy");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/assets/icon.svg"));

        GameState state = new GameState();
        ControlPanel panel = new ControlPanel();
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
