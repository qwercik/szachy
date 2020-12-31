package szachy;

public class Player {
    public enum Type {
        WHITE,
        BLACK
    }

    Player(Type type) {
        this.type = type;
    }

    Type getType() {
        return this.type;
    }

    boolean compareTo(Player player) {
        return this.type == player.type;
    }

    private Type type;
}
