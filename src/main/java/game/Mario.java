package game;

import Screens.MenuScreen;
import Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Mario extends com.badlogic.gdx.Game {
    public static final int visionWidth = 400; // bredde for spillet
    public static final int visionHeight= 208; // høyde for spillet
    public static final float PPM = 100;

    public static final short groundBit = 1;
    public static final short bit = 2;
    public static final short coinBit = 4;
    public static final short removedBit = 8;
    public static final short enemyBit = 16;
    public static final short objectBit = 32;
    public static final short enemyTop = 64;
    public static final short goalBit = 128;

    public SpriteBatch batch; // container for forskjellige bilder

    @Override
    public void create() {
        batch = new SpriteBatch();
        setScreen(new MenuScreen(this)); // funksjonalitet for å endre aktiv skjerm
    }

    @Override
    public void render () {
        super.render(); // delegere render metoden til aktiv skjerm (PlayScreen)
    }
}
