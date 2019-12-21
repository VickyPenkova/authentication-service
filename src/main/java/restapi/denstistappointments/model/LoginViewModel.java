package restapi.denstistappointments.model;

public class LoginViewModel {
    private String egnAsId; //EGN of Doctor or Patient
    private String password; //Password of Doctor or Patient

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEgnAsId() {
        return egnAsId;
    }

    public void setEgnAsId(String username) {
        this.egnAsId = username;
    }
}
