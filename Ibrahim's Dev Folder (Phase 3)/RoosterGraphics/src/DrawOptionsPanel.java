import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class DrawOptionsPanel extends JPanel
{
    private boolean toggled = false;

    public DrawOptionsPanel()
    {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();

        ImageIcon toolsIcon = resizeIcon(new ImageIcon("Resources/Tools.png"), 30, 30);
        ImageIcon colorsIcon = resizeIcon(new ImageIcon("Resources/Colors.png"), 30, 30);
        ImageIcon effectsIcon = resizeIcon(new ImageIcon("Resources/Effects.png"), 30, 30);

        JPanel panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
        tabbedPane.addTab("Tools", toolsIcon, panel1,"Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JButton rotateButton = new JButton("Rotate");
        JButton paintBucket = new JButton("paintBucket");
        JButton brush = new JButton("paintBrush");
        JButton textBox = new JButton("textBox");

        rotateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){}
        });
        brush.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){}
        });

        panel1.add(rotateButton);
        panel1.add(textBox);
        panel1.add(paintBucket);
        panel1.add(brush);

        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Colors", colorsIcon, panel2,"Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        //JComponent panel3 = makeTextPanel("Panel #3");
        JPanel panel3 = new JPanel();
        panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
        tabbedPane.addTab("Effects", effectsIcon, panel3,"Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
//        panel4.setPreferredSize(new Dimension(410, 50));
        JSlider blurDegree = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        //Turn on labels at major tick marks.
        blurDegree.setMajorTickSpacing(2);
        blurDegree.setMinorTickSpacing(1);
        //blurDegree.setPaintTicks(true);
        blurDegree.setPaintLabels(true);

        JSlider sharpnessDegree = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        //Turn on labels at major tick marks.
        sharpnessDegree.setMajorTickSpacing(2);
        sharpnessDegree.setMinorTickSpacing(1);
        //sharpnessDegree.setPaintTicks(true);
        sharpnessDegree.setPaintLabels(true);

        JToggleButton blackwhite = new JToggleButton("BlackWhite");
        JToggleButton warmfilter = new JToggleButton("WarmFilter");

        panel3.add(blurDegree);
        panel3.add(sharpnessDegree);
        panel3.add(blackwhite);
        panel3.add(warmfilter);


        //Add the tabbed pane to this panel.
        this.add(tabbedPane);

        //The following line enables us to use scrolling tabs.
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