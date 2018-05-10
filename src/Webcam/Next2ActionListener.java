package Webcam;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Next2ActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Panel.panel.removeAll();
		Panel.panel.setBackground(Color.white);
		
		Panel.label.setBounds(10, 5, 320, 240);
		Panel.panel.add(Panel.label);
		
		//Panel1.text.removeAll();
		Panel.text.setText("Настройка чувствительности");
		Panel.text.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.text.setBounds(10, 250, 320, 31);
		Panel.panel.add(Panel.text);
		
		Panel.upper.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.upper.onNext(Panel.upper.getValue());
				settings.upper.subscribe(value -> Panel.upper.setValue((int) value));
			}
		});
		Panel.upper.setPaintTicks(true);
		Panel.upper.setPaintLabels(true);
		Panel.upper.setMajorTickSpacing(30);
		Panel.upper.setSnapToTicks(false);
		Panel.upper.setBackground(Color.white);
		Panel.upper.setBounds(10, 290, 320, 45);
		Panel.panel.add(Panel.upper);
		
		
		Panel.back3.setBorderPainted(true);
		Panel.back3.setBackground(Color.white);
		Panel.back3.setBounds(10, 340, 75, 25);
		Panel.back3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.back3.addActionListener(new Next1ActionListener());
		Panel.panel.add(Panel.back3);
		
		Panel.next3.setBackground(Color.white);
		Panel.next3.setBorderPainted(true);
		Panel.next3.setBounds(255, 340, 75, 25);
		Panel.next3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.next3.addActionListener(new Next3ActionListener());
		Panel.panel.add(Panel.next3);
	}

}
