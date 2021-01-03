package szachy.controls;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import szachy.Dimensions;
import szachy.engine.GameState;

public class ControlPanel extends VBox implements Control {
    public ControlPanel() {
        this.setPrefWidth(Dimensions.CONTROL_PANEL_WIDTH);
        this.turnLabel.setPrefWidth(Dimensions.CONTROL_PANEL_WIDTH);
        this.takeBack.setPrefWidth(Dimensions.CONTROL_PANEL_WIDTH);
        this.draw.setPrefWidth(Dimensions.CONTROL_PANEL_WIDTH);
        this.giveUp.setPrefWidth(Dimensions.CONTROL_PANEL_WIDTH);
        this.info.setPrefWidth(Dimensions.CONTROL_PANEL_WIDTH);

        this.setPadding(new Insets(10, 10, 10, 10));
        this.getChildren().addAll(turnLabel, takeBack, draw, giveUp, info);
        for (Node child : this.getChildren()) {
            setMargin(child, new Insets(10, 0, 10, 0));
        }
    }

    @Override
    public void update(GameState state) {
        this.turnLabel.update(state);
        this.takeBack.update(state);
        this.draw.update(state);
        this.giveUp.update(state);
        this.info.update(state);
    }

    private final TurnInfo turnLabel = new TurnInfo();
    private final TakeBack takeBack = new TakeBack();
    private final OfferDraw draw = new OfferDraw();
    private final GiveUp giveUp = new GiveUp();
    private final About info = new About();
}
