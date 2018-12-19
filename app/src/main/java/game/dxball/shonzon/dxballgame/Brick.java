package game.dxball.shonzon.dxballgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by khans on 12/21/2016.
 */

public class Brick {
    Paint paint;
    float brickX,brickY,brickWidth,brickHeight;
    int r,g,b;
    public Brick(float bx,float by,float bw,float bh,Paint paint){
        this.brickX=bx;
        this.brickY=by;
        this.brickHeight=bh;
        this.brickWidth=bw;
        this.paint=paint;
        Random rand = new Random();
        r = rand.nextInt(255-0)+0;
        g = rand.nextInt(255-0)+0;
        b = rand.nextInt(255-0)+0;

    }
    public void drawBrick(Canvas canvas){
        paint.setARGB(255,r,g,b);
        canvas.drawRect(brickX,brickY,brickX+brickWidth,brickY+brickHeight,paint);
    }

}
