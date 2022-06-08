package FishGame;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;


public class Fish_size1 extends Fish{
    int xx;
    int change=0;
    public Fish_size1() {
        action();
        step=2;
        xx=(int)(Math.random()*2);//0或者1
        switch(xx) {//出场位置
            case 0:
                MoveLeft=false;
                x=-62;
                break;
            case 1:
                MoveLeft=true;
                x=1400;
                break;
        }
        y=(int)(Math.random()*900);//垂直位置
        length= 66;
        height=	50;
        power=1;
        //界面坐标

        img = DataImg.fish08;
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
            g.drawImage(img, x=x-4, y, x+length, y+height, sx1, sy1, sx2,sy2, null);//可以控制鱼的速度
        }
        else {//右移
            sx2=0;
            sy2=height*change;;
            sx1=length;
            sy1=height+height*change;
            g.drawImage(img, x=x+4, y, x+length, y+height, sx1, sy1, sx2,sy2, null);
        }
    }

}


