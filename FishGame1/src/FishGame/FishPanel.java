package FishGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.event.KeyEvent;
import java.awt.Graphics;

public class FishPanel extends JPanel{
    //标识状态0开始，1运行，2暂停，3是结束
    public int state = START;
    public static final int START=0;
    public static final int RUNNING =1;
    public static final int PAUSE =2;
    public static final int GAMEOVER =3;
    public static final int WIN =4;

    Image bkImg = DataImg.background;
    Image start =DataImg.start;

    int score=0;//经验得分
    int goal=30;//每一关的目标
    ArrayList<Fish> fishes = new ArrayList<Fish>();//装其他🐟的集合

    int fish_1=0,fish_2=1,fish_3=1,fish_4=1,fish_5=1;
    boolean lock=true;
    Point p;
    myFish fish;

    String username;


    public FishPanel(String username){
        this.username=username;
        fish=new myFish();
    }
    @Override
    public void paint(Graphics g)  {
        super.paint(g);
        //绘制游戏背景界面
        switch(state) {
            case START: g.drawImage(start, 0, 0, 1300, 850, null);
                g.setFont(new Font("宋体",Font.BOLD,45));
                g.setColor(Color.WHITE);
                g.drawString("按下空格开始",500,500);
                score=0;//再次初始化
                break;
            case RUNNING:
                //背景绘制
                drawBackGround(g);
                //绘制己方的小鱼
                drawMyFish(g);
                //绘制其他的小鱼
                drawFishes(g);
                //设置字体颜色
                g.setColor(Color.white);
                Font f = new Font("黑体",Font.BOLD,22);//加粗
                g.setFont(f);  //传进去
                g.drawString("当前得分:"+score, 15, 22);//在x=15,y=20显示分数
                g.drawString("战斗力:"+fish.power, 200, 22);
                g.drawString("目标得分:"+goal, 400, 22);
                break;//一样

            case PAUSE:
                drawBackGround(g);
                drawMyFish(g);
                drawFishes(g);
                g.setColor(Color.white);
                Font fff = new Font("黑体",Font.BOLD,50);
                g.setFont(fff);
                g.drawString("按下空格继续游戏",500 ,500);
                break;

            case GAMEOVER:
                drawBackGround(g);
                drawMyFish(g);
                drawFishes(g);
                g.drawString("当前得分:"+score, 15, 22);
                g.drawString("目标得分:"+goal, 400, 22);
                g.setColor(Color.white);
                Font ff = new Font("黑体",Font.BOLD,50);
                g.setFont(ff);
                g.drawString("很遗憾 游戏结束",600 ,500);
                goal=30;
                break;

            case WIN:
                drawBackGround(g);
                drawMyFish(g);
                drawFishes(g);
                g.drawString("当前得分:"+score, 15, 22);
                g.drawString("目标得分:"+goal, 400, 22);
                g.setColor(Color.white);
                Font ffff = new Font("黑体",Font.BOLD,50);
                g.setFont(ffff);
                g.drawString("恭喜你获胜了",600 ,500);
                score=0;//再次初始化
                goal=goal+1;
                break;
        }
    }

