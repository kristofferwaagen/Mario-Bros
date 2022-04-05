package game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.*;

public class GamePlayer extends Sprite {
    public World world;
    public Body b2body;
    private Sprite playerSprite1 = new Sprite(new Texture("src/resources/Steffen16Transp.png"));

    public GamePlayer(World world){
        this.world = world;
        definePlayer();
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Mario.PPM, 32 / Mario.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Mario.PPM);

        fdef.shape = shape;
        b2body.createFixture(fdef);
    }

    public void playerRender(SpriteBatch batch){
        float x = b2body.getPosition().x * Mario.PPM;
        float y = b2body.getPosition().y * Mario.PPM;
        playerSprite1.setPosition(x, y);

        // Then we simply draw it as a normal sprite.
        playerSprite1.draw(batch);
    }

}