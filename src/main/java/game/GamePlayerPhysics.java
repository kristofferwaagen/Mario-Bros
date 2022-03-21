package game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;

/**
 * En klasse med GamePlayer-element, posisjon, update, men uten det grafiske.
 * Gjør det enklere å teste logikken uten å loade Gfx og sprites.
 * !!!! Fungerer ikke om vi skal bruke TiledMapTileLayer, siden dette bruker grafikk,
 *  så slik den er nå er den ubrukelig :/.
 * @author samit
 *
 */
public class GamePlayerPhysics {
	
    public Rectangle bottom, left, right, top;
    int action;
    float velocityY;
    
    private TiledMapTileLayer collisionLayer;

	public GamePlayerPhysics(TiledMapTileLayer collisionLayer) {
    	this.collisionLayer = collisionLayer;
        bottom = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        velocityY = 0;
        this.setPosition(0, 0);
    }

    public void setPosition(float x, float y) {
        bottom.x = x;
        bottom.y = y;
    }
    
    public void moveLeft(float delta) {
    	float oldX = bottom.x, oldY = bottom.y, tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
        bottom.x -= (200 * delta);	// delta == "change of time" "for fluid change om motion"
    	if(collidesLeftwards(tileWidth, tileHeight)) {
    		bottom.x = (int) (bottom.x / tileWidth) * tileWidth + tileWidth;
    	}
    }

    public void moveRight(float delta) {
    	float oldX = bottom.x, oldY = bottom.y, tileWidth = collisionLayer.getTileWidth(), tileHeight = collisionLayer.getTileHeight();
        bottom.x += (200 * delta);	// (value) * delta, endre value for å endre movementspeed
    	if(collidesRightwards(tileWidth, tileHeight)) {
    		bottom.x = (int) (bottom.x / tileWidth) * tileWidth;
        }
    }
    
    public void jump() {
        if (velocityY == 0) // fjerner dobbelhopping
            velocityY = 7; // hvor høyt spilleren kan hoppe
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
        	bottom.y = (int) (bottom.y / tileHeight) * tileHeight + tileHeight; //sprite.getHeight();
        	velocityY = 0;
        
        } else if(collisionUpwards) {
        	bottom.y = (int) (bottom.y / tileHeight) * tileHeight;
        	if(velocityY > 0)
        		velocityY = 0;
        	velocityY -= (25 * delta);
        } else {
        	velocityY -= (25 * delta);
        }
    }
	private boolean collidesDownwards(float tileWidth, float tileHeight) {

		if(isCellBlocked((bottom.x), (bottom.y))){
			return true;
		}

		if(isCellBlocked((bottom.x) + tileWidth, (bottom.y))){
			return true;
		}
		return false;
	}
	
	private boolean collidesUpwards(float tileWidth, float tileHeight) {

		if(isCellBlocked((bottom.x), (bottom.y) + tileHeight)){
			return true;
		}
		
		if(isCellBlocked((bottom.x) + tileHeight, (bottom.y) + tileHeight)){
			return true;
		}
		return false;
	}
	
	private boolean collidesLeftwards(float tileWidth, float tileHeight) {
		for(float step = 0; step < tileHeight; step += collisionLayer.getTileHeight() / 2) {
			if(isCellBlocked((bottom.x), (bottom.y) + step)){
				return true;
			}
		}
		return false;
	}
	private boolean collidesRightwards(float tileWidth, float tileHeight) {
		for(float step = 0; step < tileHeight; step += collisionLayer.getTileHeight() / 2) {
			if(isCellBlocked((bottom.x) + tileWidth, (bottom.y) + step)){
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
}
