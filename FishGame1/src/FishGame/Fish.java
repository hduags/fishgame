package FishGame;
import java.awt.Graphics;
import java.awt.Image;
//父类的鱼
public class Fish {
    int x,y;//初始时刻鱼位置
    int length,height;//初始时刻鱼的大小
    boolean MoveLeft=true;//初始时刻鱼的方向


    int step;
    int power;
    Image img;//引入鱼的图片

    //原图片的坐标左上角和右下角(裁剪)
    int sx1,sy1;
    int sx2,sy2;

    //绘制
    public void draw(Graphics g) {};
}

