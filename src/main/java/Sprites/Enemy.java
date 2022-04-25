package Sprites;

import Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Enemy extends Sprite {
    protected World world;
    protected PlayScreen screen;
    public Body b2body;
    public Vector2 speed;


    public Enemy(PlayScreen screen, float x, float y){
        this.world = screen.getWorld();
        this.screen = screen;
        setPosition(x, y);
        defineEnemy();
        speed = new Vector2(-1,-2);
        b2body.setActive(false);
    }

    protected abstract void defineEnemy();

    public abstract void contactTop();

    public void flipSpeed(boolean x, boolean y){
        if(x){
            speed.x = -speed.x;
        }
        if(y){
            speed.y = -speed.y;
        }
    }

}
