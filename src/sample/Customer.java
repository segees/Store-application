package sample;

import java.util.HashMap;

public class Customer {

    private String FirstName;
    private String LastName;
    private String PhoneNumber;
    private String EmailAddress;
    private String Password;

    Customer(){
    }

    //constructor
    public Customer(String firstName, String lastName, String phoneNumber, String emailAddress, String password) {
        FirstName = firstName;
        LastName = lastName;
        PhoneNumber = phoneNumber;
        EmailAddress = emailAddress;
        Password = password;
    }

    //getter and setters
    public String getPassword() {return Password;}
    public void setPassword(String password) {Password = password;}
    public String getFirstName() {
        return FirstName;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public String getLastName() {
        return LastName;
    }
    public void setLastName(String lastName) { LastName = lastName; }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
    public String getEmailAddress() {
        return EmailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public boolean equals(Customer c){
        return (this.getEmailAddress() == c.getEmailAddress());
    }

}
