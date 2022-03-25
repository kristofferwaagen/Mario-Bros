package game;

/**
 * Interface for checking for collision
 *
 */
public interface ICollision {
	boolean collidesDownwards(float x, float y);
	boolean collidesUpwards(float x, float y);
	boolean collidesLeftwards(float x, float y);
	boolean collidesRightwards(float x, float y);
	boolean isCellBlocked(float x, float y);
	float getTileWidth();
	float getTileHeight();
}