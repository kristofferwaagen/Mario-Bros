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

    public static Player player1, player2;
    private final SpriteBatch batch;

    public static int gameState = 2; //1 == mainMenu, 2 == mainGame, 3 == nextLevel, 4 == gameOver

    private final World world;
    private final Box2DDebugRenderer b2dr;

    private final BasicEnemy basicEnemy;
    private final AdvancedEnemy advancedEnemy;

    public static Boolean singlePlayer;

    public static boolean jumping, hit, gameover, coin, key;

    public PlayScreen(Mario game, Boolean singlePlayer, int level){
        this.singlePlayer = singlePlayer;
        this.game = game;
        batch = game.batch;

        // kamera
        camera = new OrthographicCamera(); // kamera som skal følge spiller gjennom spillebrettet
        gamePort = new FitViewport(Mario.visionWidth / Mario.PPM, Mario.visionHeight / Mario.PPM, camera); // skalerer responsivt med vinduets størrelse, henter resolution størrelse fra Mario.java
        hud = new Hud(game.batch); // Hud som skal vise poeng/tid/info

        float gWidth = gamePort.getWorldWidth() / 2;
        float gHeight = gamePort.getWorldHeight() / 2;

        // kart
        TmxMapLoader mapLoader = new TmxMapLoader(); // laster inn spillebrettet
        String mapLocation = "src/resources/levels/"+Integer.toString(level)+".tmx";
        map = mapLoader.load(mapLocation); // henter ut hvilket spillebrett som skal brukes
        renderer = new OrthogonalTiledMapRenderer(map, 1 / Mario.PPM); // viser spillebrettet

        /*
         * når et kamera blir laget slik som gamePort er blitt, begynner det på posisjonen x:0 y:0
         * dette ønskes ikke ettersom spillebrettet er da lokalisert i kun positive verdier for x og y
         * bruker da halvparten av bredde og høyde for å "sentrere" spillebrettet på x- og y-aksen
         * */
        camera.position.set(gWidth, gHeight, 0);

        world = new World(new Vector2(0, -5), true);
        b2dr = new Box2DDebugRenderer();
        new WorldGenerator(world, map);

        //create player / players
        player1 = new Player(this,"src/resources/objects/Steffen16Transp.png" );
        if(!singlePlayer) {
            player2 = new Player(this,"src/resources/objects/Elias16Transp.png"); // spiller 2
        }
        //create enemies
        basicEnemy = new BasicEnemy(this, 1447 / Mario.PPM, 32 / Mario.PPM);
        advancedEnemy = new AdvancedEnemy(this, 1680 / Mario.PPM, 32 / Mario.PPM, singlePlayer);
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
        float pos = t.b2body.getPosition().x;
        if (Math.abs(player1.getX() - pos) > Math.abs(player2.getX() - pos)){
            return player2;
        } else {
            return player1;
        }
    }

    private Sprite createSprite(String string) {
        Texture texture = new Texture(Gdx.files.internal(string));
        return new Sprite(texture, 0, 0, 16, 16);
    }

    /**
     * håndterer input fra klientene - sjekker om det er two eller single player
     */
    public void handleInput() { // sjekker input
        if (gameState == 2) {
            if(!singlePlayer) {
                if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
                    player2.b2body.applyLinearImpulse(new Vector2(0, 1.5f), player2.b2body.getWorldCenter(), true);
                    jumping = true;
                }
                if (Gdx.input.isKeyPressed(Input.Keys.D) && player2.b2body.getLinearVelocity().x <= 2) {
                    player2.b2body.applyLinearImpulse(new Vector2(0.03f, 0), player2.b2body.getWorldCenter(), true);

                }
                if (Gdx.input.isKeyPressed(Input.Keys.A) && player2.b2body.getLinearVelocity().x >= -2) {
                    player2.b2body.applyLinearImpulse(new Vector2(-0.03f, 0), player2.b2body.getWorldCenter(), true);
                }
            }

            if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
                player1.b2body.applyLinearImpulse(new Vector2(0, 1.5f), player1.b2body.getWorldCenter(), true);
                jumping = true;

            }
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player1.b2body.getLinearVelocity().x <= 2) {
                player1.b2body.applyLinearImpulse(new Vector2(0.03f, 0), player1.b2body.getWorldCenter(), true);

            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && player1.b2body.getLinearVelocity().x >= -2) {
                player1.b2body.applyLinearImpulse(new Vector2(-0.03f, 0), player1.b2body.getWorldCenter(), true);

            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                gameState = 4;
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

        basicEnemy.update(dt);
        advancedEnemy.update(dt);
        if(basicEnemy.getX() < player1.getX() + 224 / Mario.PPM){
            basicEnemy.b2body.setActive(true);
            advancedEnemy.b2body.setActive(true);
        }

        hud.update(dt);

        if(gameState == 2) {
            if(player1.isDead){
                camera.position.x = player2.getX();
            } else{
                camera.position.x = player1.getX();
            }
        }

        camera.update(); // må oppdatere kamera hver gang det flytter på seg
        renderer.setView(camera); // metoden som viser spillebrettet trenger å vite hva den skal oppdatere av spillebrettet
    }

    @SuppressFBWarnings("SF_SWITCH_NO_DEFAULT")
    @Override
    public void render(float v) {
        if(!singlePlayer)
            update(v);
        else
            updateSingle(v);

        Gdx.gl.glClearColor(1, 1, 1, 1); // setter farge og alfa
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // tømmer skjermen
        renderer.render();

        switch(gameState) {
            case 1:
                if(gameOverMusic != null)
                    gameOverMusic.stop();
                this.mainMenu(); //hoved meny
                break;
            case 2:
                if(gameOverMusic != null)
                    gameOverMusic.stop();
                gameMusic.play();
                this.mainGame(v); // når spillet pågår
                break;
            case 3:
                if(Mario.levelCounter == 4){
                    this.victoryScreen();
                }
                else
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

    private void chooselvl() {
        gameState = 2;
        new ChooseLevel(game);
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
            update(v); // kaller på update metoden
            Gdx.gl.glClearColor(1, 1, 1, 1); // setter farge og alfa
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // tømmer skjermen
            renderer.render(); // kaller på at spillebrettet skal vises
            b2dr.render(world, camera.combined);
            batch.begin();
            game.batch.setProjectionMatrix(camera.combined);
            player1.draw(game.batch);
            player2.draw(game.batch);
            batch.end(); // avslutter batch
            game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
            hud.stage.draw(); // viser Hud til spillet
        }
        else {
            updateSingle(v);
            Gdx.gl.glClearColor(1, 1, 1, 1); // setter farge og alfa
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // tømmer skjermen
            renderer.render();
            b2dr.render(world, camera.combined);
            batch.begin();
            game.batch.setProjectionMatrix(camera.combined);
            player1.draw(game.batch);
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

        //change music/sound
        if(jumping){
            music.getJumpSound();
        }
        if(hit){
            music.getHitSound();
        }
        if(coin){
            music.getCoinSound();
        }
        if(key){
            music.getKeySound();
        }

        if(player1.isDead)
            gameState = 4;

        world.step(1/60f, 6, 2);

        fallsOffSingle();
        player1.update(v);
        basicEnemy.update(v);
        advancedEnemy.update(v);

        if(basicEnemy.getX() < player1.getX() + 224 / Mario.PPM){
            basicEnemy.b2body.setActive(true);
            advancedEnemy.b2body.setActive(true);
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
        gamePort.update(width, height); // viewPort blir oppdatert når vinduet blir justert
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
}
