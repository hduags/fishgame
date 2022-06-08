package FishGame;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
public class Fish_size3 extends Fish{
    int xx;
    int change=0;
    public Fish_size3() {
        step=3;
        action();
        xx=(int)(Math.random()*2);
        switch(xx) {
            case 0:
                MoveLeft=false;
                x=-90;
                break;
            case 1:
                MoveLeft=true;
                x=1430;
                break;
        }
        y=(int)(Math.random()*900);
        length= 115;
        height=65;
        power=3;
        //界面坐标

        img = DataImg.lantern;
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
            sy1=336+height*change;;
            sx1=330;
            sy2=336+height+height*change;
            sx2=336+length;
            g.drawImage(img, x=x-3, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
        else {//右移
            sx2=330;
            sy2=336+height*change;;
            sx1=330+length;
            sy1=336+height+height*change;
            g.drawImage(img, x=x+3, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
    }
}

