package Sprites;

import Scene.Hud;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;
import static game.Mario.music;


public class Key extends InteractiveObject{
    public Key (World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.keyBit);
    }

    @Override
    public void onTouch() {
        music.getKeySound();
        categoryFilter(Mario.removedBit);
        getTileCell().setTile(null);
        Mario.hasKey = true;
        Hud.addKey(1);
    }
}
