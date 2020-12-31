package szachy;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class ControlPanel extends VBox {
    private static final int WIDTH = 200;

    public ControlPanel() {
        Insets padding = new Insets(10, 10, 10, 10);
        Insets margin = new Insets(10, 0, 10, 0);

        this.setPrefWidth(WIDTH);

        Label turnLabel = new Label("Ruch białych");
        turnLabel.setAlignment(Pos.CENTER);
        turnLabel.setPrefWidth(this.getPrefWidth());
        turnLabel.setPadding(padding);

        Button takeback = new Button("Cofnij ruch");
        takeback.setPrefWidth(this.getPrefWidth());
        takeback.setPadding(padding);
        setMargin(takeback, margin);

        Button draw = new Button("Zaproponuj remis");
        draw.setPrefWidth(this.getPrefWidth());
        draw.setPadding(padding);
        setMargin(draw, margin);

        Button surrender = new Button("Poddaj się");
        surrender.setPrefWidth(this.getPrefWidth());
        surrender.setPadding(padding);
        setMargin(surrender, margin);

        Button info = new Button("O programie");
        info.setPrefWidth(this.getPrefWidth());
        info.setPadding(padding);
        setMargin(info, margin);
        info.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.NONE);
            alert.setTitle("O programie");
            alert.setContentText("Autorem programu jest Eryk Andrzejewski.\nProjekt został wykonany na potrzeby przedmiotu Programowanie Obiektowe na Politechnice Poznańskiej.");

            alert.show();
        });

        this.getChildren().addAll(
                turnLabel,
                takeback,
                draw,
                surrender,
                info
        );

        this.setPadding(padding);
    }
}
