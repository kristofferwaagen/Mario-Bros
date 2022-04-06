package Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import game.Mario;

public class Player extends Sprite {
    public World world;
    public Body b2body;
    private Texture t = new Texture("src/resources/Steffen16Transp.png");

    public Player(World world){
        this.world = world;
        definePlayer();
        setBounds(0,0,16 / Mario.PPM, 16 / Mario.PPM);
        setRegion(t);
    }

    public void render(float dt){
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
    }

    public void definePlayer(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(32 / Mario.PPM, 32 / Mario.PPM);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Mario.PPM);
        fdef.filter.categoryBits = Mario.bit;
        fdef.filter.maskBits = Mario.groundBit | Mario.coinBit | Mario.enemyBit | Mario.objectBit | Mario.enemyTop;

        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape top = new EdgeShape();
        top.set(new Vector2(-2 / Mario.PPM, 5 / Mario.PPM), new Vector2(2 / Mario.PPM, 5 / Mario.PPM));
        fdef.shape = top;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("top");
    }
}