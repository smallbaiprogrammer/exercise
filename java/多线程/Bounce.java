package com.company.java.多线程;

import com.sun.jndi.toolkit.url.Uri;
import javafx.scene.image.ImageView;

import javax.swing.*;
import javax.xml.stream.Location;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Bounce {

    public static void main(String[] args) {
        

      EventQueue.invokeLater(()->{
          JFrame frame = new BounceFrame("多线程测试");
          frame.setVisible(true);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      });
    }

}
class BounceFrame extends JFrame{
    private BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 3;

    private JButton button;
    private ImageIcon imageIcon;
    private Container container;
    public BounceFrame(String s){
       setTitle(s);
       comp = new BallComponent();
       add(comp,BorderLayout.CENTER);
       JPanel buttonPanel = new JPanel();
       addButton(buttonPanel,"START",event->addBall());
       addButton(buttonPanel,"CLOSE",event->System.exit(0));
       add(buttonPanel,BorderLayout.SOUTH);
       pack();
    }
    public void addButton(Container container,String title,ActionListener listener){
        JButton button = new JButton(title);
        container.add(button);
        button.addActionListener(listener);
    }
    public void addBall(){
        try{
            Ball ball = new Ball();
            comp.add(ball);
            for (int i=1;i<= STEPS;i++){
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("move error");
        }
    }
}
