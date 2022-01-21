package sample;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Inventory extends JPanel {

    String title , image;
    Product product;
    int ID;

    Inventory(String title , String image , Product product){
        this.title = title;
        this.image = image;
        this.product = product;
        this.ID = product.getProductId();
    }

    public void paintComponent (Graphics g) {
        int xpos = 10,ypos = 10;
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawRect(10, 10, 110, 140);
        if (image != null) {
            BufferedImage theimage = null;
            try {
                theimage = ImageIO.read(new File("src/imgs/" + image));
                //final BufferedImage image = ImageIO.read(new File(this.getImg()));
                g.drawImage(theimage, 14, 44,100,100, null);
                Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 10);
                g.setFont(f2);
                g.drawString("ID: "+product.getProductId() + " : "+ title,20,30);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
