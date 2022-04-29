package Scene;

import Sprites.Player;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import game.Mario;

public class Hud implements Disposable {
    public Stage stage;
    private Viewport viewport; // når bakgrunnen flytter på seg vil man at hud skal vær lik, bruker da nytt kamera for hud

    private static Integer score, timer, collectedKey, life;
    private float counter;

    // enkel tekst for de forskjellige hud elementene
    static Label scoreLabel, timeLabel, keyLabel, lifeLabel;


    public Hud(SpriteBatch Batch){
        timer = 300;
        counter = 0;
        score = 0;
        collectedKey = 0;
        life = 1;


        viewport = new FitViewport(Mario.visionWidth, Mario.visionHeight, new OrthographicCamera()); // nye kamera for hud
        stage = new Stage(viewport, Batch);


        Table table = new Table();
        table.top();
        table.setFillParent(true);
        lifeLabel = new Label(String.format("%01d", life), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeLabel = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        keyLabel = new Label(String.format("%01d", collectedKey), new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        Label lives = new Label("Lives", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label gameName = new Label("KURT MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label player1 = new Label("Score p1", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label timer = new Label("Timer", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        Label key = new Label("Key", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        //top row
        table.add(lives).expandX();
        table.add(key).expandX();
        table.add(timer).expandX();
        table.add(player1).expandX();

        table.add(gameName).expandX();
        table.row();

        //bottom row
        table.add(lifeLabel).expandX();
        table.add(keyLabel).expandX();
        table.add(timeLabel).expandX();
        table.add(scoreLabel).expandX();

        stage.addActor(table); // legger til tabellen til stage
    }

    /**
     * oppdaterer antall liv i Hud
     */
    public static void addLife(int i){
        lifeLabel.setText(String.format("%01d", Player.hp+i));
    }

    /**
     * oppdaterer om vi har nøkkelen i Hud
     */
    public static void addKey(int i) {
        collectedKey = i;
        keyLabel.setText(String.format("%01d", collectedKey));
    }

    /**
     * oppdaterer Hud og timeren
     */
    public void update(float dt){
        counter += dt;
        if(counter >= 1){
            timer--;
            timeLabel.setText(String.format("%03d", timer));
            counter = 0;
        }
    }

    /**
     * legger til score i Hud dersom spilleren får poeng
     */
    public static void scoreAdder(int x){
        score += x;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
