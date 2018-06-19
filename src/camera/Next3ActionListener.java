package camera;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Next3ActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		Panel.panel.removeAll();
		Panel.panel.setBackground(new Color(255, 254, 255));
		settings.height.onNext(460);
		
		Panel.label.setBounds(10, 5, 320, 240);
		Panel.panel.add(Panel.label);
		
		Panel.text.setText("Минимальный и максимальный размер");
		Panel.text.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.text.setBounds(10, 250, 320, 31);
		Panel.panel.add(Panel.text);
		
		settings.maxArea.subscribe(value -> Panel.maxArea.setValue(value));
		Panel.maxArea.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.maxArea.onNext(Panel.maxArea.getValue());
			}
		});
		Panel.maxArea.setPaintTicks(true);
		Panel.maxArea.setPaintLabels(true);
		Panel.maxArea.setMajorTickSpacing(40);
		Panel.maxArea.setSnapToTicks(false);
		Panel.maxArea.setBackground(Color.white);
		Panel.maxArea.setBounds(10, 290, 320, 45);
		Panel.panel.add(Panel.maxArea);

		settings.minArea.subscribe(value -> Panel.minArea.setValue(value));
		Panel.minArea.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				settings.minArea.onNext(Panel.minArea.getValue());
			}
		});
		Panel.minArea.setPaintTicks(true);
		Panel.minArea.setPaintLabels(true);
		Panel.minArea.setMajorTickSpacing(40);
		Panel.minArea.setSnapToTicks(false);
		Panel.minArea.setBackground(Color.white);
		Panel.minArea.setBounds(10, 340, 320, 45);
		Panel.panel.add(Panel.minArea);
		
		Panel.back4.setBorderPainted(true);
		Panel.back4.setBackground(Color.white);
		Panel.back4.setBounds(10, 390, 75, 25);
		Panel.back4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.back4.addActionListener(new Next2ActionListener());
		Panel.panel.add(Panel.back4);
		
		Panel.next4.setBackground(Color.white);
		Panel.next4.setBorderPainted(true);
		Panel.next4.setBounds(255, 390, 75, 25);
		Panel.next4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		//Panel.next4.addActionListener(new Next3ActionListener());
		Panel.panel.add(Panel.next4);
	}

}