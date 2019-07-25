import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

public class DrawOptionsPanel extends JPanel
{
    public DrawOptionsPanel(CanvasPanel canvasPanel, QuickSelectPanel quickSelectPanel)
    {
        super(new GridLayout(1, 1));

        JTabbedPane tabbedPane = new JTabbedPane();
        SpringLayout layout = new SpringLayout();

        ImageIcon toolsIcon = resizeIcon(new ImageIcon("Resources/Tools.png"), 30, 30);
        ImageIcon colorsIcon = resizeIcon(new ImageIcon("Resources/Colors.png"), 30, 30);
        ImageIcon effectsIcon = resizeIcon(new ImageIcon("Resources/Effects.png"), 30, 30);
        
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
        
        JComponent panel1 = makeTextPanel("Panel #1");
        tabbedPane.addTab("Tools", toolsIcon, panel1,"Does nothing");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        JComponent panel2 = makeTextPanel("");
        tabbedPane.addTab("Colors", colorsIcon, panel2,"Does twice as much nothing");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        panel2.setPreferredSize(new Dimension(100, 625));
        cw.setPreferredSize(new Dimension(200, 200));
        
        //JLabel redValue = new JLabel("R:");
        //redValue.setFont(new Font("Sans Serif", Font.BOLD, 20));

        JComponent panel3 = makeTextPanel("Panel #3");
        tabbedPane.addTab("Effects", effectsIcon, panel3,"Still does nothing");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        //Add the tabbed pane to this panel.
        this.add(tabbedPane);

        //The following line enables to use scrolling tabs.
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        
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