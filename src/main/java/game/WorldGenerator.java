package game;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;

public class WorldGenerator {
    public WorldGenerator(World world, TiledMap map){

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // Ground
        for(MapObject o : map.getLayers().get(1).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((r.getX() + r.getWidth() / 2) / Mario.PPM, (r.getY() + r.getHeight() / 2) / Mario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((r.getWidth() / 2) / Mario.PPM, (r.getHeight() / 2) / Mario.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        //Coin
        for(MapObject o : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((r.getX() + r.getWidth() / 2) / Mario.PPM, (r.getY() + r.getHeight() / 2) / Mario.PPM);

            body = world.createBody(bdef);

            shape.setAsBox((r.getWidth() / 2) / Mario.PPM, (r.getHeight() / 2) / Mario.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}
