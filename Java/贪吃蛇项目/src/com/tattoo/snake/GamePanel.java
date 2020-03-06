package com.tattoo.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    int length;
    int[] snakeX = new int[600];
    int[] snakeY = new int[600];
    String fx;
    boolean isStart;
    Timer timer = new Timer(150, this); // 100 毫秒
    // 定义食物
    int foodX, foodY;
    Random random = new Random();
    boolean isFail;


    public GamePanel() {
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        this.addKeyListener(this);
        //让定时器动起来
        timer.start();
    }

    public void init() {
        length = 3;
        fx = "R";
        isFail = false;
        isStart = false;
        snakeX[0] = 100;
        snakeY[0] = 100;
        snakeX[1] = 75;
        snakeY[1] = 100;
        snakeX[2] = 50;
        snakeY[2] = 100;
        //初始化食物
        foodX = 25 + 25 * random.nextInt(34); //25  ----- 875
        foodY = 75 + 25 * random.nextInt(24); // 75 ----- 675
    }

    //画板：画界面
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        //Data.header.paintIcon(this, g, 25, 11);

        //绘制游戏区域
        g.fillRect(25, 75, 850, 600);

        //根据方向画蛇的头部的位置
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeY[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeY[0]);
        }

        for (int i = 1; i < length; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeY[i]);
        }
        // 画出食物
        Data.food.paintIcon(this, g, foodX, foodY);

        if (isStart == false) {
            //询问游戏是否开始
            g.setColor(Color.WHITE);
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.drawString("按下空格键游戏开始", 250, 300);
        }
        if (isFail) {
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑", Font.BOLD, 20));
            g.drawString(" Game Over !!! 按下空格游戏重新开始 ", 250, 300);
        }
        //监听键盘的输入
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //按下键盘
        //获取按下了什么键
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE) {
            if (isFail) {
                isFail = false;
                init();
            } else {
                isStart = !isStart;
            }
            repaint();  //刷新界面
        } else if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        } else if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        }
    }

    //定时器
    //执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        if (isStart && isFail == false) {
            //移动身体的操作,身体向前移动即可
            for (int i = length - 1; i > 0; i--) {
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //根据方向移动头部，注意判断是否出界
            if (fx.equals("R")) {
                snakeX[0] += 25;
                if (snakeX[0] > 850) snakeX[0] = 25;//边界判断
            } else if (fx.equals("L")) {
                snakeX[0] -= 25;
                if (snakeX[0] < 25) snakeX[0] = 850;
            } else if (fx.equals("U")) {
                snakeY[0] -= 25;
                if (snakeY[0] < 75) snakeY[0] = 650;
            } else if (fx.equals("D")) {
                snakeY[0] += 25;
                if (snakeY[0] > 650) snakeY[0] = 75;
            }
            //判断是不是吃上了食物？
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                length++;
                foodX = 25 + 25 * random.nextInt(34); //25  ----- 875
                foodY = 75 + 25 * random.nextInt(24); // 75 ----- 675
            }
            //判断是否死亡 ？
            for (int i = length - 1; i > 0; i--) {
                if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                    isFail = true;
                }
            }
            repaint();  //刷新界面
        }
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //释放某个键
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //敲击了一下键盘
    }

}


