// Itay Zahor 208127480

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The type Game flow.
 *
 * @author Itay Zahor
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the animation runner
     * @param ks the keyboard sensor
     * @param gui the gui
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
    }

    /**
     * Run the levels of the game.
     *
     * @param levels the levels list
     */
    public void runLevels(List<LevelInformation> levels) {
        Counter score = new Counter();
        // runs throw the levels list
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.animationRunner,
                    this.keyboardSensor, this.gui, score);
            level.initialize();
            while (level.getBallCounter() > 0 && level.getBlockCounter() > 0) {
                level.run();
            }
            // if all the balls are gone ends the game
            if (level.getBallCounter() == 0) {
                Animation gameOver = new GameOverAnimation(score, this.gui);
                // shows the game over animation until space key
                this.animationRunner.run(new KeyPressStoppableAnimation(
                        this.keyboardSensor, KeyboardSensor.SPACE_KEY, gameOver));
                gui.close();
            }
            // the level was finished adds 100 points to the score
            score.increase(100);
        }
        // if the player finished all the levels runs the winner animation
        Animation youWin = new YouWinAnimation(score, this.gui);
        this.animationRunner.run(new KeyPressStoppableAnimation(
                this.keyboardSensor, KeyboardSensor.SPACE_KEY, youWin));
        gui.close();
    }
}
