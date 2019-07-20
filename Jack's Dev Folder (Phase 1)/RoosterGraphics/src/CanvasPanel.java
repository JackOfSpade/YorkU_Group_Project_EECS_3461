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
    List<List<Point>> listOfPoints = new ArrayList<>();
    BufferedImage image;
    boolean drawImage;

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
        g.setColor(Color.black);

        if(drawImage && image != null)
        {
            int width = (this.getWidth() - image.getWidth(null)) / 2;
            int height = (this.getHeight() - image.getHeight(null)) / 2;
            g.drawImage(image, width, height, this);
        }

        for(int x=0; x<listOfPoints.size();x++)
        {
            if (listOfPoints.get(x).size() >= 2)
            {
                Iterator<Point> it = listOfPoints.get(x).iterator();
                Point p1 = it.next();
                while (it.hasNext())
                {
                    Point p2 = it.next();
                    g.drawLine(p1.x, p1.y, p2.x, p2.y);
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
            listOfPoints.get(listOfPoints.size()-1).add(mouseEvent.getPoint());
            repaint();
        }

        public void mousePressed(MouseEvent mouseEvent)
        {
            // initialize first point in list.
            listOfPoints.add(new ArrayList<>());
            listOfPoints.get(listOfPoints.size()-1).add(mouseEvent.getPoint());
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
                Iterator<Point> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                    Point point = it.next();
                    point.setLocation(point.getX(), point.getY() - quickSelectPanel.getHeight()/2);
                }

            }
        }
        else
        {
            for (int x = 0; x < listOfPoints.size(); x++)
            {
                Iterator<Point> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                    Point point = it.next();
                    point.setLocation(point.getX(), point.getY() + quickSelectPanel.getHeight()/2);
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
                Iterator<Point> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                    Point point = it.next();
                    point.setLocation(point.getX() - menuPanel.getWidth()/2, point.getY());
                }

            }
        }
        else
        {
            for (int x = 0; x < listOfPoints.size(); x++)
            {
                Iterator<Point> it = listOfPoints.get(x).iterator();
                while (it.hasNext())
                {
                    Point point = it.next();
                    point.setLocation(point.getX() + menuPanel.getWidth()/2, point.getY() );
                }

            }
        }
    }
}