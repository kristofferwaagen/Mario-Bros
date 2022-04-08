package Sprites;

import Scene.Hud;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

public class Object extends InteractiveObject{
    public Object(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.objectBit);
    }

    @Override
    public void onTouch() {
        categoryFilter(Mario.removedBit);
        getTileCell().setTile(null);
        Hud.scoreAdder(300);
    }
}
