import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuickSelectPanel extends JPanel
{
	Color currentColour = Color.black;
    Color previousColour = null;
    DrawOptionsPanel drawOptionsPanel;
	
    public QuickSelectPanel(CanvasPanel canvasPanel)
    {
    	SpringLayout layout = new SpringLayout();
        drawOptionsPanel = new DrawOptionsPanel(canvasPanel, this);
        ImageIcon paintBrushIcon = resizeIcon(new ImageIcon("Resources/PaintBrush.png"), 50, 50);
        ImageIcon eraserIcon = resizeIcon(new ImageIcon("Resources/Eraser.png"), 60, 60);
        
        ImageIcon darkPaintBrushIcon = resizeIcon(new ImageIcon("Resources/DarkPaintBrush.png"), 50, 50);
        ImageIcon darkEraserIcon = resizeIcon(new ImageIcon("Resources/DarkEraser.png"), 60, 60);
        
        ImageIcon smallBrushIcon = resizeIcon(new ImageIcon("Resources/Thin.png"), 60, 60); 
        ImageIcon medBrushIcon = resizeIcon(new ImageIcon("Resources/Medium.png"), 60, 60);
        ImageIcon largeBrushIcon = resizeIcon(new ImageIcon("Resources/Large.png"), 60, 60);
       

        JButton newPaintBrushButton = new JButton(paintBrushIcon);
        JButton eraserButton = new JButton(eraserIcon);
        
        JButton smallBrushButton = new JButton(smallBrushIcon);
        JButton medBrushButton = new JButton(medBrushIcon);
        JButton largeBrushButton = new JButton(largeBrushIcon);
        
        JButton blackPresetButton = new JButton();
        JButton greyPresetButton = new JButton();
        JButton redPresetButton = new JButton();
        JButton orangePresetButton = new JButton();
        JButton yellowPresetButton = new JButton();
        JButton greenPresetButton = new JButton();
        JButton purplePresetButton = new JButton();
        JButton bluePresetButton = new JButton();
        
        JButton whitePresetButton = new JButton();
        JButton lightGreyPresetButton = new JButton();
        JButton pinkPresetButton = new JButton();
        JButton lightOrangePresetButton = new JButton();
        JButton lightYellowPresetButton = new JButton();
        JButton lightGreenPresetButton = new JButton();
        JButton lightPurplePresetButton = new JButton();
        JButton lightBluePresetButton = new JButton();
        
        //JButton greyPresetButton = new JButton();
        
        //newCanvasButton.setBorder(BorderFactory.createEmptyBorder());
        newPaintBrushButton.setBorderPainted( false );
        eraserButton.setBorderPainted( false );
        newPaintBrushButton.setContentAreaFilled(false);
        //newCanvasButton.setFocusable(false);
        
        blackPresetButton.setPreferredSize(new Dimension(25, 25));
        greyPresetButton.setPreferredSize(new Dimension(25, 25));
        redPresetButton.setPreferredSize(new Dimension(25, 25));
        orangePresetButton.setPreferredSize(new Dimension(25, 25));
        yellowPresetButton.setPreferredSize(new Dimension(25, 25));
        greenPresetButton.setPreferredSize(new Dimension(25, 25));
        purplePresetButton.setPreferredSize(new Dimension(25, 25));
        bluePresetButton.setPreferredSize(new Dimension(25, 25));
        
        whitePresetButton.setPreferredSize(new Dimension(25, 25));
        lightGreyPresetButton.setPreferredSize(new Dimension(25, 25));
        pinkPresetButton.setPreferredSize(new Dimension(25, 25));
        lightOrangePresetButton.setPreferredSize(new Dimension(25, 25));
        lightYellowPresetButton.setPreferredSize(new Dimension(25, 25));
        lightGreenPresetButton.setPreferredSize(new Dimension(25, 25));
        lightPurplePresetButton.setPreferredSize(new Dimension(25, 25));
        lightBluePresetButton.setPreferredSize(new Dimension(25, 25));
        
        smallBrushButton.setPreferredSize(new Dimension(60, 60));
        medBrushButton.setPreferredSize(new Dimension(60, 60));
        largeBrushButton.setPreferredSize(new Dimension(60, 60));
        
        blackPresetButton.setBackground(Color.black);
        greyPresetButton.setBackground(Color.gray);
        redPresetButton.setBackground(Color.red);
        orangePresetButton.setBackground(new Color(255, 128, 0));
        yellowPresetButton.setBackground(Color.yellow);
        greenPresetButton.setBackground(Color.green);
        purplePresetButton.setBackground(new Color(163, 73, 164));
        bluePresetButton.setBackground(Color.blue);
        
        whitePresetButton.setBackground(Color.white);
        lightGreyPresetButton.setBackground(Color.lightGray);
        pinkPresetButton.setBackground(Color.pink);
        lightOrangePresetButton.setBackground(new Color(255, 201, 14));
        lightYellowPresetButton.setBackground(new Color(255, 255, 130));
        lightGreenPresetButton.setBackground(new Color(128, 255, 128));
        lightPurplePresetButton.setBackground(Color.magenta);
        lightBluePresetButton.setBackground(new Color(0, 128, 255));

        newPaintBrushButton.setBackground(new Color(238, 238, 238));
        eraserButton.setBackground(new Color(238, 238, 238));        
        
    
        this.setLayout(layout);
        this.setBorder(BorderFactory.createLineBorder(Color.gray));
        this.add(newPaintBrushButton);
        this.add(eraserButton);
        
        this.add(blackPresetButton);
        this.add(greyPresetButton);
        this.add(redPresetButton);
        this.add(orangePresetButton);
        this.add(yellowPresetButton);
        this.add(greenPresetButton);
        this.add(purplePresetButton);
        this.add(bluePresetButton);
        
        this.add(whitePresetButton);
        this.add(lightGreyPresetButton);
        this.add(pinkPresetButton);
        this.add(lightOrangePresetButton);
        this.add(lightYellowPresetButton);
        this.add(lightGreenPresetButton);
        this.add(lightPurplePresetButton);
        this.add(lightBluePresetButton);
        
        this.add(smallBrushButton);
        this.add(medBrushButton);
        this.add(largeBrushButton);
        		       
//newPaintBrush Layout
        layout.putConstraint(SpringLayout.WEST, newPaintBrushButton,
                10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, newPaintBrushButton,
                20,
                SpringLayout.NORTH, this);
        
//EraserButton Layout
        layout.putConstraint(SpringLayout.WEST, eraserButton,
                10,
                SpringLayout.EAST, newPaintBrushButton);
        layout.putConstraint(SpringLayout.NORTH, eraserButton,
               15,
                SpringLayout.NORTH, this);
        
        		/* First row of Preset Colours */         
//blackPresetlayout        
        layout.putConstraint(SpringLayout.WEST, blackPresetButton,
                125,
                SpringLayout.EAST, eraserButton);
        layout.putConstraint(SpringLayout.NORTH, blackPresetButton,
                25,
                SpringLayout.NORTH, this);
        
//greyPresetLayout
        layout.putConstraint(SpringLayout.WEST, greyPresetButton,
                0,
                SpringLayout.EAST, blackPresetButton);
        layout.putConstraint(SpringLayout.NORTH, greyPresetButton,
                25,
                SpringLayout.NORTH, this);
        
//redPresetLayout       
        layout.putConstraint(SpringLayout.WEST, redPresetButton,
                0,
                SpringLayout.EAST, greyPresetButton);
        
        layout.putConstraint(SpringLayout.NORTH, redPresetButton,
                25,
                SpringLayout.NORTH, this);

//oranagePresetLayout
        layout.putConstraint(SpringLayout.WEST, orangePresetButton,
                0,
                SpringLayout.EAST, redPresetButton);
        layout.putConstraint(SpringLayout.NORTH, orangePresetButton,
                25,
                SpringLayout.NORTH, this);

//yellowPresetLayout
        layout.putConstraint(SpringLayout.WEST, yellowPresetButton,
                0,
                SpringLayout.EAST, orangePresetButton);
        layout.putConstraint(SpringLayout.NORTH, yellowPresetButton,
                25,
                SpringLayout.NORTH, this);

//greenPresetLayout        
        layout.putConstraint(SpringLayout.WEST, greenPresetButton,
                0,
                SpringLayout.EAST, yellowPresetButton);  
        layout.putConstraint(SpringLayout.NORTH, greenPresetButton,
                25,
                SpringLayout.NORTH, this);

//purplePresetLayout        
        layout.putConstraint(SpringLayout.WEST, purplePresetButton,
                0,
                SpringLayout.EAST, greenPresetButton);
        layout.putConstraint(SpringLayout.NORTH, purplePresetButton,
                25,
                SpringLayout.NORTH, this);
        
//bluePresetLayout        
        layout.putConstraint(SpringLayout.WEST, bluePresetButton,
                0,
                SpringLayout.EAST, purplePresetButton);
        layout.putConstraint(SpringLayout.NORTH, bluePresetButton,
                25,
                SpringLayout.NORTH, this);
        
        		/* Second row of Preset Colours */
        
//whitePresetlayout        
        layout.putConstraint(SpringLayout.WEST, whitePresetButton,
                125,
                SpringLayout.EAST, eraserButton);
        layout.putConstraint(SpringLayout.NORTH, whitePresetButton,
                50,
                SpringLayout.NORTH, this);
        
//lightGreyPreseyLayout       
        layout.putConstraint(SpringLayout.WEST, lightGreyPresetButton,
                0,
                SpringLayout.EAST, whitePresetButton);
        layout.putConstraint(SpringLayout.NORTH, lightGreyPresetButton,
                50,
                SpringLayout.NORTH, this);
        
//pinkPresetLayout       
        layout.putConstraint(SpringLayout.WEST, pinkPresetButton,
                0,
                SpringLayout.EAST, lightGreyPresetButton);
        
        layout.putConstraint(SpringLayout.NORTH, pinkPresetButton,
                50,
                SpringLayout.NORTH, this);

//lightOrangePresetLayout
        layout.putConstraint(SpringLayout.WEST, lightOrangePresetButton,
                0,
                SpringLayout.EAST, pinkPresetButton);
        layout.putConstraint(SpringLayout.NORTH, lightOrangePresetButton,
                50,
                SpringLayout.NORTH, this);

//lightYellowPresetButton
        layout.putConstraint(SpringLayout.WEST, lightYellowPresetButton,
                0,
                SpringLayout.EAST, lightOrangePresetButton);
        layout.putConstraint(SpringLayout.NORTH, lightYellowPresetButton,
                50,
                SpringLayout.NORTH, this);

//lightGreenPresetButton       
        layout.putConstraint(SpringLayout.WEST, lightGreenPresetButton,
                0,
                SpringLayout.EAST, lightYellowPresetButton);  
        layout.putConstraint(SpringLayout.NORTH, lightGreenPresetButton,
                50,
                SpringLayout.NORTH, this);

//lightPurplePresetLayout        
        layout.putConstraint(SpringLayout.WEST, lightPurplePresetButton,
                0,
                SpringLayout.EAST, lightGreenPresetButton);
        layout.putConstraint(SpringLayout.NORTH, lightPurplePresetButton,
                50,
                SpringLayout.NORTH, this);
        
//lightBluePresetLayout        
        layout.putConstraint(SpringLayout.WEST, lightBluePresetButton,
                0,
                SpringLayout.EAST, lightPurplePresetButton);
        layout.putConstraint(SpringLayout.NORTH, lightBluePresetButton,
                50,
                SpringLayout.NORTH, this);
        
//smallBrushtLayout        
        layout.putConstraint(SpringLayout.WEST, smallBrushButton,
                360,
                SpringLayout.EAST, eraserButton);
        layout.putConstraint(SpringLayout.NORTH, smallBrushButton,
                20,
                SpringLayout.NORTH, this);
        
//medBrushtLayout        
        layout.putConstraint(SpringLayout.WEST, medBrushButton,
                25,
                SpringLayout.EAST, smallBrushButton);
        layout.putConstraint(SpringLayout.NORTH, medBrushButton,
                20,
                SpringLayout.NORTH, this);
        
//largeBrushLayout
        layout.putConstraint(SpringLayout.WEST, largeBrushButton,
                25,
                SpringLayout.EAST, medBrushButton);
        layout.putConstraint(SpringLayout.NORTH, largeBrushButton,
                20,
                SpringLayout.NORTH, this);
        
        newPaintBrushButton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                if (previousColour == null) {
                    canvasPanel.paintBrushColour = Color.black;
                }else {
                    canvasPanel.paintBrushColour = previousColour;
                }
                //repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				newPaintBrushButton.setIcon(darkPaintBrushIcon);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				newPaintBrushButton.setIcon(paintBrushIcon);
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

        eraserButton.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                // TODO Auto-generated method stub
                canvasPanel.paintBrushColour = Color.white;
                previousColour = currentColour;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                eraserButton.setIcon(darkEraserIcon);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                eraserButton.setIcon(eraserIcon);
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
        
        blackPresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = Color.black;
            	currentColour = Color.black;
            	repaint();
            }
        });
        greyPresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = Color.gray;
            	currentColour = Color.gray;
            	repaint();
            }
        });
        redPresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = Color.red;
            	currentColour = Color.red;
            	repaint();
            }
        });
        orangePresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = Color.orange;
            	currentColour = Color.orange;
            	repaint();
            }
        });
       yellowPresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = Color.yellow;
        	currentColour = Color.yellow;
        	repaint();
           }
       });
       greenPresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = Color.green;
            	currentColour = Color.green;
            	repaint();
            }
        });
        purplePresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = new Color(163, 73, 164);
            	currentColour = new Color(163, 73, 164);
            	repaint();
            }
        });
        bluePresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = Color.blue;
            	currentColour = Color.blue;
            	repaint();
            }
        });
        
        whitePresetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	canvasPanel.paintBrushColour = Color.white;
            	currentColour = Color.white;
            	repaint();
            }
        });
       lightGreyPresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = Color.lightGray;
        	currentColour = Color.lightGray;
        	repaint();
           }
       });
       pinkPresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = Color.pink;
        	currentColour = Color.pink;
        	repaint();
           }
       });
       lightOrangePresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = new Color(255, 201, 14);
        	currentColour = new Color(255, 201, 14);
        	repaint();
           }
       });
       lightYellowPresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = new Color(255, 255, 130);
        	currentColour = new Color(255, 255, 130);
        	repaint();
           }
       });
       lightGreenPresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = new Color(128, 255, 128);
        	currentColour = new Color(128, 255, 128);
        	repaint();
           }
       });
       lightPurplePresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = Color.magenta;
        	currentColour = Color.magenta;
        	repaint();
           }
       });
       lightBluePresetButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.paintBrushColour = new Color(0, 128, 255);
        	currentColour = new Color(0, 128, 255);
        	repaint();
           }
       });
        
       smallBrushButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.strokeSize = new BasicStroke(3);
               drawOptionsPanel.setStroke(3);
           }
       });
       medBrushButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
           	canvasPanel.strokeSize = new BasicStroke(6);
               drawOptionsPanel.setStroke(6);
           }
       });
       largeBrushButton.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent ae) {
               canvasPanel.strokeSize = new BasicStroke(9);
               drawOptionsPanel.setStroke(9);
           }
       });
    }
 

    
    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight)
    {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	Graphics2D g2d = (Graphics2D) g;
    	Rectangle r = new Rectangle(230, 25, 50, 50);
    	
    	g2d.setPaint(currentColour);
    	
    	g2d.fillOval(
    	        (int)r.getX(),
    	        (int)r.getY(),
    	        (int)r.getWidth(),
    	        (int)r.getHeight()
    	     );  
    	
    	g2d.setColor(Color.black);
    	g2d.drawOval((int)r.getX(),(int)r.getY(),(int)r.getWidth(),(int)r.getHeight());

    }

    
}
