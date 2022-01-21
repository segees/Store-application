package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static sample.GUI_MAIN.ICON;

public class GUI_HOME {

    public static String TITLE = " Online Store ";
    public static int SIZE = 700, HEIGHT = 500; // width and height
    DBConnection dbConnection = new DBConnection(); // db connection

    String USER_EMAIL= "";
    String USER_ID = "";

    //for cart list...
    static ArrayList<CartProduct> cartList  = new ArrayList<CartProduct>();

    JFrame JFrame;
    BorderLayout borderLayout;

    //for cart
    JTextField product_id_field = new JTextField("");
    JButton addtoCart  = new JButton("ADD TO CART"), removeFromCart = new JButton("REMOVE FROM CART");

    public GUI_HOME(String USER_ID) throws SQLException, ClassNotFoundException, IOException {
        this.USER_EMAIL = USER_ID; // userID
        this.USER_ID = dbConnection.getUserID(USER_EMAIL); // getting user ID

        borderLayout = new BorderLayout();
        JFrame = new JFrame(String.valueOf(borderLayout));
        JFrame.setTitle(TITLE);
        JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adding header to top
        JFrame.add(header(),BorderLayout.NORTH);
        JFrame.add(bottom(),BorderLayout.SOUTH);

        if(dbConnection.isConnected()){
            System.out.println("DB connected");
        }

        loadData(); // load the data...


        JFrame.add(HomeScreen(), BorderLayout.CENTER);
        JFrame.add(cartGUI(), BorderLayout.EAST);
        JFrame.setSize(new Dimension(SIZE,HEIGHT));
        Image icon = Toolkit.getDefaultToolkit().getImage(ICON);
        JFrame.setIconImage(icon);
        JFrame.setLocationRelativeTo(null);
        JFrame.setVisible(true);
        JFrame.setResizable(true);
    }

    //method to load data...
    private void loadData() throws SQLException, ClassNotFoundException {
    System.out.println("Total products : " + GUI_MAIN.product_list.size());

    }

    //header gui
    public JPanel header(){

        //top
        JPanel top  = new JPanel();
        top.setBackground(Color.ORANGE);
        top.setBorder(new EmptyBorder(5,5,5,5));
        JLabel lbl = new JLabel(" Welcome to Online Store ");
        lbl.setBackground(Color.ORANGE);
        lbl.setFont(new Font("sans-serif", Font.BOLD, 17));

        top.add(lbl);

        return top;
    }

