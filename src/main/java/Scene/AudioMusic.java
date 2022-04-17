package Scene;

import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioMusic {
    public static Music gameMusic, gameOverMusic;
    Sound jumpSound, hitSound, coinSound, keySound;
    public AudioMusic() {
        gameMusic = Gdx.audio.newMusic(Gdx.files.internal("src/resources/audio/backgroundMusic.ogg"));
        gameMusic.setLooping(true);
        gameMusic.setVolume(0.2f);

        gameOverMusic = Gdx.audio.newMusic(Gdx.files.internal("src/resources/audio/gameoverAudio.ogg"));
        gameOverMusic.setLooping(true);
        gameOverMusic.setVolume(0.1f);
    }
    public void getJumpSound(){
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/jump.ogg"));
        jumpSound.play(0.1f);
        PlayScreen.jumping = false;
    }

    public void getHitSound(){
        hitSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/hit.ogg"));
        hitSound.play(0.3f);
        PlayScreen.hit = false;
    }

    public void getCoinSound() {
        coinSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/coin.ogg"));
        coinSound.play(0.05f);
        PlayScreen.coin = false;
    }

    public void getKeySound(){
        keySound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/key.ogg"));
        keySound.play(0.2f);
        PlayScreen.key = false;
    }

    public void dispose() {
        coinSound.dispose();
        hitSound.dispose();
        jumpSound.dispose();
        gameMusic.dispose();
        gameOverMusic.dispose();
    }
}
