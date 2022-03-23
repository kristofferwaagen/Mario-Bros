package game;

import com.badlogic.gdx.math.Rectangle;

public class GamePlayer {

	public Rectangle hitbox;
    int action;
    float tileHeight, tileWidth;
    float spriteHeight, spriteWidth;
    ICollision collision;
    float velocityY;
    /**
     * Tar for seg alt det logiske med spillerne.
     * @param spriteHeight
     * @param spriteWidth
     * @param collision
     */
    public GamePlayer(float spriteHeight, float spriteWidth, ICollision collision) {
    	hitbox = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        velocityY = 0;
        hitbox.x = 0; hitbox.y = 0;
        tileWidth = collision.getTileWidth(); tileHeight = collision.getTileHeight();
        this.spriteHeight = spriteHeight; this.spriteWidth = spriteWidth;
        this.collision = collision;
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
        hitbox.x -= (200 * delta);	// delta == "change of time" "for fluid change om motion"
    	if(collision.collidesLeftwards(hitbox.x, hitbox.y)) {
    		hitbox.x = (int) (hitbox.x / tileWidth) * tileWidth + spriteWidth;
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