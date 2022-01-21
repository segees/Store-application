package sample;

import java.time.Year;
import java.util.Date;
import java.util.HashMap;
public class CustomerOrder {
        int Day;
        int Month;
        int Year;
       private HashMap<String,Integer> OrderDate=new HashMap<String,Integer>();

        private int CustomerOrderId;
        private int CustomerId;
        private double TotalPrice;
       // private Date OrderDate;
        private String OrderStatus;

    public HashMap<String, Integer> gethmap()
    {
        return this.OrderDate;
    }
    public void sethmap(HashMap OrderDate)
    {
        this.OrderDate.put("Year", Year);
        this.OrderDate.put("Month", Month);
        this.OrderDate.put("Day", Day);
    }



}