    /*定时器、
	1.生成其他鱼
	2.移动*/
    public void action(){//多线程,timer内部实现
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {//定时器任务
            @Override
            public void run() {
                if(state==1) {

                    addFish();//1.创建其他鱼2.不断的改变其他鱼的位置：
                    eatFish();//3.吃鱼
                    if(lock)move(p);
                    outOfRegion();//4.当鱼跑出圈外，我们要删除集合中的鱼；
                    repaint(); //重画
                }
            }
        },20,10);//20延迟执行，10每隔10ms执行run里面的内容一次

    }
    private void eatFish() {
        for(int i=0;i<fishes.size();i++) {
            if(hit(fishes.get(i))) {
                if(fish.power>=fishes.get(i).power) {

                    switch(fishes.get(i).power) {
                        case 1:
                            score++;
                            break;
                        case 2:
                            score+=2;
                            break;
                        case 3:
                            score+=3;
                            break;
                        case 4:
                            score+=4;
                            break;
                    }
                    fishes.remove(i);//根据分数加战力
                    if(score>=3 && score<6)
                        fish.power=2;
                    if(score>=6 && score<12)
                        fish.power=3;
                    if(score>=18 && score<goal)
                        fish.power=4;
                    if(score>=goal)
                        state=4;
                }else {
                    state=3;//运行结束
                }

            }
        }
    }
    //产生鱼
    private void addFish() {
        if((fish_1++%200)==0) {//每隔10ms自加一次
            fishes.add(new Fish_size1());
        }
        if((fish_2++%300)==0) {
            fishes.add(new Fish_size2());
            fishes.add(new Fish_size2_1());
        }
        if((fish_3++%400)==0) {
            fishes.add(new Fish_size3());
        }
        if((fish_4++%600)==0) {
            fishes.add(new Fish_size4());
        }
        if((fish_5++%1500)==0) {
            fishes.add(new Fish_size5());
        }
    }

    private void outOfRegion() {
        for(int i=0;i<fishes.size();i++) {
            if(fishes.get(i).MoveLeft) {
                if(fishes.get(i).x+fishes.get(i).length<=0) {
                    fishes.remove(i);
                }
            } else
            if(fishes.get(i).x>=1440) {
                fishes.remove(i);
            }
        }
    }
    //中心点在鱼的xy范围内,检测碰撞
    public boolean hit(Fish fishs) {
        int x=(fish.x1+fish.x)/2,y=(fish.y+fish.y1)/2;
        int x1 = fish.x1,y1 = fish.y1;
        int x2 = fish.x,y2 = fish.y;
        if(((x>=fishs.x)&&(x<=fishs.x+fishs.length))||((x1>=fishs.x)&&(x1<=fishs.x+fishs.length))||((x2>=fishs.x)&&(x2<=fishs.x+fishs.length)) ){
            if(((y>=fishs.y)&&(y<=fishs.y+fishs.height))||((y1>=fishs.y)&&(y1<=fishs.y+fishs.height))||((y2>=fishs.y)&&(y2<=fishs.y+fishs.height))) {
                return true;//产生碰撞
            }
        }
        return false;
    }

    public void move(int keycode) {//键盘事件移动鱼
        switch(keycode) {
            case KeyEvent.VK_RIGHT:
                fish.moveRight();
                break;
            case KeyEvent.VK_LEFT:
                fish.moveLeft();
                break;
            case KeyEvent.VK_UP:
                fish.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                fish.moveDown();
                break;
            case KeyEvent.VK_ESCAPE:
                if (state==RUNNING)state=GAMEOVER;
                break;
            case KeyEvent.VK_SPACE:
                if(state == 0) {
                    state=RUNNING;

                }else if(state==1){
                    state=PAUSE;
                }else if(state==2){
                    state=RUNNING;
                }
                else if(state==3) {
                    state=START;
                    fish=new myFish();
                    fishes = new ArrayList<Fish>();


                }
                else if(state==4) {
                    state=WIN;
                    fish=new myFish();
                    fishes = new ArrayList<Fish>();
                    state=RUNNING;

                }
                break;

        }
        repaint();
    }

    public void move(Point point){
        p=point;
        lock=true;//产生鼠标事件
        if(p.x>(fish.x1+fish.x)/2 && p.y>=(fish.y1+fish.y)/2){fish.moveDownRight();}
        else if(p.x>(fish.x1+fish.x)/2 && p.y<=(fish.y1+fish.y)/2){fish.moveUpRight();}
        else if(p.x<(fish.x1+fish.x)/2 && p.y>=(fish.y1+fish.y)/2){fish.moveDownLeft();}
        else if(p.x<(fish.x1+fish.x)/2 && p.y<=(fish.y1+fish.y)/2){ fish.moveUpLeft();}
        else{lock=false;}
    }

    //画自己的鱼
    private void drawMyFish(Graphics g) {
        // TODO Auto-generated method stub
        fish.draw(g);
    }

    //画其他鱼
    private void drawFishes(Graphics g) {
        // TODO Auto-generated method stub
        for(int i=0;i<fishes.size();i++) {
            fishes.get(i).draw(g);
        }
    }


    private void drawBackGround(Graphics g) {
        // TODO Auto-generated method stub
        g.drawImage(bkImg, 0, 0, 1440, 900, 0, 0, 1920, 1080, null);
    }

}

