package camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Moments;

public class PointerDetector {
    List<Mat> channel = new ArrayList<>(3);
	Scalar mLowerBound = new Scalar(0);
    Scalar mUpperBound = new Scalar(255);
   double mMinContourArea = 0;
    double mMaxContourArea = 100;
    private Mat mSpectrum = new Mat();
    private List<MatOfPoint> mContours = new ArrayList<MatOfPoint>();

    Mat mPyrDownMat = new Mat();
    Mat mHsvMat = new Mat();
    Mat mMask = new Mat();
    Mat mHierarchy = new Mat();

    List<MatOfPoint> contours = new ArrayList<>();
    
    public PointerDetector() {
        settings.minArea.subscribe(minArea -> setMinContourArea(minArea));
        settings.maxArea.subscribe(maxArea -> setMaxContourArea(maxArea));        
    }

    public void setHsvColor(Scalar hsvColor) {
        double minH = (hsvColor.val[0] >= 10) ? hsvColor.val[0]-10 : 0;
        double maxH = (hsvColor.val[0]+10 <= 255) ? hsvColor.val[0]+10 : 255;

        mLowerBound.val[0] = minH;
        mUpperBound.val[0] = maxH;

        Mat spectrumHsv = new Mat(1, (int)(maxH-minH), CvType.CV_8UC3);

        for (int j = 0; j < maxH-minH; j++) {
            byte[] tmp = {(byte)(minH+j), (byte)255, (byte)255};
            spectrumHsv.put(0, j, tmp);
        }

        Imgproc.cvtColor(spectrumHsv, mSpectrum, Imgproc.COLOR_HSV2RGB_FULL, 4);
    }

    public Mat getSpectrum() {
        return mSpectrum;
    }

    public void setMinContourArea(double area) {
        mMinContourArea = area;
    }
    public void setMaxContourArea(double area) {
        mMaxContourArea = area;
    }

    public void process(Mat rgbaImage, Mat mDilatedMask) {
        mContours.clear();

        // Imgproc.pyrDown(rgbaImage, mPyrDownMat);
        // Imgproc.cvtColor(mPyrDownMat, mHsvMat, Imgproc.COLOR_RGB2YCrCb);
        
        Imgproc.cvtColor(rgbaImage, mHsvMat, Imgproc.COLOR_RGB2YCrCb);
        
		Core.split(mHsvMat, channel ); 
        Mat Y = channel.get(0);
        //Mat Cr = channel.get(1); 
        //Mat Cb = channel.get(2);
        Imgproc.threshold(Y, Y, mLowerBound.val[0], mUpperBound.val[0], Imgproc.THRESH_BINARY);
        
        Core.bitwise_not(Y, mMask);
        
        Mat canny = new Mat();
        Imgproc.Canny(mMask, canny, 20, 40, 3, true);
        Imgproc.dilate(canny, mDilatedMask, new Mat());
        Imgproc.findContours(mDilatedMask, mContours, mHierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        Iterator<MatOfPoint> each = contours.iterator();
        // Filter contours by area and resize to fit the original image size
        while (each.hasNext()) {
            MatOfPoint contour = each.next();
            double area = Imgproc.contourArea(contour);
            if (area >= mMinContourArea && area <= mMaxContourArea ) {
                Core.multiply(contour, new Scalar(255, 0, 0), contour);
                mContours.add(contour);
            }
        }
    }
        
    public List<MatOfPoint> getContours() {
        return mContours;
    }
    
	public void drawDetectedPointers(Mat image){
		Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2RGB);
    		for (int i = 0; i < mContours.size(); i++) {
    			Imgproc.drawContours(image, mContours, i, new Scalar(0,0,250), -1);
    			System.out.println("detected");
    		}
    }
	
	public void centerOfContour(Mat rgbaImage) {
		List<Moments> mu = new ArrayList<Moments>(mContours.size());
	    for (int i = 0; i < mContours.size(); i++) {
	        mu.add(i, Imgproc.moments(mContours.get(i), false));
	        Moments p = mu.get(i);
	        int x = (int) (p.get_m10() / p.get_m00());
	        int y = (int) (p.get_m01() / p.get_m00());
	        Imgproc.circle(rgbaImage, new Point(x, y), 4, new Scalar(255,49,0,255));
	    }
	}

}