package camera;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import camera.Distance.PointerDetectionSettingsProvider;
import camera.Distance.PointerDetectorSetting;

public class Next1ActionListener implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		Panel.panel.removeAll();
		settings.height.onNext(165);
		Panel.panel.setBackground(new Color(255, 254, 255));
		
		Panel.text.setText("Выберите расстояние до поверхности");
		Panel.text.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.text.setBounds(10, 5, 320, 31);
		Panel.panel.add(Panel.text);
		
		Panel.distance.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				PointerDetectorSetting tempSettings = PointerDetectionSettingsProvider
						.GetPointerDetectorSetting(Panel.distance.getValue());
				settings.upper.onNext((int) tempSettings.Sensivity.Max);
				settings.exposure.onNext((int) tempSettings.Exposure);
				settings.minArea.onNext((int) tempSettings.Diameter.Min);
				settings.maxArea.onNext((int) tempSettings.Diameter.Max);
			}
		});
		Panel.distance.setPaintTicks(true);
		Panel.distance.setPaintLabels(true);
		Panel.distance.setMajorTickSpacing(1);
		Panel.distance.setSnapToTicks(false);
		Panel.distance.setBackground(Color.white);
		Panel.distance.setBounds(10, 45, 320, 45);
		Panel.panel.add(Panel.distance);
		
		
		Panel.back2.setBorderPainted(true);
		Panel.back2.setBackground(Color.white);
		Panel.back2.setBounds(10, 95, 75, 25);
		Panel.back2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.back2.addActionListener(new Back2ActionListener());
		Panel.panel.add(Panel.back2);
		
		Panel.next2.setBackground(Color.white);
		Panel.next2.setBorderPainted(true);
		Panel.next2.setBounds(255, 95, 75, 25);
		Panel.next2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		Panel.next2.addActionListener(new Next2ActionListener());
		Panel.panel.add(Panel.next2);
	}

}