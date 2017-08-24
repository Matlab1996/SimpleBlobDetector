package camera;

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

import camera.ImageProcessor;

@SuppressWarnings("serial")
public class Webcam extends JFrame {

	public JPanel contentPane;
	
	public static JLabel lblWebcam, lblFps;
	public static int 	WIDTH = 640, HEIGHT = 480;
	
	public static Scalar Upper = new Scalar(255);

	public static PointerDetector pointerDetector = new PointerDetector();

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
		lblWebcam.setBounds(0, 14, WIDTH, HEIGHT);
		contentPane.add(lblWebcam);
	}

	private static void subscribeForSettings(VideoCapture capture){
		settings.width.subscribe(widthValue -> capture.set(Videoio.CAP_PROP_FRAME_WIDTH, widthValue));
		settings.height.subscribe(heightValue -> capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, heightValue));
		settings.brightness.subscribe(brightnessValue -> capture.set(Videoio.CAP_PROP_BRIGHTNESS, brightnessValue));
		settings.contrast.subscribe(contrastValue -> capture.set(Videoio.CAP_PROP_CONTRAST, contrastValue));
		settings.saturation.subscribe(saturationValue -> capture.set(Videoio.CAP_PROP_SATURATION, saturationValue));
		settings.sharpness.subscribe(sharpnessValue -> capture.set(Videoio.CAP_PROP_SHARPNESS, sharpnessValue));
		settings.hue.subscribe(hueValue -> capture.set(Videoio.CAP_PROP_HUE, hueValue));
		settings.exposure.subscribe(exposureValue -> capture.set(Videoio.CAP_PROP_EXPOSURE, exposureValue));
		settings.gamma.subscribe(gammaValue -> capture.set(Videoio.CAP_PROP_GAMMA, gammaValue));
		settings.gain.subscribe(gainValue -> capture.set(Videoio.CAP_PROP_GAIN, gainValue));
		settings.upper.subscribe(upperValue -> {
													pointerDetector.setHsvColor(Upper);
													capture.set((int) Upper.val[0], upperValue);
													});
	}
	
	//public static void Area(int b){
		//VideoCapture capture = new VideoCapture();
		//pointerDetector.setMaxContourArea(max);
		//pointerDetector.setMinContourArea(min);
		//maxArea
		//if (checking.param_check_maxArea())
			//capture.set(b, checking.maxArea);
		//minArea
		//if (checking.param_check_minArea())
		//	capture.set(min, checking.minArea);
	//}

	public static void runMainLoop(){
		ImageProcessor imageProcessor = new ImageProcessor();
		Mat webCamMatImage = new Mat();
		Image tempImage;
		
		int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
		int FRAMEcount = 0;
		double captureTime =  System.currentTimeMillis();

		VideoCapture capture = new VideoCapture(0);
		subscribeForSettings(capture);

		//Нужно или до установки FOURCC или после установить размер кадра - проверь, пожалуйста
	
		//capture.set(Videoio.CAP_PROP_FRAME_WIDTH, checking.width);
		//capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, checking.height);
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
					//Обновляем счетчик FPS
					if (FRAMEcount>500){
						FRAMEcount = 0;
						captureTime = System.currentTimeMillis();
					}

					Mat frame = new Mat();
					frame = webCamMatImage;
					pointerDetector.process(frame, frame);
					pointerDetector.drawDetectedPointers(frame);
					pointerDetector.getContours();
					
					tempImage = imageProcessor.toBufferedImage(frame); 
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
