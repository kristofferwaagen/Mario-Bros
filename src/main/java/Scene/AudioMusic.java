package Scene;

import Screens.PlayScreen;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Klasse hvor alle lydene lagres i hver sin metode.
 */
public class AudioMusic {
    public static Music gameMusic, gameOverMusic;
    Sound jumpSound, hitSound, coinSound, keySound, hurtSound, extralifeSound, winSound, noCoinSound, ammoSound, shotSound;
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
        jumpSound.play(0.04f);
    }

    public void getHitSound(){
        hitSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/hit.ogg"));
        hitSound.play(0.6f);
    }

    public void getCoinSound() {
        coinSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/coin.ogg"));
        coinSound.play(0.04f);
    }

    public void getKeySound(){
        keySound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/key.ogg"));
        keySound.play(0.2f);
    }

    public void getHurtSound(){
        hurtSound= Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/hurt.ogg"));
        hurtSound.play(0.5f);
    }
    public void getExtraLifeSound(){
        extralifeSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/extralife.ogg"));
        extralifeSound.play(0.2f);
    }
    public void getWinnerSound(){
        winSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/winner.ogg"));
        winSound.play(0.6f);
    }
    public void getNoCoinSound() {
        noCoinSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/noCoin.ogg"));
        noCoinSound.play(0.6f);
    }
    public void getShotSound(){
        shotSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/shot.ogg"));
        shotSound.play(0.5f);
    }
    public void getAmmoSound(){
        ammoSound = Gdx.audio.newSound(Gdx.files.internal("src/resources/audio/ammoPickup.ogg"));
        ammoSound.play(0.5f);
    }
    public void dispose() {
        coinSound.dispose();
        hitSound.dispose();
        jumpSound.dispose();
        extralifeSound.dispose();
        winSound.dispose();
        ammoSound.dispose();
        shotSound.dispose();
        noCoinSound.dispose();
        gameMusic.dispose();
        gameOverMusic.dispose();
    }
}
