package szachy;

import de.codecentric.centerdevice.javafxsvg.SvgImageLoaderFactory;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import szachy.controls.ControlPanel;
import szachy.engine.GameState;
import szachy.engine.Player;
import szachy.events.*;

public class GameWindow extends Application {
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        SvgImageLoaderFactory.install();

        stage.setTitle(WINDOW_TITLE);
        stage.setResizable(false);
        stage.getIcons().add(new Image(ICON_PATH));

        this.startNewGame();
    }

    public static void main(String[] args) {
        launch();
    }

    public Stage getStage() {
        return this.stage;
    }

    public GameState getGameState() {
        return this.state;
    }

    public ControlPanel getControlPanel() {
        return this.panel;
    }

    public void showResultsAndAskForOneMoreGame(Player winner) {
        ButtonType onceAgain = new ButtonType("Zagraj jeszcze raz");
        ButtonType endGame = new ButtonType("Zakończ grę");

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Koniec gry", onceAgain, endGame);
        alert.setTitle("Koniec gry");
        if (winner == null) {
            alert.setHeaderText("Remis");
            alert.setContentText("Gra zakończyła się remisem");
        } else {
            alert.setHeaderText("Zwycięstwo");
            alert.setContentText("Partię wygrały " + winner.getPluralName());
        }

        var result = alert.showAndWait();

        if (result.isPresent() && result.get() == endGame) {
            stage.close();
        } else {
            this.startNewGame();
        }
    }

    public void startNewGame() {
        this.state = new GameState();
        this.panel = new ControlPanel();

        HBox root = new HBox();
        this.addEventHandlers(root);
        root.getChildren().addAll(state.getBoard(), panel);

        Scene scene = new Scene(root);
        this.stage.setScene(scene);
        this.stage.show();
    }

    private void addEventHandlers(Node node) {
        node.addEventHandler(EndGame.TYPE, event -> event.handle(this));
        node.addEventHandler(GiveUp.TYPE, event -> event.handle(this));
        node.addEventHandler(OfferDraw.TYPE, event -> event.handle(this));
        node.addEventHandler(TakeBack.TYPE, event -> event.handle(this));
        node.addEventHandler(MoveHandler.TYPE, event -> event.handle(this));
    }


    private static final String WINDOW_TITLE = "Szachy";
    private static final String ICON_PATH = "/assets/icon.svg";

    private Stage stage;
    private GameState state;
    private ControlPanel panel;
}
