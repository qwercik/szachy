package szachy;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import szachy.pieces.*;

import java.util.Random;

public class Main extends Application {
    private static final int WINDOW_WIDTH = 400;
    private static final int WINDOW_HEIGHT = 400;

    @Override
    public void start(Stage stage) {
        SvgImageLoaderFactory.install();

        stage.setTitle("Szachy");
        stage.setResizable(false);
        stage.getIcons().add(new Image("/assets/icon.svg"));

        ChessPiece pieces[] = {
                new Pawn(),
                new Rook(),
                new Queen(),
                new King(),
                new Bishop(),
                new Knight()
        };

        Field field = new Field();
        field.setOnAction(event -> field.setPiece(pieces[new Random().nextInt(pieces.length)]));

        Scene scene = new Scene(new StackPane(field), WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
