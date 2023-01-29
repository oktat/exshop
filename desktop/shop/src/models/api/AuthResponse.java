package models.api;

public class AuthResponse {
    String token;
    String name;
    boolean success;
    public String getToken() {
        return token;
    }
    public String getName() {
        return name;
    }
    public boolean getSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
}
