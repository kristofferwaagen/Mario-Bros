package Screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import game.Mario;

public class GameOverScreen implements Screen {
    Stage stage;
    Mario game;
    public GameOverScreen(Mario game){
        this.game = game;
        stage = new Stage();
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        //used to position table
        float centralW = Gdx.graphics.getWidth() * 0.4f;
        float centralH = Gdx.graphics.getHeight() * 0.2f;
        float buttonW =  (float) (Gdx.graphics.getWidth() * 0.4);
        float buttonH = (float) (Gdx.graphics.getHeight() * 0.2);

        //buttons and background
        Image backgroundImage;

        //create table for buttons and background
        Table backgroundTable = new Table();
        Table buttonTable = new Table();
        buttonTable.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        buttonTable.setSize(centralW, centralH);
        buttonTable.setPosition((Gdx.graphics.getWidth() - centralW) / 2.0f, (Gdx.graphics.getHeight() - centralH) / 2.0f);

        //create the buttons and design
        BitmapFont white = new BitmapFont();
        TextButton.TextButtonStyle exitStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle gameoverStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle retryStyle = new TextButton.TextButtonStyle();

        gameoverStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("src/resources/gameover.png")));
        retryStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("src/resources/retry.png")));
        exitStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture("src/resources/exit.png")));

        retryStyle.font = white;
        gameoverStyle.font = white;
        exitStyle.font = white;
        TextButton quitButton = new TextButton("", exitStyle);
        TextButton gameoverButton = new TextButton("", gameoverStyle);
        TextButton retryButton = new TextButton("", retryStyle);
        backgroundImage = new Image(new Texture("src/resources/backgroundForMeny.png"));

        //set background
        backgroundTable.add(backgroundImage);
        backgroundTable.setFillParent(true);

        //add buttons to table
        buttonTable.row().expandX().fillX();
        buttonTable.add(gameoverButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();
        buttonTable.add(retryButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();
        buttonTable.add(quitButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();

        stage.addActor(backgroundTable);
        stage.addActor(buttonTable);

        quitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        retryButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new PlayScreen(game));
                dispose();
            }
        });

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();

    }
}
