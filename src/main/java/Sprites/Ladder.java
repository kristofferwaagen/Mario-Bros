package Sprites;

import Screens.PlayScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Ladder extends InteractiveObject{
    public Ladder(World world, TiledMap map, Rectangle r){
        super(world, map, r);
    }
    @Override
    public void onTouch() {
        PlayScreen.player1.b2body.applyLinearImpulse(new Vector2(0, 1.5f), PlayScreen.player1.b2body.getWorldCenter(), true);
        }
}
