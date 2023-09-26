
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * The animation of a countdown.
 */
public class CountdownAnimation implements Animation {
    private static final int FONT_SIZE = 200;
    private static final int MILLIE_TO_SECONDS = 1000;
    private static final int FRAMES_PER_SECOND = 60;
    private int countFrom;
    private final int milliSeconds;
    private final SpriteCollection gameScreen;
    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds, int countFrom,
                              SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        // divides the number of milliseconds by the number to count from and
        // decrease the frames per second spent on the animation
        this.milliSeconds = (int) (((numOfSeconds * MILLIE_TO_SECONDS)
                / countFrom)) - (MILLIE_TO_SECONDS / FRAMES_PER_SECOND);
        this.gameScreen = gameScreen;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        long startTime = System.currentTimeMillis();
        biuoop.Sleeper sleeper = new Sleeper();
        // draws the background
        gameScreen.drawAllOn(d);
        // draws the countdown with a black frame
        d.setColor(Color.black);
        d.drawText(d.getWidth() / 2 - (FONT_SIZE / 2), d.getHeight() / 2 + (FONT_SIZE / 2),
                String.valueOf(this.countFrom), FONT_SIZE);
        d.setColor(Color.ORANGE);
        d.drawText(d.getWidth() / 2 - (FONT_SIZE / 2) + 4,
                d.getHeight() / 2 + (FONT_SIZE / 2) - 4,
                String.valueOf(this.countFrom), FONT_SIZE - 16);
        // decrease the countdown number
        this.countFrom -= 1;
        long usedTime = System.currentTimeMillis() - startTime;
        // waits the time requested without the time spent on running the code
        long milliSecondLeftToSleep = milliSeconds - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }
    }
    @Override
    public boolean shouldStop() {
        return countFrom < 0;
    }
}