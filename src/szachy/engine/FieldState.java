package szachy.engine;

public class FieldState {
    public enum Type {
        DEFAULT,
        STARTING_POINT,
        DESTINATION
    }

    public FieldState() {}

    public FieldState(Type type, boolean check) {
        this.type = type;
        this.check = check;
    }

    public Type getType() {
        return this.type;
    }

    public boolean isCheck() {
        return this.check;
    }

    public FieldState withType(Type type) {
        return new FieldState(type, this.check);
    }

    public FieldState withCheck(boolean check) {
        return new FieldState(this.type, check);
    }

    public String getCssClass() {
        if (this.type == Type.DEFAULT && this.check) {
            return "field--check";
        } else if (this.type == Type.STARTING_POINT) {
            return "field--starting-point";
        } else if (this.type == Type.DESTINATION) {
            return "field--destination";
        } else {
            return null;
        }
    }

    private Type type = Type.DEFAULT;
    private boolean check = false;
}