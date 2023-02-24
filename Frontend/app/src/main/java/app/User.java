package app;

public class User {
    private String username;

    private String password;

    private String type;

    private int id;

    public User(int id, String username, String password, String type){
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
    }
    public String getUsername() {
        return this.username;
    }

    protected String getPassword() {
        return this.password;
    }

    protected String getType() {
        return this.type;
    }

    protected int getId() {
        return this.id;
    }

}
