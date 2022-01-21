package sample;

import java.sql.SQLException;

//abstract class
public abstract class ManageDB {

    //abstract method...
    abstract void signUpUser(String firstName, String lastName, String  phoneNumber, String emailAddress, String password, String type) throws ClassNotFoundException, SQLException;

}
