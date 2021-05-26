package co.com.evertec.model.enumerator;

import co.com.evertec.model.common.Constants;

public enum UserEnum {
    USER("admin", "admin@evertec.com", "admin", Constants.ROLE_USER);

    private String username;
    private String email;
    private String password;
    private String role;

    UserEnum(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public static UserEnum authentication(String username, String password) {
        for (UserEnum user : UserEnum.values()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        return null;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
