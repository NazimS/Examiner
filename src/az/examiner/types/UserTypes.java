package az.examiner.types;

/**
 *
 * @author Hp-Pc
 */
public enum UserTypes {

    STUDENT("student"), TEACHER("teacher"), NONE;

    private UserTypes() {
    }

    private UserTypes(String type) {
        this.type = type;
    }
    private String type;

    public static UserTypes parseFrom(String type) {
        for (UserTypes ut : values()) {
            if (ut.type.equalsIgnoreCase(type)) {
                return ut;
            }
        }
        return NONE;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
