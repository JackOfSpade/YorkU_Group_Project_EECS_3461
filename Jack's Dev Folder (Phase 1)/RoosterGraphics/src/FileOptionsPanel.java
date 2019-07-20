import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class FileOptionsPanel extends JPanel
{
    public FileOptionsPanel(CanvasPanel canvasPanel, QuickSelectPanel quickSelectPanel, MenuPanel menuPanel)
    {
        ImageIcon newCanvasIcon = resizeIcon(new ImageIcon("Resources/NewCanvas.png"), 30, 30);
        ImageIcon saveFileIcon = resizeIcon(new ImageIcon("Resources/SaveFile.png"), 30, 30);
        ImageIcon openFileIcon = resizeIcon(new ImageIcon("Resources/OpenFile.png"), 30, 30);

        JButton newCanvasButton = new JButton(newCanvasIcon);
        JButton saveFileButton = new JButton(saveFileIcon);
        JButton openFileButton = new JButton(openFileIcon);

        newCanvasButton.setBackground(new Color(238, 238, 238));
        saveFileButton.setBackground(new Color(238, 238, 238));
        openFileButton.setBackground(new Color(238, 238, 238));

        newCanvasButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                canvasPanel.newCanvas();
            }
        });

        saveFileButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if(quickSelectPanel.isVisible())
                {
                    menuPanel.setVisible(false);
                    canvasPanel.adjustPointsBasedOnMenuVisibility(menuPanel);
                    quickSelectPanel.setVisible(false);
                    canvasPanel.adjustPointsBasedOnQuickSelectVisibility(quickSelectPanel);

                    Timer timer = new Timer(100, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            canvasPanel.saveFile();
                            menuPanel.setVisible(true);
                            canvasPanel.adjustPointsBasedOnMenuVisibility(menuPanel);
                            quickSelectPanel.setVisible(true);
                            canvasPanel.adjustPointsBasedOnQuickSelectVisibility(quickSelectPanel);
                            ((Timer)evt.getSource()).stop();
                        }
                    });

                    timer.start();

                }
                else
                {
                    menuPanel.setVisible(false);
                    canvasPanel.adjustPointsBasedOnMenuVisibility(menuPanel);

                    Timer timer = new Timer(100, new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            canvasPanel.saveFile();
                            menuPanel.setVisible(true);
                            canvasPanel.adjustPointsBasedOnMenuVisibility(menuPanel);
                            ((Timer)evt.getSource()).stop();
                        }
                    });

                    timer.start();

                }
            }

        });

        openFileButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                canvasPanel.openFile();
            }
        });

        this.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        this.add(newCanvasButton);
        this.add(saveFileButton);
        this.add(openFileButton);
    }

    private static ImageIcon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight)
    {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
