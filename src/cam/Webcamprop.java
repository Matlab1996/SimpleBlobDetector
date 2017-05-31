package cam;

import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Webcamprop extends JFrame {

	private JPanel contentPane;
	private JButton btnCamera;
	
	public static JSlider slider_brightness, slider_contrast, slider_saturation, slider_sharpness, slider_hue, slider_exposure, slider_gamma, slider_gain;
	private JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5,textField_6,textField_7;
	
	Webcam MAIN;
	
	public static void main(String[] args) throws IOException {
		System.load("C:/opencv_java320.dll");
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Webcamprop frame = new Webcamprop();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		Webcam.runMainLoop();
	}

	public Webcamprop() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 528, 541);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		MAIN = new Webcam();
		
		btnCamera = new JButton("CAMERA");
		btnCamera.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MAIN.setVisible(true);
			}
		});
		btnCamera.setBounds(338, 470, 89, 23);
		contentPane.add(btnCamera);
		
		slider_brightness = new JSlider(-64,64,0);
		slider_brightness.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Webcam.BRIGHTNESS = slider_brightness.getValue();
				textField.setText("" + slider_brightness.getValue());
			}
		});
		slider_brightness.setPaintTicks(true);
		slider_brightness.setPaintLabels(true);
		slider_brightness.setMajorTickSpacing(16);
		slider_brightness.setSnapToTicks(true);
		slider_brightness.setBounds(96, 10, 403, 45);
		contentPane.add(slider_brightness);
		
		slider_contrast = new JSlider(0,64,32);
		slider_contrast.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Webcam.CONTRAST = slider_contrast.getValue();
				textField_1.setText("" + slider_contrast.getValue());
			}
		});
		slider_contrast.setPaintTicks(true);
		slider_contrast.setPaintLabels(true);
		slider_contrast.setMajorTickSpacing(8);
		slider_contrast.setSnapToTicks(true);
		slider_contrast.setBounds(96, 66, 403, 45);
		contentPane.add(slider_contrast);
		
		slider_saturation = new JSlider(0,128,60);
		slider_saturation.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Webcam.SATURATION = slider_saturation.getValue();
				textField_2.setText("" + slider_saturation.getValue());
			}
		});
		slider_saturation.setPaintTicks(true);
		slider_saturation.setPaintLabels(true);
		slider_saturation.setMajorTickSpacing(16);
		slider_saturation.setSnapToTicks(true);
		slider_saturation.setBounds(96, 122, 403, 45);
		contentPane.add(slider_saturation);
		
		slider_sharpness = new JSlider(0,6,2);
		slider_sharpness.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Webcam.SHARPNESS = slider_sharpness.getValue();
				textField_3.setText("" + slider_sharpness.getValue());
			}
		});
		slider_sharpness.setPaintTicks(true);
		slider_sharpness.setPaintLabels(true);
		slider_sharpness.setMajorTickSpacing(1);
		slider_sharpness.setSnapToTicks(true);
		slider_sharpness.setBounds(96, 178, 403, 45);
		contentPane.add(slider_sharpness);
		
		slider_hue = new JSlider(-40,40,0);
		slider_hue.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Webcam.HUE = slider_hue.getValue();
				textField_4.setText("" + slider_hue.getValue());
			}
		});
		slider_hue.setPaintTicks(true);
		slider_hue.setPaintLabels(true);
		slider_hue.setMajorTickSpacing(5);
		slider_hue.setSnapToTicks(true);
		slider_hue.setBounds(96, 234, 403, 45);
		contentPane.add(slider_hue);
		
		slider_exposure = new JSlider(-13,-1,-6);
		slider_exposure.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Webcam.EXPOSURE = slider_exposure.getValue();
				textField_5.setText("" + slider_exposure.getValue());
			}
		});
		slider_exposure.setPaintTicks(true);
		slider_exposure.setPaintLabels(true);
		slider_exposure.setMajorTickSpacing(1);
		slider_exposure.setSnapToTicks(true);
		slider_exposure.setBounds(96, 290, 403, 45);
		contentPane.add(slider_exposure);
		
		slider_gamma = new JSlider(72,500,100);
		slider_gamma.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Webcam.GAMMA = slider_gamma.getValue();
				textField_6.setText("" + slider_gamma.getValue());
			}
		});
		slider_gamma.setPaintTicks(true);
		slider_gamma.setPaintLabels(true);
		slider_gamma.setMajorTickSpacing(53);
		slider_gamma.setSnapToTicks(true);
		slider_gamma.setBounds(96, 346, 403, 45);
		contentPane.add(slider_gamma);
		
		slider_gain = new JSlider(0,100,0);
		slider_gain.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				Webcam.GAIN = slider_gain.getValue();
				textField_7.setText("" + slider_gain.getValue());
			}
		});
		slider_gain.setPaintTicks(true);
		slider_gain.setPaintLabels(true);
		slider_gain.setMajorTickSpacing(10);
		slider_gain.setSnapToTicks(true);
		slider_gain.setBounds(96, 402, 403, 45);
		contentPane.add(slider_gain);
		
		JLabel lblBrightness = new JLabel("Brightness");
		lblBrightness.setHorizontalAlignment(SwingConstants.CENTER);
		lblBrightness.setBounds(10, 10, 76, 14);
		contentPane.add(lblBrightness);
		
		JLabel lblContrast = new JLabel("Contrast");
		lblContrast.setHorizontalAlignment(SwingConstants.CENTER);
		lblContrast.setBounds(10, 66, 76, 14);
		contentPane.add(lblContrast);
		
		JLabel lblSaturation = new JLabel("Saturation");
		lblSaturation.setHorizontalAlignment(SwingConstants.CENTER);
		lblSaturation.setBounds(10, 122, 76, 14);
		contentPane.add(lblSaturation);
		
		JLabel lblSharpness = new JLabel("Sharpness");
		lblSharpness.setHorizontalAlignment(SwingConstants.CENTER);
		lblSharpness.setBounds(10, 178, 76, 14);
		contentPane.add(lblSharpness);
		
		JLabel lblHue = new JLabel("Hue");
		lblHue.setHorizontalAlignment(SwingConstants.CENTER);
		lblHue.setBounds(10, 234, 76, 14);
		contentPane.add(lblHue);
		
		JLabel lblExposure = new JLabel("Exposure");
		lblExposure.setHorizontalAlignment(SwingConstants.CENTER);
		lblExposure.setBounds(10, 290, 76, 14);
		contentPane.add(lblExposure);
		
		JLabel lblGamma = new JLabel("Gamma");
		lblGamma.setHorizontalAlignment(SwingConstants.CENTER);
		lblGamma.setBounds(10, 346, 76, 14);
		contentPane.add(lblGamma);
		
		JLabel lblGain = new JLabel("Gain");
		lblGain.setHorizontalAlignment(SwingConstants.CENTER);
		lblGain.setBounds(10, 402, 76, 14);
		contentPane.add(lblGain);
			
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Dialog", Font.BOLD, 27));
		textField.setBounds(10, 24, 76, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_1.setColumns(10);
		textField_1.setBounds(10, 80, 76, 31);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_2.setBounds(10, 136, 76, 31);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_3.setBounds(10, 192, 76, 31);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.CENTER);
		textField_4.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_4.setBounds(10, 248, 76, 31);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setHorizontalAlignment(SwingConstants.CENTER);
		textField_5.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_5.setBounds(10, 304, 76, 31);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setHorizontalAlignment(SwingConstants.CENTER);
		textField_6.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_6.setBounds(10, 360, 76, 31);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setHorizontalAlignment(SwingConstants.CENTER);
		textField_7.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_7.setBounds(10, 416, 76, 31);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		String[] resolution = new String[] {"320 x 240", "640 x 480", "800 x 600", "1024 x 768", "1280 x 720", "1280 x 1024", "1920 x 1080"};
		JComboBox<String> comboBox = new JComboBox<String>(resolution);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch((String)comboBox.getSelectedItem()){
					case("320 x 240"):
						{Webcam.WIDTH = 320; Webcam.HEIGHT = 240; Webcam.lblWebcam.setSize(Webcam.WIDTH, Webcam.HEIGHT); MAIN.setSize(Webcam.WIDTH + 16, Webcam.HEIGHT + 54);
						break;}
					case("640 x 480"):
						{Webcam.WIDTH = 640; Webcam.HEIGHT = 480; Webcam.lblWebcam.setSize(Webcam.WIDTH, Webcam.HEIGHT); MAIN.setSize(Webcam.WIDTH + 16, Webcam.HEIGHT + 54);
						break;}
					case("800 x 600"):
						{Webcam.WIDTH = 800; Webcam.HEIGHT = 600; Webcam.lblWebcam.setSize(Webcam.WIDTH, Webcam.HEIGHT); MAIN.setSize(Webcam.WIDTH + 16, Webcam.HEIGHT + 54);
						break;}
					case("1024 x 768"):
						{Webcam.WIDTH = 1024; Webcam.HEIGHT = 768; Webcam.lblWebcam.setSize(Webcam.WIDTH, Webcam.HEIGHT); MAIN.setSize(Webcam.WIDTH + 16, Webcam.HEIGHT + 54);
						break;}
					case("1280 x 720"):
						{Webcam.WIDTH = 1280; Webcam.HEIGHT = 720; Webcam.lblWebcam.setSize(Webcam.WIDTH, Webcam.HEIGHT); MAIN.setSize(Webcam.WIDTH + 16, Webcam.HEIGHT + 54);
						break;}
					case("1280 x 1024"):
						{Webcam.WIDTH = 1280; Webcam.HEIGHT = 1024; Webcam.lblWebcam.setSize(Webcam.WIDTH, Webcam.HEIGHT); MAIN.setSize(Webcam.WIDTH + 16, Webcam.HEIGHT + 54);
						break;}
					case("1920 x 1080"):
						{Webcam.WIDTH = 1920; Webcam.HEIGHT = 1080; Webcam.lblWebcam.setSize(Webcam.WIDTH, Webcam.HEIGHT); MAIN.setSize(Webcam.WIDTH + 16, Webcam.HEIGHT + 54);
						break;}
				}
			}
		});
		comboBox.setBounds(86, 470, 141, 23);
		contentPane.add(comboBox);
	}
}
