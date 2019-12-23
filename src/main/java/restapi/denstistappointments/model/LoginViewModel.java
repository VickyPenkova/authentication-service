package restapi.denstistappointments.model;

public class LoginViewModel {
    private String username; //EGN of Doctor or Patient
    private String password; //Password of Doctor or Patient

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
