package cam;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Webcam extends JFrame {

	public JPanel contentPane;
	public static JLabel lblWebcam, lblFps;
	public static int 	BRIGHTNESS = 0, CONTRAST = 32,
						SATURATION = 60, SHARPNESS = 2,
						HUE = 0, EXPOSURE = -6,
						GAMMA = 100, GAIN = 0,
						WIDTH = 640, HEIGHT = 480;

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

	protected static void updateCameraParams(checking, VideoCapture capture){
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
	}

	public static void runMainLoop(){
		ImageProcessor imageProcessor = new ImageProcessor();
		Mat webCamMatImage = new Mat();
		Image tempImage;
		ImageIcon imageIcon = new ImageIcon(tempImage, "Captured Video");
		int fourcc = VideoWriter.fourcc('M', 'J', 'P', 'G');
		int FRAMEcount = 0;
		double captureTime =  System.currentTimeMillis();

		VideoCapture capture = new VideoCapture(0);

		//Нужно или до установки FOURCC или после установить размер кадра - проверь, пожалуйста
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH, WIDTH);
  	capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, HEIGHT);
 		capture.set(Videoio.CAP_PROP_FOURCC, fourcc);
		capture.set(Videoio.CAP_PROP_FRAME_WIDTH, WIDTH);
		capture.set(Videoio.CAP_PROP_FRAME_HEIGHT, HEIGHT);

		// blobDetector можно настроить один раз и не согдавать его для каждого кадра, то выносим его из цикла
		Mat MatOut= new Mat();
		FeatureDetector blobDetector;
		blobDetector = FeatureDetector.create(FeatureDetector.SIMPLEBLOB);
		blobDetector.write("blob.html");
		blobDetector.read("blob.html");
		MatOfKeyPoint keypoints1 = new MatOfKeyPoint();
		org.opencv.core.Scalar cores = new org.opencv.core.Scalar(0,0,255);

		if(capture.isOpened())
		{
			while(true)
			{
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

					blobDetector.detect(webCamMatImage,keypoints1);
          org.opencv.features2d.Features2d.drawKeypoints(webCamMatImage,keypoints1,MatOut,cores,2);
					tempImage = imageProcessor.toBufferedImage(MatOut);
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
