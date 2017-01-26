package pojos;

import types.UserTypes;

/**
 *
 * @author Hp-Pc
 */
public class User {

    private int userId;
    private String name;
    private String surname;
    private UserTypes userType;
    private String email;
    private String password;
    private boolean enabled;
    private byte attemptCount;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public UserTypes getUserType() {
        return userType;
    }

    public void setUserType(UserTypes userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public byte getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(byte attemptCount) {
        this.attemptCount = attemptCount;
    }

}
