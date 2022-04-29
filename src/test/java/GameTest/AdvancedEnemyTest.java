package GameTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import Sprites.AdvancedEnemy;
import game.WorldContact;

public class AdvancedEnemyTest {
	AdvancedEnemy e;
	World world;
	
	@BeforeEach
	void setup() {
        world = new World(new Vector2(0, -5), true);
        world.setContactListener(new WorldContact());
        e = new AdvancedEnemy(world,1,1);
	}
	
	@Test
	void movesWhenActive() {
		float oldX = e.getX();
        for(int i = 50; i > 0; i--) {
        	e.b2body.setActive(true);
        	world.step(1/60f, 6, 2);
        	e.update(0);
        }
        assertTrue(e.getX()<oldX);
	}
	
	@Test
	void getsRemovedWhenHitOnTop() {
		e.contactTop();
		e.b2body.setActive(true);
		world.step(1/60f, 6, 2);
		e.update(0);
		assertTrue(e.removed);
	}

}
