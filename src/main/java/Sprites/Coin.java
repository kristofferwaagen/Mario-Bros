package Sprites;

import Scene.Hud;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;
import static game.Mario.music;

import java.util.Random;

public class Coin extends InteractiveObject{
    int totalCoins;
    public static boolean toRemove, removed;

    public Coin(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.coinBit);
        Random rand = new Random();
        totalCoins = rand.nextInt(7) + 3;
    }


    @Override
    public void onTouch() {
        music.getCoinSound();

        if(toRemove){
            categoryFilter(Mario.removedBit);
            getTileCell().setTile(null);
            toRemove = false;
            removed = true;
        }
        else {
            if (totalCoins == 1) {
                toRemove = true;
                totalCoins = 0;
            } else {
                totalCoins--;
            }
            Hud.scoreAdder(100);
        }
    }
}
