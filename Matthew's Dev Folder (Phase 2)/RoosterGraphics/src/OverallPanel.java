import java.awt.*;
import java.awt.event.*;
import java.security.Key;

import javax.swing.*;

public class OverallPanel extends JPanel implements KeyListener
{
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected final int DELTA_DIMENSION = 25;
    protected final int MAX_DIMENSION = (int)(screenSize.getWidth()/2);
    private static final int DELAY = 10;
    private CanvasPanel canvasPanel = new CanvasPanel();
    private QuickSelectPanel quickSelectPanel = new QuickSelectPanel(canvasPanel);
    private MenuPanel menuPanel = new MenuPanel(canvasPanel, quickSelectPanel);
    private int dimension = 0;

    public OverallPanel()
    {
        JPanel tempCenterPanel = new JPanel(new BorderLayout());
        tempCenterPanel.add(canvasPanel, BorderLayout.CENTER);
        tempCenterPanel.add(quickSelectPanel, BorderLayout.SOUTH);

        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.EAST);
        this.add(tempCenterPanel, BorderLayout.CENTER);

        menuPanel.setMinimumSize(new Dimension(300, 0));
        quickSelectPanel.setMinimumSize(new Dimension(0, 100));


        menuPanel.setPreferredSize(menuPanel.getMinimumSize());
        quickSelectPanel.setPreferredSize(quickSelectPanel.getMinimumSize());

        new Timer(DELAY, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dimension += DELTA_DIMENSION;
                if (dimension < MAX_DIMENSION)
                {
                    canvasPanel.setPreferredSize(new Dimension(dimension, 3*dimension/4));
                    SwingUtilities.getWindowAncestor(OverallPanel.this).pack();

                }
                else
                {
                    ((Timer) e.getSource()).stop();
                }
            }
        }).start();

        this.setFocusable(true);
        this.requestFocus();
        this.setFocusTraversalKeysEnabled(false);
        this.addKeyListener(this);

        this.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent ev) {
                requestFocus();
            }
        });
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode()== KeyEvent.VK_1)
        {
            if(quickSelectPanel.isVisible())
            {
                quickSelectPanel.setVisible(false);
                canvasPanel.adjustPointsBasedOnQuickSelectVisibility(quickSelectPanel);
            }
            else
            {
                quickSelectPanel.setVisible(true);
                canvasPanel.adjustPointsBasedOnQuickSelectVisibility(quickSelectPanel);
            }
        }

        if(e.getKeyCode()== KeyEvent.VK_2)
        {
            if(menuPanel.isVisible())
            {
                menuPanel.setVisible(false);
                canvasPanel.adjustPointsBasedOnMenuVisibility(menuPanel);
            }
            else
            {
                menuPanel.setVisible(true);
                canvasPanel.adjustPointsBasedOnMenuVisibility( menuPanel);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}