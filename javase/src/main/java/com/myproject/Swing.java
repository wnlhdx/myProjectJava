package com.myproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Swing
{

    public  static void main( String[] args )
    {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container=frame.getContentPane();
        //frame.setSize(500,500);
        frame.setBounds(100,100,500,500);
        frame.setIconImage(null);
        frame.setResizable(true);
        frame.setTitle(Toolkit.getDefaultToolkit().getScreenSize().toString());
        frame.setTitle(Arrays.toString(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        TestG  testG=new TestG();
        testG.setBackground(Color.WHITE);
        testG.setForeground(Color.WHITE);
        Button button=new Button();
        button.setBounds(0,0,100,100);
        button.addActionListener(x->button.setBackground(Color.RED));

        container.add(testG,0);
        container.add(button,1);
        frame.pack();
        frame.setVisible(true);


    }
    static class TestG extends JComponent{
        @Override
        public void paintComponent(Graphics g){
            Graphics2D graphics2D=(Graphics2D)g;
            graphics2D.drawString("???",100,100);
            graphics2D.setPaint(Color.RED);
            Rectangle2D rectangle2D=new Font(Font.SANS_SERIF,Font.BOLD,14).getStringBounds("，，，",graphics2D.getFontRenderContext());
            graphics2D.draw(new Rectangle(100,100));
        }
        @Override
        public Dimension getPreferredSize(){
            return  new Dimension(200,200);
        }
    }
}
