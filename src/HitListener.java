// Itay Zahor 208127480

/**
 * The interface Hit listener when a hit occurs it activates the hit event.
 *
 * @author Itay Zahor
 */
public interface HitListener {
    /**
     * Hit event between a ball and a block.
     *
     * @param beingHit the block
     * @param hitter   the ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
