package game;

import Sprites.*;
import Sprites.BlockObjects.*;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import Screens.PlayScreen;
public class WorldGenerator {
    private Array<BasicEnemy> basicEnemies;
    private Array<AdvancedEnemy> advancedEnemies;

    public WorldGenerator(PlayScreen screen){
        World world = screen.getWorld();
        TiledMap map = screen.getMap();
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
            new Coin(world, map, r);
        }
        //Goal
        for(MapObject o : map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();
            new Goal(world, map, r);
        }
        //Key
        for(MapObject o : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();
            new Key(world, map, r);
        }
        //expiring blocks
        for(MapObject o : map.getLayers().get(7).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();
            new ExpiringBlocks(world, map, r);
        }
        //extra life
        for(MapObject o : map.getLayers().get(6).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();
            new ExtraLife(world, map, r);
        }
        //basic enemies
        basicEnemies = new Array<>();
        for(MapObject o : map.getLayers().get(8).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();
            basicEnemies.add(new BasicEnemy(screen, r.getX()/Mario.PPM, r.getY()/Mario.PPM));
        }
        //advanced enemies
        advancedEnemies = new Array<>();
        for(MapObject o : map.getLayers().get(9).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();
            advancedEnemies.add(new AdvancedEnemy(screen, r.getX()/Mario.PPM, r.getY()/Mario.PPM, PlayScreen.singlePlayer));
        }
        //Ammo
        for(MapObject o : map.getLayers().get(10).getObjects().getByType(RectangleMapObject.class)){
            Rectangle r = ((RectangleMapObject) o).getRectangle();
            new AmmoBlock(world, map, r);
        }
    }
    public Array<BasicEnemy> getEnemies(){
        Array<BasicEnemy> e =new Array<>();
        e.addAll(basicEnemies);
        return e;
    }
    public Array<AdvancedEnemy> getAdvancedEnemies() {
        Array<AdvancedEnemy> a = new Array<>();
        a.addAll(advancedEnemies);
        return a;
    }
}
