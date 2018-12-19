package game.dxball.shonzon.dxballgame;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.provider.MediaStore;

/**
 * Created by khans on 12/22/2016.
 */

public class SoundPlayer {
    private  static SoundPool soundPool;
    private  static  int backgroundSound;
    public SoundPlayer(Context context){

        soundPool=new SoundPool(2, AudioManager.STREAM_MUSIC,0);
        backgroundSound=soundPool.load(context,R.raw.start,1);
    }
    public void playBackgroundSound(){
        soundPool.play(backgroundSound,5.0f,5.0f,1,1,1.0f);
    }
}
