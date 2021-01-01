package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import szachy.GameState;

public class AboutButton extends Button {
    private static final String title = "O programie";
    private static final String content =
            "Autorem programu jest Eryk Andrzejewski.\n" +
            "Projekt został wykonany na potrzeby przedmiotu Programowanie Obiektowe " +
            "na Politechnice Poznańskiej.";

    public AboutButton(GameState state) {
        super(title);
        this.setPadding(new Insets(10, 10, 10, 10));
        this.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(title);
            alert.setHeaderText(title);
            alert.setContentText(content);
            alert.show();
        });
    }
}
