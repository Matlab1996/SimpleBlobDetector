package Webcam;

import java.awt.AWTException;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.apache.commons.lang3.SystemUtils;

import Webcam.Webcamprop;

@SuppressWarnings("serial")
public class Webcamprop extends JFrame {

	PointerDetector pointerDetector = new PointerDetector();
	
	public static void main(String[] args) throws IOException, AWTException {
		String libName = "";
		if (SystemUtils.IS_OS_WINDOWS) {
		    libName = "opencv_java330.dll";
		} else if (SystemUtils.IS_OS_LINUX) {
		    libName = "libopencv_java320.so";
		} else if (SystemUtils.IS_OS_MAC_OSX) {
			libName = "libopencv_java341.dylib";
		}
		System.load(new File("lib/".concat(libName)).getAbsolutePath());
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