package Sprites;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import game.Mario;

public abstract class InteractiveObject {
    protected World world;
    protected TiledMap map;
    protected TiledMapTile tile;
    protected Rectangle r;
    protected Body body;

    protected Fixture fixture;

    public InteractiveObject(World world, TiledMap map, Rectangle r){
        this.world = world;
        this.map = map;
        this.r = r;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();

        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((r.getX() + r.getWidth() / 2) / Mario.PPM, (r.getY() + r.getHeight() / 2) / Mario.PPM);

        body = world.createBody(bdef);

        shape.setAsBox((r.getWidth() / 2) / Mario.PPM, (r.getHeight() / 2) / Mario.PPM);
        fdef.shape = shape;
        fixture = body.createFixture(fdef);
    }

    public abstract void onTouch();

    public void categoryFilter(short bit){
        Filter filter = new Filter();
        filter.categoryBits = bit;
        fixture.setFilterData(filter);
    }

    public TiledMapTileLayer.Cell getTileCell(){
     TiledMapTileLayer layer = (TiledMapTileLayer) map.getLayers().get(5);
     return layer.getCell((int) (body.getPosition().x * Mario.PPM / 16), (int) (body.getPosition().y * Mario.PPM / 16));
    }
}
