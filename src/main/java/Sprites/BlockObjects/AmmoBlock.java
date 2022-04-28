package Sprites.BlockObjects;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

import static game.Mario.music;

public class AmmoBlock extends InteractiveObject {
    int ammoInBlock;
    public AmmoBlock(World world, TiledMap map, Rectangle r){
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.ammoBit);
        ammoInBlock = 10;
    }
    @Override
    public void onTouch() {
        if(ammoInBlock >0){
            Hud.addShot(1);
            music.getAmmoSound();
            ammoInBlock--;
        }
        else{
            categoryFilter(Mario.removedBit);
            getTileCell().setTile(null);
        }
    }
}
