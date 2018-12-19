package game.dxball.shonzon.dxballgame;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnstart,quitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnstart=(Button)findViewById(R.id.button);
        quitButton=(Button)findViewById(R.id.button2);
        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameFinish();
            }
        });
        quitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void gameFinish(){
        Intent intent=new Intent(this,GameFinish.class);
        startActivity(intent);
    }
}
