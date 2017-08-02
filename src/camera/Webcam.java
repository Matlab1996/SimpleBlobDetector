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
import camera.checking;

@SuppressWarnings("serial")
public class Webcam extends JFrame {

	public JPanel contentPane;
	
	public static JLabel lblWebcam, lblFps;
	public static int 	WIDTH = 640, HEIGHT = 480;
		// BRIGHTNESS = 0, CONTRAST = 32,
		// SATURATION = 60, SHARPNESS = 2,
		// HUE = 0, EXPOSURE = -6,
		// GAMMA = 100, GAIN = 0,
		// maxArea = 5000, minArea = 25, max, min,
		// mUpper0 = 255;
	
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
		lblWebcam.setBounds(0, 14, checking.widht, checking.height);
		contentPane.add(lblWebcam);
	}

	public static void updateCameraParams(VideoCapture capture){
		 
		if(checking.param_check_width())
			capture.set(Videoio.CAP_PROP_FRAME_WIDTH, checking.width);
		if(checking.param_check_height())
			capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, checking.height);
		if(checking.param_check_brightness())
			capture.set(Videoio.CAP_PROP_BRIGHTNESS, checking.brightness);
		if(checking.param_check_contrast())
			capture.set(Videoio.CAP_PROP_CONTRAST, checking.contrast);
		if(checking.param_check_saturation())
			capture.set(Videoio.CAP_PROP_SATURATION, checking.saturation);
		if(checking.param_check_sharpness())
			capture.set(Videoio.CAP_PROP_SHARPNESS, checking.sharpness);
		if(checking.param_check_hue())
			capture.set(Videoio.CAP_PROP_HUE, checking.hue);
		if(checking.param_check_exposure())
			capture.set(Videoio.CAP_PROP_EXPOSURE, checking.exposure);
		if(checking.param_check_gamma())
			capture.set(Videoio.CAP_PROP_GAMMA, checking.gamma);
		if(checking.param_check_gain())
			capture.set(Videoio.CAP_PROP_GAIN, checking.gain);
	}

	public static void Upper(VideoCapture capture){
		pointerDetector.setHsvColor(Upper);
		if (checking.param_check_mUpper()){
			capture.set((int) Upper.val[0], mUpper0);
		}
	}
	
	public static void Area(VideoCapture capture){
		pointerDetector.setMaxContourArea(max);
		pointerDetector.setMinContourArea(min);
		//maxArea
		if (checking.param_check_maxArea())
			capture.set(max, maxArea);
		//minArea
		if (checking.param_check_minArea())
			capture.set(min, minArea);
	}

	public static void runMainLoop(){
		ImageProcessor imageProcessor = new ImageProcessor();
		Mat webCamMatImage = new Mat();
		Image tempImage;
		
		int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
		int FRAMEcount = 0;
		double captureTime =  System.currentTimeMillis();

		VideoCapture capture = new VideoCapture(0);

		//Нужно или до установки FOURCC или после установить размер кадра - проверь, пожалуйста
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH, checking.width);
		capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, checking.height);
 		capture.set(Videoio.CAP_PROP_FOURCC, fourcc);

		if(capture.isOpened())
		{
			while(true)
			{
				updateCameraParams(capture);
				Upper(capture);
				Area(capture);
				
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
					pointerDetector.process(frame);
					pointerDetector.drawDetectedPointers(frame);
					tempImage = imageProcessor.toBufferedImage(pointerDetector.mDilatedMask); 
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
