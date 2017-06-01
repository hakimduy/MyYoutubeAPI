package vn.edu.funix.myyoutube;


public class User {

    public int id;
    public String userName;
    public String password;
    public String fullName;
    public String email;


    public User(int id, String userName, String password, String fullName, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;

    }
}