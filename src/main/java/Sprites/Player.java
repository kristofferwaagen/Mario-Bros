package Sprites;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import game.Mario;
import static game.Mario.music;

public class Player extends Sprite {
    public static int hp;
    public Boolean isDead;
    private Boolean removed;
    public Boolean flipped = false;
    private Boolean testing = true; // Hvis true, kaller musikk, HUD og andre klasser som ikke instansieres i tester.
    public World world;
    public Body b2body;
    public boolean canJumpOnGround, canJumpInAir;
    private Texture t1,t2,t3,t4;
    private Array<Texture> frames;
    private Animation animation;
    private float time;
    private Array<Bullets> bullets;
    private PlayScreen screen;


    public Player(PlayScreen screen){
        this.screen = screen;
        this.world = screen.getWorld();
        hp = 1;
        testing = false;
        definePlayer();
        setBounds(0,0,16 / Mario.PPM, 16 / Mario.PPM);
        bullets = new Array<Bullets>();
        //bilder til animation av spiller
        t1 = new Texture(Gdx.files.internal("tileset/16x16/Hero/day/run1.png"));
        t2 = new Texture(Gdx.files.internal("tileset/16x16/Hero/day/run2.png"));
        t3 = new Texture(Gdx.files.internal("tileset/16x16/Hero/day/run3.png"));
        t4 = new Texture(Gdx.files.internal("tileset/16x16/Hero/day/run4.png"));
        frames = new Array<>();
        frames.add(t1,t2,t3,t4);
        animation = new Animation(0.4f, frames);
        isDead= false;
        removed = false;
    }
    /**
     * Konstruktør som ikke er avhengig av textures og sprites, brukes for testing
     */
    public Player(World world) {
    	this.world = world;
        definePlayer();
        setBounds(0,0,16 / Mario.PPM, 16 / Mario.PPM);
        isDead = false;
        hp = 1;
        bullets = new Array<Bullets>();
    }

    public void update(float dt){
        time += dt;
        if(isDead && !removed){
            world.destroyBody(b2body);
            removed = true;
        }
        setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
        if(!testing)
        	setRegion((Texture) animation.getKeyFrame(time, true));

        for(Bullets b: bullets){
            b.update(dt/2);
            if(b.removed)
                bullets.removeValue(b, true);
        }
        if(flipped) {
            setFlip(true, isFlipY());
        }
    }

    public void hit(){
    	if(!testing) {
    		music.getHurtSound();
    		Hud.addLife(-1);
    	}
        hp--;
        if (hp < 1)
            isDead = true;
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
        fdef.filter.maskBits = Mario.groundBit | Mario.coinBit | Mario.bit | Mario.enemyBit | Mario.objectBit | Mario.enemyTop | Mario.goalBit | Mario.keyBit | Mario.exprBlockBit | Mario.extraLifeBit | Mario.ammoBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        EdgeShape top = new EdgeShape();
        top.set(new Vector2(-2 / Mario.PPM, 5 / Mario.PPM), new Vector2(2 / Mario.PPM, 5 / Mario.PPM));
        fdef.shape = top;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData("top");

        PolygonShape bot = new PolygonShape();
        Vector2[] edge = new Vector2[4];
        edge[0] = new Vector2(-5, -5).scl(1 / Mario.PPM);
        edge[1] = new Vector2(5, -5).scl(1 / Mario.PPM);
        edge[2] = new Vector2(-2, -2).scl(1 / Mario.PPM);
        edge[3] = new Vector2(2, -2).scl(1 / Mario.PPM);
        bot.set(edge);

        fdef.shape = bot;
        fdef.restitution = 0.1f;
        fdef.filter.categoryBits = Mario.playerBot;
        b2body.createFixture(fdef).setUserData(this);
    }
	public int jumped() {
		if(canJumpOnGround) {
			canJumpOnGround = false;
			return 1;
		}
		else if (canJumpInAir) {
			canJumpInAir = false;
			return 2;
		}
		return 0;
	}
    public void shootBullets(){
        bullets.add(new Bullets(screen, b2body.getPosition().x, b2body.getPosition().y, this));
    }
    public void draw(Batch batch){
        super.draw(batch);
        for(Bullets b : bullets)
            b.draw(batch);
    }
}