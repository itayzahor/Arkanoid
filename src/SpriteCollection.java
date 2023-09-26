// Itay Zahor 208127480

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Sprite collection.
 * contains a list of sprites.
 *
 * @author Itay Zahor
 */
public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<>();
    }

    /**
     * Add sprite to the collection.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Gets the list of sprites.
     *
     * @return the sprites list
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Notify all the sprites that the time passed.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < sprites.size(); i++) {
            sprites.get(i).timePassed();
        }
    }

    /**
     * Draw all the sprites on a given draw surface.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
