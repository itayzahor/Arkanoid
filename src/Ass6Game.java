// Itay Zahor 208127480

import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * The game of assignment number 6.
 *
 * @author Itay Zahor
 */
public class Ass6Game {
    private static final int GUI_WIDTH = 800;
    private static final int GUI_HEIGHT = 600;

    /**
     * changes from strings to array
     * change the arguments to an array of numbers.
     *
     * @param args the arguments
     * @return the array of numbers
     */
    public static int[] stringsToArray(String[] args) {
        // the last number is the target
        int[] nums = new int[args.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(args[i]);
        }
        return nums;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        // creates the game flow
        GUI gui = new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT);
        AnimationRunner ar = new AnimationRunner(gui);
        KeyboardSensor ks = gui.getKeyboardSensor();
        GameFlow game = new GameFlow(ar, ks, gui);
        List<LevelInformation> levels = new ArrayList<>();
        // puts the arguments in an array
        int[] nums = stringsToArray(args);
        // if the array is empty runs the levels in order
        if (nums.length == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            game.runLevels(levels);
        } else {
            // adds the level numbers to the list of levels
            for (int num : nums) {
                if (num == 1) {
                    levels.add(new Level1());
                } else if (num == 2) {
                    levels.add(new Level2());
                } else if (num == 3) {
                    levels.add(new Level3());
                }
            }
            // runs only if the levels entered are between 1-3
            if (levels.size() > 0) {
                game.runLevels(levels);
            }
        }
    }
}
