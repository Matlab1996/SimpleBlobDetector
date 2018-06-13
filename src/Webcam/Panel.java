package Webcam;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Panel extends JFrame{

	static JPanel panel = new JPanel();
	static JLabel label = new JLabel();
	static JLabel text = new JLabel();
	
	static JSlider exposure = new JSlider(-13, -1, -6);
	static JSlider distance = new JSlider(0, 15, 1);
	static JSlider upper 	= new JSlider(10, 220, 100);
	static JSlider maxArea 	= new JSlider(0, 400, 100);
	static JSlider minArea 	= new JSlider(0, 400, 25);
	
	static JButton back1 = new JButton("Назад");
	static JButton back2 = new JButton("Назад");
	static JButton back3 = new JButton("Назад");
	static JButton back4 = new JButton("Назад");
	
	static JButton next1 = new JButton("Далее");
	static JButton next2 = new JButton("Далее");
	static JButton next3 = new JButton("Далее");
	static JButton next4 = new JButton("Далее");
	
	public Panel() {
		super("Application");
		setBounds(100, 100, 355, 460);
		setLocationRelativeTo(null);
		
		settings.height.subscribe(h -> setSize(355, h));
		
		panel.setBackground(Color.white);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		label.setBounds(10, 5, 320, 240);
		panel.add(label);
		
		text.setText("Настройка экспозиции");
		text.setFont(new Font("Times New Roman", Font.BOLD, 14));
		text.setBounds(10, 250, 320, 31);
		panel.add(text);

		settings.exposure.subscribe(value -> exposure.setValue(value));
		exposure.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.exposure.onNext(exposure.getValue());
			}
		});
		exposure.setPaintTicks(true);
		exposure.setPaintLabels(true);
		exposure.setMajorTickSpacing(1);
		exposure.setSnapToTicks(false);
		exposure.setBackground(Color.white);
		exposure.setBounds(10, 290, 320, 45);
		panel.add(exposure);
		
		back1.setBorderPainted(true);
		back1.setBackground(Color.white);
		back1.setBounds(10, 340, 75, 25);
		back1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		panel.add(back1);
		
		next1.setBackground(Color.white);
		next1.setBorderPainted(true);
		next1.setBounds(255, 340, 75, 25);
		next1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		next1.addActionListener(new Next1ActionListener());
		panel.add(next1);
	}
}