    //bottom gui
    public JPanel bottom(){

        //top
        JPanel top  = new JPanel();
        JLabel lbl = new JLabel(" ENTER PRODUCT ID");
        lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 14));
        top.setBackground(Color.white);

        Box box = Box.createHorizontalBox();

        box.add(lbl);

        product_id_field.setPreferredSize(new Dimension(100,15));
        box.add(product_id_field);

        JLabel lbl2 = new JLabel(" PRODUCT QTY");
        lbl2.setBorder(new EmptyBorder(10,10,10,10));
        lbl2.setFont(new Font("sans-serif", Font.BOLD, 14));
        box.add(lbl2);

        JTextField product_qty = new JTextField();
        product_qty.setPreferredSize(new Dimension(50,15));
        box.add(product_qty);

        box.add(addtoCart);
        box.add(removeFromCart);

        //remove cart button click
        removeFromCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cartList.size() > 0){
                    cartList.remove(cartList.size()-1);
                }

            }
        });

        //add to cart button click
        addtoCart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                boolean a = false;

                if(!product_id_field.getText().isEmpty() && !product_qty.getText().isEmpty() &&
                Integer.parseInt(product_qty.getText()) >= 1) {

                    Integer id = Integer.parseInt(product_id_field.getText());
                    for (int i = 0; i < GUI_MAIN.product_list.size(); i++) {
                        if (id == GUI_MAIN.product_list.get(i).getProductId()) {
                            a = true;

                            //added to cart...
                            cartList.add(new CartProduct(GUI_MAIN.product_list.get(i).getProductId(),Integer.parseInt(product_qty.getText()),
                                    GUI_MAIN.product_list.get(i).getProductPrice()));

                            product_id_field.setText("");
                            product_qty.setText("");

                        }
                    }

                    if (!a) {
                        JOptionPane.showMessageDialog(JFrame, " PRODUCT NOT FOUND ! WITH THIS ID : " + id);
                    } else {
                        JOptionPane.showMessageDialog(JFrame, " PRODUCT ADDED TO CART !");
                    }

                }else{
                    JOptionPane.showMessageDialog(JFrame, " PLZ ENTER THE PRODUCT ID & QTY!\n Note: qty should be >= 1");
                }

            }
        });

        top.add(box);

        return top;
    }


    //Payment
    public void AddPayment(){

        JFrame jFrame2 = new JFrame();
        jFrame2.setTitle("PAYMENT DETAILS!");

        JPanel top  = new JPanel();
        top.setPreferredSize(new Dimension(150,500));
        JLabel lbl = new JLabel(" < ENTER PAYMENT DETAILS > ");
        lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 17));
        top.setBackground(Color.LIGHT_GRAY);
        top.add(lbl);

        Box box = Box.createVerticalBox();
        top.add(box);

        //
        jFrame2.add(top, BorderLayout.CENTER);

        JLabel stLabel = new JLabel(" STREET: ");box.add(stLabel);
        JTextField street = new JTextField("");box.add(street);
        street.setPreferredSize(new Dimension(190,35));

        JLabel stateLabel = new JLabel(" STATE: ");box.add(stateLabel);
        JTextField state = new JTextField("");box.add(state);
        state.setPreferredSize(new Dimension(190,35));

        JLabel citylbl = new JLabel(" CITY: ");box.add(citylbl);
        JTextField city = new JTextField("");box.add(city);
        city.setPreferredSize(new Dimension(190,35));

        JLabel ctrylbl = new JLabel(" COUNTRY: ");box.add(ctrylbl);
        JTextField country = new JTextField("");box.add(country);
        country.setPreferredSize(new Dimension(190,35));

        JLabel postlbl = new JLabel(" POSTAL CODE: ");box.add(postlbl);
        JTextField postalcode = new JTextField("");box.add(postalcode);
        postalcode.setPreferredSize(new Dimension(190,35));

        JLabel creditCardDetailslable = new JLabel(" CreditCard Info/Delivery Address: ");box.add(creditCardDetailslable);
        JTextField creditCard = new JTextField("");box.add(creditCard);
        creditCard.setPreferredSize(new Dimension(190,35));

        JButton jButton = new JButton("REQUEST ORDER !");
        jButton.setPreferredSize(new Dimension(190,35));
        box.add(jButton);

        // on request button
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


             if(!street.getText().isEmpty() && !state.getText().isEmpty() && !city.getText().isEmpty() &&
                     !postalcode.getText().isEmpty() && !creditCard.getText().isEmpty()) {

                 try {

                     String id = dbConnection.getUserID(USER_EMAIL);

                     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                     LocalDateTime now = LocalDateTime.now();

                     dbConnection.InsertPayment(id, street.getText(),state.getText(),city.getText(),
                             Integer.parseInt(postalcode.getText()),creditCard.getText(),country.getText(),""+dtf.format(now),getTotalPrice());

                     street.setText("");
                     state.setText("");
                     city.setText("");
                     country.setText("");
                     creditCard.setText("");

                     JOptionPane.showMessageDialog(JFrame, " Order has been sent successfully !");

                 } catch (ClassNotFoundException classNotFoundException) {
                     classNotFoundException.printStackTrace();
                 } catch (SQLException exception) {
                     exception.printStackTrace();
                 }

             }else{
                 JOptionPane.showMessageDialog(JFrame, "please enter the details for order !");
             }



            }
        });

        jFrame2.setSize(new Dimension(300,600));
        jFrame2.setLocationRelativeTo(null);
        jFrame2.setVisible(true);
        jFrame2.setResizable(false);

    }

    //to get the total price ...
    Double getTotalPrice(){
        Double total = 0.0;
        for (int i = 0; i <cartList.size() ; i++) { total += cartList.get(i).getPrice() * cartList.get(i).getQuantity(); }
        return total;
    }

    //cart GUI
    public JPanel cartGUI() throws IOException {

        //top
        JPanel top  = new JPanel();
        top.setPreferredSize(new Dimension(150,500));
        JLabel lbl = new JLabel(" CART ");
        lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 17));
        top.setBackground(Color.LIGHT_GRAY);
        top.add(lbl);

        JButton viewcart,checkout, viewOrders,clearcheckout, logout;

        Image img = ImageIO.read(new File("src/imgs/cart.png"));
        Image newImage = img.getScaledInstance(20, 20, Image.SCALE_DEFAULT);


        viewcart = new JButton("   View Your Cart  ");
        viewcart.setSize(20,200);
        viewcart.setIcon(new ImageIcon(newImage));


        Image img2 = ImageIO.read(new File("src/imgs/checkout.png"));
        Image newImage2 = img2.getScaledInstance(20, 20, Image.SCALE_DEFAULT);

        checkout = new JButton("         Checkout       ");
        checkout.setIcon(new ImageIcon(newImage2));
        viewcart.setIcon(new ImageIcon(newImage2));




        Box b = Box.createVerticalBox();
        b.setBorder(BorderFactory.createLineBorder(Color.black));

        Image img3 = ImageIO.read(new File("src/imgs/logout.png"));
        Image newImage3 = img3.getScaledInstance(20, 20, Image.SCALE_DEFAULT);

        // checkout button click...
        checkout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(cartList.size() > 0)
                AddPayment();
                else JOptionPane.showMessageDialog(JFrame,"PLEASE AD PRODUCTS TO CART THEN CHECKOUT !");


            }
        });


        logout = new JButton("          LOGOUT        ");
        logout.setIcon(new ImageIcon(newImage3));
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame.dispose();
                try {
                    new GUI_MAIN();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });


        viewcart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


               String info  = "";

               if(cartList.size() > 0) {
                   info += "" + "PRODUCT ADDED TO CART :\n";

                   for (int i = 0; i < cartList.size(); i++) {

                       Product p = new Product();
                       p.setProductPrice(cartList.get(i).getPrice());
                       p.setProductId(cartList.get(i).getProductId());
                       p.setQuantity(cartList.get(i).getQuantity());
                       info += "PRODUCT ID: " + p.getProductId() + " , " + "PRODUCT PRICE: " + p.getProductPrice() + "\n";
                       System.out.println("CART SIZE : " + cartList.size());

                   }

                   //show message...
                   JOptionPane.showMessageDialog(JFrame, ""+ cartList.toString());

               }else{
                   //show message...
                   info = " THERE IS NO ITEM !";
                   JOptionPane.showMessageDialog(JFrame, ""+ info);
               }



            }
        });

        viewOrders = new JButton(" View Order Summary ");

        viewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    ArrayList<Payment> list = dbConnection.getOrdersSummary(Integer.parseInt(dbConnection.getUserID(USER_EMAIL)));
                    System.out.println("TOTAL ORDERS : " + list.size());

                    if(list.size() > 0)
                    JOptionPane.showMessageDialog(JFrame," < YOUR ORDERS > \n " +
                            list.toString());


                } catch (SQLException exception) {
                    exception.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }


            }
        });


        clearcheckout = new JButton("     Clear Cart Bucket     ");
        clearcheckout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cartList.clear();

            }
        });

        b.add(viewOrders);
        b.add(clearcheckout);
        b.add(viewcart);
        b.add(checkout);
        b.add(logout);

        top.add(b);
        top.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));



        return top;
    }

    // method to show Products in the main screen
    public JScrollPane HomeScreen() throws SQLException, ClassNotFoundException {

        JPanel jPanel = new JPanel(new GridLayout());
        jPanel.setPreferredSize(new Dimension(100,200));
        jPanel.setBorder(new EmptyBorder(10,10,10,10));

        GUI_MAIN.product_list  = dbConnection.getProducts(); // getting the products...

        ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
        jPanel.setLayout(new GridLayout());

        if(GUI_MAIN.product_list.size() > 0) {

            if(GUI_MAIN.product_list.size() == 1) {
                jPanel.setLayout(new GridLayout(1, 1));
            }else if(GUI_MAIN.product_list.size() == 2) {
                jPanel.setLayout(new GridLayout(1, 2));
            }else if(GUI_MAIN.product_list.size() == 3) {
                jPanel.setLayout(new GridLayout(1, 3));
            }else if(GUI_MAIN.product_list.size() == 4) {
                jPanel.setLayout(new GridLayout(1, 4));
            }else{
                jPanel.setLayout(new GridLayout(GUI_MAIN.product_list.size()/2, 3));
            }

            for (Product product : GUI_MAIN.product_list) {
                inventoryList.add(new Inventory(product.getProductDesc(), product.getImageName(),product));
                jPanel.add(new Inventory(product.getProductDesc(), product.getImageName(),product));
                System.out.println("" + product.getProductDesc() + " \t " + product.getImageName());
            }

            for (Inventory inventory : inventoryList){

            }

        }

        JScrollPane scrollFrame = new JScrollPane(jPanel);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        // jPanel.add(scrollFrame);

        return scrollFrame;
    }


}
