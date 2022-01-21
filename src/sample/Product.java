package sample;

public class Product implements Cate {

    private int ProductId;
    private int Quantity;
    private double ProductPrice;
    private String ProductDesc;
    private String ProductCategory;
    private String LastUpdate;
    private String imageName;

    public Product(){
    }

    public Product(int productId, int quantity, double productPrice, String productDesc, String productCategory, String lastUpdate) {
        ProductId = productId;
        Quantity = quantity;
        ProductPrice = productPrice;
        ProductDesc = productDesc;
        ProductCategory = productCategory;
        LastUpdate = lastUpdate;
    }

    public String getProductCategory() {
        return ProductCategory;
    }

    public void setProductCategory(String productCategory) {
        ProductCategory = productCategory;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public int getProductId() {
        return ProductId;
    }

    public String getProductDesc() {
        return ProductDesc;
    }

    public void setProductDesc(String productDesc) {
        ProductDesc = productDesc;
    }

    public String getLastUpdate() {
        return LastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        LastUpdate = lastUpdate;
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
    public double getProductPrice() {
        return ProductPrice;
    }
    public void setProductPrice(double productPrice) {
        ProductPrice = productPrice;
    }

    public boolean equals(Product product){
        return (this.getProductId() == product.getProductId());
    }

    public String toString(){
        return " PRODUCT ID : "+ this.getProductId() + ", PRODUCT PRICE : " + this.getProductPrice() + " "
                + ", PRODUCT QTY : " + this.getProductPrice() + " "+ ", PRODUCT DESC : " + this.getProductDesc() + "\n";
    }

    @Override
    public String getCategoryName() {
        return ProductCategory;
    }

    @Override
    public void setCategoryName(String categoryName) {
        this.ProductCategory = categoryName;
    }
}