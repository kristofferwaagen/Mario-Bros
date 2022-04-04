package game;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Mario extends com.badlogic.gdx.Game {
    public static final int visionWidth = 400; // bredde for spillet
    public static final int visionHeight= 208; // høyde for spillet
    public static final float PPM = 100;

    public SpriteBatch batch; // container for forskjellige bilder

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new PlayScreen(this)); // funksjonalitet or å endre aktiv skjerm
    }

    @Override
    public void render () {
        super.render(); // delegere render metoden til aktiv skjerm (PlayScreen)
    }
}
