package camera;

import rx.subjects.BehaviorSubject;

public class settings {
    public static BehaviorSubject<Integer> width = BehaviorSubject.create(640);
    public static BehaviorSubject<Integer> height = BehaviorSubject.create(480);
    public static BehaviorSubject<Integer> brightness = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> contrast = BehaviorSubject.create(32);
    public static BehaviorSubject<Integer> saturation = BehaviorSubject.create(60);
    public static BehaviorSubject<Integer> sharpness = BehaviorSubject.create(2);
    public static BehaviorSubject<Integer> hue = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> exposure = BehaviorSubject.create(-6);
    public static BehaviorSubject<Integer> gamma = BehaviorSubject.create(100);
    public static BehaviorSubject<Integer> gain = BehaviorSubject.create(0);
    public static BehaviorSubject<Integer> upper = BehaviorSubject.create(100);
    
}