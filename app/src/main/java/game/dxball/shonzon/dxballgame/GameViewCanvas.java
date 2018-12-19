package game.dxball.shonzon.dxballgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by khans on 12/20/2016.
 */

public class GameViewCanvas extends View implements View.OnTouchListener {
    Paint paint;
    Ball createball;
    Bar barcreate;
    int move = 5;
    int horozm = 0;
    boolean verticalMove = true;
    float x1, x2, y1, y2;
    GameThread gameThread;
    boolean step = false;
    float width, height;
    int score, life = 3;
    float barw;
    float distance;
    boolean finish=false;
    ArrayList<Brick> addBricks=new ArrayList<>();
    boolean startagain=true;
    final MediaPlayer mediaPlayer;
    AppCompatActivity appCompatActivity;
    int arraysize=30;
    Random rand = new Random();
    int genarateRandom;
    float speed;
    public GameViewCanvas(Context context,AppCompatActivity apc) {
        super(context);
        this.appCompatActivity=apc;
       //soundPlayer=new SoundPlayer(context);
        paint = new Paint();
        gameThread = new GameThread(this);
        gameThread.start();
        mediaPlayer =MediaPlayer.create(context,R.raw.start);;
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
        //soundPlayer.playBackgroundSound();
        this.setOnTouchListener(this);
    }

