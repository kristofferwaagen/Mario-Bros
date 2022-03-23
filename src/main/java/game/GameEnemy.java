package game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class GameEnemy implements IGameFigures {
    public Rectangle bottom, left, right, top;
    Sprite sprite;
    Texture texture;
    float velocityY;

    public GameEnemy(String string){
        bottom = new Rectangle(0.0f, 0.0f, 128.0f, 128.0f);
        texture = new Texture(Gdx.files.internal(string));
        sprite = new Sprite(texture, 0,0, 16, 16);
        velocityY = 0;
    }
    @Override
    public void update(float delta){

    }
    @Override
    public void setPosition(float x, float y) {
        bottom.x = x;
        bottom.y = y;
        sprite.setPosition(x,y);
    }

    @Override
    public void moveLeft(float delta) {
        bottom.x -= (50*delta);
        sprite.setPosition(bottom.x, bottom.y);
    }

    @Override
    public void moveRight(float delta) {
        bottom.x += (50*delta);
        sprite.setPosition(bottom.x, bottom.y);
    }

    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }

    @Override
    public void jump() {
        if (velocityY == 0){
            velocityY = 10;
        }
    }
}
