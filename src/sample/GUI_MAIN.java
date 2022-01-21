package sample;

import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.ImageView;

public class GUI_MAIN {

    public static String TITLE = " Online Store ";
    public static String ICON = "src/imgs/store.png"; // window icon path
    public static int SIZE = 400, HEIGHT = 300; // width and height
    DBConnection dbConnection = new DBConnection(); // db connection

    //for product list...
    static ArrayList<Product> product_list  = new ArrayList<Product>();
    //for product list...GUI


    JFrame JFrame;
    BorderLayout borderLayout;
    JComboBox<String> usertype;

    public GUI_MAIN() throws SQLException, ClassNotFoundException, IOException {
        borderLayout = new BorderLayout();
        JFrame = new JFrame(String.valueOf(borderLayout));
        JFrame.setTitle(TITLE);
        JFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //adding header to top
        JFrame.add(header(),BorderLayout.NORTH);


        if(dbConnection.isConnected()){
            System.out.println("DB connected");
        }

        loadData();

        JFrame.add(loginScreen(),BorderLayout.CENTER); // adding to center login screen


        Image icon = Toolkit.getDefaultToolkit().getImage(ICON);
        JFrame.setIconImage(icon);
        JFrame.setSize(new Dimension(SIZE,HEIGHT));
        JFrame.setLocationRelativeTo(null);
        JFrame.setVisible(true);
       // JFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        JFrame.setResizable(true);
    }

    //method to load data...
    private void loadData() throws SQLException, ClassNotFoundException {
    product_list.clear();

    //getting the product list...
    product_list  = dbConnection.getProducts(); // added to list...



    }

    //header text
    public JPanel header() throws IOException {

        //top
        JPanel top  = new JPanel();

        Box box = Box.createHorizontalBox();
        top.add(box);

        JLabel lbl = new JLabel(" Welcome to Online Store ");
       // lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 17));
        top.setBackground(Color.gray);

        BufferedImage myPicture = ImageIO.read(new File("src/imgs/store.png"));
        Image newImage = myPicture.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        JLabel picLabel = new JLabel(new ImageIcon(newImage));
        picLabel.setAlignmentX(-500);
        box.add(picLabel);
        box.add(lbl);

        top.add(box);

        return top;
    }

