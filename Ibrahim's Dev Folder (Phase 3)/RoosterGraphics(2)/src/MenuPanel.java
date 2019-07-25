import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MenuPanel extends JPanel
{
    public MenuPanel(CanvasPanel canvasPanel, QuickSelectPanel quickSelectPanel)
    {
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.gray));

        JPanel drawOptionsPanel = new DrawOptionsPanel(canvasPanel);
        JPanel fileOptionsPanel = new FileOptionsPanel(canvasPanel, quickSelectPanel, this);

        this.add(drawOptionsPanel, BorderLayout.NORTH);
        this.add(fileOptionsPanel, BorderLayout.SOUTH);
    }
}
