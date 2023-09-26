// Itay Zahor 208127480

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The Score indicator.
 *
 * @author Itay Zahor
 */
public class ScoreIndicator implements Sprite {
    private final Rectangle scoreRectangle;
    private final Counter scoreCounter;
    private final String levelName;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreBlock the score block
     * @param c          the counter
     * @param levelName  the level name
     */
    public ScoreIndicator(Rectangle scoreBlock, Counter c, String levelName) {
        this.scoreRectangle = scoreBlock;
        this.scoreCounter = c;
        this.levelName = levelName;
    }

    @Override
    public void drawOn(DrawSurface d) {
        // the score board is white
        d.setColor(Color.white);
        d.fillRectangle((int) this.scoreRectangle.getX(),
                (int) this.scoreRectangle.getY(),
                (int) this.scoreRectangle.getWidth(),
                (int) this.scoreRectangle.getHeight());
        // writes the score on the block
        d.setColor(Color.black);
        d.drawText((int) (this.scoreRectangle.getWidth() / 4), 14,
                "Score : " + scoreCounter.getValue(), 14);
        // writes the level name
        d.drawText((int) (this.scoreRectangle.getWidth() / 4) * 3, 14,
                this.levelName, 14);
    }

    @Override
    public void timePassed() {

    }

    /**
     * adds the score indicator to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
