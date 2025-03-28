package cs.project.evolt.Response;

import cs.project.evolt.model.User;

public class AuthResponse {
    private String message;
    private User user;

    public AuthResponse(String message, User username) {
        this.message = message;
        this.user = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}