package Sprites;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

public class Key extends InteractiveObject{
    public Key (World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.keyBit);
    }

    @Override
    public void onTouch() {
        PlayScreen.key = true;
        categoryFilter(Mario.removedBit);
        getTileCell().setTile(null);
        Mario.hasKey = true;
        Hud.addKey(1);
    }
}
