// Itay Zahor 208127480

import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;

/**
 * The game winner animation.
 *
 * @author Itay Zahor
 */
public class YouWinAnimation implements Animation {
    private final Counter score;
    private final boolean stop;
    private final GUI gui;
    /**
     * Instantiates a new winner animation.
     *
     * @param score the ending score
     * @param gui   the GUI
     */
    public YouWinAnimation(Counter score,  GUI gui) {
        this.score = score;
        this.stop = false;
        this.gui = gui;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        Color color1 = new Color(0, 102, 204); // Dark blue
        Color color2 = new Color(135, 206, 250); // Light blue

        // Draw the gradient background
        int width = gui.getDrawSurface().getWidth();
        int height = gui.getDrawSurface().getHeight();

        for (int y = 0; y < height; y++) {
            float ratio = (float) y / height;
            int red = (int) (color1.getRed() * (1 - ratio) + color2.getRed() * ratio);
            int green = (int) (color1.getGreen() * (1 - ratio) + color2.getGreen() * ratio);
            int blue = (int) (color1.getBlue() * (1 - ratio) + color2.getBlue() * ratio);
            d.setColor(new Color(red, green, blue));
            d.drawLine(0, y, width, y);
        }
        // Draw the "You Win!" message and score centered
        d.setColor(Color.yellow);
        d.drawText(130, 200, "YOU WIN!!!", 96);

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

