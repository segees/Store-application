package sample;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DBConnection extends ManageDB{

	//for creating connection
	//Singleton design pattern instance
	public static Connection connection;
	// these credentials are for localServer
	private  String USERNAME = "root";
	private  String PASSWORD = "";
	// URL path to database
	//creating connection

	private String DB_URL = "jdbc:mysql://localhost:3306/store";
	public DBConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		connection = DriverManager.getConnection(DB_URL,USERNAME,PASSWORD);
	}

	ArrayList<Product> getProducts() throws SQLException, ClassNotFoundException {

		ArrayList<Product> list = new ArrayList<>();

		String query = " select * from product";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		while(rs.next()) {
			Product product = new Product(rs.getInt(1),rs.getInt(2),rs.getInt(3),
					rs.getString(4),
					""+rs.getString(5),""+rs.getString(6));
			product.setImageName(rs.getString(7)); // setting image name
			list.add(product);
		}

		return list;
	}

	ArrayList<Product> getProductUsingID(int id) throws SQLException, ClassNotFoundException {

		ArrayList<Product> list = new ArrayList<>();

		String query = " select * from product where productid = ?";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		// execute the preparedstatement
		preparedStmt.setInt(1, id);
		rs = preparedStmt.executeQuery();

		//if true
		while(rs.next()) {
			Product product = new Product(rs.getInt(1),rs.getInt(2),rs.getInt(3),
					rs.getString(4),
					""+rs.getString(5),""+rs.getString(6));
			product.setImageName(rs.getString(7)); // setting image name
			list.add(product);
		}

		return list;
	}

	// method for sign up user
	@Override
	void signUpUser(String firstName, String lastName, String  phoneNumber, String emailAddress, String password, String type) throws ClassNotFoundException, SQLException {

		//query to insert the data into table
		String query = " insert into users (firstname,lastname,phone,email,pass,usertype)"
				+ " values (?,?,?,?,?,?)";

		ResultSet rs = null;

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setString(1, firstName);
		preparedStmt.setString(2, lastName);
		preparedStmt.setString(3, phoneNumber);
		preparedStmt.setString(4, emailAddress);
		preparedStmt.setString(5, password);
		preparedStmt.setString(6, (type.equalsIgnoreCase("Seller")? "Seller" : "Customer"));
		// execute the preparedstatement
		preparedStmt.execute();

	}

	//to update the category
	void udpateCate(String cate, int id) throws SQLException, ClassNotFoundException {

		String query1 = " update cat set category = ? where catid = ?  ;";
		// create the mysql prepared statement
		PreparedStatement preparedStmt1 = this.getConnection().prepareStatement(query1);
		preparedStmt1.setString(1, cate);
		preparedStmt1.setInt(2, id);
		preparedStmt1.execute();

	}

	//to insert the category
	void insertCate(String cate) throws SQLException, ClassNotFoundException {

		String query1 = " insert into cat (category) values(?) ;";
		// create the mysql prepared statement
		PreparedStatement preparedStmt1 = this.getConnection().prepareStatement(query1);
		preparedStmt1.setString(1, cate);
		preparedStmt1.execute();

	}

	//to delete the category
	void deleteCate(int id) throws SQLException, ClassNotFoundException {

		String query1 = " delete from cat where catid = ?  ";
		// create the mysql preparedstatement
		PreparedStatement preparedStmt1 =
				this.getConnection().prepareStatement(query1);
		preparedStmt1.setInt(1, id);
		preparedStmt1.execute();
	}

	//method to get the categories
	ArrayList<String> getCateById(int id) throws SQLException, ClassNotFoundException {

		ArrayList<String> list = new ArrayList<>();

		String query = " select * from cat where catid = ? ";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		// execute the preparedstatement
		preparedStmt.setInt(1,id);
		rs = preparedStmt.executeQuery();

		//if true
		while(rs.next()) {
			list.add(rs.getString(1) + ","+rs.getString(2));
		}

		return list;
	}


	//method to get the categories
	ArrayList<String> getCate() throws SQLException, ClassNotFoundException {

		ArrayList<String> list = new ArrayList<>();

		String query = " select * from cat";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		while(rs.next()) {
			list.add(rs.getString(1) + ","+rs.getString(2));
		}

		return list;
	}


	//method to get the cart products
	ArrayList<CartProduct> getCartProducts() throws SQLException, ClassNotFoundException {

		ArrayList<CartProduct> list = new ArrayList<>();

		String query = " select * from cartproduct";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		while(rs.next()) {
			CartProduct product = new CartProduct(rs.getInt(1),rs.getInt(2),rs.getInt(3));
			list.add(product);
		}

		return list;
	}

	//to add the product
	void InsertProduct(int quantity, double productPrice, String productDesc, String productCategory, String lastUpdate, String img) throws ClassNotFoundException, SQLException, ParseException {

		String query = " insert into product (qty,productPrice,productDesc,category,lastUpdate,img)"
				+ " values (?,?,?,?,?,?)";

		ResultSet rs = null;

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setInt(1, quantity);
		preparedStmt.setDouble(2, productPrice);
		preparedStmt.setString(3, productDesc);
		preparedStmt.setString(4, productCategory);
		preparedStmt.setString(5, lastUpdate);
		preparedStmt.setString(6, img);
		// execute the preparedstatement
		preparedStmt.execute();
	}

	//to update the product
	void UpdateProduct(int quantity, double productPrice, String productDesc, String productCategory, String lastUpdate, int productID, String img) throws ClassNotFoundException, SQLException, ParseException {

		String query = " update product set qty = ?,productPrice = ?,productDesc = ?," +
				"category = ?,lastUpdate = ?,  img = ? where productid = ?"
				+ " ";

		ResultSet rs = null;

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setInt(1, quantity);
		preparedStmt.setDouble(2, productPrice);
		preparedStmt.setString(3, productDesc);
		preparedStmt.setString(4, productCategory);
		preparedStmt.setString(5, lastUpdate);
		preparedStmt.setString(6, img);//update img as well...
		preparedStmt.setInt(7, productID);
		// execute the preparedstatement
		preparedStmt.execute();

	}

	void InsertPayment(String customerID, String street, String state, String city, int postalCOde, String credtCardDetails, String country, String paymentDate, double totalPrice) throws ClassNotFoundException, SQLException {

		String query = " insert into payment (customerid,street,state,city,postalCode,creditCardDetails,country,paymentDate,totalPrice,status)"
				+ " values (?,?,?,?,?,?,?,?,?,?)";

		ResultSet rs = null;

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setInt(1, Integer.parseInt(customerID));
		preparedStmt.setString(2, street);
		preparedStmt.setString(3, state);
		preparedStmt.setString(4, city);
		preparedStmt.setInt(5, postalCOde);
		preparedStmt.setString(6, credtCardDetails);
		preparedStmt.setString(7, country);
		preparedStmt.setString(8, paymentDate);
		preparedStmt.setInt(9, (int) totalPrice);
		preparedStmt.setString(10, "pending");

		// execute the preparedstatement
		preparedStmt.execute();

	}

	//method to get the orders
	ArrayList<Payment> getAllOrders() throws SQLException, ClassNotFoundException {

		ArrayList<Payment> list = new ArrayList<>();

		String query = " select * from payment";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		while(rs.next()) {

			//Payment(String customerID, String street, String state, String city, int postalCOde, String credtCardDetails, String country, String paymentDate, double totalPrice) {
			Payment product = new Payment(""+rs.getInt(1),rs.getString(2),rs.getString(3)
					,rs.getString(4),rs.getInt("postalCode"),rs.getString("creditCardDetails"),rs.getString("country")
					,rs.getString("paymentDate"),rs.getInt("totalPrice"));

			product.setStatus(rs.getString("status"));
			product.setPaymentId(""+rs.getInt("paymentid"));

			list.add(product);
		}

		return list;
	}


	//to delete the user
	void deleteProduct(int id) throws SQLException, ClassNotFoundException {

		String query1 = " delete from product where productid = ?  ";
		// create the mysql preparedstatement
		PreparedStatement preparedStmt1 =
				this.getConnection().prepareStatement(query1);
		preparedStmt1.setInt(1, id);
		preparedStmt1.execute();
	}



	//method to get the orders
	ArrayList<Payment> getOrdersSummary(int id) throws SQLException, ClassNotFoundException {

		ArrayList<Payment> list = new ArrayList<>();

		String query = " select * from payment where customerid = ?";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setInt(1, id);
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		while(rs.next()) {

			//Payment(String customerID, String street, String state, String city, int postalCOde, String credtCardDetails, String country, String paymentDate, double totalPrice) {
			Payment product = new Payment(""+rs.getInt(1),rs.getString(2),rs.getString(3)
					,rs.getString(4),rs.getInt("postalCode"),rs.getString("creditCardDetails"),rs.getString("country")
					,rs.getString("paymentDate"),rs.getInt("totalPrice"));

			product.setStatus(rs.getString("status"));
			product.setPaymentId(""+rs.getInt("paymentid"));

			list.add(product);
		}

		return list;
	}

	//to get the user id
	String getUserID(String email) throws SQLException, ClassNotFoundException {

		String query = " select * from users where email = ? ";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setString(1, email);
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		if(rs.next()) {
			return rs.getString("id");
		}


		return null;
	}

	// method to add cart...
	void addToCart(int productid, int qty, int  price) throws ClassNotFoundException, SQLException {

		//query to insert the data into table
		String query = " insert into cartproduct (productid,qty,price)"
				+ " values (?,?,?)";

		ResultSet rs = null;

		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setInt(1, productid);
		preparedStmt.setInt(2, qty);
		preparedStmt.setInt(3, price);
		// execute the preparedstatement
		preparedStmt.execute();

	}

	//method to check login - User (Customer)
	boolean isUserLoginSuccess(String email, String pass) throws SQLException, ClassNotFoundException {

		String query = " select * from users where email = ? AND pass = ? AND usertype = ? ";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setString(1, email);
		preparedStmt.setString(2, pass);
		preparedStmt.setString(3, "Customer");
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		if(rs.next()) {
			return true;
		}


		return false;
	}

	//method to check login - User (Seller)
	boolean isSellerLoginSuccess(String email, String pass) throws SQLException, ClassNotFoundException {

		String query = " select * from users where email = ? AND pass = ? AND usertype = ? ";
		ResultSet rs = null;

		// create the mysql  preparedstatement
		PreparedStatement preparedStmt = this.getConnection().prepareStatement(query);
		preparedStmt.setString(1, email);
		preparedStmt.setString(2, pass);
		preparedStmt.setString(3, "Seller");
		// execute the preparedstatement
		rs = preparedStmt.executeQuery();

		//if true
		if(rs.next()) {
			return true;
		}


		return false;
	}

	//check if db is connected then return true
	public static boolean isConnected() throws ClassNotFoundException, SQLException {
		if(connection != null) {
			return true;
		}
		return false;
	}

	public Connection getConnection() {

		if(connection != null){
			return connection;
		}

		return null;
	}

}
