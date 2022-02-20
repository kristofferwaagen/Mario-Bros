package game;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


public class Game implements ApplicationListener {

    private OrthographicCamera camera;
    private Viewport gamePort;
    private SpriteBatch batch;
    private BitmapFont font;
    private GamePlayer player1, player2; //player3, player4
    private Texture background;

    @Override
    public void create() {
        camera = new OrthographicCamera();
        gamePort = new FitViewport(1280, 720, camera);
        batch = new SpriteBatch();
        font = new BitmapFont();

        player1 = new GamePlayer("src/resources/Steffen.png");
        player1.setPosition(200, 100);

        player2 = new GamePlayer("src/resources/Elias.png");
        player2.setPosition(50, 100); //p2

        background = new Texture(Gdx.files.internal("src/resources/testmapbackground.png"));

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
        background.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(background, 0, 0);
        player1.draw(batch); // p1
        player2.draw(batch); // p2
        batch.end();

        Rectangle temp = new Rectangle(0, 0, 1280, 10); // gulvet i spillet

        // updates

        // p1 updates
        player1.update(Gdx.graphics.getDeltaTime());

        if(player1.hits(temp) != -1) {
            player1.action(1, 0, 10);
        }
        // p1 controls
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            player1.moveLeft(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            player1.moveRight(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            player1.jump();
        }

        // p2 updates
        player2.update(Gdx.graphics.getDeltaTime());

        if(player2.hits(temp) != -1) {
            player2.action(1, 0, 10);
        }
        // p2 controls
        if(Gdx.input.isKeyPressed(Input.Keys.A)) {
            player2.moveLeft(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.D)) {
            player2.moveRight(Gdx.graphics.getDeltaTime());
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            player2.jump();
        }
    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }
}
