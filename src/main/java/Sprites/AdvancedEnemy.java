package Sprites;

import Scene.Hud;
import Screens.PlayScreen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import game.Mario;

/**
 * Fiende som er mer krevende å drepe enn BasicEnemy
 */
public class AdvancedEnemy extends Enemy{
    private Boolean toRemove;
	public Boolean removed;
	private Boolean singlePlayer;
	private Boolean testing = false;
    private float time;
    private Animation animation;
    private Array<Texture> frames;
    private Texture t1,t2;

    public AdvancedEnemy(PlayScreen screen, float x, float y, boolean singlePlayer) {
        super(screen, x, y);
        toRemove = false;
        removed = false;
        this.singlePlayer = singlePlayer;

        setBounds(getX(), getY(), 16/Mario.PPM, 16/Mario.PPM);
        t1 = new Texture("src/resources/tileset/16x16/Enemies/slime1.png");
        t2 = new Texture("src/resources/tileset/16x16/Enemies/slime2.png");
        frames = new Array<>();
        frames.add(t1);
        frames.add(t2);
        animation = new Animation(0.4f, frames);
    }
    /**
     * Konstruktør for testing, bruker ikke PlayScreen, HUD, musikk osv.
     */
    public AdvancedEnemy(World world, float x, float y) {
    	super(world, x, y);
    	toRemove = false;
    	removed = false;
    	this.singlePlayer = true;
    	testing = true;
    }

    public void update(float dt){
        time += dt;
        if(toRemove && !removed){
            world.destroyBody(b2body);
            removed = true;
        }
        else if (!removed) {
            setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 2);
            if(!testing) {
            	setRegion((Texture) animation.getKeyFrame(time, true));
                if(speed.x < 0 && (!this.isFlipX()))
                	this.setFlip(true, isFlipY());
            }
            if(!singlePlayer){
                Player currentPlayer = screen.getClosest(this);
                if(b2body.getPosition().x < currentPlayer.getX())
                    b2body.setLinearVelocity(1,-2);
                    //flipSpeed(true, false);
                else {
                    b2body.setLinearVelocity(-1,-2);
                }
            }
            else{
                b2body.setLinearVelocity(speed);
            }
        }
    }

    @Override
    protected void defineBody() {
        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(5 / Mario.PPM);
        fdef.filter.categoryBits = Mario.enemyBit;
        fdef.filter.maskBits = Mario.groundBit | Mario.coinBit | Mario.bit | Mario.objectBit | Mario.enemyBit | Mario.bulletBit;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        PolygonShape top = new PolygonShape();
        Vector2[] edge = new Vector2[4];
        edge[0] = new Vector2(-3, 7).scl(1 / Mario.PPM);
        edge[1] = new Vector2(3, 7).scl(1 / Mario.PPM);
        edge[2] = new Vector2(-2, 2).scl(1 / Mario.PPM);
        edge[3] = new Vector2(2, 2).scl(1 / Mario.PPM);
        top.set(edge);

        fdef.shape = top;
        fdef.restitution = 0.1f;
        fdef.filter.categoryBits = Mario.enemyTop;
        b2body.createFixture(fdef).setUserData(this);

    }

    @Override
    public void contactTop() {
        Bullets.toRemove = true;
        toRemove = true;
        if(!testing)
        	Hud.scoreAdder(100);
    }
    public void draw(Batch batch){
        if(!removed){
            super.draw(batch);
        }
    }
}
