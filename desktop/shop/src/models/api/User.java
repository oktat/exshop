package models.api;

public class User {
    String name;
    String email;
    String password;
    String password_confirmation;
    public User(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.password = pass;
        this.password_confirmation = pass;
    }    
}
