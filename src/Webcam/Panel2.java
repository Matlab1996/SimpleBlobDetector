package Webcam;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class Panel2 extends JFrame{
	static JPanel jpanel2 = new JPanel();
	static JLabel label2 = new JLabel();
	private JLabel text2 = new JLabel("Настройка чувствительности");
	private JSlider mUpper = new JSlider(10, 220, 100);
	private JButton back2 = new JButton("Назад");
	private JButton next2 = new JButton("Далее");
	
	public Panel2() {
		super("Application");
		setBounds(100, 100, 355, 415);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		jpanel2.setBackground(new Color(255, 255, 255));
		jpanel2.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(jpanel2);
		jpanel2.setLayout(null);
		
		label2.setBounds(10, 5, 320, 240);
		jpanel2.add(label2);
		
		text2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		text2.setBounds(10, 250, 320, 31);
		jpanel2.add(text2);

		mUpper.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.upper.onNext(mUpper.getValue());
				settings.upper.subscribe(value -> {
					mUpper.setValue((int) value);
				});
			
			}
		});	
		mUpper.setPaintTicks(true);
		mUpper.setPaintLabels(true);
		mUpper.setMajorTickSpacing(30);
		mUpper.setSnapToTicks(false);
		mUpper.setBackground(new Color(255, 255, 255));
		mUpper.setBounds(10, 290, 320, 45);
		jpanel2.add(mUpper);
		
		back2.setBorderPainted(true);
		back2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		back2.setBackground(Color.WHITE);
		back2.setBounds(10, 340, 75, 25);
		back2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		back2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Panel1 panel = new Panel1();
				panel.setVisible(true);
				
			}
			
		});
		jpanel2.add(back2);
		
		next2.setBackground(Color.WHITE);
		next2.setBorderPainted(true);
		next2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		next2.setBounds(255, 340, 75, 25);
		next2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		next2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		jpanel2.add(next2);
		
	}
}
