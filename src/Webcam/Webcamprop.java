package Webcam;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import Webcam.Webcamprop;

@SuppressWarnings("serial")
public class Webcamprop extends JFrame {

	PointerDetector pointerDetector = new PointerDetector();
	
	public static void main(String[] args) throws IOException, AWTException {
		File name = new File ("lib//opencv_java330.dll");
		System.load(name.getAbsolutePath());
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Panel app = new Panel();
					app.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Webcam.runMainLoop();
	}
}
