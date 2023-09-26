// Itay Zahor 208127480

import biuoop.DrawSurface;
import java.awt.Color;
import biuoop.GUI;

/**
 * The type Game over animation.
 *
 * @author Itay Zahor
 */
public class GameOverAnimation implements Animation {
    private final Counter score;
    private final boolean stop;
    private final GUI gui;

    /**
     * Instantiates a new Game over animation.
     *
     * @param score the score
     * @param gui   the GUI
     */
    public GameOverAnimation(Counter score, GUI gui) {
        this.score = score;
        this.stop = false;
        this.gui = gui;
    }


    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw the game over message and score centered
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());

        d.setColor(Color.RED);
        d.drawText(130, 200, "Game Over", 96);

        d.setColor(Color.WHITE);
        d.drawText(200, 350,
                "Your score is: " + this.score.getValue(), 48);

        d.drawText(300, 500, "Press SPACE to exit", 18);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
