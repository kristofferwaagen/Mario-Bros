package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

public class GamePlayer {

    public Rectangle bottom, left, right, top;
    Sprite sprite;
    Texture texture;
    int action;
    float velocityY;

    private TiledMapTileLayer collisionLayer;
    
    public GamePlayer(String string, TiledMapTileLayer collisionLayer) {
    	this.collisionLayer = collisionLayer;
        bottom = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        texture = new Texture(Gdx.files.internal(string));
        sprite = new Sprite(texture, 0, 0, 16, 16);
        velocityY = 0;
        this.setPosition(0, 0);
    }

    public int hits(Rectangle r) {
        if(bottom.overlaps(r))
            return 1;
        return -1;
    }

    public void action(int type, float x, float y) {
        if(type == 1) {
            velocityY = 0;
            setPosition(bottom.x, y);
        }
    }

    public void update(float delta) {
    	bottom.y += velocityY;
        sprite.setPosition(bottom.x, bottom.y);
        
        float oldX = bottom.x, oldY = bottom.y, tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
        boolean collisionDown = false;
        boolean collisionUpwards = false;
        
        if(velocityY <= 0) {
        	collisionDown = collidesDownwards(tileWidth, tileHeight);
        }
        if(velocityY >= 0) {
        	collisionUpwards = collidesUpwards(tileWidth, tileHeight);
        }
        if(collisionDown) {
//        	bottom.y = collisionLayer.getCell((int) (bottom.x /tileWidth), (int) (bottom.y /tileHeight)).getHeight + tileHeight;
        	bottom.y = (int) (bottom.y / tileHeight) * tileHeight + sprite.getHeight();
        	velocityY = 0;
            sprite.setPosition(bottom.x, bottom.y);
        
        } else if(collisionUpwards) {
        	bottom.y = (int) (bottom.y / tileHeight) * tileHeight;
        	if(velocityY > 0)
        		velocityY = 0;
        	velocityY -= (25 * delta);
            sprite.setPosition(bottom.x, bottom.y);
        } else {
        	velocityY -= (25 * delta);
        }
    }

	private boolean collidesDownwards(float tileWidth, float tileHeight) {
//		if(collisionLayer.getCell((int) ((bottom.x /tileWidth) + 0.01), (int) (bottom.y /tileHeight)) != null) { // sjekker om blokken karakteren står på finnes
//			if(collisionLayer.getCell((int) (bottom.x /tileWidth + 0.01), (int) (bottom.y /tileHeight)).	// sjekker om blokken er et gulv.
//					getTile().getProperties().containsKey("collide")) {
//				return true;
//			}
//		}
		if(isCellBlocked((bottom.x), (bottom.y))){
			return true;
		}
//		else if(collisionLayer.getCell((int) ((bottom.x /tileWidth) + 0.99), (int) (bottom.y /tileHeight)) != null) {
//			if(collisionLayer.getCell((int) ((bottom.x /tileWidth) + 0.99), (int) (bottom.y /tileHeight))
//					.getTile().getProperties().containsKey("collide")) {
//				return true;
//			}
//		}
		if(isCellBlocked((bottom.x) + sprite.getWidth(), (bottom.y))){
			return true;
		}
		return false;
	}
	
	private boolean collidesUpwards(float tileWidth, float tileHeight) {
//		if(collisionLayer.getCell((int) ((bottom.x /tileWidth) + 0.01), (int) ((bottom.y /tileHeight) + 1)) != null) { // sjekker om blokken karakteren står på finnes
//			if(collisionLayer.getCell((int) ((bottom.x /tileWidth) + 0.01), (int) ((bottom.y /tileHeight)+1)).	// sjekker om blokken er et gulv.
//					getTile().getProperties().containsKey("collide")) {
//				return true;
//			}
//		}
		if(isCellBlocked((bottom.x), (bottom.y) + sprite.getHeight())){
			return true;
		}
//		else if(collisionLayer.getCell((int) ((bottom.x /tileWidth) + 0.99), (int) ((bottom.y  /tileHeight) + 1)) != null) {
//			if(collisionLayer.getCell((int) ((bottom.x /tileWidth) + 0.99), (int) ((bottom.y /tileHeight) + 1))
//					.getTile().getProperties().containsKey("collide")) {
//				return true;
//			}
//		}
		if(isCellBlocked((bottom.x) + sprite.getWidth(), (bottom.y) + sprite.getHeight())){
			return true;
		}
		return false;
	}
	
	private boolean collidesLeftwards(float tileWidth, float tileHeight) {
		for(float step = 0; step < sprite.getHeight(); step += collisionLayer.getTileHeight() / 2) {
			if(isCellBlocked((bottom.x), (bottom.y) + step)){
				return true;
			}
		}
		return false;
	}
	private boolean collidesRightwards(float tileWidth, float tileHeight) {
		for(float step = 0; step < sprite.getHeight(); step += collisionLayer.getTileHeight() / 2) {
			if(isCellBlocked((bottom.x) + sprite.getWidth(), (bottom.y) + step)){
				return true;
			}
		}
		return false;
	}

	private boolean isCellBlocked(float x, float y){
		if(collisionLayer.getCell((int)x / collisionLayer.getTileWidth(),(int)y / collisionLayer.getTileHeight()) != null) { // sjekker om blokken karakteren står på finnes
			if(collisionLayer.getCell((int)x / collisionLayer.getTileWidth(),(int)y / collisionLayer.getTileHeight()).	// sjekker om blokken er et gulv.
					getTile().getProperties().containsKey("collide")) {
				return true;
			}
		}
		return false;
	}
    public void setPosition(float x, float y) {
        bottom.x = x;
        bottom.y = y;
        sprite.setPosition(x, y);
    }

    public void moveLeft(float delta) {
    	float oldX = bottom.x, oldY = bottom.y, tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
        bottom.x -= (200 * delta);	// delta == "change of time" "for fluid change om motion"
    	if(collidesLeftwards(tileWidth, tileHeight)) {
    		bottom.x = (int) (bottom.x / tileWidth) * tileWidth + sprite.getWidth();
    	}
    	sprite.setPosition(bottom.x, bottom.y);
    }

    public void moveRight(float delta) {
    	float oldX = bottom.x, oldY = bottom.y, tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
        bottom.x += (200 * delta);	// (value) * delta, endre value for å endre movementspeed
    	if(collidesRightwards(tileWidth, tileHeight)) {
    		bottom.x = (int) (bottom.x / tileWidth) * tileWidth;
        }
    	sprite.setPosition(bottom.x, bottom.y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void jump() {
        if (velocityY == 0) // fjerner dobbelhopping
            velocityY = 7; // hvor høyt spilleren kan hoppe
    }

}

