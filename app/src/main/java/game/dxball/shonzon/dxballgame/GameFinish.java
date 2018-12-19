package game.dxball.shonzon.dxballgame;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class GameFinish extends AppCompatActivity {

    GameViewCanvas gvc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
       gvc=new GameViewCanvas(this,this);
        setContentView(gvc);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gvc.mediaPlayer.pause();

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        gvc.mediaPlayer.start();
    }
}
