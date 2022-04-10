package GameTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import Sprites.Player;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;


import game.WorldContact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/**
 * Simulerer 50 updates av Player i World med bevegelse
 * @author samit
 *
 */
public class PlayerTest {
	Player g;
	public World world;
	
	@BeforeEach
	void setup() {
        world = new World(new Vector2(0, -5), true);
        world.setContactListener(new WorldContact());
        g = new Player(world);
	}

	@Test
	void movesRight() {
		float oldX = g.getX();
        for(int i = 50; i > 0; i--) {
        	world.step(1/60f, 6, 2);
        	g.b2body.applyLinearImpulse(new Vector2(0.03f, 0), g.b2body.getWorldCenter(), true);
        	g.update(0);
        }
//        System.out.println(g.getX()); // Sjekk hvor langt spilleren har bevegd seg i løpet av 50 updates
		assertTrue(g.getX()>oldX);
	}
	
	@Test
	void movesLeft() {
		float oldX = g.getX();
		for(int i = 50; i > 0; i --) {
			world.step(1/60f,  6,  2);
        	g.b2body.applyLinearImpulse(new Vector2(-0.03f, 0), g.b2body.getWorldCenter(), true);
        	g.update(0);
		}
		assertTrue(g.getX()<oldX);
	}
	
	@Test
	void jumpsCauseElevation() {
		float oldY = g.getY();
		for(int i = 50; i > 0; i--) {
			world.step(1/60f,  6,  2);
        	g.b2body.applyLinearImpulse(new Vector2(0, 1.5f), g.b2body.getWorldCenter(), true);
        	g.update(0);
		}
		assertTrue(g.getY() > oldY);
	}
	
	@Test
	void fallsOnUpdate() {
		float oldY = g.getY();
		for(int i = 50; i > 0; i--) {
			world.step(1/60f,  6,  2);
        	g.update(0);
		}
		assertTrue(g.getY() < oldY);
	}

	@Test
	void playerDies(){
		g.hit();
		assertTrue(g.isDead);
	}

// !! Disse må oppdateres !!
//
//	@Test
//	void landsOnGround() {
//		g.setPosition(0, 10);
//		g.jump();
//		g.update(0.04f);
//		assertTrue(g.hitbox.getY() > 10);
//		for(int i = 50; i > 0; i--) {
//			g.update(0.04f);
//		}
//		assertTrue(g.hitbox.getY() == 10);
//	}
//	@Test
//	void crashesIntoWalls() {
//		g.setPosition(10, 70);
//		for(int i = 50; i > 0; i--) {
//			g.moveRight(0.04f);
//			g.update(0.04f);
//		}
//		assertTrue(g.hitbox.getX() == 90f);
//	}

}

