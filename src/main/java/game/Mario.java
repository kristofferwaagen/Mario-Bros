package game;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Mario extends com.badlogic.gdx.Game {
    public static final int visionWidth = 400;
    public static final int visionHeight= 208;

    public SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this));
    }

    @Override
    public void render () {
        super.render();
    }
}