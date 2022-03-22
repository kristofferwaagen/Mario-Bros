package game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

/**
 * En klasse med GamePlayer-element, posisjon, update, men uten det grafiske.
 * Gjør det enklere å teste logikken uten å loade Gfx og sprites.
 * @author samit
 *
 */
public class GamePlayerLogic {
	
    public Rectangle hitbox;
    int action;
    float velocityY;

	public GamePlayerLogic() {
        hitbox = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        velocityY = 0;
        hitbox.x = 0; hitbox.y = 0;
    }

    public void setPosition(float x, float y) {
        hitbox.x = x;
        hitbox.y = y;
    }
    
    public void moveLeft(float delta) {
        hitbox.x -= (200 * delta);	// delta == "change of time" "for fluid change om motion"
    }

    public void moveRight(float delta) {
        hitbox.x += (200 * delta);	// (value) * delta, endre value for å endre movementspeed
    }
    
    public void jump() {
        if (velocityY == 0) // fjerner dobbelhopping
            velocityY = 7; // hvor høyt spilleren kan hoppe
    }
    
    public int hits(Rectangle r) {
        if(hitbox.overlaps(r))
            return 1;
        return -1;
    }

    public void action(int type, float x, float y) {
        if(type == 1) {
            velocityY = 0;
            setPosition(hitbox.x, y);
        }
    }
    
    public void update(float delta) {
    	hitbox.y += velocityY;
    	velocityY -= (25 * delta);
    }
}
