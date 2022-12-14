package Screens;

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

public class VictoryScreen implements Screen {
    Stage stage;
    Mario game;

    public VictoryScreen(Mario game){
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
        TextButton.TextButtonStyle menuStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle exitStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle victoryStyle = new TextButton.TextButtonStyle();

        menuStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/mainmenu.png"))));
        victoryStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/victory.png"))));
        exitStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/exit.png"))));

        victoryStyle.font = white;
        exitStyle.font = white;
        menuStyle.font = white;
        TextButton exitButton = new TextButton("", exitStyle);
        TextButton victoryButton = new TextButton("", victoryStyle);
        TextButton menuButton = new TextButton("", menuStyle);
        backgroundImage = new Image(new Texture(Gdx.files.internal("button/backgroundForMeny.png")));

        //set background
        backgroundTable.add(backgroundImage);
        backgroundTable.setFillParent(true);

        //add buttons to table
        buttonTable.row().expandX().fillX();
        buttonTable.add(victoryButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();
        buttonTable.add(menuButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();
        buttonTable.add(exitButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();

        stage.addActor(backgroundTable);
        stage.addActor(buttonTable);

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        menuButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Mario.levelCounter =1 ;
                game.setScreen(new MenuScreen(game));
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
