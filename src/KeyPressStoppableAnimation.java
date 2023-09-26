// Itay Zahor 208127480


import biuoop.KeyboardSensor;
import biuoop.DrawSurface;

/**
 * The type Key press stoppable animation.
 *
 * @author Itay Zahor
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor keyboard;
    private final String key;
    private final Animation animation;
    private boolean isAlreadyPressed;
    private boolean shouldStop;

    /**
     * Instantiates a new animation that stop with a special key keyboard press.
     *
     * @param sensor    the keyboard sensor
     * @param key       the key
     * @param animation the running animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        isAlreadyPressed = true;
        this.shouldStop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        // wait until the key is not pressed
        if (!keyboard.isPressed(key) && isAlreadyPressed) {
            isAlreadyPressed = false;
        }
        // if the key is pressed now ends the animation
        if (keyboard.isPressed(key) && !isAlreadyPressed) {
            shouldStop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return (this.animation.shouldStop()) || (this.shouldStop);
    }
}

