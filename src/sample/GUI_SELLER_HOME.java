package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static sample.GUI_MAIN.ICON;

public class GUI_SELLER_HOME {

    public static String TITLE = " Online Store  - SELLER ";
    public static int SIZE = 700, HEIGHT = 500; // width and height
    DBConnection dbConnection = new DBConnection(); // db connection
    int CURRENT_PRODUCT_ID = 0 , CURRENT_CTEGORY_ID = 0;

    String USER_EMAIL= "";
    String USER_ID = "";
    JFileChooser fileChooser; // file chooser
    String selectedFileName = "";

    //for cart list...
    static ArrayList<CartProduct> cartList  = new ArrayList<CartProduct>();

    JFrame JFrame;
    BorderLayout borderLayout;


    JTextArea jTextField;

    public GUI_SELLER_HOME(String USER_ID) throws SQLException, ClassNotFoundException, IOException {
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
        JFrame.setLocationRelativeTo(null);
        Image icon = Toolkit.getDefaultToolkit().getImage(ICON);
        JFrame.setIconImage(icon);
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
        JLabel lbl = new JLabel(" YOU ARE LOGGED IN AS : " + USER_EMAIL);
        lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 14));
        top.setBackground(Color.white);

        top.add(lbl);

        return top;
    }


    public void AddCategory(){

    JFrame jFrame2 = new JFrame();
    jFrame2.setTitle(" ADD PRODUCT CATEGORY - STORE ");

    JPanel top  = new JPanel();
    top.setPreferredSize(new Dimension(250,300));
    JLabel lbl = new JLabel(" < ENTER PRODUCT CATEGORY > ");
    lbl.setBorder(new EmptyBorder(10,10,10,10));
    lbl.setFont(new Font("sans-serif", Font.BOLD, 17));
    top.setBackground(Color.LIGHT_GRAY);
    top.add(lbl);

    Box box = Box.createVerticalBox();
    top.add(box);
    jFrame2.add(top, BorderLayout.CENTER);

    JLabel stLabel = new JLabel(" Product Category / ID: ");box.add(stLabel);
    JTextField category = new JTextField("");box.add(category);
    category.setPreferredSize(new Dimension(150,35));

    JButton addCategory = new JButton("   ADD PRODUCT CATEGORY   !");box.add(addCategory);

    addCategory.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!category.getText().isEmpty()){

                try {
                    String cate = category.getText().toString();
                    dbConnection.insertCate(cate);
                    JOptionPane.showMessageDialog(JFrame,"category added success!");
                    category.setText("");

                } catch (SQLException exception) {
                    exception.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(JFrame,"Please enter the id to perform operations!");
            }
        }
    });

    JButton deleteCategory = new JButton(" DELETE PRODUCT CATEGORY !");box.add(deleteCategory);

    deleteCategory.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            try{

                Integer ID = Integer.parseInt(category.getText().toString());
                dbConnection.deleteCate(ID);
                JOptionPane.showMessageDialog(JFrame," PRODUCT CATEGORY DELETED SUCCESS !");
                category.setText("");

            }catch (Exception exception){
                JOptionPane.showMessageDialog(JFrame,"Please enter valid ID !");
            }


        }
    });

    JButton getCategory = new JButton(" GET PRODUCT CATEGORY !");box.add(getCategory);

    getCategory.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            try{

                Integer ID = Integer.parseInt(category.getText());
                ArrayList<String> list = dbConnection.getCateById(ID);
                String[] data = list.get(0).split(",");

                CURRENT_CTEGORY_ID = Integer.parseInt(data[0]);

                category.setText(""+data[1]);

            }catch (Exception exception){
                JOptionPane.showMessageDialog(JFrame,"Please enter valid ID !");
            }


        }
    });

    JButton updateCategory = new JButton(" UPDATE PRODUCT CATEGORY !");box.add(updateCategory);
    updateCategory.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            if(CURRENT_CTEGORY_ID!= 0){

                try {
                    if(!category.getText().isEmpty()) {
                        String cate = category.getText().toString();
                        dbConnection.udpateCate(cate, CURRENT_CTEGORY_ID);

                        JOptionPane.showMessageDialog(JFrame," category updated...!");

                    }else{
                        JOptionPane.showMessageDialog(JFrame,"Please enter any name of category...!");
                    }
                } catch (SQLException exception) {
                    exception.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }else{
                JOptionPane.showMessageDialog(JFrame,"Please enter valid id \n then click on get ... then change\n " +
                        "then update ! !");
            }


        }
    });


    jFrame2.setSize(new Dimension(350,300));
    jFrame2.setLocationRelativeTo(null);
    jFrame2.setVisible(true);
    jFrame2.setResizable(false);

    }

    //Payment
    public void AddProduct() throws SQLException, ClassNotFoundException {

        JFrame jFrame2 = new JFrame();
        jFrame2.setTitle(" ADD PRODUCT DETAILS - STORE ");

        JPanel top  = new JPanel();
        top.setPreferredSize(new Dimension(250,400));
        JLabel lbl = new JLabel(" < ENTER PRODUCT DETAILS > ");
        lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 17));
        top.setBackground(Color.LIGHT_GRAY);
        top.add(lbl);

        Box box = Box.createVerticalBox();
        top.add(box);
        jFrame2.add(top, BorderLayout.CENTER);

        JButton selectImage = new JButton(" SELECT PRODUCT IMAGE !");
        box.add(selectImage);

        //for selected image
        JLabel label = new JLabel("");
        box.add(label);


        selectImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(new ImageFilter());
                fileChooser.setAcceptAllFileFilterUsed(false);

                int option = fileChooser.showOpenDialog(JFrame);
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();

                    File destination = new File("src/imgs/", file.getName());
                    boolean success = file.renameTo(destination);
                    if(success){
                        selectedFileName = file.getName();
                        label.setText(" Image Selected: " + file.getName());
                    }else{
                        selectedFileName = file.getName();
                        label.setText(" Image Selected: " + file.getName());
                    }

                }else{
                    label.setText(" Image not selected !");
                }


            }
        });

        JLabel stLabel = new JLabel(" Product Qty/Stock : ");box.add(stLabel);
        JTextField quantity = new JTextField("");box.add(quantity);
        quantity.setPreferredSize(new Dimension(150,35));

        JLabel stateLabel = new JLabel(" Product Price: ");box.add(stateLabel);
        JTextField productPrice = new JTextField("");box.add(productPrice);
        productPrice.setPreferredSize(new Dimension(150,35));

        JLabel citylbl = new JLabel(" Product Desc: ");box.add(citylbl);
        JTextField product_Desc = new JTextField("");box.add(product_Desc);
        product_Desc.setPreferredSize(new Dimension(150,35));

        JLabel ctrylbl = new JLabel(" Product Category: ");box.add(ctrylbl);
        ArrayList<String> list = dbConnection.getCate();

        JComboBox<String> product_category = new JComboBox<String>(list.toArray(new String[0]));
        box.add(product_category);
        product_category.setPreferredSize(new Dimension(150,35));

        JButton jButton = new JButton(" ADD PRODUCT !");
        box.add(jButton);

        JLabel ctrylbl2 = new JLabel(" ENTER Product ID:(UPDATE/DELETE/GET) ");box.add(ctrylbl2);
        JTextField p_ID = new JTextField("");box.add(p_ID);
        p_ID.setPreferredSize(new Dimension(190,35));

        JButton getProuct = new JButton(" GET PRODUCT !");
        box.add(getProuct);

        getProuct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    Integer ID = Integer.parseInt(p_ID.getText().toString());
                    ArrayList<Product> list = dbConnection.getProductUsingID(ID);

                    if (list.size() == 0 ){
                        JOptionPane.showMessageDialog(JFrame," PRODUCT NOT FOUND !");
                    }else{

                        CURRENT_PRODUCT_ID = list.get(0).getProductId();
                        quantity.setText(""+list.get(0).getQuantity());
                        productPrice.setText(""+list.get(0).getProductPrice());
                        product_Desc.setText(""+list.get(0).getProductDesc());

                    }



                }catch (Exception exception){
                    JOptionPane.showMessageDialog(JFrame,"Please enter valid ID");
                }


            }
        });

        JButton updateProuct = new JButton(" UPDATE PRODUCT !");;
        box.add(updateProuct);

        updateProuct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!quantity.getText().isEmpty() && !productPrice.getText().isEmpty() && !product_Desc.getText().isEmpty()
                        && !selectedFileName.isEmpty()) {
                    try {

                        LocalDateTime now = LocalDateTime.now();
                        String d = now.getYear() + "-" + now.getDayOfMonth() + "-" + now.getDayOfMonth();

                        dbConnection.UpdateProduct(Integer.parseInt(quantity.getText()),Double.parseDouble(productPrice.getText()),product_Desc.getText(),
                                product_category.getSelectedItem().toString().split(",")[1],d,CURRENT_PRODUCT_ID,selectedFileName);

                        quantity.setText("");
                        productPrice.setText("");
                        product_Desc.setText("");
                        selectedFileName = "";
                        JOptionPane.showMessageDialog(JFrame, " Product has been updated successfully !");

                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(JFrame, "please select image and enter the details for product !");
                }
            }
        });

        JButton deleteProuct = new JButton(" DELETE PRODUCT !");
        box.add(deleteProuct);

        deleteProuct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    Integer ID = Integer.parseInt(p_ID.getText().toString());
                    dbConnection.deleteProduct(ID);

                    JOptionPane.showMessageDialog(JFrame," PRODUCT DELETED SUCCESS !");

                    p_ID.setText("");

                }catch (Exception exception){
                        JOptionPane.showMessageDialog(JFrame,"Please enter valid ID");
                }

            }
        });

        // on request button
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {




             if(!quantity.getText().isEmpty() && !productPrice.getText().isEmpty() && !product_Desc.getText().isEmpty()
                     && !selectedFileName.isEmpty()) {

                 try {

                     String id = dbConnection.getUserID(USER_EMAIL);

                     LocalDateTime now = LocalDateTime.now();
                     String d = now.getYear() + "-" + now.getDayOfMonth() + "-" + now.getDayOfMonth();

                    dbConnection.InsertProduct(Integer.parseInt(quantity.getText()),Double.parseDouble(productPrice.getText()),product_Desc.getText(),
                            product_category.getSelectedItem().toString().split(",")[1].toString(),d,selectedFileName);

                     quantity.setText("");
                     productPrice.setText("");
                     product_Desc.setText("");
                     selectedFileName = "";

                     JOptionPane.showMessageDialog(JFrame, " Product has been added successfully !");

                 } catch (ClassNotFoundException classNotFoundException) {
                     classNotFoundException.printStackTrace();
                 } catch (SQLException exception) {
                     exception.printStackTrace();
                 } catch (ParseException parseException) {
                     parseException.printStackTrace();
                 }

             }else{
                 JOptionPane.showMessageDialog(JFrame, "please select image and  enter the details for product !");
             }



            }
        });

        jFrame2.setSize(new Dimension(350,550));
        jFrame2.setLocationRelativeTo(null);
        jFrame2.setVisible(true);
        jFrame2.setResizable(false);

    }


    //cart GUI
    public JPanel cartGUI() throws IOException {

        //top
        JPanel top  = new JPanel();
        top.setPreferredSize(new Dimension(130,500));
        JLabel lbl = new JLabel(" CART ");
        lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 17));
        top.setBackground(Color.LIGHT_GRAY);
        top.add(lbl);

        JButton addcate, viewCategories , addproduct , viewcart, viewOrders, logout;

        viewCategories = new JButton("              View Categories           ");
        viewCategories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                ArrayList<String> list = dbConnection.getCate();
                if(list.size() > 0){
                    try {
                        ArrayList<String> list2 = dbConnection.getCate();
                        String d = "";

                        for (String d2 : list2){
                           d += d2 + "\n";
                        }

                        JOptionPane.showMessageDialog(JFrame, ""+ d);

                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }

                } catch (SQLException exception) {
                    exception.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        addcate = new JButton("         Add Categories       ");
        addcate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCategory();
            }
        });

        addproduct = new JButton("          Add Products         ");

        addproduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    AddProduct();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }

            }
        });

        viewcart = new JButton("          View Products          ");
        viewcart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(GUI_MAIN.product_list.size() > 0){
                    try {
                        GUI_MAIN.product_list = dbConnection.getProducts();
                        //jTextField.setText(GUI_MAIN.product_list.toString());

                        JOptionPane.showMessageDialog(JFrame,GUI_MAIN.product_list.toString());

                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }

                }

            }
        });

        Box b = Box.createVerticalBox();
        b.setBorder(BorderFactory.createLineBorder(Color.black));

        Image img3 = ImageIO.read(new File("src/imgs/logout.png"));
        Image newImage3 = img3.getScaledInstance(20, 20, Image.SCALE_DEFAULT);

        logout = new JButton("          LOGOUT          ");
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

        viewOrders = new JButton("              View Orders                ");

        viewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    ArrayList<Payment> list = dbConnection.getAllOrders();
                    System.out.println("TOTAL ORDERS : " + list.size());

                    if(list.size() > 0){
                        JOptionPane.showMessageDialog(JFrame,""+list.toString());
                    }



                } catch (SQLException exception) {
                    exception.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }


            }
        });

        b.add(addproduct);
        b.add(addcate);
        b.add(viewCategories);
        b.add(viewOrders);
        b.add(viewcart);
        b.add(logout);

        top.add(b);
        top.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));



        return top;
    }

    // method to show Products in the main screen
    public JScrollPane HomeScreen() throws SQLException, ClassNotFoundException {

        JPanel jPanel = new JPanel();
        jPanel.setBorder(new EmptyBorder(10,10,10,10));

        GUI_MAIN.product_list  = dbConnection.getProducts(); // getting the products...

        ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();

        JScrollPane scrollFrame = new JScrollPane(jPanel);
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFrame.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollFrame.setLayout(new ScrollPaneLayout());

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
                inventoryList.add(new Inventory(product.getProductDesc(), product.getImageName() ,product));
                jPanel.add(new Inventory(product.getProductDesc(), product.getImageName(),product));
                System.out.println("" + product.getProductDesc() + " \t " + product.getImageName());
            }
        }

        return scrollFrame;
    }


}
