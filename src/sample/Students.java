package sample;

public class Students {
    private  int id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private String borrowedBooks="";
    Students(int i, String f, String l, String e, String login, String p, String borrowedBooks){
        setId(i);
        setFirstName(f);
        setLastName(l);
        setEmail(e);
        setLogin(login);
        setPassword(p);
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
