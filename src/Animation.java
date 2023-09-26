// Itay Zahor 208127480

import biuoop.DrawSurface;

/**
 * The interface Animation.
 *
 * @author Itay Zahor
 */
public interface Animation {
    /**
     * Do one frame of the animation.
     *
     * @param d the draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop or continue.
     *
     * @return boolean
     */
    boolean shouldStop();
}