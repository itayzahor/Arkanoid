// Itay Zahor 208127480

import biuoop.DrawSurface;

/**
 * The interface Sprite.
 *
 * @author Itay Zahor
 */
public interface Sprite {
    /**
     * Draw on.
     * draws the sprite of a given draw surface.
     *
     * @param d the d
     */
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     * notifies the sprite that the time passed, and it needs to do something.
     */
    void timePassed();
}
