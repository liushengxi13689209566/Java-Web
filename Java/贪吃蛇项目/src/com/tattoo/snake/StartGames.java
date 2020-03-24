package com.tattoo.snake;

import javax.swing.*;


public class StartGames {
    public static  void main(String []args)
    {
        //1.绘制静态窗口 Jframe
        JFrame frame =  new JFrame("刘生玺的贪吃蛇");
        frame.setBounds(10,10,900,720);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //2.面板 JPanel
        frame.add(new GamePanel());

        frame.setVisible(true); //让窗口能够展现出来
    }
}
