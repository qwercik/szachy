package szachy;

import javafx.scene.image.Image;

public abstract class ChessPiece {
    public ChessPiece(Player owner) {
        this.owner = owner;
    }

    public abstract Image getIcon();


    protected String getIconsDirectory() {
        String basePath = "/assets/pieces";
        if (this.owner.getType() == Player.Type.WHITE) {
            return String.format("%s/white", basePath);
        } else {
            return String.format("%s/black", basePath);
        }
    }

    protected String obtainIconPath(String filename) {
        return String.format("" +
                "%s/%s.svg",
                this.getIconsDirectory(),
                filename
        );
    }

    protected Player owner;
}
