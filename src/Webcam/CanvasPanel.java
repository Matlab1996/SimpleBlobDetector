package Webcam;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class CanvasPanel extends JFrame{
	
    public CanvasPanel() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(640, 720);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    @Override
    public void paint(Graphics g) {
    	g.setColor(Color.WHITE);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	g.setColor(Color.BLACK);
    	
    	for(int x = 0; x <= getWidth(); x+=getWidth()/2) {
    		g.drawLine(x, 0, x, getHeight());
    	}
    	
    	for(int y = 0; y <= getHeight(); y+=getHeight()/2) {
			g.drawLine(0, y, getWidth(), y);
		}
    }
    
}
