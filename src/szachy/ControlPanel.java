package szachy;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import szachy.controls.*;

public class ControlPanel extends VBox {
    private static final int WIDTH = 200;

    public ControlPanel(GameState state) {
        TurnInfo turnLabel = new TurnInfo(state);
        Button takeBack = new TakeBack(state);
        Button draw = new OfferDraw(state);
        Button giveUp = new GiveUp(state);
        AboutButton info = new AboutButton(state);

        this.setPrefWidth(WIDTH);
        turnLabel.setPrefWidth(WIDTH);
        takeBack.setPrefWidth(WIDTH);
        draw.setPrefWidth(WIDTH);
        giveUp.setPrefWidth(WIDTH);
        info.setPrefWidth(WIDTH);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(turnLabel, takeBack, draw, giveUp, info);
        for (Node child : this.getChildren()) {
            setMargin(child, new Insets(10, 0, 10, 0));
        }
    }
}
