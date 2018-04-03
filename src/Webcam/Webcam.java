package Webcam;

import java.awt.AWTException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

@SuppressWarnings("serial")
public class Webcam extends JFrame {

	public JPanel contentPane;
	
	public static JLabel lblWebcam, lblFps;
	
	public static Scalar Upper = new Scalar(255);

	public static PointerDetector pointerDetector = new PointerDetector();
	public static ImageProcessor imageProcessor = new ImageProcessor();

	public Webcam() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 675, 565);

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
		// capture.set принимает первым параметром свойства камеры (код), а вторым - значение, которое необходимо установить
		// у камеры нет свойства mMaxContourArea и mMinContourArea, только экспозиция и т.п.
		// я закомментировал эти строки, т.к. этот код может вызвать странное поведение программы.
		// К примеру, когда значение pointerDetector.mMaxContourArea совпадет с Videoio.CAP_PROP_EXPOSURE, то изменится экспозиция :)
		// settings.maxArea.subscribe(Area -> capture.set((int) pointerDetector.mMaxContourArea, Area));
		// settings.minArea.subscribe(Area -> capture.set((int) pointerDetector.mMinContourArea, Area));
	}

	public static void runMainLoop() throws AWTException{
		Mat webCamMatImage = new Mat();
		Image tempImage;
		
		int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
		int FRAMEcount = 0;
		double captureTime =  System.currentTimeMillis();

		VideoCapture capture = new VideoCapture(0);
		subscribeForSettings(capture);
 		capture.set(Videoio.CAP_PROP_FOURCC, fourcc);
 		
		if(capture.isOpened())
		{
			while(true)
			{
				lblFps.setText("FPS: " + ((FRAMEcount*1000)/(System.currentTimeMillis() - captureTime)));
				capture.read(webCamMatImage);
				if(!webCamMatImage.empty())
				{
					FRAMEcount++;
					if (FRAMEcount>500){
						FRAMEcount = 0;
						captureTime = System.currentTimeMillis();
					}

					Mat output = new Mat();
					pointerDetector.process(webCamMatImage, output);
					pointerDetector.getContours();
					pointerDetector.drawDetectedPointers(webCamMatImage);
					pointerDetector.centerOfContour(webCamMatImage);
					
					tempImage = imageProcessor.toBufferedImage(webCamMatImage); 
					ImageIcon imageIcon = new ImageIcon(tempImage, "Captured Video");
					lblWebcam.setIcon(imageIcon);
			 		
				}else
				{
					System.out.println("Frame not captured");
				}
			}
		}else
			System.out.println("Couldn't open capture.");
	}
}
