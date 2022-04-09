package Screens;

import Scene.Hud;
import Sprites.AdvancedEnemy;
import Sprites.BasicEnemy;
import Sprites.Enemy;
import Sprites.Player;
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
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import game.*;

import com.badlogic.gdx.math.Vector3;


public class PlayScreen implements Screen {
    //private String mapLocation = "src/resources/test.tmx"; // used to test graphical features
    public  Mario game;
    private OrthographicCamera camera;
    private Viewport gamePort;

    private Hud hud;
    private TiledMap map; // referanse til selve spillebrettet
    private OrthogonalTiledMapRenderer renderer; // funksjonalitet som viser spillebrettet

    private Player player1, player2;
    private SpriteBatch batch;
    private Sprite  player1Sprite, player2Sprite, enemy1Sprite;

    private int gameState = 2; //1 == mainMenu, 2 == mainGame, 3 == nextLevel, 4 == gameOver

    private World world;
    private Box2DDebugRenderer b2dr;

    private BasicEnemy basicEnemy;
    private AdvancedEnemy advancedEnemy;

    public PlayScreen(Mario game){
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
        String mapLocation = "src/resources/levels/randomlvl.tmx";
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

        player1Sprite = createSprite("src/resources/objects/Steffen16Transp.png");
        player1 = new Player(this); // spiller 1

        player2Sprite = createSprite("src/resources/objects/Elias16Transp.png");
        player2 = new Player(this); // spiller 2

        enemy1Sprite = createSprite("src/resources/objects/Mario_and_Enemies3.png");
        basicEnemy = new BasicEnemy(this, 1447 / Mario.PPM, 32 / Mario.PPM);
        advancedEnemy = new AdvancedEnemy(this, 1680 / Mario.PPM, 32 / Mario.PPM);


        world.setContactListener(new WorldContact());

    }

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

    public void handleInput(float dt) { // sjekker input
        if (this.gameState == 2) {
            if(Gdx.input.isKeyJustPressed (Input.Keys.UP)) {
                player1.b2body.applyLinearImpulse(new Vector2(0, 1.5f), player1.b2body.getWorldCenter(), true);
            }if (Gdx.input.isKeyPressed (Input.Keys.RIGHT) && player1.b2body.getLinearVelocity().x <= 2) {
                player1.b2body.applyLinearImpulse(new Vector2(0.03f, 0), player1.b2body.getWorldCenter(), true);
            }if (Gdx.input.isKeyPressed (Input.Keys.LEFT) && player1.b2body.getLinearVelocity().x >= -2) {
                player1.b2body.applyLinearImpulse(new Vector2(-0.03f, 0), player1.b2body.getWorldCenter(), true);
            }if(Gdx.input.isKeyJustPressed (Input.Keys.W)) {
                player2.b2body.applyLinearImpulse(new Vector2(0, 1.5f), player2.b2body.getWorldCenter(), true);
            }if (Gdx.input.isKeyPressed (Input.Keys.D) && player2.b2body.getLinearVelocity().x <= 2) {
                player2.b2body.applyLinearImpulse(new Vector2(0.03f, 0), player2.b2body.getWorldCenter(), true);
            }if (Gdx.input.isKeyPressed (Input.Keys.A) && player2.b2body.getLinearVelocity().x >= -2) {
                player2.b2body.applyLinearImpulse(new Vector2(-0.03f, 0), player2.b2body.getWorldCenter(), true);
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
                gameState = 4;
            }
        }



    }

    public void update(float dt){ // oppdaterer enheter
        handleInput(dt);

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
        update(v);
        Gdx.gl.glClearColor(1, 1, 1, 1); // setter farge og alfa
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // tømmer skjermen
        renderer.render();

        switch(this.gameState) {
            case 1:
                this.mainMenu(); //hoved meny
                break;
            case 2:
                this.mainGame(v); // når spillet pågår
                break;
            case 4:
                this.gameOver();
            default:
                break;
        }

        // bruker kamera definert i Hud.java for hva spilleren kan se i spillet
//    	game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
    }

    public void mainMenu() {

        game.setScreen(new MenuScreen(game));
    }

    public void mainGame(float v) {
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

    public void gameOver() {
        game.setScreen(new GameOverScreen(game));
    }

    public World getWorld(){
        return world;
    }

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
    }
}
