import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class Frame extends JFrame
{
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();

    public Frame()
    {
        this.setTitle("Rooster Graphics");
        this.setIconImage(new ImageIcon("Resources/Rooster.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(dimension.width/2-this.getSize().width/2, dimension.height/2-this.getSize().height/2);
        this.getContentPane().add(new OverallPanel());
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);

        //Resizing messes with the draws, disabled for now.
        this.setResizable(false);
    }
}