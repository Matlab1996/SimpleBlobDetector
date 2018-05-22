package Webcam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Distance {

	public class RangeDouble{
		public double Min;
		public double Max;
		
		public RangeDouble (double min, double max) {
			Min = min;
			Max = max;
		}
	}
	
	public class PointerDetectorSetting{
		double DistanceFromSurface;
        double Exposure;
        RangeDouble Sensivity;
        RangeDouble Diameter;
        
        PointerDetectorSetting (double distanceFromSurface, double exposure, RangeDouble sensivity, RangeDouble diameter) {
        	DistanceFromSurface = distanceFromSurface;
        	Exposure = exposure;
        	Sensivity = sensivity;
        	Diameter = diameter;
        }
	}
	
	public class PointerDetectionSettingsProvider{
		private List<PointerDetectorSetting> pointerDetectionSettings = new ArrayList<PointerDetectorSetting>();
		
		public PointerDetectionSettingsProvider() {
			pointerDetectionSettings.add(new PointerDetectorSetting(0.2, -13, new RangeDouble(0, 220), new RangeDouble(7, 18)));
			pointerDetectionSettings.add(new PointerDetectorSetting(0.4, -13, new RangeDouble(0, 220), new RangeDouble(6, 15)));
			pointerDetectionSettings.add(new PointerDetectorSetting(0.6, -13, new RangeDouble(0, 220), new RangeDouble(5, 12)));
			pointerDetectionSettings.add(new PointerDetectorSetting(1, -13, new RangeDouble(0, 215), new RangeDouble(5, 11)));
			pointerDetectionSettings.add(new PointerDetectorSetting(1.3, -13, new RangeDouble(0, 215), new RangeDouble(4, 9)));
			pointerDetectionSettings.add(new PointerDetectorSetting(1.82, -13, new RangeDouble(0, 200), new RangeDouble(2, 9)));
			pointerDetectionSettings.add(new PointerDetectorSetting(2.6, -12, new RangeDouble(0, 180), new RangeDouble(4, 10)));
			pointerDetectionSettings.add(new PointerDetectorSetting(3.1, -12, new RangeDouble(0, 180), new RangeDouble(3, 8)));
			pointerDetectionSettings.add(new PointerDetectorSetting(3.5, -12, new RangeDouble(0, 170), new RangeDouble(3, 7)));
			pointerDetectionSettings.add(new PointerDetectorSetting(3.9, -12, new RangeDouble(0, 160), new RangeDouble(3, 7)));
			pointerDetectionSettings.add(new PointerDetectorSetting(4.3, -12, new RangeDouble(0, 155), new RangeDouble(3, 7)));
			pointerDetectionSettings.add(new PointerDetectorSetting(4.7, -12, new RangeDouble(0, 150), new RangeDouble(2, 6)));
			pointerDetectionSettings.add(new PointerDetectorSetting(5.5, -12, new RangeDouble(0, 145), new RangeDouble(2, 6)));
			pointerDetectionSettings.add(new PointerDetectorSetting(6, -12, new RangeDouble(0, 135), new RangeDouble(2, 6)));
			
			//_pointerDetectionSettings.OrderBy(s => s.DistanceFromSurface);
			Collections.sort(pointerDetectionSettings, new Comparator<PointerDetectorSetting>() {
				public int compare(PointerDetectorSetting o1, PointerDetectorSetting o2) {
					return  (int) o1.DistanceFromSurface;
				}
			});
		}
		
		public PointerDetectorSetting GetPointerDetectorSetting (double distanceFromSurface) {
			for (int i = 0; i< pointerDetectionSettings.size()-1; i++) {
				if (pointerDetectionSettings.get(i).DistanceFromSurface < distanceFromSurface &&
					pointerDetectionSettings.get(i+1).DistanceFromSurface >= distanceFromSurface) 
						return pointerDetectionSettings.get(i+1);
			}
			
			if (distanceFromSurface <= pointerDetectionSettings.get(0).DistanceFromSurface) return pointerDetectionSettings.get(0);
			else return pointerDetectionSettings.get(pointerDetectionSettings.size()-1);
		}
	}
}
