package NewSurveyApp;

public class User {
    public String name;
    public String email;
    public String phone;
    public String password;

    public User() {
    }

    public User(String name, String email, String phone, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public void setName(String name) {

    }

    public static void main(String[] args) {
        new LoginForm(null);
    }


}
