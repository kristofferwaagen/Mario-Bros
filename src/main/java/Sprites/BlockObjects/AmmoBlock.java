package Sprites.BlockObjects;

import Screens.PlayScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

public class AmmoBlock extends InteractiveObject {
    int ammoInBlock;
    public AmmoBlock(World world, TiledMap map, Rectangle r){
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.ammoBit);
        //ammoInBlock = 5;
    }
    @Override
    public void onTouch() {
        ammoInBlock = 1;
        if(ammoInBlock >0){
            ammoInBlock--;
            PlayScreen.ammo++;
        }
    }
}
