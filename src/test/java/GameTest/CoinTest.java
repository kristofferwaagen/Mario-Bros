package GameTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import Sprites.BlockObjects.Coin;
import game.WorldContact;

public class CoinTest {
	World world;
	Coin coin;
	Rectangle r;
	
	@BeforeEach
	void setup() {
		r = new Rectangle(1f,1f,1f,1f);
        world = new World(new Vector2(0, -5), true);
        world.setContactListener(new WorldContact());
        coin = new Coin(world, r);
	}
	
	@Test
	void totalCoinsDropsOnTouch() {
		int coins = coin.getTotalCoins();
		coin.onTouch();
		assertTrue(coin.getTotalCoins() < coins);
	}
	
	@Test
	void coinEmpty() {
		for(int i = coin.getTotalCoins(); i > -1; i--) {
			assertFalse(coin.removed);
			coin.onTouch();
		}
		assertTrue(coin.getTotalCoins() == 0);
	}
	
	@Test
	void coinNotSurpassingEmpty() {
		for(int i = coin.getTotalCoins() + 10; i > -1; i--) {
			assertFalse(coin.removed);
			coin.onTouch();
		}
		assertTrue(coin.getTotalCoins() == 0);
	}

}
