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

public class ChooseLevel implements Screen {
    Stage stage;
    Mario game;

    public ChooseLevel (Mario game){
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
        TextButton level1, level2, level3, level4, level5, menu;
        Image backgroundImage;

        //create table for buttons and background
        Table backgroundTable = new Table();
        Table buttonTable = new Table();
        buttonTable.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        buttonTable.setSize(centralW, centralH);
        buttonTable.setPosition((Gdx.graphics.getWidth() - centralW) / 2.0f, (Gdx.graphics.getHeight() - centralH) / 2.0f);

        //create the buttons and design
        BitmapFont white = new BitmapFont();
        TextButton.TextButtonStyle lvl1Style = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle lvl2Style = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle lvl3Style = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle lvl4Style = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle lvl5Style = new TextButton.TextButtonStyle();
        TextButton.TextButtonStyle menuStyle = new TextButton.TextButtonStyle();

        lvl1Style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/level1.png"))));
        lvl2Style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/level2.png"))));
        lvl3Style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/level3.png"))));
        lvl4Style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/level4.png"))));
        lvl5Style.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/level5.png"))));
        menuStyle.up = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("button/mainmenu.png"))));

        lvl1Style.font = white;
        lvl2Style.font = white;
        lvl3Style.font = white;
        lvl4Style.font = white;
        lvl5Style.font = white;
        menuStyle.font = white;

        level3 = new TextButton("", lvl3Style);
        level2 = new TextButton("", lvl2Style);
        level1 = new TextButton("", lvl1Style);
        level4 = new TextButton("", lvl4Style);
        level5 = new TextButton("", lvl5Style);
        menu = new TextButton("", menuStyle);
        backgroundImage = new Image(new Texture(Gdx.files.internal("button/backgroundForMeny.png")));

        //set background
        backgroundTable.add(backgroundImage);
        backgroundTable.setFillParent(true);

        //add buttons to table
        buttonTable.add(level1).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.add(level2).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();
        buttonTable.add(level3).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.add(level4).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.row().expandX().fillX();
        buttonTable.add(level5).height(buttonH).width(buttonW).expandX().fillX();
        buttonTable.add(menu).height(buttonH).width(buttonW).expandX().fillX();

        stage.addActor(backgroundTable);
        stage.addActor(buttonTable);

        level1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(PlayScreen.singlePlayer)
                    game.setScreen(new PlayScreen(game, true, 1));
                else
                    game.setScreen(new PlayScreen(game, false, 1));

                dispose();
            }
        });
        level2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(PlayScreen.singlePlayer)
                    game.setScreen(new PlayScreen(game, true, 2));
                else
                    game.setScreen(new PlayScreen(game, false, 2));
                Mario.levelCounter = 2;
                dispose();
            }
        });
        level3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(PlayScreen.singlePlayer)
                    game.setScreen(new PlayScreen(game, true, 3));
                else
                    game.setScreen(new PlayScreen(game, false, 3));
                Mario.levelCounter = 3;
                dispose();
            }
        });
        level4.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(PlayScreen.singlePlayer)
                    game.setScreen(new PlayScreen(game, true, 4));
                else
                    game.setScreen(new PlayScreen(game, false, 4));
                Mario.levelCounter = 4;
                dispose();
            }
        });level5.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if(PlayScreen.singlePlayer)
                    game.setScreen(new PlayScreen(game, true, 5));
                else
                    game.setScreen(new PlayScreen(game, false, 5));
                Mario.levelCounter = 5;
                dispose();
            }
        });
        menu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MenuScreen(game));
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
