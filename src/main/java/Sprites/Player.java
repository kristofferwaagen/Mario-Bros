package Sprites;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import game.Mario;
import static game.Mario.music;


public class Player extends Sprite {
    public static int hp;
    public Boolean isDead;
    private Boolean removed;
    public World world;
    public Body b2body;
    private Texture t1,t2,t3,t4;
    private Array<Texture> frames;
    private Animation animation;
    private float time;

    public Player(PlayScreen screen, String texture){
        this(screen.getWorld());
        Texture t = new Texture(texture);
        setBounds(0,0,16 / Mario.PPM, 16 / Mario.PPM);

        t1 = new Texture("src/resources/tileset/16x16/Hero/day/run1.png");
        t2 = new Texture("src/resources/tileset/16x16/Hero/day/run2.png");
        t3 = new Texture("src/resources/tileset/16x16/Hero/day/run3.png");
        t4 = new Texture("src/resources/tileset/16x16/Hero/day/run4.png");
        frames = new Array<>();
        frames.add(t1,t2,t3,t4);
        animation = new Animation(0.4f, frames);
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
        time += dt;
        if(isDead && !removed){
            world.destroyBody(b2body);
            removed = true;
        } else if (!removed) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion((Texture) animation.getKeyFrame(time, true));
        }
    }

    public void hit(){
        music.getHurtSound();
        Hud.addLife(-1);
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
        fdef.filter.maskBits = Mario.groundBit | Mario.coinBit | Mario.enemyBit | Mario.objectBit | Mario.enemyTop | Mario.goalBit | Mario.keyBit | Mario.exprBlockBit | Mario.extraLifeBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape top = new EdgeShape();
        top.set(new Vector2(-2 / Mario.PPM, 5 / Mario.PPM), new Vector2(2 / Mario.PPM, 5 / Mario.PPM));
        fdef.shape = top;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("top");
    }
}