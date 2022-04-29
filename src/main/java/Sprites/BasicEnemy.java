package Sprites;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.utils.Array;
import game.Mario;

import static game.Mario.music;

public class BasicEnemy extends Enemy{
    private float time;
    private Boolean toRemove;
    private Boolean removed;
    private Animation animation;
    private Array<Texture> frames;
    private Texture t1, t2;

    public BasicEnemy(PlayScreen screen, float x, float y) {

        super(screen, x, y);
        toRemove = false;
        removed = false;

        setBounds(getX(),getY(),16 / Mario.PPM, 16 / Mario.PPM);

        t1 = new Texture(Gdx.files.internal("tileset/16x16/Enemies/snail1.png"));
        t2 = new Texture(Gdx.files.internal("tileset/16x16/Enemies/snail2.png"));


        frames = new Array<>();
        frames.add(t1, t2);

        animation = new Animation(0.4f, frames);
    }

    public void update(float dt){
        time += dt;
        if(toRemove && !removed){
            world.destroyBody(b2body);
            removed = true;
        }
        else if (!removed) {
            b2body.setLinearVelocity(speed);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            setRegion((Texture) animation.getKeyFrame(time, true));
            if(speed.x < 0 && (!this.isFlipX()))
            	this.setFlip(true, isFlipY());

        }
    }

    @Override
    protected void defineEnemy() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Mario.PPM);
        fdef.filter.categoryBits = Mario.enemyBit;
        fdef.filter.maskBits = Mario.groundBit | Mario.coinBit | Mario.bit | Mario.objectBit | Mario.enemyBit| Mario.bulletBit | Mario.exprBlockBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape top = new PolygonShape();
        Vector2[] edge = new Vector2[4];
        edge[0] = new Vector2(-3, 7).scl(1 / Mario.PPM);
        edge[1] = new Vector2(3, 7).scl(1 / Mario.PPM);
        edge[2] = new Vector2(-2, 2).scl(1 / Mario.PPM);
        edge[3] = new Vector2(2, 2).scl(1 / Mario.PPM);
        top.set(edge);

        fdef.shape = top;
        fdef.restitution = 0.1f;
        fdef.filter.categoryBits = Mario.enemyTop;
        b2body.createFixture(fdef).setUserData(this);

    }

    @Override
    public void contactTop() {
        toRemove = true;
        music.getHitSound();
        Hud.scoreAdder(100);
    }
    public void draw(Batch batch){
        if(!removed)
            super.draw(batch);
    }
}