    //method for login Screen
    public JPanel loginScreen(){

        //login Part
        JPanel center = new JPanel(new GridLayout(3,1)); // login layout
        center.setPreferredSize(new Dimension(1000,600));
        center.setBorder(new EmptyBorder(10,10,10,20));
        //box.add(center);


        JLabel lbl2 = new JLabel(" LOGIN ");
        lbl2.setBorder(new EmptyBorder(10,10,10,10));
        lbl2.setFont(new Font("sans-serif", Font.BOLD, 15));

        JLabel userLabel = new JLabel("E-MAIL ");
        userLabel.setBorder(new EmptyBorder(2,2,2,2));
        JLabel passwordLabel = new JLabel("PASSWORD ");
        passwordLabel.setBorder(new EmptyBorder(2,2,2,2));
        JTextField email = new JTextField("");email.setPreferredSize(new Dimension(190,30));
        JPasswordField pass = new JPasswordField("");pass.setPreferredSize(new Dimension(190,30));
        JButton loginButton=new JButton(" LOGIN ");
        JButton signup=new JButton(" SIGNUP ");
        loginButton.setBounds(10,150,190,30);

        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                signup();

            }
        });

        //on login button click
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String EM = email.getText();
                String PS = pass.getText();

                try {

                    System.out.println("CONNECTION: " + dbConnection.isConnected());
                    System.out.println("CONNECTION: " + dbConnection.getConnection());


                    if(dbConnection.isUserLoginSuccess(EM,PS)){
                        JOptionPane.showMessageDialog(JFrame," CUSTOMER- LOGIN SUCCESS !");

                        JFrame.dispose();
                        String Email = EM;
                        GUI_HOME gui_home = new GUI_HOME(Email);


                    }else if(dbConnection.isSellerLoginSuccess(EM,PS)){

                        JOptionPane.showMessageDialog(JFrame," SELLER - LOGIN SUCCESS !");

                        JFrame.dispose();
                        String Email = EM;
                        GUI_SELLER_HOME gui_home = new GUI_SELLER_HOME(Email);

                    }else{
                        JOptionPane.showMessageDialog(JFrame," LOGIN FAILED , PLEASE ENTER VALID CREDENTIALS!");
                    }

                } catch (ClassNotFoundException | SQLException | IOException notFoundException) {
                    notFoundException.printStackTrace();
                }


            }
        });
        loginButton.setSize(new Dimension(1000,40));

        //adding widgets to center..
        //center.add(new JLabel(""));
        //center.add(lbl2);
        center.add(userLabel);
        center.add(email);
        center.add(passwordLabel);
        center.add(pass);
        center.add(signup);
        center.add(loginButton);

        return  center;
    }


    //method for the sign up part
    public void signup(){

        JFrame jFrame = new JFrame();
        jFrame.setTitle("SIGN UP");
        Image icon = Toolkit.getDefaultToolkit().getImage(ICON);
        jFrame.setIconImage(icon);
        JPanel jPanel = new JPanel();

        jPanel.setBorder(new EmptyBorder(10,10,10,10));
        jPanel.setPreferredSize(new Dimension(250,500));
        Box box = Box.createVerticalBox(); // to holding the widgets
        jPanel.setBackground(Color.lightGray);
        jPanel.add(box);

        JLabel lbl = new JLabel(" SIGN UP ");
        lbl.setBorder(new EmptyBorder(10,10,10,10));
        lbl.setFont(new Font("sans-serif", Font.BOLD, 15));
        box.add(lbl);

        //labels for sign up
        JLabel fLabel = new JLabel(" FIRST NAME ");
        JLabel lLabel = new JLabel(" LAST NAME ");
        JLabel emailLabel = new JLabel(" EMAIL ");
        JLabel passwordLabel = new JLabel(" PASSWORD ");
        JLabel phoneLabel = new JLabel(" PHONE ");
        passwordLabel.setBorder(new EmptyBorder(2,2,2,2));
        fLabel.setBorder(new EmptyBorder(2,2,2,2));
        lLabel.setBorder(new EmptyBorder(2,2,2,2));

        JTextField first = new JTextField("");first.setPreferredSize(new Dimension(190,30));
        JTextField last = new JTextField("");last.setPreferredSize(new Dimension(190,30));
        JTextField email = new JTextField("");email.setPreferredSize(new Dimension(190,30));
        JTextField phone = new JTextField("");phone.setPreferredSize(new Dimension(190,30));
        JPasswordField pass = new JPasswordField("");pass.setPreferredSize(new Dimension(190,30));
        JButton signButton =new JButton(" SIGN UP ");signButton.setPreferredSize(new Dimension(100,30));

        String[] list = {"customer","seller"};
        usertype = new JComboBox<>(list);

        box.add(fLabel);box.add(first);
        box.add(lLabel);box.add(last);
        box.add(emailLabel);box.add(email);
        box.add(passwordLabel);box.add(pass);
        box.add(phoneLabel);box.add(phone);
        box.add(usertype);
        box.add(signButton);

        signButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!first.getText().isEmpty() && !last.getText().isEmpty() &&
                        !emailLabel.getText().isEmpty() && !phone.getText().isEmpty() &&
                        !pass.getText().isEmpty()){

                    try {

                        if(pass.getText().length() >= 6) {

                            //customer object
                            Customer customer = new Customer(first.getText(), last.getText(), phone.getText(), email.getText(), pass.getText());

                            dbConnection.signUpUser(first.getText(), last.getText(), phone.getText(), email.getText(), pass.getText(),
                                    ""+usertype.getSelectedItem().toString());
                            JOptionPane.showMessageDialog(JFrame, " Account creation success!");

                            first.setText("");
                            last.setText("");
                            phone.setText("");
                            email.setText("");
                            pass.setText("");

                        }else{
                            //else message ...
                            JOptionPane.showMessageDialog(JFrame, " Password length should be grater than or equal to 6 !");
                        }

                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (SQLException exception) {
                        exception.printStackTrace();
                    }

                }else{
                    JOptionPane.showMessageDialog(JFrame , " Please enter valid info to signup !");
                }
            }
        });
        jFrame.setSize(new Dimension(300,500));
        jFrame.add(jPanel);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }

    /* Main method to run the application */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        new GUI_MAIN();
    }

}
