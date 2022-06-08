package FishGame;

import java.util.TimerTask;
import java.util.Timer;
import java.awt.*;


public class myFish extends Fish {
    int x1, y1;//
    int change = 0;

    public myFish() {
        action();
        length = 68;//鱼的大小
        height = 55;

        power = 1;
        //初始鱼界面坐标
        x = 100;
        y = 450;
        //初始坐标加上长宽后就确定了鱼图片的位置
        x1 = x + 68;
        y1 = y + 55;
        step = 2;
    }

    //鱼的转向
    public void draw(Graphics g) {
        super.draw(g);
        changeSize(power);
        if (MoveLeft) {
            sy1 = height * change;
            sx1 = 0;
            sy2 = height + height * change;
            sx2 = length;

        } else {
            sy2 = height * change;
            sx2 = 0;
            sy1 = height + height * change;
            sx1 = length;

        }

        Image img = selectImage(power);
        //画图前4个坐标确定鱼显示位置，后4个对鱼进行剪裁

        g.drawImage(img, x, y, x1, y1, sx1, sy1, sx2, sy2, null);
    }

    private void changeSize(int level) {
        if (level < 2) {
        } else if (level == 2) {
            height = 20;
            length = 90;
            step = 3;
            x1 = x + 90;
            y1 = y + 20;
        } else if (level == 3) {
            height = 100;
            length = 300;
            step = 4;
            x1 = x + 300;
            y1 = y + 100;
        } else {
            height = 200;
            length = 370;
            step = 2;
            x1 = x + 385;
            y1 = y + 200;
        }
    }

    public void moveLeft() {
        MoveLeft = true;
        if (x >= 10) {
            x -= 10;
            x1 -= 10;
        }//水平移动y不变；
    }

    public void moveRight() {
        MoveLeft = false;
        if (x <= 1350) {//符合移动规范
            x += 10;
            x1 += 10;
        }//水平移动y不变；
    }

    //上下移动不关其他事
    public void moveDown() {
        if (y <= 800) {
            y += 10;
            y1 += 10;
        }
    }

    public void moveUp() {
        if (y >= 0) {
            y -= 10;
            y1 -= 10;
        }
    }

    public void moveUpLeft() {
        MoveLeft = true;
        y -= 1;
        x -= 1;
        x1 = x + 68;
        y1 = y + 55;
    }

    public void moveUpRight() {
        MoveLeft = false;
        y -= 1;
        x += 1;
        x1 = x + 68;
        y1 = y + 55;
    }

    public void moveDownLeft() {
        MoveLeft = true;
        y += 1;
        x -= 1;
        x1 = x + 68;
        y1 = y + 55;
    }

    public void moveDownRight() {
        MoveLeft = false;
        y += 1;
        x += 1;
        x1 = x + 68;
        y1 = y + 55;
    }

    public void action() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                change = (++change) % step;
            }
        }, 20, 500);
    }

    public Image selectImage(int level) {//进化
        if (level < 2) {
            return DataImg.fish08;
        } else if (level == 2) {
            return DataImg.fish12;
        } else if (level == 3) {
            return DataImg.fish03;
        } else {
            return DataImg.fish17;
        }
    }
}


