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

    private TurnInfo turnLabel;
    private TakeBack takeBack;
    private Button draw;
    private Button giveUp;
    private AboutButton info;

    public ControlPanel(GameState state) {
        state.setControlPanel(this);

        this.turnLabel = new TurnInfo(state);
        this.takeBack = new TakeBack(state);
        this.draw = new OfferDraw(state);
        this.giveUp = new GiveUp(state);
        this.info = new AboutButton(state);

        this.setPrefWidth(WIDTH);
        this.turnLabel.setPrefWidth(WIDTH);
        this.takeBack.setPrefWidth(WIDTH);
        this.draw.setPrefWidth(WIDTH);
        this.giveUp.setPrefWidth(WIDTH);
        this.info.setPrefWidth(WIDTH);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(turnLabel, takeBack, draw, giveUp, info);
        for (Node child : this.getChildren()) {
            setMargin(child, new Insets(10, 0, 10, 0));
        }
    }

    public void update() {
        this.turnLabel.update();
        this.takeBack.update();
    }
}
