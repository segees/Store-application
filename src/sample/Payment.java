package sample;

import java.util.HashMap;

public class Payment {

    private String customerID;
    private String Street;
    private String State;
    private String City;
    private int PostalCOde;
    private String credtCardDetails;
    private String Country;
    private String paymentDate;
    private double totalPrice;
    private String status;
    private String paymentId;

    public Payment(String customerID, String street, String state, String city, int postalCOde, String credtCardDetails, String country, String paymentDate, double totalPrice) {
        this.customerID = customerID;
        Street = street;
        State = state;
        City = city;
        PostalCOde = postalCOde;
        this.credtCardDetails = credtCardDetails;
        Country = country;
        this.paymentDate = paymentDate;
        this.totalPrice = totalPrice;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public int getPostalCOde() {
        return PostalCOde;
    }

    public void setPostalCOde(int postalCOde) {
        PostalCOde = postalCOde;
    }

    public String getCredtCardDetails() {
        return credtCardDetails;
    }

    public void setCredtCardDetails(String credtCardDetails) {
        this.credtCardDetails = credtCardDetails;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    //method to get the payment data....
    public String toString(){
        return " ORDER" +
                " [ PaymentID : " + this.getPaymentId() + " ] " +
                " [ PaymentDate : " + this.getPaymentDate() + " ] " +
                "[ TotalPrice : " + this.getTotalPrice() + " ]" +
                "[ Status : " + this.getStatus() + " ]" +
                " \n ";
    }

}
