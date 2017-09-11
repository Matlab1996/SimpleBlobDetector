package camera;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import camera.Webcam;
import camera.Webcamprop;
import camera.settings.CaptureSize;

@SuppressWarnings("serial")
public class Webcamprop extends JFrame {

	private JPanel contentPane;
	private JButton btnCamera;

	public static JSlider slider_brightness, slider_contrast, slider_saturation, slider_sharpness, slider_hue,
			slider_exposure, slider_gamma, slider_gain, slider_minArea, slider_maxArea, slider_mUpper;
	private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6,
			textField_7, textField_8, textField_9, textField_11;

	Webcam MAIN;

	PointerDetector pointerDetector = new PointerDetector();

	public static void main(String[] args) throws IOException {
		URL executionDirPath = Webcamprop.class.getProtectionDomain().getCodeSource().getLocation();

		System.load(executionDirPath.toString() + "/opencv_java330.dll");

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
		setBounds(50, 50, 1070, 650);
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
		btnCamera.setBounds(338, 578, 89, 23);
		contentPane.add(btnCamera);

		slider_brightness = new JSlider(-64, 64, 0);

		slider_brightness.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				settings.brightness.onNext(slider_brightness.getValue());
				settings.brightness.subscribe(value -> {
					textField.setText("" + value);
					slider_brightness.setValue(value);
				});
			}
		});
		slider_brightness.setPaintTicks(true);
		slider_brightness.setPaintLabels(true);
		slider_brightness.setMajorTickSpacing(8);
		slider_brightness.setSnapToTicks(false);
		slider_brightness.setBounds(96, 10, 403, 45);
		contentPane.add(slider_brightness);

		slider_contrast = new JSlider(0, 64, 32);
		slider_contrast.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.contrast.onNext(slider_contrast.getValue());
				settings.contrast.subscribe(value -> {
					textField_1.setText("" + value);
					slider_contrast.setValue(value);
				});
			}
		});
		slider_contrast.setPaintTicks(true);
		slider_contrast.setPaintLabels(true);
		slider_contrast.setMajorTickSpacing(5);
		slider_contrast.setSnapToTicks(false);
		slider_contrast.setBounds(96, 66, 403, 45);
		contentPane.add(slider_contrast);

		slider_saturation = new JSlider(0, 128, 60);

		slider_saturation.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.saturation.onNext(slider_saturation.getValue());
				settings.saturation.subscribe(value -> {
					textField_2.setText("" + value);
					slider_saturation.setValue(value);
				});
			}
		});
		slider_saturation.setPaintTicks(true);
		slider_saturation.setPaintLabels(true);
		slider_saturation.setMajorTickSpacing(16);
		slider_saturation.setSnapToTicks(false);
		slider_saturation.setBounds(96, 122, 403, 45);
		contentPane.add(slider_saturation);

		slider_sharpness = new JSlider(0, 6, 2);

		slider_sharpness.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.sharpness.onNext(slider_sharpness.getValue());
				settings.sharpness.subscribe(value -> {
					textField_3.setText("" + value);
					slider_sharpness.setValue(value);
				});
			}
		});
		slider_sharpness.setPaintTicks(true);
		slider_sharpness.setPaintLabels(true);
		slider_sharpness.setMajorTickSpacing(1);
		slider_sharpness.setSnapToTicks(false);
		slider_sharpness.setBounds(96, 178, 403, 45);
		contentPane.add(slider_sharpness);

		slider_hue = new JSlider(-40, 40, 0);

		slider_hue.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.hue.onNext(slider_hue.getValue());
				settings.hue.subscribe(value -> {
					textField_4.setText("" + value);
					slider_hue.setValue(value);
				});
			}
		});
		slider_hue.setPaintTicks(true);
		slider_hue.setPaintLabels(true);
		slider_hue.setMajorTickSpacing(5);
		slider_hue.setSnapToTicks(false);
		slider_hue.setBounds(96, 234, 403, 45);
		contentPane.add(slider_hue);

		slider_exposure = new JSlider(-13, -1, -6);

		slider_exposure.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.exposure.onNext(slider_exposure.getValue());
				settings.exposure.subscribe(value -> {
					textField_5.setText("" + value);
					slider_exposure.setValue(value);
				});
			}
		});
		slider_exposure.setPaintTicks(true);
		slider_exposure.setPaintLabels(true);
		slider_exposure.setMajorTickSpacing(1);
		slider_exposure.setSnapToTicks(false);
		slider_exposure.setBounds(96, 290, 403, 45);
		contentPane.add(slider_exposure);

		slider_gamma = new JSlider(72, 500, 100);

		slider_gamma.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.gamma.onNext(slider_gamma.getValue());
				settings.gamma.subscribe(value -> {
					textField_6.setText("" + value);
					slider_gamma.setValue(value);
				});
			}
		});
		slider_gamma.setPaintTicks(true);
		slider_gamma.setPaintLabels(true);
		slider_gamma.setMajorTickSpacing(30);
		slider_gamma.setSnapToTicks(false);
		slider_gamma.setBounds(96, 346, 403, 45);
		contentPane.add(slider_gamma);

		slider_gain = new JSlider(0, 100, 0);

		slider_gain.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.gain.onNext(slider_gain.getValue());
				settings.gain.subscribe(value -> {
					textField_7.setText("" + value);
					slider_gain.setValue(value);
				});
			}
		});
		slider_gain.setPaintTicks(true);
		slider_gain.setPaintLabels(true);
		slider_gain.setMajorTickSpacing(5);
		slider_gain.setSnapToTicks(false);
		slider_gain.setBounds(96, 402, 403, 45);
		contentPane.add(slider_gain);

		// Добавил JSlider maxArea
		slider_maxArea = new JSlider(0, 100, 100);

		slider_maxArea.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.maxArea.onNext(slider_maxArea.getValue());
				settings.maxArea.subscribe(value -> {
					textField_8.setText("" + value);
					slider_maxArea.setValue(value);
				});
			}
		});
		slider_maxArea.setPaintTicks(true);
		slider_maxArea.setPaintLabels(true);
		slider_maxArea.setMajorTickSpacing(5);
		slider_maxArea.setSnapToTicks(false);
		slider_maxArea.setBounds(96, 458, 403, 45);
		contentPane.add(slider_maxArea);

		// Добавил JSlider minArea
		slider_minArea = new JSlider(0, 100, 25);

		slider_minArea.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.minArea.onNext(slider_minArea.getValue());
				settings.minArea.subscribe(value -> {
					textField_9.setText("" + value);
					slider_minArea.setValue(value);
				});
			}
		});
		slider_minArea.setPaintTicks(true);
		slider_minArea.setPaintLabels(true);
		slider_minArea.setMajorTickSpacing(5);
		slider_minArea.setSnapToTicks(false);
		slider_minArea.setBounds(96, 510, 403, 45);
		contentPane.add(slider_minArea);

		slider_mUpper = new JSlider(10, 220, 100);

		slider_mUpper.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.upper.onNext(slider_mUpper.getValue());
				// Webcam.Upper.val[0] = slider_mUpper.getValue();
				settings.upper.subscribe(value -> {
					textField_11.setText("" + value);
					slider_mUpper.setValue(value);
				});
			}
		});
		slider_mUpper.setPaintTicks(true);
		slider_mUpper.setPaintLabels(true);
		slider_mUpper.setMajorTickSpacing(30);
		slider_mUpper.setSnapToTicks(false);
		slider_mUpper.setBounds(634, 10, 403, 45);
		contentPane.add(slider_mUpper);

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

		//Добавил JLabel maxArea
		JLabel lblmaxArea = new JLabel("maxArea");
		lblmaxArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblmaxArea.setBounds(10, 458, 76, 14);
		contentPane.add(lblmaxArea);

		//Добавил JLabel minArea
		JLabel lblminArea = new JLabel("minArea");
		lblminArea.setHorizontalAlignment(SwingConstants.CENTER);
		lblminArea.setBounds(10, 512, 76, 14);
		contentPane.add(lblminArea);

		JLabel lblmUpper = new JLabel("mUpper");
		lblmUpper.setHorizontalAlignment(SwingConstants.CENTER);
		lblmUpper.setBounds(538, 10, 76, 14);
		contentPane.add(lblmUpper);

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

		//Добавил TextField maxArea
		textField_8 = new JTextField();
		textField_8.setHorizontalAlignment(SwingConstants.CENTER);
		textField_8.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_8.setBounds(10, 472, 76, 31);
		contentPane.add(textField_8);
		textField_8.setColumns(10);

		//Добавил TextField minArea
		textField_9 = new JTextField();
		textField_9.setHorizontalAlignment(SwingConstants.CENTER);
		textField_9.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_9.setBounds(10, 528, 76, 31);
		contentPane.add(textField_9);
		textField_9.setColumns(10);

		//Upper
		textField_11 = new JTextField();
		textField_11.setHorizontalAlignment(SwingConstants.CENTER);
		textField_11.setFont(new Font("Dialog", Font.BOLD, 27));
		textField_11.setBounds(538, 24, 76, 31);
		contentPane.add(textField_11);
		textField_11.setColumns(10);

		String[] resolution = new String[] { "320 x 240", "640 x 480", "800 x 600", "1024 x 768", "1280 x 720",
				"1280 x 1024", "1920 x 1080" };
		JComboBox<String> comboBox = new JComboBox<String>(resolution);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				switch ((String) comboBox.getSelectedItem()) {
				case ("320 x 240"): {
					settings.captureSize.onNext(new CaptureSize(320, 240));
					settings.captureSize.subscribe(captureSize -> {
						Webcam.lblWebcam.setSize(captureSize.w, captureSize.h);
						MAIN.setSize(captureSize.w + 16, captureSize.h + 54);
					});

					break;
				}
				case ("640 x 480"): {
					settings.captureSize.onNext(new CaptureSize(640, 480));
					settings.captureSize.subscribe(captureSize -> {
						Webcam.lblWebcam.setSize(captureSize.w, captureSize.h);
						MAIN.setSize(captureSize.w + 16, captureSize.h + 54);
					});
					break;
				}
				case ("800 x 600"): {
					settings.captureSize.onNext(new CaptureSize(800, 600));
					settings.captureSize.subscribe(captureSize -> {
						Webcam.lblWebcam.setSize(captureSize.w, captureSize.h);
						MAIN.setSize(captureSize.w + 16, captureSize.h + 54);
					});
					break;
				}
				case ("1024 x 768"): {
					settings.captureSize.onNext(new CaptureSize(1024, 768));
					settings.captureSize.subscribe(captureSize -> {
						Webcam.lblWebcam.setSize(captureSize.w, captureSize.h);
						MAIN.setSize(captureSize.w + 16, captureSize.h + 54);
					});
					break;
				}
				case ("1280 x 720"): {
					settings.captureSize.onNext(new CaptureSize(1280, 720));
					settings.captureSize.subscribe(captureSize -> {
						Webcam.lblWebcam.setSize(captureSize.w, captureSize.h);
						MAIN.setSize(captureSize.w + 16, captureSize.h + 54);
					});
					break;
				}
				case ("1280 x 1024"): {
					settings.captureSize.onNext(new CaptureSize(1280, 1024));
					settings.captureSize.subscribe(captureSize -> {
						Webcam.lblWebcam.setSize(captureSize.w, captureSize.h);
						MAIN.setSize(captureSize.w + 16, captureSize.h + 54);
					});
					break;
				}
				case ("1920 x 1080"): {
					settings.captureSize.onNext(new CaptureSize(1920, 1080));
					settings.captureSize.subscribe(captureSize -> {
						Webcam.lblWebcam.setSize(captureSize.w, captureSize.h);
						MAIN.setSize(captureSize.w + 16, captureSize.h + 54);
					});
					break;
				}
				}
			}
		});
		comboBox.setBounds(86, 578, 141, 23);
		contentPane.add(comboBox);
	}
}
