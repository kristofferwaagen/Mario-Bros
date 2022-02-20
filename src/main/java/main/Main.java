package main;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import game.Game;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("GameIngame");
        cfg.setIdleFPS(60);
        cfg.setWindowedMode(1280, 720);

        new Lwjgl3Application(new Game(), cfg);
    }
}