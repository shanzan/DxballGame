package game.dxball.shonzon.dxballgame;

import android.annotation.SuppressLint;

/**
 * Created by khans on 12/21/2016.
 */

public class GameThread extends Thread {
    GameViewCanvas gameViewCanvas;
    boolean running=false;
    public GameThread(GameViewCanvas g){
        this.gameViewCanvas=g;
    }
    public void setRunning(boolean run){
        this.running=run;
    }
    @Override
    public void run() {
        super.run();
        while (running){
            try{
                sleep(1);

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
