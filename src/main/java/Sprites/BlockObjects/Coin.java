package Sprites.BlockObjects;

import Scene.Hud;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;
import static game.Mario.music;

import java.util.Random;

public class Coin extends InteractiveObject {
    int totalCoins;
    public static boolean removed;
    private boolean testing = false;

    public Coin(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.coinBit);
        Random rand = new Random();
        totalCoins = rand.nextInt(7) + 3;
    }
    /**
     * Konstruktør for testing, uten TiledMap
     */
    public Coin(World world, Rectangle r) {
    	super(world, r);
    	fixture.setUserData(this);
        categoryFilter(Mario.coinBit);
        Random rand = new Random();
        totalCoins = rand.nextInt(7) + 3;
        testing = true;
    }


    @Override
    public void onTouch() {
        if(totalCoins > 0){
        	if(!testing) {
        		music.getCoinSound();
                Hud.scoreAdder(100);
        	}
            totalCoins --;
        }

        else if (!testing){
            music.getNoCoinSound();
        }

    }
    public int getTotalCoins() {
    	return totalCoins;
    }
}
