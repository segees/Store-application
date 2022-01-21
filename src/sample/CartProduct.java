package sample;

public class CartProduct implements  Product_Interface{

    private int ProductId;
    private int Quantity;
    private double Price;

    public CartProduct(int productId, int quantity, double price) {
        ProductId = productId;
        Quantity = quantity;
        Price = price;
    }

    public int getProductId() {
        return ProductId;
    }
    public void setProductId(int productId) {
        ProductId = productId;
    }
    public int getQuantity() {
        return Quantity;
    }
    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
    public double getPrice() {
        return Price;
    }
    public void setPrice(double price) {
        Price = price;
    }

    @Override
    public String toString(){
        return "[ PID : " +this.getProductId()+ "]" + "[ PRICE : " +this.getPrice()+ "]"
                + "[ QTY : " +this.getQuantity()+ "]\n";
    }

    @Override
    public boolean equals(CartProduct product){
        return (this.getProductId() == product.getProductId());
    }

}
