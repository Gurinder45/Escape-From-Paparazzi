/**
 * this class will handle all the on-screen UI
 *  この間にコメントを記述する
 *
 */
package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class Hud {

    Font arial_40;
    double timer;
    DecimalFormat deciF = new DecimalFormat("#0.00");
    GameFrame gf;

    public Hud(GameFrame gFrame) {
        this.gf = gFrame;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(arial_40);
        g2d.setColor(Color.black);

        // time
        timer += (double) 1 / 60;
        float deciTimer = Float.parseFloat(deciF.format(timer));

        if (deciTimer % 5.00 == 0 && deciTimer <= 25.00) {
            int intTimer = (int) deciTimer;
            int rewardNum = intTimer / 5;
            gf.loadBonusRewards(rewardNum);
        }

        // SHOW minute and second separately

        g2d.drawString("Time: " + deciF.format(timer), 50, 750);

        g2d.drawString("Fame:  " + gf.getScore(), 900, 750);

    }

}
