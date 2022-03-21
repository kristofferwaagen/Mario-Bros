package inf112.skeleton.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.lwjgl.opengl.GL;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.headless.HeadlessApplication;
import com.badlogic.gdx.backends.headless.HeadlessApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import org.mockito.Mockito;

import game.GamePlayerPhysics;
import game.Mario;

public class GamePlayerPhysicsTest {
	
	private static GamePlayerPhysics kurt;
	private TmxMapLoader mapLoader; // funksjonalitet som laster inn spillebrettet
    private TiledMap map; // referanse til selve spillebrettet
    private OrthogonalTiledMapRenderer renderer; // funksjonalitet som viser spillebrettet
    private TiledMapTileLayer floor;
    private Mario mario;
	
	@BeforeEach
	void setUp() {
		HeadlessApplicationConfiguration conf = new HeadlessApplicationConfiguration();
		Gdx.gl20 = Mockito.mock(GL20.class);
		Gdx.gl = Gdx.gl20;
		Gdx.graphics = Mockito.mock(Graphics.class);
		Mockito.when(Gdx.graphics.getWidth()).thenReturn(1000);
        Mockito.when(Gdx.graphics.getHeight()).thenReturn(1000);
        
//		new HeadlessApplication(new Mario(), conf);
//		new HeadlessApplication(new ApplicationListener() {
//
//			@Override
//			public void create() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void resize(int width, int height) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void render() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void pause() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void resume() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void dispose() {
//				// TODO Auto-generated method stub
//				
//			}
//			
//		});
		
		mario = new Mario();
		mario.create();
		mario.render();
		
		mapLoader = new TmxMapLoader(); // laster inn spillebrettet
        map = mapLoader.load("src/resources/1.tmx"); // henter ut hvilket spillebrett som skal brukes
        renderer = new OrthogonalTiledMapRenderer(map); // viser spillebrettet
                
        floor = (TiledMapTileLayer) map.getLayers().get("graphics");
		kurt = new GamePlayerPhysics(floor);

	}

	@Test
	void isTrue() {
		assertTrue(true);
	}
	@Test
	void kurtMovesLeft() {
		kurt.setPosition(0, 0);
		float startPos = kurt.bottom.x;
		kurt.moveLeft(10f);
		float now = kurt.bottom.x;
		assertTrue(now < startPos);
	}

}
