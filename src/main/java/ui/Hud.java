package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

/**
 * this class will handle all the on-screen UI
 *
 * @author Haruka Mibuchi
 */
public class Hud {

    Font arial_40;
    double timer;
    DecimalFormat deciF = new DecimalFormat("#0.00");
    GameFrame gf;

    public Hud() {
        this.gf = GameFrame.getInstance();
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    /**
     * This method will display some texts on the screen such as time and fame
     */
    public void draw(Graphics2D g2d) {

        g2d.setFont(arial_40);
        g2d.setColor(Color.black);

        // showing time on the screen
        timer += (double) 1 / 60;
        float deciTimer = Float.parseFloat(deciF.format(timer));

        // setting the time for bonus rward to pop up on the screen at certain time
        if (deciTimer % 10.00 == 0 && deciTimer <= 50.00) {
            int intTimer = (int) deciTimer;
            int rewardNum = intTimer / 10;
            gf.loadBonusRewards(rewardNum);
        }

        g2d.drawString("Time: " + deciF.format(timer), 50, 750);

        g2d.drawString("Fame:  " + gf.getScore(), 900, 750);

    }

}
