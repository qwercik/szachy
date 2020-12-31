package szachy;

public class Player {
    public enum Type {
        WHITE,
        BLACK
    }

    public Player(Type type) {
        this.type = type;
    }

    public Type getType() {
        return this.type;
    }

    public boolean compareTo(Player player) {
        return this.type == player.type;
    }

    private Type type;
}
