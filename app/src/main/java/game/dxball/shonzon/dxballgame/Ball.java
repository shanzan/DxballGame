package game.dxball.shonzon.dxballgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by khans on 12/20/2016.
 */

public class Ball {
    float ballXpos,ballYpos,ballRadius;
    Paint pafloat;
    public Ball(float ballx,float bally,float radius,Paint pafloat){
        this.ballXpos=ballx;
        this.ballYpos=bally;
        this.ballRadius=radius;
        this.pafloat=pafloat;
    }
    public void drawBall(Canvas canvas){
       // canvas.drawRGB(255, 255, 255);
        pafloat.setColor(Color.RED);
        pafloat.setStyle(Paint.Style.FILL);
        canvas.drawCircle(ballXpos,ballYpos,ballRadius,pafloat);
    }
    public float getBallXpos() {
        return ballXpos;
    }

    public void setBallXpos(float ballXpos) {
        this.ballXpos = ballXpos;
    }

    public float getBallYpos() {
        return ballYpos;
    }

    public void setBallYpos(float ballYpos) {
        this.ballYpos = ballYpos;
    }
}
