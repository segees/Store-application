package sample;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class TestCases {

    /* testCase - 1*/
    @Test
    void testProduct(){

        Product product1 = new Product(1 ,5, 44.00,"product is about","cate","01/02/2020");
        Product product2 = new Product(1 ,5, 44.00,"product is about","cate","01/02/2020");

        //Equals...
        Assertions.assertEquals(true, product1.equals(product2));
    }

    /* testCase - 1*/
    @Test
    void testProduct2(){

        Product product1 = new Product(1 ,5, 44.00,"product is about","cate","01/02/2020");
        Product product2 = new Product(1 ,5, 44.00,"product is about","cate","01/02/2020");

        //Equals...
        Assertions.assertEquals(false, !product1.equals(product2));
    }

    /* testCase - 2*/
    @Test
    void testCardProduct(){

        CartProduct product1 = new CartProduct(111,56,200.00);
        CartProduct product2 = new CartProduct(111,56,200.00);

        //Equals...
        Assertions.assertEquals(true, product1.equals(product2));
    }

    /* testCase - 2*/
    @Test
    void testCardProduct2(){

        CartProduct product1 = new CartProduct(111,56,200.00);
        CartProduct product2 = new CartProduct(111,56,200.00);

        //Equals...
        Assertions.assertEquals(false, !product1.equals(product2));
    }

    /* db connection check - 3*/
    @Test
    void testDB() throws SQLException, ClassNotFoundException {

        DBConnection dbConnection = new DBConnection();
        //Equals...
        Assertions.assertEquals(true, dbConnection.isConnected());
    }


    /* testCase - 4*/
    @Test
    void testCustomer(){
        Customer customer = new Customer("A1","B2","3243242","a1@gmail.com","pass");
        Customer customer2 = new Customer("A1","B2","3243242","a1@gmail.com","pass");
        //Equals...
        Assertions.assertEquals(true, customer.equals(customer2));
    }

    /* testCase - 4*/
    @Test
    void testCustomer2(){
        Customer customer = new Customer("A1","B2","3243242","a1@gmail.com","pass");
        Customer customer2 = new Customer("A1","B2","3243242","a1@gmail.com","pass");
        //Equals...
        Assertions.assertEquals(false, !customer.equals(customer2));
    }



}