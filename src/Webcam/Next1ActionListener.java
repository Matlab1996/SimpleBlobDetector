package Webcam;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;

public class Next1ActionListener implements ActionListener{

	
	@Override
	public void actionPerformed(ActionEvent e) {
		Panel.panel.removeAll();
		Panel.panel.setBackground(Color.CYAN);
		
		Panel.label.setBounds(10, 5, 320, 240);
		Panel.panel.add(Panel.label);
		
		Panel.text.setText("Выберите расстояние до поверхности");
		Panel.text.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.text.setBounds(10, 250, 320, 31);
		Panel.panel.add(Panel.text);
		
		//slider.addMouseMotionListener(new MouseMotionAdapter() {
		//	@Override
		//	public void mouseDragged(MouseEvent e) {
		//		settings.upper.onNext(slider.getValue());
		//		settings.upper.subscribe(value -> slider.setValue((int) value));
		//	}
		//});
		Panel.distance.setPaintTicks(true);
		Panel.distance.setPaintLabels(true);
		Panel.distance.setMajorTickSpacing(1);
		Panel.distance.setSnapToTicks(false);
		Panel.distance.setBackground(new Color(0, 127, 127));
		Panel.distance.setBounds(10, 290, 320, 45);
		Panel.panel.add(Panel.distance);
		
		
		Panel.back2.setBorderPainted(true);
		Panel.back2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		Panel.back2.setBackground(Color.GREEN);
		Panel.back2.setBounds(10, 340, 75, 25);
		Panel.back2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.back2.addActionListener(new Back2ActionListener());
		Panel.panel.add(Panel.back2);
		
		Panel.next2.setBackground(Color.BLUE);
		Panel.next2.setBorderPainted(true);
		Panel.next2.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		Panel.next2.setBounds(255, 340, 75, 25);
		Panel.next2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.next2.addActionListener(new Next2ActionListener());
		Panel.panel.add(Panel.next2);
	}

}
