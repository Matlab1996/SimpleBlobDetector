package camera;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

public class Mause {
	private static Robot robot;

	public static void control(int x, int y) throws AWTException {
		if (robot == null) {
			robot = new Robot();
		}

		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
}