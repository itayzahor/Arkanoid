// Itay Zahor 208127480

/**
 * The BlockRemover is in charge of removing blocks from the game, as well as
 * keeping count of the number of blocks that remain.
 *
 * @author Itay Zahor
 */

public class BlockRemover implements HitListener {
    private final GameLevel gameLevel;
    private final Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param gameLevel            the game
     * @param remainingBlocks the removed blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.gameLevel);
    }
}