package Screens;

import Scene.Hud;
import Sprites.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Input;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import game.*;

import static Scene.AudioMusic.gameMusic;
import static Scene.AudioMusic.gameOverMusic;
import static game.Mario.music;


public class PlayScreen implements Screen {
    //private String mapLocation = "src/resources/test.tmx"; // used to test graphical features
    public  Mario game;
    private final OrthographicCamera camera;
    private final Viewport gamePort;

    private final Hud hud;
    private final TiledMap map; // referanse til selve spillebrettet
    private final OrthogonalTiledMapRenderer renderer; // funksjonalitet som viser spillebrettet
    private final TmxMapLoader mapLoader;
    private final String mapLocation;
    public static Player player1, player2;
    private final SpriteBatch batch;
    private final float gWidth;
    private final float gHeight;
    private float pos;
    public static int gameState = 2; //1 == mainMenu, 2 == mainGame, 3 == nextLevel, 4 == gameOver

    private final World world;
    private final Box2DDebugRenderer b2dr;

    public static Boolean singlePlayer;
    WorldGenerator worldG;
    public static int points;


    public PlayScreen(Mario game, Boolean singlePlayer, int level){
        this.singlePlayer = singlePlayer;
        this.game = game;
        batch = game.batch;
        // kamera
        camera = new OrthographicCamera();
        gamePort = new FitViewport(Mario.visionWidth / Mario.PPM, Mario.visionHeight / Mario.PPM, camera); // skalerer responsivt med vinduets størrelse, henter resolution størrelse fra Mario.java
        hud = new Hud(game.batch); // Hud som skal vise poeng/tid/info

        gWidth = gamePort.getWorldWidth() / 2;
        gHeight = gamePort.getWorldHeight() / 2;

        // kart
        mapLoader = new TmxMapLoader(); // laster inn spillebrettet
        mapLocation = "src/resources/levels/"+Integer.toString(level)+".tmx";
        map = mapLoader.load(mapLocation); // henter ut hvilket spillebrett som skal brukes
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Mario.PPM); // viser spillebrettet

        //posisjoner kamera på spiller
        camera.position.set(gWidth, gHeight, 0);
        world = new World(new Vector2(0, -5), true);
        b2dr = new Box2DDebugRenderer();
        worldG = new WorldGenerator(this);

