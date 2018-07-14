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
        setUndecorated(true);
    }
    @Override
    public void paint(Graphics g) {
    	g.setColor(Color.BLACK);
    	g.fillRect(0, 0, getWidth(), getHeight());
    	g.setColor(Color.GREEN);
    	//g.setFont(new Font("Times New Roman", Font.BOLD, 44));
    	
    	for(int x = 0; x <= getWidth(); x+=(getWidth()-1)/2) {
    		for(int y = 0; y <= getHeight(); y+=(getHeight()-1)/2) {
	    		//g.drawString("+", x-11, y+15);
	    		g.drawLine(x, y-10, x, y+10);
	    		g.drawLine(x-10, y, x+10, y);
    		}
    	}
    } 
    
    //public static void main(String args[]) {
    //	new CanvasPanel();
    //}
    
}
