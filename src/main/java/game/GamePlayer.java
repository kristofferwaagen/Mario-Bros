package game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class GamePlayer {

    int action;
    float tileWidth, tileHeight;
    float spriteHeight, spriteWidth;
    Collision collision;
    public Rectangle hitbox;
    float velocityY;
    
    public GamePlayer(float spriteHeight, float spriteWidth, TiledMapTileLayer collisionLayer) {
    	hitbox = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        velocityY = 0;
        hitbox.x = 0; hitbox.y = 0;
        tileWidth = collisionLayer.getTileWidth();
        tileHeight = collisionLayer.getTileHeight();
        collision = new CollisionWithTiles(spriteHeight, spriteWidth, collisionLayer);
    }
    /**
     * Used when testing the class, avoids TiledMapTileLayer
     * @param collisionUsedForTesting
     */
    public GamePlayer(CollisionUsedForTesting collisionUsedForTesting) {
    	hitbox = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        velocityY = 0;
        hitbox.x = 0; hitbox.y = 0;
    	collision = collisionUsedForTesting;
    	tileWidth = tileHeight = spriteHeight = spriteWidth = 10;
    }

    public void action(int type, float x, float y) {
        if(type == 1) {
            velocityY = 0;
            setPosition(hitbox.x, y);
        }
    }

    /**
     * Oppdaterer posisjonen til objektet, sjekker om den har kollidert oppover eller nedover, og korrigerer så posisjonen i henhold til det.
     * @param delta
     */
    public void update(float delta) {
        float oldY = hitbox.y;
        hitbox.y += velocityY;
    	velocityY -= (25 * delta);
        
        boolean collisionDown = false;
        boolean collisionUpwards = false;
        
        if(velocityY < 0) {
        	collisionDown = collision.collidesDownwards(hitbox.x, hitbox.y);
        } else if(velocityY > 0) {
        	collisionUpwards = collision.collidesUpwards(hitbox.x, hitbox.y);
        }
        if(collisionDown) {
        	hitbox.y = (int) (oldY / tileHeight) * tileHeight;
        	velocityY = 0;
        }
        if(collisionUpwards) {
        	hitbox.y = (int) ((hitbox.y+spriteHeight/2) / tileHeight) * tileHeight;
        	if(velocityY > 0)
        		velocityY = 0;
        	velocityY -= (25 * delta);
        }
    }
	
    public void setPosition(float x, float y) {
    	hitbox.x = x;
        hitbox.y = y;
    }

    public void moveLeft(float delta) {
    	float oldX = hitbox.x;
        hitbox.x -= (200 * delta);	// delta == "change of time" "for fluid change om motion"
    	if(collision.collidesLeftwards(hitbox.x, hitbox.y)) {
    		hitbox.x = (int) (oldX / tileWidth) * tileWidth + spriteWidth;
    	}
    }

    public void moveRight(float delta) {
        hitbox.x += (200 * delta);	// (value) * delta, endre value for å endre movementspeed
    	if(collision.collidesRightwards(hitbox.x, hitbox.y)) {
    		hitbox.x = (int) (hitbox.x / tileWidth) * tileWidth;
        }
    }
    
    public void setVelocityY(float newVelY) {
    	velocityY = newVelY;
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

}

