package game;

import com.badlogic.gdx.math.Rectangle;

abstract class AGameFigures {
    public Rectangle hitbox;
    int action;
    ICollision collision;
    float tileHeight, tileWidth;
    float spriteHeight, spriteWidth;
    float velocityY;

    public AGameFigures(){
        super();
        this.hitbox = hitbox;
        this.action = action;
        this.collision = collision;
        this.tileHeight = tileHeight;
        this.tileWidth =tileWidth;
        this.spriteHeight = spriteHeight;
        this.spriteWidth = spriteWidth;
        this.velocityY = 0;
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

    abstract void moveLeft(float delta);

    abstract void moveRight(float delta);

    public void setVelocityY(float newVelY) {
        velocityY = newVelY;
    }
    public void jump() {
        if (velocityY == 0)
            velocityY = 7;

    }
    public int hits(Rectangle r) {
        if(hitbox.overlaps(r))
            return 1;
        return -1;
    }
}
