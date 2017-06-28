package camera;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class PointerDetector {
    // Нижние и Верхние границы для проверки диапазона в цветовом пространстве HSV
    Scalar mLowerBound = new Scalar(0);
    Scalar mUpperBound = new Scalar(255);
    // Минимальная область контура для фильтрации контуров
    private static double mMinContourArea = 0;
    private static double mMaxContourArea = 0;
    // Цветной радиус для проверки диапазона в цветовом пространстве HSV
    private Scalar mColorRadius = new Scalar(25,50,50,0);
    private Mat mSpectrum = new Mat();
    private List<MatOfPoint> mContours = new ArrayList<MatOfPoint>();

    // Кэш
    Mat mPyrDownMat = new Mat();
    Mat mHsvMat = new Mat();
    Mat mMask = new Mat();
    Mat mDilatedMask = new Mat();
    Mat mHierarchy = new Mat();

    public PointerDetector(){
    	mLowerBound.val[0] = 30;
        mUpperBound.val[0] = 205;

        mLowerBound.val[1] = 80;
        mUpperBound.val[1] = 205;

        mLowerBound.val[2] = 30;
        mUpperBound.val[2] = 205;

        mLowerBound.val[3] = 30;
        mUpperBound.val[3] = 205;
    }
    
    public void setColorRadius(Scalar radius) {
        mColorRadius = radius;
    }

    public void setHsvColor(Scalar hsvColor) {
        double minH = (hsvColor.val[0] >= mColorRadius.val[0]) ? hsvColor.val[0]-mColorRadius.val[0] : 0;
        double maxH = (hsvColor.val[0]+mColorRadius.val[0] <= 255) ? hsvColor.val[0]+mColorRadius.val[0] : 255;

        mLowerBound.val[0] = minH;
        mUpperBound.val[0] = maxH;

        mLowerBound.val[1] = 255;
        mUpperBound.val[1] = 255;

        mLowerBound.val[2] = 0;
        mUpperBound.val[2] = 255;

        mLowerBound.val[3] = 0;
        mUpperBound.val[3] = 255;

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

    public void process(Mat rgbaImage) {
        //Размывает изображение и субдискретизирует его
    	Imgproc.pyrDown(rgbaImage, mPyrDownMat);
        Imgproc.pyrDown(mPyrDownMat, mPyrDownMat);

        Imgproc.cvtColor(mPyrDownMat, mHsvMat, Imgproc.COLOR_RGB2YCrCb);
        //Imgproc.cvtPixToPlane
        
        Core.inRange(mHsvMat, mLowerBound, mUpperBound, mMask);
        //Расширяет изображение при помощи определенного элемента структурирования
        //int dilation_size = 5;
        //Mat element1 = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new  Size(2*dilation_size + 1, 2*dilation_size+1));
        Imgproc.dilate(mMask, mDilatedMask, new Mat());

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>();

        Imgproc.findContours(mDilatedMask, contours, mHierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

        Iterator<MatOfPoint> each = contours.iterator();
        
        // Filter contours by area and resize to fit the original image size
        mContours.clear();
        each = contours.iterator();
        while (each.hasNext()) {
            MatOfPoint contour = each.next();
            double area = Imgproc.contourArea(contour);
            if (area >= mMinContourArea && area <= mMaxContourArea ) {
                Core.multiply(contour, new Scalar(4,4), contour);
                mContours.add(contour);
            }
        }
    }

    public List<MatOfPoint> getContours() {
        return mContours;
    }
    
    public void drawDetectedPointers(Mat image){
    	if(!mContours.isEmpty()){
    		Imgproc.drawContours(image, mContours, 0, new Scalar(255,0,0),5);
    		System.out.println("detected");
    	}
    }

}