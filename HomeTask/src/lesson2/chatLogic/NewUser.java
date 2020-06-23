package lesson2.chatLogic;

public class NewUser {
    private String userName;
    private String login;
    private String password;

    public NewUser(String userName, String login, String password) {
        this.userName = userName;
        this.login = login;
        this.password = password;
    }

    public NewUser() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
