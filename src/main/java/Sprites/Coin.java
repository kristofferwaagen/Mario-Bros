package Sprites;

import Scene.Hud;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

public class Coin extends InteractiveObject{
    int totalCoins = 5;

    public Coin(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.coinBit);
    }

    @Override
    public void onTouch() {
        if(totalCoins == 1){
            categoryFilter(Mario.removedBit);
            getTileCell().setTile(null);
            totalCoins = 0;
        }
        else if(totalCoins == 0){
            categoryFilter(Mario.removedBit);
            getTileCell().setTile(null);
        }
        else{
            totalCoins --;
        }
        Hud.scoreAdder(100);
    }
}
