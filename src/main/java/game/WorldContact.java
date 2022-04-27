package game;

import Sprites.*;
import Sprites.BlockObjects.*;
import com.badlogic.gdx.physics.box2d.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class WorldContact implements ContactListener {
	Fixture a;
	Fixture b;
	int contactDef;
	
	private void definitions(Contact contact) {
    	a = contact.getFixtureA();
        b = contact.getFixtureB();
        contactDef = a.getFilterData().categoryBits | b.getFilterData().categoryBits;

	}
    @SuppressFBWarnings("SF_SWITCH_NO_DEFAULT")
    @Override
    public void beginContact(Contact contact) {
    	definitions(contact);
    	if(a.getUserData() == "top" || b.getUserData() == "top"){
            Fixture top = a.getUserData() == "top" ? a : b;
            Fixture object = top == a ? b : a;

            if(object.getUserData() != null && InteractiveObject.class.isAssignableFrom(object.getUserData().getClass())){
                ((InteractiveObject) object.getUserData()).onTouch();
            }
        }
        switch (contactDef){
            case Mario.ammoBit | Mario.bit:
                groundJump(Mario.ammoBit);
                break;
            case Mario.groundBit | Mario.bit:
                groundJump(Mario.groundBit);
                break;
            case Mario.exprBlockBit | Mario.bit:
                groundJump(Mario.exprBlockBit);
                break;
            case Mario.coinBit | Mario.bit:
            	groundJump(Mario.coinBit);
            	break;
            case Mario.extraLifeBit | Mario.bit:
            	groundJump(Mario.extraLifeBit);
            	break;
            case Mario.enemyTop | Mario.bulletBit:
            case Mario.enemyTop | Mario.bit:
                if(a.getFilterData().categoryBits == Mario.enemyTop){
                    ((Enemy)a.getUserData()).contactTop();
                }else{
                    ((Enemy)b.getUserData()).contactTop();
                }
                break;
            case Mario.enemyBit | Mario.groundBit:
            case Mario.enemyTop | Mario.groundBit:
            case Mario.enemyTop | Mario.enemyBit:
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
                break;
            case Mario.bit | Mario.keyBit:
                ((Key) a.getUserData()).onTouch();
                break;
            case Mario.playerBot | Mario.exprBlockBit:
                ((ExpiringBlocks) a.getUserData()).onTouch();
                break;

            default:
                break;
        }
    }

    @Override
    public void endContact(Contact contact) {
    	definitions(contact);
        switch (contactDef){
            case Mario.ammoBit | Mario.bit:
                airJump(Mario.ammoBit);
                break;
            case (Mario.groundBit) | Mario.bit:
                airJump(Mario.groundBit);
                break;
            case Mario.exprBlockBit | Mario.bit:
                airJump(Mario.exprBlockBit);
                break;
            case Mario.coinBit | Mario.bit:
                airJump(Mario.coinBit);
                break;
            case Mario.extraLifeBit | Mario.bit:
                airJump(Mario.extraLifeBit);
                break;
            default:
                break;
        }
    }
    
    private void airJump(short bit) {
    	if(a.getFilterData().categoryBits == bit && b.getFilterData().categoryBits == Mario.bit){
        	if(b.getBody().getLinearVelocity().y >= 0) {
        		((Player)b.getUserData()).canJumpOnGround = false;
                ((Player)b.getUserData()).canJumpInAir = true;
        	}
        }
    }
    
    private void groundJump(short bit) {
    	if(a.getFilterData().categoryBits == bit && b.getFilterData().categoryBits == Mario.bit){
        	if(b.getBody().getLinearVelocity().y <= 0) {
        		((Player)b.getUserData()).canJumpOnGround = true;
                ((Player)b.getUserData()).canJumpInAir = false;
        	}
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
