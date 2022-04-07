package GameTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

import Screens.PlayScreen;
import Sprites.Player;
import com.badlogic.gdx.math.Vector2;
import game.Mario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PlayerTest {
	Player g;

	@BeforeEach
	void setup() {
        g = new Player(new PlayScreen(new Mario()));
	}

	@Test
	void movesLeftAndRight() {
		float oldX = g.getX();
		g.b2body.applyLinearImpulse(new Vector2(-1f, 0), g.b2body.getWorldCenter(), true);
		assertTrue(g.getX()<oldX);
        g.b2body.applyLinearImpulse(new Vector2(1f, 0), g.b2body.getWorldCenter(), true);
		assertTrue(g.getX()>oldX);
	}

//	@Test
//	void jumpsCauseElevation() {
//		float oldY = g.hitbox.getY();
//		g.jump();
//		g.update(0.04f); //may fail if this time is high enough for player to crash into ground again
//		assertTrue(oldY < g.hitbox.getY());
//	}
//
//	@Test
//	void fallsOnUpdate() {
//		g.setPosition(g.hitbox.x, 20);
//		float oldY = g.hitbox.getY();
//		g.setVelocityY(1);
//		for(int i = 50; i > 0; i--) { // Simulates 50 frames played in game.
//			g.update(0.04f);
//		}
//		assertTrue(g.hitbox.getY() < oldY);
//	}
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

