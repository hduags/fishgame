package FishGame;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fish_size4 extends FishGame.Fish {
    int xx;
    int change=0;
    public Fish_size4() {
        step=4;
        action();
        xx=(int)(Math.random()*2);
        switch(xx) {
            case 0:
                MoveLeft=false;
                x=-280;
                break;
            case 1:
                MoveLeft=true;
                x=1410;
                break;
        }
        y=(int)(Math.random()*900);
        length= 315;
        height=	105;
        power=3;
        //界面坐标

        img = DataImg.fish03;
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
            g.drawImage(img, x=x-2, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
        else {//右移
            sx2=0;
            sy2=height*change;;
            sx1=length;
            sy1=height+height*change;
            g.drawImage(img, x=x+2, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
    }

}

