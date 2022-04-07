package Sprites;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import game.Mario;

public class BasicEnemy extends Enemy{

    private Boolean toRemove;
    private Boolean removed;

    public BasicEnemy(PlayScreen screen, float x, float y) {
        super(screen, x, y);
        toRemove = false;
        removed = false;
    }

    public void update(float dt){
        if(toRemove && !removed){
            world.destroyBody(b2body);
            removed = true;
        }
        else if (!removed) {
            b2body.setLinearVelocity(speed);
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
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
        fdef.filter.maskBits = Mario.groundBit | Mario.coinBit | Mario.bit | Mario.objectBit | Mario.enemyBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape top = new PolygonShape();
        Vector2[] edge = new Vector2[4];
        edge[0] = new Vector2(-3, 5).scl(1 / Mario.PPM);
        edge[1] = new Vector2(3, 5).scl(1 / Mario.PPM);
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
        Hud.scoreAdder(100);
    }
}
