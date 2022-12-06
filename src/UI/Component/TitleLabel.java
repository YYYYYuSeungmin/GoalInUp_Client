package UI.Component;

import javax.swing.*;
import java.awt.*;

public class TitleLabel extends JLabel {
    public TitleLabel(JFrame jFrame, int width, int height, int x, int y) {
        this.setText("GOAL IN UP");
        this.setSize(width,height);
        //this.setOpaque(true);
        this.setFont(new Font("SanSerif", Font.BOLD, 60));
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setLocation(x, y);

        jFrame.add(this);
    }
}
