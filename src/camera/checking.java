package camera;

import camera.Webcam;
import camera.Webcamprop;

public class checking {
	public static int 	brightness = Webcamprop.slider_brightness.getValue(),
						contrast = Webcamprop.slider_contrast.getValue(),
						saturation = Webcamprop.slider_saturation.getValue(),
						sharpness = Webcamprop.slider_sharpness.getValue(),
						hue = Webcamprop.slider_hue.getValue(),
						exposure = Webcamprop.slider_exposure.getValue(),
						gamma = Webcamprop.slider_gamma.getValue(),
						gain = Webcamprop.slider_gain.getValue(),
						width = Webcam.WIDTH,
						height = Webcam.HEIGHT;

	public static boolean param_check_brightness(){
		if(brightness == Webcamprop.slider_brightness.getValue())
			return false;
		else{
			brightness = Webcamprop.slider_brightness.getValue();
			return true;
		}
	}
	
	public static boolean param_check_contrast(){
		if(contrast == Webcamprop.slider_contrast.getValue())
			return false;
		else{
			contrast = Webcamprop.slider_contrast.getValue();
			return true;
		}
	}
	
	public static boolean param_check_saturation(){
			if(saturation == Webcamprop.slider_saturation.getValue())
				return false;
			else{
				saturation = Webcamprop.slider_saturation.getValue();
				return true;
			}
	}
	
	public static boolean param_check_sharpness(){
		if(sharpness == Webcamprop.slider_sharpness.getValue())
			return false;
		else{
			sharpness = Webcamprop.slider_sharpness.getValue();
			return true;
		}
	}
	
	public static boolean param_check_hue(){
		if(hue == Webcamprop.slider_hue.getValue())
			return false;
		else{
			hue = Webcamprop.slider_hue.getValue();
			return true;
		}
	}
	
	public static boolean param_check_exposure(){
		if(exposure == Webcamprop.slider_exposure.getValue())
			return false;
		else{
			exposure = Webcamprop.slider_exposure.getValue();
			return true;
		}
	}
	
	public static boolean param_check_gamma(){
		if(gamma == Webcamprop.slider_gamma.getValue())
			return false;
		else{
			gamma = Webcamprop.slider_gamma.getValue();
			return true;
		}
	}
	
	public static boolean param_check_gain(){
		if(gain == Webcamprop.slider_gain.getValue())
			return false;
		else{
			gain = Webcamprop.slider_gain.getValue();
			return true;
		}
	}
	
	public static boolean param_check_width(){
		if(width == Webcam.WIDTH)
			return false;
		else{
			width = Webcam.WIDTH;
			return true;
		}
	}
	
	public static boolean param_check_height(){
		if(height == Webcam.HEIGHT)
			return false;
		else{
			height = Webcam.HEIGHT;
			return true;
		}
	}
}
