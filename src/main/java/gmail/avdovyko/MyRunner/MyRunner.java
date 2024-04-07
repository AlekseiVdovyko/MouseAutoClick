package gmail.avdovyko.MyRunner;

import java.awt.*;
import java.awt.event.InputEvent;

public class MyRunner implements Runnable{

    long firstDelay;
    long secondDelay;
    int firstPositionX;
    int firstPositionY;
    int secondPositionX;
    int secondPositionY;

    public MyRunner(long firstDelay, long secondDelay, int firstPositionX, int firstPositionY, int secondPositionX, int secondPositionY) {
        this.firstDelay = firstDelay;
        this.secondDelay = secondDelay;
        this.firstPositionX = firstPositionX;
        this.firstPositionY = firstPositionY;
        this.secondPositionX = secondPositionX;
        this.secondPositionY = secondPositionY;
    }

    @Override
    public void run() {
        clickDelay(firstDelay, secondDelay, firstPositionX, firstPositionY,
        secondPositionX, secondPositionY);

    }

    public void clickDelay(long firstDelay, long secondDelay, int firstPositionX, int firstPositionY,
                           int secondPositionX, int secondPositionY) {

        while (!Thread.currentThread().isInterrupted()) {

            try {

                Robot robot = new Robot();

                while (true) {
                    Thread.sleep(firstDelay);
                    robot.mouseMove(firstPositionX, firstPositionY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

                    Thread.sleep(secondDelay);
                    robot.mouseMove(secondPositionX, secondPositionY);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                }
            } catch (AWTException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
