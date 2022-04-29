package Sprites.BlockObjects;

import Scene.Hud;
import Screens.PlayScreen;
import Sprites.Player;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

import static Screens.PlayScreen.player1;
import static Screens.PlayScreen.player2;
import static game.Mario.music;

/**
 * Når en spiller interagerer med objectlayer extralife, så skal spilleren få et ekstra liv.
 */
public class ExtraLife extends InteractiveObject {

    public ExtraLife(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.extraLifeBit);
    }
    @Override
    public void onTouch() {
        Player.hp += 1;
        music.getExtraLifeSound();
        Hud.addLife(1);

        categoryFilter(Mario.removedBit);
        getTileCell().setTile(null);
    }

}
