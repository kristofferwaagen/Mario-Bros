package game;

/**
 * This class is strictly used in tests.
 *
 */
public class CollisionUsedForTesting implements ICollision {

	float floor = 10;
	float roof = 50;
	float wall = 100;
	@Override
	public boolean collidesDownwards(float x, float y) {
		if(y < floor) {
			return true;
		}
		return false;
	}

	@Override
	public boolean collidesUpwards(float x, float y) {
		if(y > roof) {
			return true;
		}
		return false;
	}

	@Override
	public boolean collidesLeftwards(float x, float y) {
		return false;
	}

	@Override
	public boolean collidesRightwards(float x, float y) {
		if(x>wall) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isCellBlocked(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public float getTileWidth() {
		return 10;
	}

	@Override
	public float getTileHeight() {
		return 10;
	}
	
}
