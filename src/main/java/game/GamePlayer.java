package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class GamePlayer extends GamePlayerLogic {

    Sprite sprite;
    Texture texture;
    int action;
    float tileWidth;
    float tileHeight;
    Collision collision;
    
    public GamePlayer(String string, TiledMapTileLayer collisionLayer) {
    	super();
        texture = new Texture(Gdx.files.internal(string));
        sprite = new Sprite(texture, 0, 0, 16, 16);
        tileWidth = collisionLayer.getTileWidth();
        tileHeight = collisionLayer.getTileHeight();
        collision = new Collision(sprite.getHeight(), sprite.getWidth(), collisionLayer);
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
        super.update(delta);
        sprite.setPosition(hitbox.x, hitbox.y);
        
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
            sprite.setPosition(hitbox.x, hitbox.y);
        }
        if(collisionUpwards) {
        	hitbox.y = (int) ((oldY+sprite.getHeight()/2) / tileHeight) * tileHeight;
        	if(velocityY > 0)
        		velocityY = 0;
        	velocityY -= (25 * delta);
            sprite.setPosition(hitbox.x, hitbox.y);
        }
    }
	
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        sprite.setPosition(x, y);
    }

    public void moveLeft(float delta) {
    	super.moveLeft(delta);
    	if(collision.collidesLeftwards(hitbox.x, hitbox.y)) {
    		hitbox.x = (int) (hitbox.x / tileWidth) * tileWidth + sprite.getWidth();
    	}
    	sprite.setPosition(hitbox.x, hitbox.y);
    }

    public void moveRight(float delta) {
    	super.moveRight(delta);
    	if(collision.collidesRightwards(hitbox.x, hitbox.y)) {
    		hitbox.x = (int) (hitbox.x / tileWidth) * tileWidth;
        }
    	sprite.setPosition(hitbox.x, hitbox.y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

}

