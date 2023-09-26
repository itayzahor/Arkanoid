// Itay Zahor 208127480

import biuoop.DrawSurface;
import biuoop.GUI;


/**
 * The Animation runner runs an animation with 60 fps until the animation
 * should stop.
 *
 * @author Itay Zahor
 */
public class AnimationRunner {
    private static final int FRAMES_PER_SECOND = 60;
    private final GUI gui;
    private final biuoop.Sleeper sleeper;

    /**
     * Instantiates a new Animation runner.
     *
     * @param g      the gui
     */
    public AnimationRunner(GUI g) {
        this.gui = g;
        this.sleeper = new biuoop.Sleeper();
    }

    /**
     * Run.
     *
     * @param animation the animation
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / FRAMES_PER_SECOND;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();

            DrawSurface d = gui.getDrawSurface();

            // Draw the frame
            animation.doOneFrame(d);

            gui.show(d);

            // Sleep for the remaining time to maintain a constant FPS
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
