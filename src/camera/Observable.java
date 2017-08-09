package camera;

import rx.subjects.BehaviorSubject;

public class Observable {

	public static void exampleLate() {
		BehaviorSubject<Integer> s = BehaviorSubject.create();
		s.onNext(0);
		s.onNext(1);
		s.onNext(2);
		s.subscribe(v -> System.out.println("Late: " + v)); 
		s.onNext(4);
		
	}
	
}
