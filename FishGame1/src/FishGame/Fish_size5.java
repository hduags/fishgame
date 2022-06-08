package FishGame;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Fish_size5 extends Fish {
    int xx;
    int change=0;
    public Fish_size5() {
        step=2;
        action();
        xx=(int)(Math.random()*2);
        switch(xx) {
            case 0:
                MoveLeft=false;
                x=-380;
                break;
            case 1:
                MoveLeft=true;
                x=1440;
                break;
        }
        y=(int)(Math.random()*900);
        length= 380;
        height=	210;
        power=4;
        //界面坐标

        img = DataImg.fish17;
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
            g.drawImage(img, x--, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
        else {//右移
            sx2=0;
            sy2=height*change;;
            sx1=length;
            sy1=height+height*change;
            g.drawImage(img, x++, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
    }

}
