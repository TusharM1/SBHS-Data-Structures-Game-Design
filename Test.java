package Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Test extends JPanel implements KeyListener {

    Polygon player;
    JFrame frame;

    Test() {
        frame = new JFrame();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(100 + 10 * 50,100 + 10 * 50);
        frame.setVisible(true);
        frame.addKeyListener(this);

        player = new Polygon(new int[]{0, 15, -15}, new int[]{-15, 15, 15}, 3);
        player.translate(frame.getWidth() / 2, frame.getHeight() / 2);
    }

    float x = 0;

    public static void main(String[] args) {
        Test test = new Test();
//        while (true) {
//            test.x+=0.001;
//            test.x %= 360;
//            test.repaint();
//            System.out.println(test.x);
//        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).rotate(Math.toRadians(x), frame.getWidth() / 2.0, frame.getHeight() / 2.0);
        g.fillPolygon(player);
//        ((Graphics2D) g).rotate(Math.toRadians(-x));
        g.drawRect(0, 0, frame.getWidth(), frame.getHeight());
        g.drawLine(0, frame.getHeight() / 2, frame.getWidth(), frame.getHeight() / 2);
        g.drawLine(frame.getWidth() / 2, 0, frame.getWidth() / 2, frame.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        x+=90;
//        System.out.println("T");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        x+=90;
        System.out.println("P " + x);
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
//        x+=90;
//        System.out.println("R");
    }
}
