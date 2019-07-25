import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

public class CanvasPanel extends JPanel
{
    List<List<ColoredPoint>> listOfPoints = new ArrayList<>();
    BufferedImage image;
    boolean drawImage;
    
    Color paintBrushColour;
    BasicStroke strokeSize = new BasicStroke(3);

    public CanvasPanel()
    {
        MyMouseListener ml = new MyMouseListener();
        this.addMouseMotionListener(ml);
        this.addMouseListener(ml);
        this.setPreferredSize(new Dimension(500, 500));
        this.setBackground(Color.white);
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        //Brush color
        //g.setColor(Color.black);
        
        Graphics2D g2d = (Graphics2D) g;

        if(drawImage && image != null)
        {
            int width = (this.getWidth() - image.getWidth(null)) / 2;
            int height = (this.getHeight() - image.getHeight(null)) / 2;
            g.drawImage(image, width, height, this);
        }

        for(int x=0; x < listOfPoints.size(); x++)
        {
            if (listOfPoints.get(x).size() >= 2)
            {
                Iterator<ColoredPoint> it = listOfPoints.get(x).iterator();
                ColoredPoint p1 = it.next();
                
            	g.setColor(p1.paintBrushColour);
            	g2d.setStroke(p1.strokeSize);
            	
                while (it.hasNext())
                {
                    ColoredPoint p2 = it.next();
                    g.drawLine(p1.getPoint().x, p1.getPoint().y, p2.getPoint().x, p2.getPoint().y);
                    p1 = p2;
                }
            }
        }
    }

    // MouseAdapter provides dummy implementations
    private class MyMouseListener extends MouseAdapter
    {
        public void mouseDragged(MouseEvent mouseEvent)
        {
            listOfPoints.get(listOfPoints.size()-1).add(new ColoredPoint(mouseEvent.getPoint(),paintBrushColour,strokeSize));
            repaint();
        }

        public void mousePressed(MouseEvent mouseEvent)
        {
            // initialize first point in list.
            listOfPoints.add(new ArrayList<>());
            listOfPoints.get(listOfPoints.size()-1).add(new ColoredPoint(mouseEvent.getPoint(),paintBrushColour,strokeSize));
        }
    }

    public void newCanvas()
    {
        listOfPoints = new ArrayList<>();
        drawImage = false;
        repaint();
    }

    public void saveFile(){
        BufferedImage imagebuf=null;
        try
        {
            imagebuf = new Robot().createScreenCapture(this.bounds());
        }
        catch (AWTException ex)
        {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage());
        }

        Graphics2D graphics2D = imagebuf.createGraphics();
        this.paint(graphics2D);

        try
        {
            ImageIO.write(imagebuf,"JPG", new File("Saved_Image.jpg"));
        }
        catch (Exception ex)
        {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage());
        }

        JOptionPane.showMessageDialog(null, "Saved to current directory!");
    }

    public void openFile()
    {
        try
        {
            JFileChooser fc = new JFileChooser();
            fc.setCurrentDirectory(new java.io.File("."));
            fc.setDialogTitle("Choose an Image");
            fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fc.setAcceptAllFileFilterUsed(false);

            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                image = ImageIO.read(new File(fc.getSelectedFile().getAbsolutePath()));
                newCanvas();
                drawImage = true;
                repaint();
            }
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage());
        }
    }

    public void adjustPointsBasedOnQuickSelectVisibility(QuickSelectPanel quickSelectPanel)
    {
        if(quickSelectPanel.isVisible())
        {
            for (int x = 0; x < listOfPoints.size(); x++)
            {
                Iterator<ColoredPoint> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                	ColoredPoint point = it.next();
                    point.p.setLocation(point.p.getX(), point.p.getY() - quickSelectPanel.getHeight()/2);
                }

            }
        }
        else
        {
            for (int x = 0; x < listOfPoints.size(); x++)
            {
                Iterator<ColoredPoint> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                	ColoredPoint point = it.next();
                    point.p.setLocation(point.p.getX(), point.p.getY() + quickSelectPanel.getHeight()/2);
                }

            }
        }
    }

    public void adjustPointsBasedOnMenuVisibility(MenuPanel menuPanel)
    {
        if(menuPanel.isVisible())
        {
            for (int x = 0; x < listOfPoints.size(); x++)
            {
                Iterator<ColoredPoint> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                	ColoredPoint point = it.next();
                    point.p.setLocation(point.p.getX() - menuPanel.getWidth()/2, point.p.getY());
                }

            }
        }
        else
        {
            for (int x = 0; x < listOfPoints.size(); x++)
            {
                Iterator<ColoredPoint> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                	ColoredPoint point = it.next();
                    point.p.setLocation(point.p.getX() + menuPanel.getWidth()/2, point.p.getY() );
                }

            }
        }
    }
}

class ColoredPoint {  // an object of this class represents a colored line segment

	Point p;
	Color paintBrushColour;
	BasicStroke strokeSize;
	
	public ColoredPoint(Point p, Color color, BasicStroke size) {
		this.p = p;
		this.paintBrushColour = color;
		this.strokeSize = size;
	}
	
	public Point getPoint() {
		return p;
	}
	
	public Color getColor() {
		return paintBrushColour;
	}
	
	public BasicStroke getSize() {
		return strokeSize;
	}

} // end class ColoredLine