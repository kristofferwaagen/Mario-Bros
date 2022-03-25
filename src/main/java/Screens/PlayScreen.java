package Screens;

import Scene.Hud;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import game.Collision;
import game.GamePlayer;
import game.Mario;

import game.GameEnemy;

import com.badlogic.gdx.math.Vector3;


public class PlayScreen implements Screen {
    private Mario game;
    private OrthographicCamera camera;
    private Viewport gamePort;
    private GamePlayer player1, player2;
    private GameEnemy enemy;
    private Sprite player1Sprite, player2Sprite;
    private Sprite enemySprite1;
    private SpriteBatch batch;
    private Rectangle startButtonRect;
    private Hud hud;
    private TmxMapLoader mapLoader; // funksjonalitet som laster inn spillebrettet
    private TiledMap map; // referanse til selve spillebrettet
    private OrthogonalTiledMapRenderer renderer; // funksjonalitet som viser spillebrettet
    private TiledMapTileLayer floor;
    private Collision collision;
    private int gameState = 1; //1 == mainMenu, 2 == mainGame, 3 == nextLevel, 4 == gameOver  
    private Sprite startButton;
    private Texture startText;

    public PlayScreen(Mario game){
        this.game = game;

        batch = game.batch;
        
        startText = new Texture(Gdx.files.internal("src/resources/StartButton.png")); // henter startButton.png
        startButton = new Sprite(startText, 0, 0, 96, 32); 
        startButton.setPosition(150, 100);
        startButtonRect = new Rectangle(150, 100, 96, 32); // setter inn posisjonen til startButton sin Rectangle
        
        
        camera = new OrthographicCamera(); // kamera som skal følge spiller gjennom spillebrettet
        gamePort = new FitViewport(Mario.visionWidth, Mario.visionHeight, camera); // skalerer responsivt med vinduets størrelse, henter resolution størrelse fra Mario.java
        hud = new Hud(game.batch); // Hud som skal vise poeng/tid/info

        mapLoader = new TmxMapLoader(); // laster inn spillebrettet
        map = mapLoader.load("src/resources/1.tmx"); // henter ut hvilket spillebrett som skal brukes
        renderer = new OrthogonalTiledMapRenderer(map); // viser spillebrettet
        
        floor = (TiledMapTileLayer) map.getLayers().get("graphics");

        /*
        * når et kamera blir laget slik som gamePort er blitt, begynner det på posisjonen x:0 y:0
        * dette ønskes ikke ettersom spillebrettet er da lokalisert i kun positive verdier for x og y
        * bruker da halvparten av bredde og høyde for å "sentrere" spillebrettet på x- og y-aksen
        * */
        camera.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
        
        player1Sprite = createSprite("src/resources/Steffen16Transp.png");
        player2Sprite = createSprite("src/resources/Elias16Transp.png");
        
        collision = new Collision(player1Sprite.getHeight(), player1Sprite.getWidth(), floor);

        player1 = new GamePlayer(player1Sprite.getHeight(), player1Sprite.getWidth(), collision); // spiller 1
        player1.setPosition(20, 16);

        player2 = new GamePlayer(player2Sprite.getHeight(), player2Sprite.getWidth(), collision); // spiller 2
        player2.setPosition(50, 16); //p2

        enemySprite1 = createSprite("src/resources/Mario_and_Enemies3.png");
        Collision collisionE = new Collision(enemySprite1.getHeight(), enemySprite1.getWidth(), floor);
        enemy = new GameEnemy(enemySprite1.getHeight(), enemySprite1.getWidth(), collisionE);
        enemy.setPosition(80, 16);

    }
    
    private Sprite createSprite(String string) {
    	Texture texture = new Texture(Gdx.files.internal(string));
    	return new Sprite(texture, 0, 0, 16, 16);
    }
    
    @Override
    public void show() {

    }

    public void handleInput(float dt) { // sjekker input
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            player2.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player2.moveLeft(dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player2.moveRight(dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            player1.jump();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            player1.moveLeft(dt);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            player1.moveRight(dt);
        }
        if(Gdx.input.isTouched()) {
        	Vector3 touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        	camera.unproject(touchPos);
        	Rectangle touch = new Rectangle(touchPos.x, touchPos.y, 0, 0);
        	if(touch.overlaps(startButtonRect))
        		gameState = 2;
        }
    }
    
    public void update(float dt){ // oppdaterer enheter
        handleInput(dt);
        camera.update(); // må oppdatere kamera hver gang det flytter på seg
        renderer.setView(camera); // metoden som viser spillebrettet trenger å vite hva den skal oppdatere av spillebrettet
        
        player1Sprite.setPosition(player1.hitbox.x, player1.hitbox.y);
        player2Sprite.setPosition(player2.hitbox.x, player2.hitbox.y);
        enemySprite1.setPosition(enemy.hitbox.x, enemy.hitbox.y);

    }


    /**
     * oppdaterer fiendes posisjon og kamera
     * @param dt
     */
    public void updateEnemy(float dt, GameEnemy enemy){
        enemy.basicEnemyMovement(dt,player1, player2, enemy);
        camera.update();
        renderer.setView(camera);
    }

    @Override
    public void render(float v) {
    	switch(this.gameState) {
    	case 1:
    		this.mainMenu(v); //hoved meny
    		break;
    	case 2:
    		this.mainGame(v); // når spillet pågår
    		break;
    	}
    }
    
    public void mainMenu(float v) {
    	update(v);
    	Gdx.gl.glClearColor(1, 1, 1, 1); // setter farge og alfa
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // tømmer skjermen
        renderer.render();
        
        batch.begin();
        startButton.draw(batch);
        batch.end();
        
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
    }
    
    
    public void mainGame(float v) {
        update(v); // kaller på update metoden

        Gdx.gl.glClearColor(1, 1, 1, 1); // setter farge og alfa
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // tømmer skjermen

        renderer.render(); // kaller på at spillebrettet skal vises

        batch.begin(); // starter batch
        player1Sprite.draw(batch); // tegner spiller1
        player2Sprite.draw(batch); // tegner spiller2

        /*
        * henter ut nåverende posisjon for spillere
        * */
        player1.setPosition(player1.hitbox.getX(), player1.hitbox.getY());
        player2.setPosition(player2.hitbox.getX(), player2.hitbox.getY());

        //legg til fiende
        enemySprite1.draw(batch);
        enemy.setPosition(enemy.hitbox.getX(), enemy.hitbox.getY());


        // oppdaterer spillere og fiender
        player1.update(v);
        player2.update(v);
        updateEnemy(v, enemy);
        enemy.update(v);
        batch.end(); // avslutter batch

        game.batch.setProjectionMatrix(hud.stage.getCamera().combined); // bruker kamera definert i Hud.java for hva spilleren kan se i spillet
        hud.stage.draw(); // viser Hud til spillet
    }
    
    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height); // viewPort blir oppdatert når vinduet blir justert
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
