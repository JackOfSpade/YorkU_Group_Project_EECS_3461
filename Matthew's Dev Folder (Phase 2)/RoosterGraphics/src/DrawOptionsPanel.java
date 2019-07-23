import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class DrawOptionsPanel extends JPanel
{
    public DrawOptionsPanel()
    {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        SpringLayout layout = new SpringLayout();

        ImageIcon toolsIcon = resizeIcon(new ImageIcon("Resources/Tools.png"), 30, 30);
        ImageIcon colorsIcon = resizeIcon(new ImageIcon("Resources/Colors.png"), 30, 30);
        ImageIcon effectsIcon = resizeIcon(new ImageIcon("Resources/Effects.png"), 30, 30);
        
        ImageIcon paintBrushIcon = resizeIcon(new ImageIcon("Resources/PaintBrush.png"), 50, 50);
        
        JButton newPaintBrushButton = new JButton(paintBrushIcon);

        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Tools", toolsIcon, panel1,"Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Colors", colorsIcon, panel2,"Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        
        panel2.setLayout(new BorderLayout());
        panel2.add(newPaintBrushButton, BorderLayout.CENTER);
        
      //newPaintBrush Layout
       /* layout.putConstraint(SpringLayout.WEST, newPaintBrushButton,
                5,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, newPaintBrushButton,
                5,
                SpringLayout.NORTH, this);*/

        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Effects", effectsIcon, panel3,"Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
//        panel4.setPreferredSize(new Dimension(410, 50));

        //Add the tabbed pane to this panel.
        this.add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    protected JComponent makeTextPanel(String text)
    {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }

    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}