package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

public class Coin extends InteractiveObject{
    public Coin(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((r.getX() + r.getWidth() / 2) / Mario.PPM, (r.getY() + r.getHeight() / 2) / Mario.PPM);

        body = world.createBody(bdef);

        shape.setAsBox((r.getWidth() / 2) / Mario.PPM, (r.getHeight() / 2) / Mario.PPM);
        fdef.shape = shape;
        body.createFixture(fdef);
    }
}
