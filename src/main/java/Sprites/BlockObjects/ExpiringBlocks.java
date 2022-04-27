package Sprites.BlockObjects;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

public class ExpiringBlocks extends InteractiveObject {
    int totalTouches = 2;

    public ExpiringBlocks(World world, TiledMap map, Rectangle r){
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.exprBlockBit);

    }
    @Override
    public void onTouch() {
        if(totalTouches == 1){
            categoryFilter(Mario.removedBit);
            getTileCell().setTile(null);
            totalTouches = 0;
        }
        else if(totalTouches == 0){
            categoryFilter(Mario.removedBit);
            getTileCell().setTile(null);
        }
        else{
            totalTouches--;
        }
    }
}
