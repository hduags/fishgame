package FishGame;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fish_size2 extends Fish{
    int xx;
    int change=0;
    public Fish_size2() {
        step=3;
        action();
        xx=(int)(Math.random()*2);
        switch(xx) {
            case 0:
                MoveLeft=false;
                x=-93;
                break;
            case 1:
                MoveLeft=true;
                x=1400;
                break;
        }
        y=(int)(Math.random()*900);
        length= 95;
        height=	30;
        power=2;
        //界面坐标

        img = DataImg.fish12;
    }
    public void action(){
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                change=(++change)%step;
            }
        },20,420);
    }
    public void draw(Graphics g){
        if(MoveLeft) {//左移
            sy1=height*change;;
            sx1=0;
            sy2=height+height*change;
            sx2=length;
            g.drawImage(img, x=x-3, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
        else {//右移
            sx2=0;
            sy2=height*change;;
            sx1=length;
            sy1=height+height*change;
            g.drawImage(img, x=x+3, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
    }
}


