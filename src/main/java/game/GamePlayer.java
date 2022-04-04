package game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;

public class GamePlayer extends Sprite {
    public World world;
    public Body b2body;

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
}