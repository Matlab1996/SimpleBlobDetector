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
						height = Webcam.HEIGHT,
						maxArea = Webcamprop.slider_maxArea.getValue(),
						minArea = Webcamprop.slider_minArea.getValue(),
						mLower0 = Webcamprop.slider_mLower0.getValue(),
						mUpper0 = Webcamprop.slider_mUpper0.getValue(),
						mLower1 = Webcamprop.slider_mLower1.getValue(),
						mUpper1 = Webcamprop.slider_mUpper1.getValue(),
						mLower2 = Webcamprop.slider_mLower2.getValue(),
						mUpper2 = Webcamprop.slider_mUpper2.getValue(),
						mLower3 = Webcamprop.slider_mLower3.getValue(),
						mUpper3 = Webcamprop.slider_mUpper3.getValue();

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
	
	//maxArea
	public static boolean param_check_maxArea(){
		if(maxArea == Webcamprop.slider_maxArea.getValue())
			return false;
		else{
			maxArea = Webcamprop.slider_maxArea.getValue();
			return true;
		}
	}
	
	//minArea
	public static boolean param_check_minArea(){
		if(minArea == Webcamprop.slider_minArea.getValue())
			return false;
		else{
			minArea = Webcamprop.slider_minArea.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mLower0(){
		if(mLower0 == Webcamprop.slider_mLower0.getValue())
			return false;
		else{
			mLower0 = Webcamprop.slider_mLower0.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mUpper0(){
		if(mUpper0 == Webcamprop.slider_mUpper0.getValue())
			return false;
		else{
			mUpper0 = Webcamprop.slider_mUpper0.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mLower1(){
		if(mLower1 == Webcamprop.slider_mLower1.getValue())
			return false;
		else{
			mLower1 = Webcamprop.slider_mLower1.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mUpper1(){
		if(mUpper1 == Webcamprop.slider_mUpper1.getValue())
			return false;
		else{
			mUpper1 = Webcamprop.slider_mUpper1.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mLower2(){
		if(mLower2 == Webcamprop.slider_mLower2.getValue())
			return false;
		else{
			mLower2 = Webcamprop.slider_mLower2.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mUpper2(){
		if(mUpper2 == Webcamprop.slider_mUpper2.getValue())
			return false;
		else{
			mUpper2 = Webcamprop.slider_mUpper2.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mLower3(){
		if(mLower3 == Webcamprop.slider_mLower3.getValue())
			return false;
		else{
			mLower3 = Webcamprop.slider_mLower3.getValue();
			return true;
		}
	}
	
	public static boolean param_check_mUpper3(){
		if(mUpper3 == Webcamprop.slider_mUpper3.getValue())
			return false;
		else{
			mUpper3 = Webcamprop.slider_mUpper3.getValue();
			return true;
		}
	}
	
}
