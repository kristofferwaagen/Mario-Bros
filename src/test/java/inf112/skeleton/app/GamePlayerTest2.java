package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.GamePlayer;
import game.GamePlayerLogic;

public class GamePlayerTest2 {
	GamePlayerLogic g;
	
	@BeforeEach
	void setup() {
		g = new GamePlayerLogic();
	}
	@Test
	void movesLeftAndRight() {
		float oldX = g.hitbox.getX();
		g.moveLeft(1f);
		assertTrue(g.hitbox.getX()<oldX);
		g.moveRight(2f);
		assertTrue(g.hitbox.getX()>oldX);
	}
	@Test
	void jumpsCauseElevation() {
		float oldY = g.hitbox.getY();
		g.jump();
		g.update(1f);
		assertTrue(oldY < g.hitbox.getY());
	}
	@Test
	void fallsOnUpdate() {
		float oldY = g.hitbox.getY();
		g.update(1f); // Sets velocityY to negative
		g.update(1f); // Causes character to fall
		assertTrue(g.hitbox.getY() < oldY);
	}

}
