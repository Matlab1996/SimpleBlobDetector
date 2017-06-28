package camera;

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

import camera.ImageProcessor;
import camera.checking;

@SuppressWarnings("serial")
public class Webcam extends JFrame {

	public JPanel contentPane;
	
	public static JLabel lblWebcam, lblFps;
	public static int 	BRIGHTNESS = 0, CONTRAST = 32,
						SATURATION = 60, SHARPNESS = 2,
						HUE = 0, EXPOSURE = -6,
						GAMMA = 100, GAIN = 0,
						WIDTH = 640, HEIGHT = 480,
						maxArea = 5000, minArea = 25,
						mLower0 = 0, mUpper0 = 255,
						mLower1 = 0, mUpper1 = 255,
						mLower2 = 0, mUpper2 = 255,
						mLower3 = 0, mUpper3 = 255;
	
	
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

	public static void updateCameraParams(VideoCapture capture){
		 
		if(checking.param_check_width())
			 capture.set(Videoio.CAP_PROP_FRAME_WIDTH, WIDTH);
		if(checking.param_check_height())
			capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, HEIGHT);
		if(checking.param_check_brightness())
			capture.set(Videoio.CAP_PROP_BRIGHTNESS, BRIGHTNESS);
		if(checking.param_check_contrast())
			capture.set(Videoio.CAP_PROP_CONTRAST, CONTRAST);
		if(checking.param_check_saturation())
			capture.set(Videoio.CAP_PROP_SATURATION, SATURATION);
		if(checking.param_check_sharpness())
			capture.set(Videoio.CAP_PROP_SHARPNESS, SHARPNESS);
		if(checking.param_check_hue())
			capture.set(Videoio.CAP_PROP_HUE, HUE);
		if(checking.param_check_exposure())
			capture.set(Videoio.CAP_PROP_EXPOSURE, EXPOSURE);
		if(checking.param_check_gamma())
			capture.set(Videoio.CAP_PROP_GAMMA, GAMMA);
		if(checking.param_check_gain())
			capture.set(Videoio.CAP_PROP_GAIN, GAIN);
		//******************************
		if (checking.param_check_mLower0()){
			capture.set((int) pointerDetector.mLowerBound.val[0], mLower0);
		}
		if (checking.param_check_mUpper0())
			capture.set( (int) pointerDetector.mUpperBound.val[0], mLower0);
		if (checking.param_check_mLower1())
			capture.set((int) pointerDetector.mLowerBound.val[1], mLower1);
		if (checking.param_check_mUpper1())
			capture.set((int) pointerDetector.mUpperBound.val[1], mLower1);
		if (checking.param_check_mLower2())
			capture.set((int) pointerDetector.mLowerBound.val[2], mLower2);
		if (checking.param_check_mUpper2())
			capture.set((int) pointerDetector.mUpperBound.val[2], mLower2);
		if (checking.param_check_mLower3())
			capture.set((int) pointerDetector.mLowerBound.val[3], mLower3);
		if (checking.param_check_mUpper3())
			capture.set((int) pointerDetector.mUpperBound.val[3], mLower3);
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
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH, WIDTH);
		capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, HEIGHT);
 		capture.set(Videoio.CAP_PROP_FOURCC, fourcc);
		


		if(capture.isOpened())
		{
			while(true)
			{
				//maxArea
				//if (checking.param_check_maxArea()){
				//	pointerDetector.drawDetectedPointers(webCamMatImage);
				//}
				//	capture.set(FeatureDetector.SIMPLEBLOB, maxArea);
				//minArea
				//if (checking.param_check_minArea())
				//	capture.set(FeatureDetector.SIMPLEBLOB, minArea);
				updateCameraParams(capture);
				
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
