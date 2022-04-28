package Screens;

import Scene.Hud;
import Sprites.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
    private final OrthographicCamera camera1, camera2;
    private final Viewport gamePort1, gamePort2;

    private final Hud hud;
    private final TiledMap map; // referanse til selve spillebrettet
    private final OrthogonalTiledMapRenderer renderer, renderer2; // funksjonalitet som viser spillebrettet
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
        camera1 = new OrthographicCamera();
        camera2 = new OrthographicCamera();
        gamePort1 = new FitViewport(Mario.visionWidth / Mario.PPM, Mario.visionHeight / Mario.PPM, camera1); // skalerer responsivt med vinduets størrelse, henter resolution størrelse fra Mario.java
        gamePort2 = new FitViewport(Mario.visionWidth / Mario.PPM, Mario.visionHeight / Mario.PPM, camera2);
        hud = new Hud(game.batch); // Hud som skal vise poeng/tid/info

        gWidth = gamePort1.getWorldWidth() / 2;
        gHeight = gamePort1.getWorldHeight() / 2;

        // kart
        mapLoader = new TmxMapLoader(); // laster inn spillebrettet
        mapLocation = "src/resources/levels/"+Integer.toString(level)+".tmx";
        map = mapLoader.load(mapLocation); // henter ut hvilket spillebrett som skal brukes
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Mario.PPM); // viser spillebrettet
        renderer2 = new OrthogonalTiledMapRenderer(map, 1 / Mario.PPM);

        //posisjoner kamera på spiller
        camera1.position.set(gWidth, gHeight, 0);
        camera2.position.set(gWidth, gHeight, 0);
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
     * @param delta
     */
    public void handleInput(float delta) { // sjekker input
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
    public void update(float delta){ // oppdaterer enheter
        handleInput(delta);
        if(player1.isDead && player2.isDead){
            gameState = 4;
        }
        world.step(delta, 6, 2);
        fallsOff();

        player1.update(delta);
        player2.update(delta);
        for(BasicEnemy e : worldG.getEnemies()){
            e.update(delta);
            if(e.getX() < player1.getX() +224/Mario.PPM || e.getX() < player2.getX() +224/Mario.PPM){
                e.b2body.setActive(true);
            }
        }
        for(AdvancedEnemy a : worldG.getAdvancedEnemies()){
            a.update(delta);
            if(a.getX() < player1.getX()+224/Mario.PPM || a.getX() < player2.getX()+224/Mario.PPM){
                a.b2body.setActive(true);
            }
        }
        hud.update(delta);
        //flytter kamera til spiller 2 dersom spiller 1 dør
        if(gameState == 2) {
            if(player1.isDead){
                camera1.position.x = player2.getX();
                camera2.position.x = player2.getX();
            } else if (player2.isDead){
                camera1.position.x = player1.getX();
                camera2.position.x = player1.getX();
            } else {
                camera1.position.x = player1.getX();
                camera2.position.x = player2.getX();
            }
        }
        camera1.update();
        camera2.update();
        renderer.setView(camera1);
        renderer2.setView(camera2);
    }

    @SuppressFBWarnings("SF_SWITCH_NO_DEFAULT")
    @Override
    public void render(float delta) {
        if (!singlePlayer)
            update(delta);
        else
            updateSingle(delta);
        renderer.render();
        renderer2.render();
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
                    this.mainGame(delta);
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

    public void firstView(float delta){
        game.batch.setProjectionMatrix(camera1.combined);
        batch.begin();
        renderer2.render();
        drawMultiplayer(delta);
        batch.end();
        hud.stage.draw();
    }

    public void secondView(float delta){
        game.batch.setProjectionMatrix(camera2.combined);
        batch.begin();
        renderer.render();
        drawMultiplayer(delta);
        batch.end();
        hud.stage.draw();
    }

    /**
     * metode som kjører mainGame enten singleplayer eller twoplayer
     */
    public void mainGame(float delta) {
        if(!singlePlayer) {
            update(delta);
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            gamePort1.apply();
            firstView(delta);
            gamePort2.apply();
            secondView(delta);
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        }
        else {
            updateSingle(delta);
            Gdx.gl.glClearColor(1, 1, 1, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            renderer.render();
            batch.begin();
            game.batch.setProjectionMatrix(camera1.combined);
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

    public void drawMultiplayer(float delta){
        player1.draw(game.batch);
        player2.draw(game.batch);
        for(BasicEnemy e : worldG.getEnemies()){
            e.draw(game.batch);
        }
        for(AdvancedEnemy a : worldG.getAdvancedEnemies()){
            a.draw(game.batch);
        }
    }

    /**
     * update metode dersom man velger singleplayer
     */
    private void updateSingle(float delta) {
        handleInput(delta);
        if(player1.isDead)
            gameState = 4;
        world.step(delta, 6, 2);
        fallsOffSingle();
        player1.update(delta);
        for(BasicEnemy e : worldG.getEnemies()){
            e.update(delta);
            if(e.getX() < player1.getX() +224/Mario.PPM){
                e.b2body.setActive(true);
            }
        }
        for(AdvancedEnemy a : worldG.getAdvancedEnemies()){
            a.update(delta);
            if(a.getX() < player1.getX()+224/Mario.PPM){
                a.b2body.setActive(true);
            }
        }
        hud.update(delta);
        camera1.position.x = player1.getX();
        camera1.update();
        renderer.setView(camera1);
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
        if(!singlePlayer){
            gamePort1.update(width / 2, height);
            gamePort2.update(width / 2, height);
            gamePort2.setScreenX(gamePort1.getScreenWidth());
        } else {
            gamePort1.update(width, height);
        }
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
