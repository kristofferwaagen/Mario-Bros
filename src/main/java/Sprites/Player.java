package Sprites;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import game.Mario;

public class Player extends Sprite {
    private int hp;
    public Boolean isDead;
    private Boolean removed;
    public World world;
    public Body b2body;

    public Player(PlayScreen screen){
        this(screen.getWorld());
        Texture t = new Texture("src/resources/objects/Steffen16Transp.png");
        setRegion(t);
    }
    /**
     * Konstruktør som ikke er avhengig av textures og sprites, brukes for testing
     */
    public Player(World world) {
    	this.world = world;
        definePlayer();
        setBounds(0,0,16 / Mario.PPM, 16 / Mario.PPM);
        isDead = false;
        removed = false;
        hp = 1;
    }

    public void update(float dt){
        if(isDead && !removed){
            world.destroyBody(b2body);
            removed = true;
        } else if (!removed) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        }
    }

    public void hit(){
        hp--;
        if (hp < 1){
            isDead = true;
        }
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
        fdef.filter.maskBits = Mario.groundBit | Mario.coinBit | Mario.enemyBit | Mario.objectBit | Mario.enemyTop | Mario.goalBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape top = new EdgeShape();
        top.set(new Vector2(-2 / Mario.PPM, 5 / Mario.PPM), new Vector2(2 / Mario.PPM, 5 / Mario.PPM));
        fdef.shape = top;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("top");
    }
}