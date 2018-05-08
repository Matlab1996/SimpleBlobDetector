package Webcam;

import java.awt.AWTException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

@SuppressWarnings("serial")
public class Webcam extends JFrame {

	public JPanel contentPane;
	
	public static JLabel lblWebcam, lblFps;
	
	public static double Upper = 255;

	public static PointerDetector pointerDetector = new PointerDetector();
	public static ImageProcessor imageProcessor = new ImageProcessor();

	public Webcam() {
		setBounds(100, 100, 675, 565);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblFps = new JLabel("FPS");
		lblFps.setBounds(0, 0, 207, 14);
		contentPane.add(lblFps);

		lblWebcam = new JLabel("New label");
		settings.captureSize.subscribe(captureSize -> lblWebcam.setBounds(0, 14, captureSize.h, captureSize.w));
		contentPane.add(lblWebcam);
		
	}

	private static void subscribeForSettings(VideoCapture capture){
		settings.captureSize.subscribe(captureSize -> { 
			capture.set(Videoio.CAP_PROP_FRAME_WIDTH, captureSize.w);
			capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, captureSize.h);
		});
		settings.brightness.subscribe(brightnessValue -> capture.set(Videoio.CAP_PROP_BRIGHTNESS, brightnessValue));
		settings.contrast.subscribe(contrastValue -> capture.set(Videoio.CAP_PROP_CONTRAST, contrastValue));
		settings.saturation.subscribe(saturationValue -> capture.set(Videoio.CAP_PROP_SATURATION, saturationValue));
		settings.sharpness.subscribe(sharpnessValue -> capture.set(Videoio.CAP_PROP_SHARPNESS, sharpnessValue));
		settings.hue.subscribe(hueValue -> capture.set(Videoio.CAP_PROP_HUE, hueValue));
		settings.exposure.subscribe(exposureValue -> capture.set(Videoio.CAP_PROP_EXPOSURE, exposureValue));
		settings.gamma.subscribe(gammaValue -> capture.set(Videoio.CAP_PROP_GAMMA, gammaValue));
		settings.gain.subscribe(gainValue -> capture.set(Videoio.CAP_PROP_GAIN, gainValue));
		settings.upper.subscribe(upperValue -> pointerDetector.setHsvColor(upperValue));
		settings.minArea.subscribe(minArea -> pointerDetector.setMinContourArea(minArea));
        settings.maxArea.subscribe(maxArea -> pointerDetector.setMaxContourArea(maxArea));
	}

	public static void runMainLoop() throws AWTException{
		Mat webCamMatImage = new Mat();
		Image tempImage;
		
		int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
		int FRAMEcount = 0;
		@SuppressWarnings("unused")
		double captureTime =  System.currentTimeMillis();

		VideoCapture capture = new VideoCapture(0);
		subscribeForSettings(capture);
 		capture.set(Videoio.CAP_PROP_FOURCC, fourcc);
 		
		if(capture.isOpened())
		{
			while(true)
			{
				//lblFps.setText("FPS: " + ((FRAMEcount*1000)/(System.currentTimeMillis() - captureTime)));
				capture.read(webCamMatImage);
				if(!webCamMatImage.empty())
				{
					FRAMEcount++;
					if (FRAMEcount>500){
						FRAMEcount = 0;
						captureTime = System.currentTimeMillis();
					}

					Mat imageBackup = webCamMatImage.clone();
					pointerDetector.process(webCamMatImage, webCamMatImage);
					pointerDetector.getContours();
					pointerDetector.drawDetectedPointers(imageBackup);
					pointerDetector.centerOfContour(imageBackup);
					
					tempImage = imageProcessor.toBufferedImage(imageBackup); 
					ImageIcon imageIcon = new ImageIcon(tempImage, "Captured Video");
					//lblWebcam.setIcon(imageIcon);
					Panel.label.setIcon(imageIcon);
					imageBackup.release();
			 		
				}else
				{
					System.out.println("Frame not captured");
				}
			}
		}else
			System.out.println("Couldn't open capture.");
	}
}
