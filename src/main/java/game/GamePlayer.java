package game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GamePlayer {

    Rectangle bottom, left, right, top;
    Sprite sprite;
    Texture texture;
    int action;
    float velocityY;

    public GamePlayer(String string) {
        bottom = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        texture = new Texture(Gdx.files.internal(string));
        sprite = new Sprite(texture, 0, 0, 128, 128);
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
        velocityY -= (50 * delta);
        bottom.y += velocityY;
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void setPosition(float x, float y) {
        bottom.x = x;
        bottom.y = y;
        sprite.setPosition(x, y);
    }

    public void moveLeft(float delta) {
        bottom.x -= (500 * delta);	// delta == "change of time" "for fluid change om motion"
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void moveRight(float delta) {
        bottom.x += (500 * delta);	// (value) * delta, endre value for å endre movementspeed
        sprite.setPosition(bottom.x, bottom.y);
    }

    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    public void jump() {
        if (velocityY == 0) // fjerner dobbelhopping
            velocityY = 20; // hvor høyt spilleren kan hoppe
    }

}

