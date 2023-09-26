// Itay Zahor 208127480

import biuoop.DrawSurface;
import biuoop.GUI;

import java.awt.Color;

/**
 * The type Pause screen.
 *
 * @author Itay Zahor
 */
public class PauseScreen implements Animation {
    private final boolean stop;
    private final GUI gui;

    /**
     * Instantiates a new Pause screen.
     *
     * @param gui the gui
     */
    public PauseScreen(GUI gui) {
        this.stop = false;
        this.gui = gui;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        // Define the background gradient colors
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
        // Draw the "Paused" message centered
        d.setColor(Color.WHITE);
        d.drawText(230, 300,
                "Paused", 96);

        // Draw the "Press SPACE to continue" message centered below
        d.drawText(300, 500, "Press SPACE to continue", 18);
    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
