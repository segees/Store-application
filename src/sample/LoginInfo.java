package sample;

public class LoginInfo implements Login_Interface{

    //attributes
    private String FirstName;
    private String LastName;
    private String EmailAddress;
    private String Password;

    //getters
    @Override
    public String getPassword() {return Password;}
    @Override
    public String getFirstName() {
        return FirstName;
    }
    @Override
    public String getLastName() { return LastName; }
    @Override
    public String getEmailAddress() {
        return EmailAddress;
    }

    //setters
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public void setPassword(String password) {Password = password;}
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }
}