        //create player / players
        player1 = new Player(this);
        if(!singlePlayer) {
            player2 = new Player(this); // spiller 2
        }
        world.setContactListener(new WorldContact());
    }

    /**
     * true dersom man har kommet til mål og da setter man nytt level
     */
    public static void isFinished(boolean b){
        if(b){
            gameState = 3;
        }
    }
    /**
     * henter nærmeste spiller til fienden for at den skal følge den.
     *
     */
    public Player getClosest(Enemy t){
        pos = t.b2body.getPosition().x;
        if (Math.abs(player1.getX() - pos) > Math.abs(player2.getX() - pos)){
            return player2;
        } else {
            return player1;
        }
    }

    /**
     * håndterer input fra klientene - sjekker om det er two eller single player
     */
    public void handleInput() { // sjekker input
        if (gameState == 2) {
            if(!singlePlayer) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                	int mode = player2.jumped();
                	if(mode == 0) {
                		return;
                	} else if (mode == 1) { // Om karakteren er på bakken
                		player2.b2body.applyLinearImpulse(new Vector2(0, 2f), player2.b2body.getWorldCenter(), true);
                	} else if (mode == 2) { // Om karakteren er i luften
                		player2.b2body.setLinearVelocity(player2.b2body.getLinearVelocity().x, 2f);
                	}
                    music.getJumpSound();

                }
                if (Gdx.input.isKeyPressed(Input.Keys.D) && player2.b2body.getLinearVelocity().x <= 2) {
                    player2.b2body.applyLinearImpulse(new Vector2(0.025f, 0), player2.b2body.getWorldCenter(), true);
                    player2.flipped = false;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.A) && player2.b2body.getLinearVelocity().x >= -2) {
                    player2.b2body.applyLinearImpulse(new Vector2(-0.025f, 0), player2.b2body.getWorldCenter(), true);
                    player2.flipped = true;
                }
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            	int mode = player1.jumped();
            	if(mode == 0) {
            		return;
            	} else if (mode == 1) {
            		player1.b2body.applyLinearImpulse(new Vector2(0, 2f), player1.b2body.getWorldCenter(), true);
            	} else if (mode == 2) {
                    player1.b2body.setLinearVelocity(player1.b2body.getLinearVelocity().x, 2f);
            	}
            	music.getJumpSound();

            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player1.b2body.getLinearVelocity().x <= 2) {
                player1.b2body.applyLinearImpulse(new Vector2(0.025f, 0), player1.b2body.getWorldCenter(), true);
                player1.flipped = false;
            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player1.b2body.getLinearVelocity().x >= -2) {
                player1.b2body.applyLinearImpulse(new Vector2(-0.025f, 0), player1.b2body.getWorldCenter(), true);
                player1.flipped = true;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                gameState = 4;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.P)){
                game.pause();
            }
            if(Gdx.input.isKeyPressed(Input.Keys.SPACE)){
                game.resume();
            }
        }
    }

    /**
     * Oppdatereringsmetode for twoplayer funksjon
     */
    public void update(float dt){ // oppdaterer enheter
        handleInput();
        if(player1.isDead && player2.isDead){
            gameState = 4;
        }
        world.step(1/60f, 6, 2);
        fallsOff();

        player1.update(dt);
        player2.update(dt);
        for(BasicEnemy e : worldG.getEnemies()){
            e.update(dt);
            if(e.getX() < player1.getX() +224/Mario.PPM){
                e.b2body.setActive(true);
            }
        }
        for(AdvancedEnemy a : worldG.getAdvancedEnemies()){
            a.update(dt);
            if(a.getX() < player1.getX()+224/Mario.PPM){
                a.b2body.setActive(true);
            }
        }
        hud.update(dt);
        //flytter kamera til spiller 2 dersom spiller 1 dør
        if(gameState == 2) {
            if(player1.isDead){
                camera.position.x = player2.getX();
            } else{
                camera.position.x = player1.getX();
            }
        }
        camera.update();
        renderer.setView(camera);
    }

    @SuppressFBWarnings("SF_SWITCH_NO_DEFAULT")
    @Override
    public void render(float v) {
        if (!singlePlayer)
            update(v);
        else
            updateSingle(v);
        renderer.render();
        switch (gameState) {
            case 1:
                if (gameOverMusic != null)
                    gameOverMusic.stop();
                this.mainMenu();
                break;
                case 2:
                    if (gameOverMusic != null)
                        gameOverMusic.stop();
                    gameMusic.play();
                    this.mainGame(v);
                    break;
                case 3:
                    if (Mario.levelCounter == 5) {
                        music.getWinnerSound();
                        this.victoryScreen();
                    } else
                        this.newLevel();
                    break;
                case 4:
                    gameMusic.stop();
                    gameOverMusic.play();
                    this.gameOver();

                default:
                    break;
            }
    }


    /**
     * setter ny skjerm til seierskjerm
     */
    private void victoryScreen() {
        game.setScreen(new VictoryScreen(game));
    }

    /**
     * setter ny skjerm til main menu
     */
    public void mainMenu() {
        gameState = 2;
        game.setScreen(new MenuScreen(game));
    }

    /**
     * metode som kjører mainGame enten singleplayer eller twoplayer
     */
    public void mainGame(float v) {
        if(!singlePlayer) {
            update(v);
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            renderer.render();
            b2dr.render(world, camera.combined);
            batch.begin();
            game.batch.setProjectionMatrix(camera.combined);
            player1.draw(game.batch);
            player2.draw(game.batch);
            for(BasicEnemy e : worldG.getEnemies()){
                e.draw(game.batch);
            }
            for(AdvancedEnemy a : worldG.getAdvancedEnemies()){
                a.draw(game.batch);
            }
            batch.end();
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw();
        }
        else {
            updateSingle(v);
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            renderer.render();
            b2dr.render(world, camera.combined);
            batch.begin();
            game.batch.setProjectionMatrix(camera.combined);
            player1.draw(game.batch);
            for(BasicEnemy e : worldG.getEnemies()){
                e.draw(game.batch);
            }
            for(AdvancedEnemy a : worldG.getAdvancedEnemies()){
                a.draw(game.batch);
            }
            batch.end();
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw();
        }
    }

    /**
     * update metode dersom man velger singleplayer
     */
    private void updateSingle(float v) {
        handleInput();
        if(player1.isDead)
            gameState = 4;
        world.step(1/60f, 6, 2);
        fallsOffSingle();
        player1.update(v);
        for(BasicEnemy e : worldG.getEnemies()){
            e.update(v);
            if(e.getX() < player1.getX() +224/Mario.PPM){
                e.b2body.setActive(true);
            }
        }
        for(AdvancedEnemy a : worldG.getAdvancedEnemies()){
            a.update(v);
            if(a.getX() < player1.getX()+224/Mario.PPM){
                a.b2body.setActive(true);
            }
        }
        hud.update(v);
        camera.position.x = player1.getX();
        camera.update();
        renderer.setView(camera);
    }

    /**
     * lager ny playscreen med neste nivå
     */
    public void newLevel(){
        Mario.levelCounter += 1;
        PlayScreen nextScreen = new PlayScreen ( game, singlePlayer, Mario.levelCounter);
        gameState = 2;
        game.setScreen(nextScreen);
    }
    /**
     * lager ny game over screen
     */
    public void gameOver() {
        gameState = 2;
        game.setScreen(new GameOverScreen(game));
    }

    public World getWorld(){
        return world;
    }

    /**
     * sjekker om spiller i singleplayer har falt utfor banen
     */
    public void fallsOffSingle(){
        if (player1.getY() < -1)
            player1.isDead = true;
    }

    /**
     * sjekker om spiller i multiplayer har falt utfor banen
     */
    public void fallsOff(){
        if (player1.getY() < -1){
            player1.isDead = true;
        }
        if (player2.getY() < -1){
            player2.isDead = true;
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void show() {

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
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
        music.dispose();
    }

    public TiledMap getMap() {
        return map;
    }
}
