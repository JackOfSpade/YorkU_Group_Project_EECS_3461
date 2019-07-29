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
    public DrawOptionsPanel(CanvasPanel canvasPanel, QuickSelectPanel quickSelectPanel)
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
        this.label = new JLabel("   Brushsize: 3",JLabel.LEFT);
        this.brushSlider = new JSlider(JSlider.HORIZONTAL, 1, 50, 3);
        textBox.setBackground(new Color(238, 238, 238));
        rotateButton.setBackground(new Color(238, 238, 238));
        paintBucket.setBackground(new Color(238, 238, 238));
        brush.setBackground(new Color(238, 238, 238));


        label.setText("   Brushsize: 3");
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
        //panel1.add(paintBucket);
        panel1.add(brush);
        panel1.add(label);
        panel1.add(brushSlider);
        //panel1.add(brushField);

        // Button listeners and actions
        rotateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                canvasPanel.rotateImg(15);
            }
        });
        label.addPropertyChangeListener(
                new PropertyChangeListener() {

                    public void propertyChange(PropertyChangeEvent event) {
                        String property = event.getPropertyName();
                        if ("text".equals(property)) {
                            label.setText("   Brushsize: " + brushSlider.getValue());
                        }
                    }
                }
        );
        brushSlider.addPropertyChangeListener(new PropertyChangeListener() {

            public void propertyChange(PropertyChangeEvent event) {
                String property = event.getPropertyName();
                if ("value".equals(property)) {
                    int value = Math.round(canvasPanel.strokeSize.getLineWidth());
                    label.setText("   Brushsize: " + value);
                }
            }
        });
        brushSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                int value = brushSlider.getValue();
                label.setText("   Brushsize: " + value);
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

        // Add buttons

       // newPaintBrush Layout
       /* layout.putConstraint(SpringLayout.WEST, newPaintBrushButton, 5, SpringLayout.WEST, this);
          layout.putConstraint(SpringLayout.NORTH, newPaintBrushButton, 5, SpringLayout.NORTH, this);
       */

        //panel4.setPreferredSize(new Dimension(410, 50));


        // =========================================================================================================
        //============================================================================================================
        //==========================================================================================================
        // =========================================================================================================
        //============================================================================================================
        //==========================================================================================================

        /* Color Tab */
        ImageIcon dropperIcon = resizeIcon(new ImageIcon("Resources/Dropper.png"), 60, 70);
        ImageIcon darkDropperIcon = resizeIcon(new ImageIcon("Resources/DarkDropper.png"), 60, 70);

        ImageIcon letterRIcon = resizeIcon(new ImageIcon("Resources/letterR.png"), 20, 20);
        ImageIcon letterGIcon = resizeIcon(new ImageIcon("Resources/letterG.png"), 23, 23);
        ImageIcon letterBIcon = resizeIcon(new ImageIcon("Resources/letterB.png"), 20, 20);

        ImageIcon letterHIcon = resizeIcon(new ImageIcon("Resources/letterH.png"), 23, 23);
        ImageIcon letterSIcon = resizeIcon(new ImageIcon("Resources/letterS.png"), 23, 23);
        ImageIcon brightnessIcon = resizeIcon(new ImageIcon("Resources/Brightness.png"), 30, 30);

        ImageIcon RGBIcon = resizeIcon(new ImageIcon("Resources/RGB.png"), 90, 30);
        ImageIcon GradIcon = resizeIcon(new ImageIcon("Resources/Grad.png"), 50, 50);

        ColorModel cm = new ColorModel();
        ColorWheel cw = new ColorWheel(cm ,new Color(238, 238, 238));

        JLabel gradLabel = new JLabel();
        gradLabel.setIcon(GradIcon);

        JLabel RGBLabel = new JLabel();
        RGBLabel.setIcon(RGBIcon);

        JLabel letterRLabel = new JLabel();
        letterRLabel.setIcon(letterRIcon);

        JLabel letterGLabel = new JLabel();
        letterGLabel.setIcon(letterGIcon);

        JLabel letterBLabel = new JLabel();
        letterBLabel.setIcon(letterBIcon);

        JLabel letterHLabel = new JLabel();
        letterHLabel.setIcon(letterHIcon);

        JLabel letterSLabel = new JLabel();
        letterSLabel.setIcon(letterSIcon);

        JLabel brightnessLabel = new JLabel();
        brightnessLabel.setIcon(brightnessIcon);

        JLabel redValue = new JLabel("0");
        redValue.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));

        JLabel greenValue = new JLabel("0");
        greenValue.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));

        JLabel blueValue = new JLabel("0");
        blueValue.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));

        JLabel hueValue = new JLabel("0");
        hueValue.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));

        JLabel satValue = new JLabel("0");
        satValue.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));

        JLabel brightValue = new JLabel("0");
        brightValue.setFont(new Font("SANS_SERIF", Font.PLAIN, 25));

        JButton dropperButton = new JButton(dropperIcon);
        dropperButton.setBorderPainted( false );
        dropperButton.setBackground(new Color(238, 238, 238));

        JComponent panel2 = makeTextPanel("");
        tabbedPane.addTab("Colors", colorsIcon, panel2,"Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        panel2.setPreferredSize(new Dimension(100, 625));
        cw.setPreferredSize(new Dimension(200, 200));

        //JLabel redValue = new JLabel("R:");
        //redValue.setFont(new Font("Sans Serif", Font.BOLD, 20));

        panel2.setLayout(layout);
        panel2.add(dropperButton);

        panel2.add(letterRLabel);
        panel2.add(letterGLabel);
        panel2.add(letterBLabel);

        panel2.add(letterHLabel);
        panel2.add(letterSLabel);
        panel2.add(brightnessLabel);
        panel2.add(cw);

        panel2.add(redValue);
        panel2.add(greenValue);
        panel2.add(blueValue);

        panel2.add(hueValue);
        panel2.add(satValue);
        panel2.add(brightValue);

        panel2.add(RGBLabel);
        panel2.add(gradLabel);


//dropper Layout
        layout.putConstraint(SpringLayout.WEST, dropperButton,
                110,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, dropperButton,
                15,
                SpringLayout.NORTH, this);
        //RGB
        layout.putConstraint(SpringLayout.WEST, RGBLabel,
                165,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, RGBLabel,
                115,
                SpringLayout.NORTH, this);
//Grad
        layout.putConstraint(SpringLayout.WEST, gradLabel,
                40,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, gradLabel,
                105,
                SpringLayout.NORTH, this);

//letterR Layout
        layout.putConstraint(SpringLayout.WEST, letterRLabel,
                40,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, letterRLabel,
                170,
                SpringLayout.NORTH, this);
        //redVal
        layout.putConstraint(SpringLayout.WEST, redValue,
                30,
                SpringLayout.WEST, letterRLabel);
        layout.putConstraint(SpringLayout.NORTH, redValue,
                162,
                SpringLayout.NORTH, this);

//letterG Layout
        layout.putConstraint(SpringLayout.WEST, letterGLabel,
                40,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, letterGLabel,
                40,
                SpringLayout.NORTH, letterRLabel);

//greenVal
        layout.putConstraint(SpringLayout.WEST, greenValue,
                30,
                SpringLayout.WEST, letterGLabel);
        layout.putConstraint(SpringLayout.NORTH, greenValue,
                32,
                SpringLayout.NORTH, letterRLabel);

//letterB Layout
        layout.putConstraint(SpringLayout.WEST, letterBLabel,
                40,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, letterBLabel,
                40,
                SpringLayout.NORTH, letterGLabel);

//blueVal
        layout.putConstraint(SpringLayout.WEST, blueValue,
                30,
                SpringLayout.WEST, letterBLabel);
        layout.putConstraint(SpringLayout.NORTH, blueValue,
                32,
                SpringLayout.NORTH, letterGLabel);

//letterH Layout
        layout.putConstraint(SpringLayout.WEST, letterHLabel,
                185,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, letterHLabel,
                170,
                SpringLayout.NORTH, this);
//hueVal
        layout.putConstraint(SpringLayout.WEST, hueValue,
                30,
                SpringLayout.WEST, letterHLabel);
        layout.putConstraint(SpringLayout.NORTH, hueValue,
                162,
                SpringLayout.NORTH, this);

//letterS Layout
        layout.putConstraint(SpringLayout.WEST, letterSLabel,
                185,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, letterSLabel,
                40,
                SpringLayout.NORTH, letterHLabel);
//satVal
        layout.putConstraint(SpringLayout.WEST, satValue,
                30,
                SpringLayout.WEST, letterSLabel);
        layout.putConstraint(SpringLayout.NORTH, satValue,
                40,
                SpringLayout.NORTH, hueValue);

//letterBright Layout
        layout.putConstraint(SpringLayout.WEST, brightnessLabel,
                178,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, brightnessLabel,
                35,
                SpringLayout.NORTH, letterSLabel);

//brightnessVal
        layout.putConstraint(SpringLayout.WEST, brightValue,
                37,
                SpringLayout.WEST, brightnessLabel);
        layout.putConstraint(SpringLayout.NORTH, brightValue,
                40,
                SpringLayout.NORTH, satValue);

//ColorWheel Layout
        layout.putConstraint(SpringLayout.WEST, cw,
                0,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, cw,
                285,
                SpringLayout.NORTH, dropperButton);

        cw.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                //newPaintBrushButton.setBackground(cm.getColor());
                redValue.setText(Integer.toString(cm.getColor().getRed()));
                greenValue.setText(Integer.toString(cm.getColor().getGreen()));
                blueValue.setText(Integer.toString(cm.getColor().getBlue()));

                hueValue.setText(Double.toString(Math.round((cm.getHue()) * 100.0) / 100.0));
                satValue.setText(Double.toString(Math.round((cm.getSaturation()) * 100.0) / 100.0));
                brightValue.setText(Float.toString(cm.getBrightness()));

                canvasPanel.paintBrushColour = cm.getColor();
                quickSelectPanel.currentColour = cm.getColor();
                quickSelectPanel.repaint();
                //repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        dropperButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                dropperButton.setIcon(darkDropperIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                dropperButton.setIcon(dropperIcon);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

        });

        // Panel and Effects Pane declarations =========================================================================
        //JComponent panel3 = makeTextPanel("Panel #3");
        JPanel panel3 = new JPanel();
        //panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
        panel3.setLayout(new GridLayout(7,1));
        tabbedPane.addTab("Effects", effectsIcon, panel3,"Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        // Effects buttons
        JSlider blurDegree = new JSlider(JSlider.HORIZONTAL, 0, 9, 0);
        JSlider sharpnessDegree = new JSlider(JSlider.HORIZONTAL, 0, 9, 0);
        JButton blackwhite = new JButton(blackWhiteIcon);
        //JToggleButton warmfilter = new JToggleButton(warmIcon);
        JButton warmfilter = new JButton(warmIcon);
        JButton vivid = new JButton(vividIcon);
        JLabel blurLabel = new JLabel(blurIcon);
        JLabel sharpnessLabel = new JLabel("   Sharpness");
        warmfilter.setBackground(new Color(238, 238, 238));
        blackwhite.setBackground(new Color(238, 238, 238));
        vivid.setBackground(new Color(238, 238, 238));

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

    public void setStroke(int size){
        this.brushSlider.setValue(size);
        label.setText("   Brushsize: " + size);
        this.brushField.setText(Integer.toString(size));
    }
}
