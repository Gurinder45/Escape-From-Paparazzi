package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

//this class will handle all the on-screen UI
public class UI {

    GamePanel gPanel;
    Font arial_40;
    double timer;
    DecimalFormat deciF = new DecimalFormat("#0.00");
    GameFrame gf;



    public UI(GameFrame gFrame) {
        this.gf = gFrame;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }
    
    public void draw(Graphics2D g2d) {
        g2d.setFont(arial_40);
        g2d.setColor(Color.black);

        //time
        timer += (double) 1/60;

        //SHOW minute and second separately

        g2d.drawString("Time: " + deciF.format(timer), 50, 750);

        g2d.drawString("Fame:  " + gf.getScore(), 900, 750);


    }
    
}
