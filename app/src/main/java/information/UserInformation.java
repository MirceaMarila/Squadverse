package information;

public class UserInformation {
    // TODO: - adauga informatiile suplimentare
    private String Username;
    private String Email;
    private String Password;
    private String Avatar;

    //constructors
    public UserInformation(){

    }

    public UserInformation(String username, String email, String password, String avatar_name){
        Username = username;
        Email = email;
        Password = password;
        Avatar = avatar_name;
    }

    //getters and setters
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        this.Username = username;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        this.Avatar = avatar;
    }
}
