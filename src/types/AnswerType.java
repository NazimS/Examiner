package types;

/**
 *
 * @author Hp-Pc
 */
public enum AnswerType {
    TRUE("true"), FALSE("false"), NONE;

    private AnswerType() {
    }

    private AnswerType(String type) {
        this.type = type;
    }
    private String type;

    public static AnswerType parseFrom(String type) {
        for (AnswerType at : values()) {
            if (at.type.equalsIgnoreCase(type)) {
                return at;
            }
        }
        return NONE;
    }

    public static AnswerType parseFrom(boolean type) {
        if (type) {
            return TRUE;
        } else {
            return FALSE;
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
