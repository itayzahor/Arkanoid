// Itay Zahor 208127480

/**
 * Counter count integer numbers supports increasing and decreasing of the
 * counter.
 *
 * @author Itay Zahor
 */
public class Counter {
    private int count;

    /**
     * Instantiates a new Counter.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * add number to current count.
     *
     * @param number the number
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number
     */
    public void decrease(int number) {
        this.count -= number;
        if (this.count < 0) {
            this.count = 0;
        }
    }

    /**
     * Gets the current value of the count.
     *
     * @return the value of the count
     */
    public int getValue() {
        return this.count;
    }
}
