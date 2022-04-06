package game;

import Sprites.InteractiveObject;
import com.badlogic.gdx.physics.box2d.*;

public class WorldContact implements ContactListener {
    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

        if(a.getUserData() == "top" || b.getUserData() == "top"){
            Fixture top = a.getUserData() == "top" ? a : b;
            Fixture object = top == a ? b : a;

            if(object.getUserData() != null && InteractiveObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveObject) object.getUserData()).onTouch();
            }
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
