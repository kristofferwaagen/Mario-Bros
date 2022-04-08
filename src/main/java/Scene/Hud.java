package Scene;

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

    private static Integer score;
    private Integer timer;
    private float counter;

    // enkel tekst for de forskjellige hud elementene
    static Label scoreLabel;
    Label timeLabel;
    Label marioLabel;

    public Hud(SpriteBatch Batch){
        timer = 300;
        counter = 0;
        score = 0;

        viewport = new FitViewport(Mario.visionWidth, Mario.visionHeight, new OrthographicCamera()); // nye kamera for hud
        stage = new Stage(viewport, Batch);

        /*
        * tabell blir brukt for å organisere hud
        * stage kan ses på som en boks der man kan ha widgets, ved bruk av table kan teksten organiseres i boksen ved bruk av kolonner
        * */
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreLabel = new Label(String.format("%04d", score), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // tekst for "score", har 6 nummer (%06)
        timeLabel = new Label(String.format("%03d", timer), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // teskt for tid brukt
        marioLabel = new Label("KURT MARIO", new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // tekst for spiller

        /*
        * expandX strekker kolonnen fra ende til ende i vinduet, om det blir brukt flere kolonner på samme rad blir de organisert på strekningen
        * bruker patTop() for å skape mellomrom
        * */
        table.add(marioLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.add(scoreLabel).expandX().padTop(10);

        stage.addActor(table); // legger til tabellen til stage
    }

    public void update(float dt){
        counter += dt;
        if(counter >= 1){
            timer--;
            timeLabel.setText(String.format("%03d", timer));
            counter = 0;
        }
    }

    public static void scoreAdder(int x){
        score += x;
        scoreLabel.setText(String.format("%06d", score));
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
