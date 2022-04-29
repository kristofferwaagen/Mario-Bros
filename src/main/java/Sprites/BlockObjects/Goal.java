package Sprites.BlockObjects;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import game.Mario;

public class Goal extends InteractiveObject {

    public Goal(World world, TiledMap map, Rectangle r) {
        super(world, map, r);
        fixture.setUserData(this);
        categoryFilter(Mario.goalBit);
    }

    @Override
    public void onTouch() {
        Hud.scoreAdder(300);
        if(Mario.hasKey)
            PlayScreen.isFinished(true);
    }

}
