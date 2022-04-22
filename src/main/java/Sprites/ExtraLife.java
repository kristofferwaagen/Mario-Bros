package Sprites;

import Scene.Hud;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

import static game.Mario.music;

/**
 * Når en spiller interagerer med objectlayer extralife, så skal spilleren få et ekstra liv.
 */
public class ExtraLife extends InteractiveObject{

    public ExtraLife(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.extraLifeBit);
    }
    @Override
    public void onTouch() {
        music.getExtraLifeSound();
        Hud.addLife(1);
        Player.hp += 1;
        categoryFilter(Mario.removedBit);
        getTileCell().setTile(null);
    }

}
