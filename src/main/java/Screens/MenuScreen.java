package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
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


public class MenuScreen implements Screen {
    Stage stage;
    Mario game;

    public MenuScreen(Mario game){
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
        TextButton twoPlayerButton, onePlayerButton, exitButton, chooseLevelButton;
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
        TextButton.TextButtonStyle singleStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle multiStyle = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle levelStyle = new TextButton.TextButtonStyle();

        levelStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/chooselevel.png"))));
        singleStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/play.png"))));
        multiStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/twoplayer.png"))));
        exitStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/exit.png"))));

        levelStyle.font = white;
        multiStyle.font = white;
        singleStyle.font = white;
        exitStyle.font = white;

        exitButton = new TextButton("", exitStyle);
        onePlayerButton = new TextButton("", singleStyle);
        twoPlayerButton = new TextButton("", multiStyle);
        chooseLevelButton = new TextButton("", levelStyle);
        backgroundImage = new Image(new Texture(Gdx.files.internal("button/backgroundForMeny.png")));

        //set background
        backgroundTable.add(backgroundImage);
        backgroundTable.setFillParent(true);

        //add buttons to table
        buttonTable.add(onePlayerButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.add(twoPlayerButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();
        buttonTable.add(chooseLevelButton).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.add(exitButton).height(buttonH).width(buttonW).expandX().fillX();

        stage.addActor(backgroundTable);
        stage.addActor(buttonTable);

        exitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Gdx.app.exit();
            }
        });

        onePlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PlayScreen.singlePlayer = true;
                Sound lyd = Gdx.audio.newSound(Gdx.files.internal("src/main/resources/audio/enSpillereValgt.ogg"));
                lyd.play(0.2f);
            }
        });

        twoPlayerButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PlayScreen.singlePlayer = false;
                Sound lyd = Gdx.audio.newSound(Gdx.files.internal("src/main/resources/audio/toSpillereValgt.ogg"));
                lyd.play(0.2f);
            }
        });

        chooseLevelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(PlayScreen.singlePlayer!= null){
                    game.setScreen(new ChooseLevel(game));
                    dispose();
                }
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
