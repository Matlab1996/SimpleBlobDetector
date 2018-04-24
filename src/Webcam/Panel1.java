package Webcam;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Panel1 extends JFrame{

	static JPanel jpanel1 = new JPanel();
	static JLabel label1 = new JLabel();
	private JLabel text1 = new JLabel("Настройка экспозиции");
	private JSlider exposure = new JSlider(-13, -1, -6);
	private JButton back1 = new JButton("Назад");
	private JButton next1 = new JButton("Далее");
	
	public Panel1() {
		super("Application");
		setBounds(100, 100, 355, 415);
		setLocationRelativeTo(null);
		jpanel1.setBackground(new Color(255, 255, 255));
		jpanel1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jpanel1);
		jpanel1.setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label1.setBounds(10, 5, 320, 240);
		jpanel1.add(label1);
		
		text1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		text1.setBounds(10, 250, 320, 31);
		jpanel1.add(text1);

		exposure.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.exposure.onNext(exposure.getValue());
				settings.exposure.subscribe(value -> {
					exposure.setValue(value);
				});
			}
		});
		exposure.setPaintTicks(true);
		exposure.setPaintLabels(true);
		exposure.setMajorTickSpacing(1);
		exposure.setSnapToTicks(false);
		exposure.setBackground(new Color(255, 255, 255));
		exposure.setBounds(10, 290, 320, 45);
		jpanel1.add(exposure);
		
		back1.setBorderPainted(true);
		back1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		back1.setBackground(Color.WHITE);
		back1.setBounds(10, 340, 75, 25);
		back1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		back1.setSelected(false);
		jpanel1.add(back1);
		
		next1.setBackground(Color.WHITE);
		next1.setBorderPainted(true);
		next1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		next1.setBounds(255, 340, 75, 25);
		next1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		next1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove(jpanel1);
				Panel2 panel = new Panel2();
				panel.setVisible(true);
			}
		});
		jpanel1.add(next1);
	}
	
	
}
