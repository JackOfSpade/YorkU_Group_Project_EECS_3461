import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
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

    BufferedImage[] blurredImages = new BufferedImage[10];

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

        for(int x=0; x<listOfPoints.size();x++)
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
                    g.drawLine(p1.p.x, p1.p.y, p2.p.x, p2.p.y);
                    p1.p = p2.p;
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

                if(blurredImages[0]==null){ //referenced: https://stackoverflow.com/questions/29295929/java-blur-image
                    for (int j = 0; j<10; j++){
                        int blurDegree = j;
                        int size = blurDegree * 2 + 1;
                        float weight = 1.0f / (size * size);
                        float[] data = new float[size * size];

                        for (int i = 0; i < data.length; i++) {
                            data[i] = weight;
                        }

                        Kernel kernel = new Kernel(size, size, data);
                        ConvolveOp op = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
                        blurredImages[j] = op.filter(image, null);
                    }
                }
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

    public void blackWhiteFilter(){ // Referenced: https://www.dyclassroom.com/image-processing-project/how-to-get-and-set-pixel-value-in-java and https://stackoverflow.com/questions/9502569/how-to-convert-an-image-from-color-to-black-and-white-grayscale-in-java

        if(image!=null) {
//            ColorConvertOp op =
//                    new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
//            op.filter(image, image);

            int width = image.getWidth();
            int height = image.getHeight();
            for(int i = 0; i<10; i++) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int p = blurredImages[i].getRGB(x, y);
                        int a = (p >> 24) & 0xff; // Color is represented as a 32 bit int, each 8-bit section corresponds to one of the a,r,g,b values. This is how we isolate a section
                        int r = (p >> 16) & 0xff;
                        int g = (p >> 8) & 0xff;
                        int b = p & 0xff;
                        int avg = (r + g + b) / 3;
                        p = (avg << 24) | (avg << 16) | (avg << 8) | avg; //Putting the values together
                        blurredImages[i].setRGB(x, y, p);
                    }
                }
            }
            repaint();
        }
    }

    public void warmFilter(){
        if(image!=null) {
            int width = image.getWidth();
            int height = image.getHeight();
            for(int i = 0; i<10; i++) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        int p = blurredImages[i].getRGB(x, y);
                        int a = (p >> 24) & 0xff; // Color is represented as a 32 bit int, each 8-bit section corresponds to one of the a,r,g,b values. This is how we isolate a section
                        int r = (p >> 16) & 0xff;
                        int g = ((p >> 8) & 0xff) * 9 / 10;
                        int b = (p & 0xff) / 2;
                        //int avg = (r + g + b) / 3;
                        p = (a << 24) | (r << 16) | (g << 8) | b; //Putting the values together
                        blurredImages[i].setRGB(x, y, p);
                    }
                }
            }
            repaint();
        }

    }

    public void vividFilter(){
        if(image!=null){
            int width = image.getWidth();
            int height = image.getHeight();
            float[] hsb = new float[3];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int p = image.getRGB(x, y);
                    float frac = 0.01f;
                    int a = (p >> 24) & 0xff; // Color is represented as a 32 bit int, each 8-bit section corresponds to one of the a,r,g,b values. This is how we isolate a section
                    int r = ((p >> 16) & 0xff);
                    int g = ((p >> 8) & 0xff);
                    int b = (p & 0xff);

                    float[] col = Color.RGBtoHSB(r,g,b, hsb);
                    hsb[1] *= 1.01;
                    //int avg = (r + g + b) / 3;
                    //p = (a << 24) | (r << 16) | (g << 8) | b; //Putting the values together
                    image.setRGB(x, y, Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
                }
            }
            repaint();
        }
    }

    public void blurFilter(int value){
        if(image!=null){
            image = blurredImages[value];
            repaint();
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


} // end class ColoredLine