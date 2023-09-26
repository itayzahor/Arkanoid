// Itay Zahor 208127480

/**
 * The interface Hit notifier keeps track of the list of hit listeners.
 *
 * @author Itay Zahor
 */
public interface HitNotifier {
    /**
     * Add hit listener to the hit events.
     *
     * @param hl the hit listener
     */

    void addHitListener(HitListener hl);

    /**
     * Remove a hit listener from the hit events.
     *
     * @param hl the hit listener
     */
    void removeHitListener(HitListener hl);
}
