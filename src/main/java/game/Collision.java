package game;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

/**
 * Checks if tiles in the surrounding area are marked with "collide".
 *
 */
public class Collision implements ICollision {
	public float tileWidth, tileHeight, spriteHeight, spriteWidth;
	TiledMapTileLayer collisionLayer;
	
	public Collision(float spriteHeight, float spriteWidth, TiledMapTileLayer collisionLayer) {
		this.tileWidth = collisionLayer.getTileWidth(); this.tileHeight = collisionLayer.getTileHeight(); 
		this.spriteHeight = spriteHeight; this.spriteWidth = spriteWidth;
		this.collisionLayer = collisionLayer;
	}
	
	public boolean collidesDownwards(float x, float y) {
		if(isCellBlocked((x), (y))
				|| isCellBlocked((x) + spriteWidth-1, (y))){
			return true;
		}
		return false;
	}
	
	public boolean collidesUpwards(float x, float y) {
		if(isCellBlocked((x), (y) + spriteHeight)
				|| isCellBlocked((x) + spriteWidth-1, (y) + spriteHeight)){
			return true;
		}
		return false;
	}
	
	public boolean collidesLeftwards(float x, float y) {
		for(float step = 0; step < spriteHeight; step += collisionLayer.getTileHeight() / (float) 2) {
			if(isCellBlocked((x), (y) + step)){
				return true;
			}
		}
		return false;
	}
	
	public boolean collidesRightwards(float x, float y) {
		for(float step = 0; step < spriteHeight; step += collisionLayer.getTileHeight() / (float) 2) {
			if(isCellBlocked((x) + spriteWidth, (y) + step)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Sjekker om tilen på gitte koordinater har konfigurasjonen "collide".
	 * @param x x-koordinat
	 * @param y y-koordinat
	 * @return true om den har "collide"
	 */
	public boolean isCellBlocked(float x, float y){
		if(collisionLayer.getCell((int)x / collisionLayer.getTileWidth(),(int)y / collisionLayer.getTileHeight()) != null) { // sjekker om blokken karakteren står på finnes
			if(collisionLayer.getCell((int)x / collisionLayer.getTileWidth(),(int)y / collisionLayer.getTileHeight()).	// sjekker om blokken er et gulv.
					getTile().getProperties().containsKey("collide")) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public float getTileWidth() {
		return tileWidth;
	}
	
	@Override
	public float getTileHeight() {
		return tileHeight;
	}

}
