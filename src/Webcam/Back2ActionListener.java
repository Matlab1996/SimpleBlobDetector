package Webcam;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;

public class Back2ActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Panel.panel.removeAll();
		Panel.panel.setBackground(Color.YELLOW);
		
		Panel.label.setBounds(10, 5, 320, 240);
		Panel.panel.add(Panel.label);
		
		Panel.text.setText("Настройка экспозиции");
		Panel.text.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.text.setBounds(10, 250, 320, 31);
		Panel.panel.add(Panel.text);
		
		Panel.exposure.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.exposure.onNext(Panel.exposure.getValue());
				settings.exposure.subscribe(value -> {
					Panel.exposure.setValue(value);
				});
			}
		});
		Panel.exposure.setPaintTicks(true);
		Panel.exposure.setPaintLabels(true);
		Panel.exposure.setMajorTickSpacing(1);
		Panel.exposure.setSnapToTicks(false);
		Panel.exposure.setBackground(new Color(0, 255, 255));
		Panel.exposure.setBounds(10, 290, 320, 45);
		Panel.panel.add(Panel.exposure);
		
		Panel.back1.setBorderPainted(true);
		Panel.back1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		Panel.back1.setBackground(Color.WHITE);
		Panel.back1.setBounds(10, 340, 75, 25);
		Panel.back1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.panel.add(Panel.back1);
		
		Panel.next1.setBackground(Color.WHITE);
		Panel.next1.setBorderPainted(true);
		Panel.next1.setBorder(BorderFactory.createEtchedBorder(Color.BLUE , Color.BLUE));
		Panel.next1.setBounds(255, 340, 75, 25);
		Panel.next1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.next1.addActionListener(new Next1ActionListener());
		Panel.panel.add(Panel.next1);
	}

}