    public void initial() {
        if(startagain==true) {
            barw = width / 3;
        }else
        {
            barw = width / 4;
        }
        barcreate = new Bar((width / 3), height - 60, barw, paint);
        createball = new Ball(barcreate.barXpos+(barcreate.barWidth / 2), height- 75, 15, paint);
            float x = this.getX() + 10;
            float y = this.getY() + 80;
            distance = width - x;
            float k = x;
            for (int i = 0; i < arraysize; i++) {
                addBricks.add(new Brick(x, y, distance / 5 - 5, height / 20, paint));
                x = x + distance / 5;
                if (i == 4 || i == 9 || i == 14 || i == 19 || i == 24 || i == 29 || i ==34  || i == 39 || i == 44 || i == 49 ||  i == 54 ) {
                    x = k;
                    y = y + height / 20 + 5;
                }

            }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        boolean motion=false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
//                x1 = event.getX();
//                y1 = event.getY();
                    barcreate.barXpos = event.getX()-barcreate.barWidth;
                if(barcreate.barXpos<=this.getX()){
                    barcreate.barXpos=this.getX();
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
//                x2 = event.getX();
//                y2 = event.getX();
//                if (motion==true) {
//                    if (barcreate.barXpos+3<=width-barw) {
//                        barcreate.barXpos+=(width-(barcreate.barXpos+barcreate.barWidth))/3;
//                    }
//                }else {
//                    if (barcreate.barXpos-7>=0) {
//                        barcreate.barXpos-=((barcreate.barXpos-0)/3);
//                    }
//                }
                    barcreate.barXpos = event.getX() - barcreate.barWidth;
                    if(barcreate.barXpos<=this.getX()){
                        barcreate.barXpos=this.getX();
                    }
                    break;
            }
            case MotionEvent.ACTION_UP:{
                    barcreate.barXpos = event.getX()-barcreate.barWidth;
                if(barcreate.barXpos<=this.getX()){
                    barcreate.barXpos=this.getX();
                }
                break;

            }

        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (step == false) {
            this.width = canvas.getWidth();
            this.height = canvas.getHeight();
            initial();
            step = true;
            genarateRandom=rand.nextInt(30-0)+0;
        } else {
            if (life>0) {
                canvas.drawRGB(255, 255, 255);
                paint.setColor(Color.BLUE);
                paint.setTextSize(30);
                canvas.drawText("Score: " + String.valueOf(score), this.getX() + 5, this.getY() + 50, paint);
                canvas.drawText("Life: " + String.valueOf(life), this.getX() + (width / 3 + width / 3), this.getY() + 50, paint);
                createball.drawBall(canvas);
                barcreate.drawBar(canvas);
                ballMoving();
                try {

                    gameThread.setRunning(false);
                    gameThread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameThread.setRunning(true);
                for (int i = 0; i < addBricks.size(); i++) {
                    addBricks.get(i).drawBrick(canvas);
                }
            }else {
                gameThread.setRunning(false);
                canvas.drawRGB(255,255,255);
                paint.setColor(Color.RED);
                paint.setTextSize(70);
                canvas.drawText("GAME OVER!!! ",this.getX() + 5, height/2, paint);
                paint.setTextSize(40);
                canvas.drawText("You Scored : " + String.valueOf(score), this.getX() +5, height/2+100, paint);
                try {
                    canvas.drawText("HighScore : " + String.valueOf(highscoreSave(score)), this.getX() +5, height/2+200, paint);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.stop();
            }
        }
        invalidate();
    }

    void ballMoving() {
        if (horozm == 0) {
            createball.ballXpos += move;
            if (cornerCollition(this.getWidth(), createball.ballXpos)) {
                horozm = 1;
            }
        } else if (horozm == 1) {
            createball.ballXpos -= move;
            if (cornerCollition(0, createball.ballXpos))
                horozm = 0;
        } else if (horozm==3) {
            createball.ballXpos += 2.5;
            if (cornerCollition(this.getWidth(), createball.ballXpos))
                horozm = 4;
        } else if (horozm==4) {
            createball.ballXpos -= 2.5;
            if (cornerCollition(0, createball.ballXpos))
                horozm = 3;
        }else {
            createball.ballXpos += 0;
        }

        if (verticalMove == true) {
            createball.ballYpos += move;
            if (barCollition()) {
                verticalMove = false;
                horozm = 2;
            } else if (barLeftCollition()) {
                verticalMove = false;
                horozm = 1;
            } else if (barRightCollition()) {
                verticalMove = false;
                horozm = 0;
            }  else if (barLeftSecondCollition()) {
                verticalMove = false;
                horozm = 4;
            } else if (barRightFirstCollition()) {
                verticalMove = false;
                horozm = 3;
            }
            else if (cornerCollition(this.getHeight() + 200, createball.getBallYpos())) {
                createball.setBallXpos(barcreate.barXpos + barcreate.barWidth / 2);
                createball.setBallYpos(barcreate.barYpos - barcreate.barHeight);
                life--;
            }
            for (int i=0;i<addBricks.size();i++){
                if (brickBallCollision(addBricks.get(i).brickX,addBricks.get(i).brickY)){
                    if(i==genarateRandom){
                        score+=10;
                    }
                    if(arraysize>50){
                        life=0;
                    }
                    addBricks.remove(i);
                    verticalMove=false;
                    score+=5;
                    if(addBricks.size()==0){
                        startagain=false;move+=2;step=false;arraysize+=5;
                    }

                }
            }
        } else {
            createball.ballYpos -= move;
            if (cornerCollition(0, createball.ballYpos)) {
                verticalMove = true;
            }
            for (int i=0;i<addBricks.size();i++){
                    if (brickBallCollision(addBricks.get(i).brickX,addBricks.get(i).brickY)){
                        if(i==genarateRandom){
                            score+=10;
                        }
                        if(arraysize>50){
                            life=0;
                        }
                        addBricks.remove(i);
                        verticalMove=true;
                        score+=5;
                        if(addBricks.size()==0){
                            startagain=false;move+=2;
                            step=false;arraysize+=5;
                        }
                    }
                }
        }
    }

    boolean cornerCollition(float first, float rad) {
        float distancex = first - rad;
        distancex = distancex * distancex;
        float radius = createball.ballRadius * createball.ballRadius;
        if (distancex >= radius)
            return false;
        else
            return true;
    }

    boolean barCollition() {
        float range = barcreate.barWidth / 5;
        float deltax = createball.ballXpos - Math.max(barcreate.barXpos + (2*range), Math.min(createball.ballXpos, barcreate.barXpos + (3* range)));
        float deltay = createball.ballYpos - Math.max(barcreate.barYpos, Math.min(createball.ballYpos, barcreate.barYpos + barcreate.barHeight));
        float sumredius = createball.ballRadius * createball.ballRadius;
        deltax = deltax * deltax;
        deltay = deltay * deltay;
        if (Math.sqrt(deltax + deltay) <= Math.sqrt(sumredius)) {
            return true;
        } else
            return false;
    }

    boolean barLeftCollition() {
        float range = barcreate.barWidth / 5;
        float deltax = createball.ballXpos - Math.max(barcreate.barXpos - 3, Math.min(createball.ballXpos, barcreate.barXpos + range));
        float deltay = createball.ballYpos - Math.max(barcreate.barYpos, Math.min(createball.ballYpos, barcreate.barYpos + barcreate.barHeight));
        float sumredius = createball.ballRadius * createball.ballRadius;
        deltax = deltax * deltax;
        deltay = deltay * deltay;
        if (Math.sqrt(deltax + deltay) <= Math.sqrt(sumredius)) {
            return true;
        } else
            return false;
    }
    boolean barLeftSecondCollition() {
        float range = barcreate.barWidth / 5;
        float deltax = createball.ballXpos - Math.max(barcreate.barXpos+range, Math.min(createball.ballXpos, barcreate.barXpos + (2*range)));
        float deltay = createball.ballYpos - Math.max(barcreate.barYpos, Math.min(createball.ballYpos, barcreate.barYpos + barcreate.barHeight));
        float sumredius = createball.ballRadius * createball.ballRadius;
        deltax = deltax * deltax;
        deltay = deltay * deltay;
        if (Math.sqrt(deltax + deltay) <= Math.sqrt(sumredius)) {
            return true;
        } else
            return false;
    }
    boolean barRightFirstCollition() {
        float range = barcreate.barWidth / 5;
        float deltax = createball.ballXpos - Math.max(barcreate.barXpos + (3 * range), Math.min(createball.ballXpos, barcreate.barXpos + (4 * range)));
        float deltay = createball.ballYpos - Math.max(barcreate.barYpos, Math.min(createball.ballYpos, barcreate.barYpos + barcreate.barHeight));
        float sumredius = createball.ballRadius * createball.ballRadius;
        deltax = deltax * deltax;
        deltay = deltay * deltay;
        if (Math.sqrt(deltax + deltay) <= Math.sqrt(sumredius)) {
            return true;
        } else
            return false;
    }

    boolean barRightCollition() {
        float range = barcreate.barWidth / 5;
        float deltax = createball.ballXpos - Math.max(barcreate.barXpos + (4 * range), Math.min(createball.ballXpos, barcreate.barXpos + barcreate.barWidth + 3));
        float deltay = createball.ballYpos - Math.max(barcreate.barYpos, Math.min(createball.ballYpos, barcreate.barYpos + barcreate.barHeight));
        float sumredius = createball.ballRadius * createball.ballRadius;
        deltax = deltax * deltax;
        deltay = deltay * deltay;
        if (Math.sqrt(deltax + deltay) <= Math.sqrt(sumredius)) {
            return true;
        } else
            return false;
    }

    boolean brickBallCollision(float x1, float y1) {
        float deltax = createball.ballXpos - Math.max(x1, Math.min(createball.ballXpos, x1 +(distance/5)-5));
        float deltay = createball.ballYpos - Math.max(y1, Math.min(createball.ballYpos, y1 + height/20));
        float sumredius = createball.ballRadius * createball.ballRadius;
        deltax = deltax * deltax;
        deltay = deltay * deltay;
        return (deltax + deltay) < sumredius;
    }
    public int highscoreSave(int sc) throws IOException {
        int sr=0;
            SharedPreferences sharedp=appCompatActivity.getSharedPreferences("highscore", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=sharedp.edit();
            int x=sharedp.getInt("newHighscore",0);
            if(x>sc){
               sr =x;
            }else{
                sr=sc;
            }
            editor.putInt("newHighscore",sr);
            editor.commit();
        return sr;
    }
    public void startMediaplayer(){
        mediaPlayer.reset();
    }
}
