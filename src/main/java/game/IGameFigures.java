package game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Rectangle;

/**
 * Interface for spill objekter som er bevegelige (fiender, spillere, bonusgjenstander)
 */
public interface IGameFigures {
    /**
     * Sett posisjon til objektet
     * @param x posisjon
     * @param y posisjon
     */
    void setPosition(float x, float y);

    /**
     * Flytter objektet til venstre horisontalt
     *
     */
    void moveLeft(float delta);

    /**
     * Flytter objektet til høyre horisontalt
     *
     */
    void moveRight(float delta);


    /**
     * Lar spilleren hoppe, men ikke dobbelthoppe
     */
    void jump();

    /**
     * Oppdater posisjonen til objektet
     *
     */
    void update(float delta);

    /**
     * checks for hit if the hitboxes overlaps
     */
    int hits(Rectangle r);
}
