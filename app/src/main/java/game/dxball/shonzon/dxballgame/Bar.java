package game.dxball.shonzon.dxballgame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by khans on 12/21/2016.
 */

public class Bar {
    float barXpos,barYpos,barHeight=20,barWidth;
    Paint paint;
 public Bar(float barx,float bary,float barw,Paint p0){
     this.barXpos=barx;
     this.barYpos=bary;
     this.paint=p0;
     this.barWidth=barw;
    }
    public  void  drawBar(Canvas canvas){
        paint.setColor(Color.GRAY);
        canvas.drawRect(barXpos,barYpos,barXpos+barWidth,barYpos+barHeight,paint);
        paint.setColor(Color.BLUE);
       canvas.drawCircle(barXpos-3,barYpos+10,10,paint);
        canvas.drawCircle(barXpos+barWidth+3,barYpos+10,10,paint);
    }

    public float getBarXpos() {
        return barXpos;
    }

    public void setBarXpos(float barXpos) {
        this.barXpos = barXpos;
    }

    public float getBarYpos() {
        return barYpos;
    }

    public void setBarYpos(float barYpos) {
        this.barYpos = barYpos;
    }

    public float getBarHeight() {
        return barHeight;
    }

    public void setBarHeight(float barHeight) {
        this.barHeight = barHeight;
    }

    public float getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(float barWidth) {
        this.barWidth = barWidth;
    }
}
