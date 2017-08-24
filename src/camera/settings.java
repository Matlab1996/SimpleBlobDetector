package camera;

import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;
import org.opencv.videoio.Videoio;

import camera.ImageProcessor;
import camera.checking;
import camera.reac.PrintSubscriber;
import rx.Observable;

public static class Settings {
    public static BehaviorSubject<Integer> width = BehaviorSubject.create(640);
    public static BehaviorSubject<Integer> height = BehaviorSubject.create(480);
    public static BehaviorSubject<Integer> brightness = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> contrast = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> saturation = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> sharpness = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> hue = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> exposure = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> gamma = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> gain = BehaviorSubject.create(0);
}