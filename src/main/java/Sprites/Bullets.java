package Sprites;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import game.Mario;

public class Bullets extends Sprite {
    PlayScreen screen;
    World world;
    Texture t;
    public Body b2body;
    private float time;
    public static boolean toRemove, removed;

    public Bullets(PlayScreen screen, float x, float y){
        this.screen = screen;
        this.world = screen.getWorld();
        t = new Texture("src/resources/tileset/16x16/Objects/bullet.png");
        setBounds(x, y, 6/Mario.PPM, 6/Mario.PPM);
        createBullet();
        toRemove = false; removed = false;
    }
    public void update(float dt){
        time += dt;
        setRegion(t);
        setPosition(b2body.getPosition().x - getWidth()/2, b2body.getPosition().y-getHeight()/2);
        if(!removed && !toRemove)
            b2body.setLinearVelocity(3, 0);
        if(time>3 && !removed){
            world.destroyBody(b2body);
            removed = true;
        }
    }
    protected void createBullet() {
        CircleShape bullet = new CircleShape();
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();

        bdef.position.set(getX()+12/Mario.PPM,getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        bullet.setRadius(2/ Mario.PPM);
        fdef.filter.categoryBits = Mario.bulletBit;
        fdef.filter.maskBits = Mario.enemyBit | Mario.enemyTop | Mario.groundBit | Mario.coinBit | Mario.exprBlockBit;
        fdef.shape = bullet;
        fdef.restitution = 1;
        fdef.friction = 0;

        b2body.createFixture(fdef).setUserData(this);
        //b2body.setLinearVelocity(new Vector2(-2, 0));
    }
    public void destroy(){
        toRemove = true;
    }
    public void draw(Batch batch){
        if(!removed)
            super.draw(batch);
    }


}
