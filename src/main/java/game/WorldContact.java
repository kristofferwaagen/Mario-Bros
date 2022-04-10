package game;

import Sprites.Enemy;
import Sprites.Goal;
import Sprites.InteractiveObject;
import Sprites.Player;
import com.badlogic.gdx.physics.box2d.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class WorldContact implements ContactListener {
    @SuppressFBWarnings("SF_SWITCH_NO_DEFAULT")
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        int contactDef = a.getFilterData().categoryBits | b.getFilterData().categoryBits;

        if(a.getUserData() == "top" || b.getUserData() == "top"){
            Fixture top = a.getUserData() == "top" ? a : b;
            Fixture object = top == a ? b : a;

            if(object.getUserData() != null && InteractiveObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveObject) object.getUserData()).onTouch();
            }
        }

        switch (contactDef){
            case Mario.enemyTop | Mario.bit:
                if(a.getFilterData().categoryBits == Mario.enemyTop){
                    ((Enemy)a.getUserData()).contactTop();
                }else{
                    ((Enemy)b.getUserData()).contactTop();
                }
                break;
            case Mario.enemyBit | Mario.objectBit:
                if(a.getFilterData().categoryBits == Mario.enemyBit){
                    ((Enemy)a.getUserData()).flipSpeed(true, false);
                }else{
                    ((Enemy)b.getUserData()).flipSpeed(true, false);
                }
                break;
            case Mario.bit | Mario.enemyBit:
                if(a.getFilterData().categoryBits == Mario.bit){
                    ((Player)a.getUserData()).hit();
                }else{
                    ((Player)b.getUserData()).hit();
                }
                break;
            case Mario.enemyBit | Mario.enemyBit:
                ((Enemy)a.getUserData()).flipSpeed(true, false);
                ((Enemy)b.getUserData()).flipSpeed(true, false);
                break;
            case Mario.bit | Mario.goalBit:
                ((Goal)a.getUserData()).onTouch();
            default:
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
