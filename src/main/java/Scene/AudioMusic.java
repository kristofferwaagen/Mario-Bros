package Scene;

import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioMusic {
    public static Music gameMusic, gameOverMusic;
    Sound jumpSound, hitSound;
    public AudioMusic() {
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("src/resources/audio/backgroundMusic.ogg"));
        gameMusic.setLooping(true);

        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("src/resources/audio/gameoverAudio.ogg"));
        gameOverMusic.setLooping(true);
    }
    public void getJumpSound(){
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/jump.ogg"));
        jumpSound.play();
        PlayScreen.jumping = false;
    }

    public void getHitSound(){
        hitSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/hit.ogg"));
        hitSound.play();
        PlayScreen.hit = false;
    }
}
