package FishGame;

import javax.swing.*;
import java.awt.event.*;
/*
 * 步骤：
 * 1.先创建窗体对象
 * 2.背景的绘制
 * 3.键盘的监听事件
 * 		1.实现接口
 * 		2.添加监听的事件
 * 4.将己方鱼放上去
 * 5.让小鱼移动
 * 6.敌方的🐟(先创建集合把鱼装起来，获取每个对象，画图）
 * 7.吃鱼
 * 8.被吃
 * 9.增加了鼠标事件
 * */

class MyFishKeyListener implements KeyListener{


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}

public class FishFrame extends JFrame{
    public FishFrame(String username){
        setTitle("欢迎"+username+"进入大鱼吃小鱼游戏");
        FishPanel fishPanel=new FishPanel(username);
        add(fishPanel);

        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseMoved(MouseEvent e) {
                fishPanel.move(e.getPoint());
            }
            @Override
            public void mouseDragged(MouseEvent e) {

            }

        });
        addKeyListener(new MyFishKeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                int vk=e.getKeyCode();
                fishPanel.move(vk);
            }
        });

        fishPanel.action();//启动线程
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new MyFishKeyListener());
        setBounds(100,20,1455,915);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args){
        new FishFrame("用户");
    }
}

