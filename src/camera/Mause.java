package camera;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Mause {

	@SuppressWarnings("deprecation")
	public static void control(int x,int y) throws AWTException {
		 Robot robot = new Robot();
		 robot.delay(1000);
		 robot.mouseMove(x, y);
		 robot.mousePress(InputEvent.BUTTON1_MASK);
		 robot.mouseRelease(InputEvent.BUTTON1_MASK);
	 }
}
