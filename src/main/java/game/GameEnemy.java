package game;
import Sprites.GamePlayer;
import com.badlogic.gdx.math.Rectangle;

public class GameEnemy implements IGameFigures {
    ICollision collision;
    float spriteHeight, spriteWidth, tileHeight, tileWidth;
    public Rectangle hitbox;
    float velocityY;

    public GameEnemy(float spriteHeight, float spriteWidth, ICollision collision){
        hitbox = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        velocityY = 0;
        hitbox.x = 0; hitbox.y = 0;
        tileWidth = collision.getTileWidth();
        tileHeight = collision.getTileHeight();
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        this.collision = collision;
    }
    
    @Override
    public void update(float delta){
        float oldY = hitbox.y;
        hitbox.y += velocityY;
        velocityY -= (25*delta);
        boolean collisionDown = false;
        boolean collisionUpwards = false;

        if (velocityY < 0){
            collisionDown = collision.collidesDownwards(hitbox.x, hitbox.y);
        } else if(velocityY > 0){
            collisionUpwards = collision.collidesUpwards(hitbox.x, hitbox.y);
        }
        if(collisionDown){
            hitbox.y = (int) (oldY / tileHeight) * tileHeight;
            velocityY = 0;
        }
        if(collisionUpwards){
            hitbox.y = (int) ((hitbox.y + spriteHeight/2)/tileHeight)*tileHeight;
            if(velocityY > 0)
                velocityY = 0;
            velocityY -= (25*delta);
        }
    }

    @Override
    public int hits(Rectangle r) {
        if(hitbox.overlaps(r))
            return 1;
        return -1;
    }

    @Override
    public void setPosition(float x, float y) {
        hitbox.x = x;
        hitbox.y = y;
    }

    @Override
    public void moveLeft(float delta) {
        hitbox.x -= (50*delta);
        if(collision.collidesLeftwards(hitbox.x, hitbox.y)) {
            hitbox.x = (int) (hitbox.x / tileWidth) * tileWidth + spriteWidth;
        }
    }

    @Override
    public void moveRight(float delta) {
        hitbox.x += (50*delta);
        if(collision.collidesRightwards(hitbox.x, hitbox.y)){
            hitbox.x = (int) (hitbox.x / tileWidth) * tileWidth;
        }
    }

    @Override
    public void jump() {
        if (velocityY == 0){
            velocityY = 6;
        }
    }
    /**
     * får fienden til å følge etter den nærmeste spilleren
     * @param dt
     */
    public void basicEnemyMovement(float dt, GamePlayer p1, GamePlayer p2, GameEnemy enemy){
        float enemyPos = enemy.hitbox.x;
        float p1Pos = p1.b2body.getPosition().x;
        float p2Pos = p2.b2body.getPosition().x;

        if(Math.abs(p1Pos-enemyPos) < Math.abs(p2Pos-enemyPos)){
            if(p1Pos > enemy.hitbox.x){
                enemy.moveRight(dt);
            }
            else
                enemy.moveLeft(dt);
        }
        else{
            if(p2Pos > enemy.hitbox.x){
                enemy.moveRight(dt);
            }
            else
                enemy.moveLeft(dt);
        }

    }
}
