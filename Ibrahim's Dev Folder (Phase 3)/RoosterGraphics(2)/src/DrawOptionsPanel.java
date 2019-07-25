import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DrawOptionsPanel extends JPanel
{
    static JLabel label;
    static JSlider brushSlider;
    static JTextField brushField;
    public DrawOptionsPanel(CanvasPanel canvasPanel)
    {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        SpringLayout layout = new SpringLayout();
        // Icon declarations
        ImageIcon toolsIcon = resizeIcon(new ImageIcon("Resources/Tools.png"), 30, 30);
        ImageIcon colorsIcon = resizeIcon(new ImageIcon("Resources/Colors.png"), 30, 30);
        ImageIcon effectsIcon = resizeIcon(new ImageIcon("Resources/Effects.png"), 30, 30);
        ImageIcon paintBrushIcon = resizeIcon(new ImageIcon("Resources/PaintBrush.png"), 50, 50);
        ImageIcon blurIcon = resizeIcon(new ImageIcon("Resources/BlurIcon.png"), 35, 50);
        ImageIcon blackWhiteIcon = resizeIcon(new ImageIcon("Resources/BlackWhiteIcon.png"), 55, 50);
        ImageIcon warmIcon = resizeIcon(new ImageIcon("Resources/WarmIcon.png"), 55, 50);
        ImageIcon vividIcon = resizeIcon(new ImageIcon("Resources/VividIcon.png"), 97, 50);
        ImageIcon textIcon = resizeIcon(new ImageIcon("Resources/TextIcon.png"), 50, 50);
        ImageIcon rotateIcon = resizeIcon(new ImageIcon("Resources/RotateIcon.png"), 43, 50);
        ImageIcon paintBucketIcon = resizeIcon(new ImageIcon("Resources/PaintBucketIcon.png"), 62, 50);
        // Panel and Tabbed Pane declarations =========================================================================
        JPanel panel1 = new JPanel();
        //panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.setLayout(new GridLayout(8,1));
        tabbedPane.addTab("Tools", toolsIcon, panel1,"Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        // Tools buttons
        JButton newPaintBrushButton = new JButton(paintBrushIcon);
        JButton rotateButton = new JButton(rotateIcon);
        JButton paintBucket = new JButton(paintBucketIcon);
        JButton brush = new JButton(paintBrushIcon);
        JButton textBox = new JButton(textIcon);
        this.brushField = new JTextField("10",10);
        this.label = new JLabel("brushsize: 3",JLabel.LEFT);
        this.brushSlider = new JSlider(JSlider.HORIZONTAL, 1, 50, 3);
        //textBox.setBackground(new Color(238, 238, 238));

        label.setText("brushsize: 3");
        label.setAlignmentX(LEFT_ALIGNMENT);
//        brushField.setAlignmentX(LEFT_ALIGNMENT);
//        brushField.setPreferredSize(new Dimension(40,20));
//        brushField.setFocusable(true);
//        brushField.setEnabled(true);
//        brushField.setEditable(true);
//        brushField.setVisible(true);
//        brushField.requestFocusInWindow();

        // Adding buttons to panels
        panel1.add(rotateButton);
        panel1.add(textBox);
        panel1.add(paintBucket);
        panel1.add(newPaintBrushButton);
        panel1.add(brush);
        panel1.add(label);
        panel1.add(brushSlider);
        panel1.add(brushField);

        // Button listeners and actions
        rotateButton.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
            }
        });
        label.addPropertyChangeListener(
                new PropertyChangeListener() {

                    public void propertyChange(PropertyChangeEvent event) {
                        String property = event.getPropertyName();
                        if ("text".equals(property)) {
                            label.setText("brushsize: " + brushSlider.getValue());
                        }
                    }
                }
        );
        brushSlider.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent event) {
                String property = event.getPropertyName();
                if ("value".equals(property)) {
                    int value = Math.round(canvasPanel.strokeSize.getLineWidth());
                    label.setText("brushsize: " + value);
                }
            }
        });
        brushSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = brushSlider.getValue();
                label.setText("brushsize: " + value);
                canvasPanel.strokeSize = new BasicStroke(value);
            }
        });

      /*  textBox.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                canvasPanel.setFocusable( true );
                canvasPanel.setLayout( null );
                canvasPanel.addMouseListener( new MouseAdapter()
                {
                    public void mousePressed(MouseEvent e)
                    {
                        JPanel panel = (JPanel)e.getSource();

                            panel.requestFocusInWindow();
                            InvisibleTextField tf = new InvisibleTextField();
                            tf.setLocation(e.getPoint());
                            panel.add( tf );
                            tf.requestFocusInWindow();
                    }
                });
            }
        });*/

        // Panel and Colour Pane declarations =========================================================================
        JComponent panel2 = makeTextPanel("Panel #2");
        tabbedPane.addTab("Colors", colorsIcon, panel2,"Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
        panel2.setLayout(new BorderLayout());

        // Add buttons
        panel2.add(newPaintBrushButton, BorderLayout.CENTER);
        
       // newPaintBrush Layout
       /* layout.putConstraint(SpringLayout.WEST, newPaintBrushButton, 5, SpringLayout.WEST, this);
          layout.putConstraint(SpringLayout.NORTH, newPaintBrushButton, 5, SpringLayout.NORTH, this);
       */

        // Panel and Effects Pane declarations =========================================================================
        //JComponent panel3 = makeTextPanel("Panel #3");
        JPanel panel3 = new JPanel();
        //panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setLayout(new GridLayout(7,1));
        tabbedPane.addTab("Effects", effectsIcon, panel3,"Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        // Effects buttons
        JSlider blurDegree = new JSlider(JSlider.HORIZONTAL, 0, 9, 0);
        JSlider sharpnessDegree = new JSlider(JSlider.HORIZONTAL, 0, 10, 0);
        JButton blackwhite = new JButton(blackWhiteIcon);
        JToggleButton warmfilter = new JToggleButton(warmIcon);
        JButton vivid = new JButton(vividIcon);
        JLabel blurLabel = new JLabel(blurIcon);
        JLabel sharpnessLabel = new JLabel("Sharpness");
        // Adding buttons to panels
        panel3.add(blurLabel);
        panel3.add(blurDegree);
        panel3.add(sharpnessLabel);
        panel3.add(sharpnessDegree);
        panel3.add(blackwhite);
        panel3.add(warmfilter);
        panel3.add(vivid);


        // Button implementations and actions
        blurDegree.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = blurDegree.getValue();
                canvasPanel.blurFilter(value);
            }
        });
        blackwhite.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                canvasPanel.blackWhiteFilter();
            }
        });

        warmfilter.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                canvasPanel.warmFilter();
            }
        });

        vivid.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                canvasPanel.vividFilter();
            }
        });


        //Add the tabbed pane to this (DrawOptions) panel.
        this.add(tabbedPane);
        //panel4.setPreferredSize(new Dimension(410, 50));
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

    public void setStroke(int size){
        //int old = brushSlider.getValue();
        this.brushSlider.setValue(size);
        label.setText("brushsize: " + size);
        this.brushField.setText(Integer.toString(size));
        //this.brushSlider.firePropertyChange("value", old, size);
    }
}
