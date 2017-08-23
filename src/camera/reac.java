package camera;

import rx.Subscriber;

public class reac {
	
	static class PrintSubscriber extends Subscriber<Object>{
	    private final Integer name;
	    public PrintSubscriber(Integer name) {
	        this.name = name;
	    }
	    @Override
	    public void onCompleted() {
	        //System.out.println(name + ": Completed");
	    }
	    @Override
	    public void onError(Throwable e) {
	        System.out.println(name + ": Error: " + e);
	    }
	    @Override
	    public void onNext(Object v) {
	        System.out.println(name + ": " + v);
	    }
	}

	public static void exampleLate() {
		//Observable<Integer> observable = Observable.just(Videoio.CAP_PROP_BRIGHTNESS);
		//PrintSubscriber s = new PrintSubscriber(Webcamprop.slider_brightness.getValue());
		//observable.subscribe(s);
		
	}
	
}
