package Quarter_1.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Maze extends JPanel implements KeyListener {

    JFrame frame;
    ArrayList<String[]> arrayList;
    Polygon player;
    int xPos, yPos;
    int direction;
    int xDim, yDim;
    int mazeNumber;
    int moveCounter;
    int viewDistance;
    int keyNumber, keysNeeded;
    boolean isRunning;
    boolean is3D;
    boolean[][] breadCrumbs;

    Maze() {
        frame = new JFrame();
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addKeyListener(this);

        mazeNumber = 0;
        isRunning = true;
        is3D = true;
        setBoard();

        player = new Polygon(new int[]{0, 15, 0, -15}, new int[]{-15, 15, 5, 15}, 4);
    }

    public static void main(String[] args) {
        new Maze();
    }

    void setBoard() {
        direction = 90;
        moveCounter = 0;
        viewDistance = 4;
        keyNumber = 0;
        keysNeeded = 0;
        try {
            arrayList = new ArrayList<>();
            String path = "src/Quarter_1/Maze/maze" + mazeNumber + ".txt";
            BufferedReader reader = new BufferedReader(new FileReader(new File(path)));
            String input;
            int row = 0;
            while ((input = reader.readLine()) != null) {
                if (input.contains("S")) {
                    xPos = input.indexOf("S");
                    yPos = row;
                }
                row++;
                arrayList.add(input.split(""));
            }
            xDim = arrayList.get(0).length;
            yDim = arrayList.size();
            breadCrumbs = new boolean[yDim][xDim];
            for (String[] sa : arrayList)
                for (String s : sa)
                    for (char c : s.toCharArray())
                        if (c == 'K')
                            keysNeeded++;
            if (is3D)
                frame.setSize(800, 600);
            else
                frame.setSize(100 + xDim * 50, 112 + yDim * 50);
        } catch (FileNotFoundException e) {
            isRunning = false;
            frame.removeKeyListener(this);
            repaint(0, 0, frame.getWidth(), frame.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //<editor-fold desc="Success Clause">
        if (!isRunning) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
            g.setColor(Color.BLACK);
            g.setFont(new Font("LucidaGrande", Font.PLAIN, 40));
            g.drawString("Success!", frame.getWidth() / 2, frame.getHeight() / 2);
            return;
        }
        //</editor-fold>
        //<editor-fold desc="3D Part of Quarter_1.Maze">
        if (is3D) {
            //<editor-fold desc="Parsed">
            ArrayList<ArrayList<Character>> parsed = new ArrayList<>();
            if (direction == 0)
                for (int i = -1 * viewDistance; i <= 0; i++)
                    if (yPos + i >= 0) {
                        ArrayList<Character> temp = new ArrayList<>();
                        for (int j = -1; j <= 1; j++)
                            if (xPos + j >= 0 && xPos + j < xDim)
                                if (i == 0 && j == 0)
                                    temp.add('O');
                                else
                                    temp.add(arrayList.get(yPos + i)[xPos + j].charAt(0));
                            else
                                temp.add('*');
                        parsed.add(temp);
                    }
            if (direction == 90)
                for (int i = viewDistance; i >= 0; i--)
                    if (xPos + i < xDim) {
                        ArrayList<Character> temp = new ArrayList<>();
                        for (int j = -1; j <= 1; j++)
                            if (yPos + j >= 0 && yPos + j < yDim)
                                if (i == 0 && j == 0)
                                    temp.add('O');
                                else
                                    temp.add(arrayList.get(yPos + j)[xPos + i].charAt(0));
                            else
                                temp.add('*');
                        parsed.add(temp);
                    }
            if (direction == 180)
                for (int i = viewDistance; i >= 0; i--)
                    if (yPos + i < yDim) {
                        ArrayList<Character> temp = new ArrayList<>();
                        for (int j = 1; j >= -1; j--)
                            if (xPos + j >= 0 && xPos + j < xDim)
                                if (i == 0 && j == 0)
                                    temp.add('O');
                                else
                                    temp.add(arrayList.get(yPos + i)[xPos + j].charAt(0));
                            else
                                temp.add('*');
                        parsed.add(temp);
                    }
            if (direction == 270)
                for (int i = -1 * viewDistance; i <= 0; i++)
                    if (xPos + i >= 0) {
                        ArrayList<Character> temp = new ArrayList<>();
                        for (int j = 1; j >= -1; j--)
                            if (yPos + j >= 0 && yPos + j < yDim)
                                if (i == 0 && j == 0)
                                    temp.add('O');
                                else
                                    temp.add(arrayList.get(yPos + j)[xPos + i].charAt(0));
                            else
                                temp.add('*');
                        parsed.add(temp);
                    }
            while (parsed.size() != viewDistance + 1) {
                ArrayList<Character> list = new ArrayList<>();
                for (int i = 0; i < 3; i++)
                    list.add('*');
                parsed.add(0, list);
            }
            //</editor-fold>
            g.setColor(new Color(0xCCCCCC));
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            //<editor-fold desc="Foreshortening">
            int addX = (int) (Math.cos(Math.atan2(this.getHeight(), this.getWidth())) * 150), addY = (int) (Math.sin(Math.atan2(this.getHeight(), this.getWidth())) * 150);
            ArrayList<Integer[]> wallPoints = new ArrayList<>();
            int next, oldX = 0, oldY = 0, newX = addX, newY = addY;
            double slope1 = 1. * getHeight() / getWidth(), slope2 = 1. * (getHeight() / 2.0 - oldY) / (newX - oldX);
            for (int i = 0; i <= viewDistance; i++) {
                next = (int) ((slope2 * oldX - oldY + getHeight()) / (slope2 + slope1));
                wallPoints.add(0, new Integer[]{oldX, oldY});
                oldX = newX;
                oldY = newY;
                newX = next;
                newY = (int) (slope1 * next);
                slope2 = 1. * (getHeight() / 2.0 - oldY) / (newX - oldX);
            }
            int lastX = wallPoints.get(0)[0];
            int lastY = wallPoints.get(0)[1];
            int[][] lastPoints = {{lastX, this.getWidth() - lastX, this.getWidth() - lastX, lastX}, {lastY, lastY, this.getHeight() - lastY, this.getHeight() - lastY}};
            //</editor-fold>
            //<editor-fold desc="Outward Lines">
            g.setColor(Color.BLACK);
            g.drawLine(lastPoints[0][0], lastPoints[1][0], 0, 0);
            g.drawLine(lastPoints[0][1], lastPoints[1][1], this.getWidth(), 0);
            g.drawLine(lastPoints[0][2], lastPoints[1][2], this.getWidth(), this.getHeight());
            g.drawLine(lastPoints[0][3], lastPoints[1][3], 0, this.getHeight());
            Color lastColor = new Color(0x111111);
            //</editor-fold>
            //<editor-fold desc="Center Tile Reset">
            g.setColor(lastColor.darker());
            g.fillPolygon(lastPoints[0], lastPoints[1], 4);
            //</editor-fold>
            for (int i = 0; i < parsed.size(); i++) {
                g.setColor(lastColor);
                int localX = wallPoints.get(i)[0], localY = wallPoints.get(i)[1];
                int[][] nextPoints = {{localX, getWidth() - localX, getWidth() - localX, localX}, {localY, localY, getHeight() - localY, getHeight() - localY}};
                //<editor-fold desc="Left Right Tiles">
                Polygon left, right;
                if (parsed.get(i).get(0) == '.' || parsed.get(i).get(0) == 'S') {
                    Polygon leftCorner = new Polygon(new int[]{lastPoints[0][3], nextPoints[0][3], nextPoints[0][3]}, new int[]{lastPoints[1][3], lastPoints[1][3], nextPoints[1][3]}, 3);
                    g.setColor(Color.ORANGE);
                    g.fillPolygon(leftCorner);
                    g.setColor(Color.BLACK);
                    g.drawPolygon(leftCorner);
                    left = new Polygon(new int[]{lastPoints[0][0], lastPoints[0][3], nextPoints[0][3], nextPoints[0][0]}, new int[]{lastPoints[1][0], lastPoints[1][3], lastPoints[1][3], lastPoints[1][0]}, 4);
                } else if (parsed.get(i).get(0) == '*')
                    left = new Polygon(new int[]{lastPoints[0][0], lastPoints[0][3], nextPoints[0][3], nextPoints[0][0]}, new int[]{lastPoints[1][0], lastPoints[1][3], nextPoints[1][3], nextPoints[1][0]}, 4);
                else
                    left = new Polygon(new int[]{lastPoints[0][0], lastPoints[0][3], nextPoints[0][3], nextPoints[0][0]}, new int[]{lastPoints[1][0], lastPoints[1][3], lastPoints[1][3], lastPoints[1][0]}, 4);

                if (parsed.get(i).get(2) == '.' || parsed.get(i).get(2) == 'S') {
                    Polygon rightCorner = new Polygon(new int[]{lastPoints[0][2], nextPoints[0][2], nextPoints[0][2]}, new int[]{lastPoints[1][2], lastPoints[1][2], nextPoints[1][2]}, 3);
                    g.setColor(Color.ORANGE);
                    g.fillPolygon(rightCorner);
                    g.setColor(Color.BLACK);
                    g.drawPolygon(rightCorner);
                    right = new Polygon(new int[]{lastPoints[0][1], lastPoints[0][2], nextPoints[0][2], nextPoints[0][1]}, new int[]{lastPoints[1][1], lastPoints[1][2], lastPoints[1][2], lastPoints[1][1]}, 4);
                } else if (parsed.get(i).get(2) == '*')
                    right = new Polygon(new int[]{lastPoints[0][1], lastPoints[0][2], nextPoints[0][2], nextPoints[0][1]}, new int[]{lastPoints[1][1], lastPoints[1][2], nextPoints[1][2], nextPoints[1][1]}, 4);
                else
                    right = new Polygon(new int[]{lastPoints[0][1], lastPoints[0][2], nextPoints[0][2], nextPoints[0][1]}, new int[]{lastPoints[1][1], lastPoints[1][2], lastPoints[1][2], lastPoints[1][1]}, 4);
                g.setColor(lastColor);
                g.fillPolygon(left);
                g.fillPolygon(right);
                g.setColor(Color.BLACK);
                g.drawPolygon(left);
                g.drawPolygon(right);
                //</editor-fold>
                //<editor-fold desc="Up Down Tiles">
                g.setColor(lastColor);
                g.drawLine(lastPoints[0][0], lastPoints[1][0], lastPoints[0][1], lastPoints[1][1]);
                g.drawLine(lastPoints[0][2], lastPoints[1][2], lastPoints[0][3], lastPoints[1][3]);
                g.drawLine(nextPoints[0][0], nextPoints[1][0], nextPoints[0][1], nextPoints[1][1]);
                g.drawLine(nextPoints[0][2], nextPoints[1][2], nextPoints[0][3], nextPoints[1][3]);
                //</editor-fold>
                //<editor-fold desc="Center Tile">
                g.setColor(Color.BLACK);
                char centerTile = parsed.get(i).get(1);
                Polygon center = new Polygon(nextPoints[0], nextPoints[1], 4);
                g.drawPolygon(center);
                if (centerTile == '*')
                    g.setColor(lastColor.brighter());
                else if (centerTile == 'E')
                    if (keyNumber < keysNeeded)
                        g.setColor(Color.RED);
                    else
                        g.setColor(Color.GREEN);
                else if (centerTile == 'S')
                    g.setColor(Color.CYAN);
                else if (centerTile == 'P')
                    g.setColor(Color.YELLOW);
                else if (centerTile == 'K')
                    g.setColor(Color.PINK);
                else if (centerTile == '.') {
                    g.setColor(Color.ORANGE);
                    center = new Polygon(new int[]{lastPoints[0][2], lastPoints[0][3], nextPoints[0][3], nextPoints[0][2]}, new int[]{lastPoints[1][2], lastPoints[1][3], nextPoints[1][3], nextPoints[1][2]}, 4);
                } else if (centerTile == ' ') {
                    g.setColor(new Color(0xCCCCCC));
                    center = new Polygon(new int[]{lastPoints[0][2], lastPoints[0][3], nextPoints[0][3], nextPoints[0][2]}, new int[]{lastPoints[1][2], lastPoints[1][3], nextPoints[1][3], nextPoints[1][2]}, 4);
                } else
                    continue;
                g.fillPolygon(center);
                //</editor-fold>
                lastColor = lastColor.brighter();
                lastPoints = nextPoints;
            }
            //<editor-fold desc="Bottom Corners">
            g.setColor(Color.ORANGE);
            g.fillPolygon(new Polygon(new int[]{lastPoints[0][2], lastPoints[0][3], 0, this.getWidth()}, new int[]{lastPoints[1][2], lastPoints[1][3], this.getHeight(), this.getHeight()}, 4));
            //</editor-fold>
            //<editor-fold desc="View Counter">
            g.setColor(Color.BLACK);
            g.drawString(moveCounter + "", 30, 15);
            //</editor-fold>
        }
        //</editor-fold>
        //<editor-fold desc="2D Part of Quarter_1.Maze">
        else {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
            g.setColor(Color.WHITE);
            g.drawString(moveCounter + "", 20, 20);
            for (int i = 0; i < arrayList.get(0).length; i++) {
                for (int j = 0; j < arrayList.size(); j++) {
                    g.setColor(Color.WHITE);
                    g.drawRect(50 + i * 50, 50 + j * 50, 50, 50);
                    switch (arrayList.get(j)[i]) {
                        case " ":
                            g.setColor(Color.WHITE);
                            break;
                        case "*":
                            g.setColor(Color.BLACK);
                            break;
                        case "S":
                            g.setColor(Color.BLUE);
                            break;
                        case "E":
                            if (keyNumber == keysNeeded)
                                g.setColor(Color.GREEN);
                            else
                                g.setColor(Color.RED);
                            break;
                        case "P":
                            g.setColor(Color.YELLOW);
                            break;
                        case "K":
                            g.setColor(Color.PINK);
                            break;
                    }
                    g.fillRect(50 + i * 50, 50 + j * 50, 50, 50);
                    if (i == xPos && j == yPos) {
                        g.setColor(Color.RED);
                        player.translate(xPos * 50 + 75, yPos * 50 + 75);
                        ((Graphics2D) g).rotate(Math.toRadians(direction), xPos * 50 + 75, yPos * 50 + 75);
                        g.fillPolygon(player);
                        ((Graphics2D) g).rotate(Math.toRadians(-direction), xPos * 50 + 75, yPos * 50 + 75);
                        player.translate(-(xPos * 50 + 75), -(yPos * 50 + 75));
                    }
                }
            }
        }
        //</editor-fold>
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            direction = ((direction + 360) - 90) % 360;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            direction = ((direction + 360) + 90) % 360;
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (direction == 0)
                if (yPos > 0 && ((!arrayList.get(yPos - 1)[xPos].equals("*") && !arrayList.get(yPos - 1)[xPos].equals("E")) || (arrayList.get(yPos - 1)[xPos].equals("E") && keyNumber == keysNeeded)))
                    yPos--;
            if (direction == 90)
                if (xPos < xDim - 1 && ((!arrayList.get(yPos)[xPos + 1].equals("*") && !arrayList.get(yPos)[xPos + 1].equals("E")) || (arrayList.get(yPos)[xPos + 1].equals("E") && keyNumber == keysNeeded)))
                    xPos++;
            if (direction == 180)
                if (yPos < yDim - 1 && ((!arrayList.get(yPos + 1)[xPos].equals("*") && !arrayList.get(yPos + 1)[xPos].equals("E")) || (arrayList.get(yPos + 1)[xPos].equals("E") && keyNumber == keysNeeded)))
                    yPos++;
            if (direction == 270)
                if (xPos > 0 && ((!arrayList.get(yPos)[xPos - 1].equals("*") && !arrayList.get(yPos)[xPos - 1].equals("E")) || (arrayList.get(yPos)[xPos - 1].equals("E") && keyNumber == keysNeeded)))
                    xPos--;
            moveCounter++;
            if (arrayList.get(yPos)[xPos].equals("E")) {
                mazeNumber++;
                setBoard();
                return;
            }
        }
        if (arrayList.get(yPos)[xPos].equals("S")) {
            repaint(0, 0, frame.getWidth(), frame.getHeight());
            return;
        }
        if (arrayList.get(yPos)[xPos].equals("P"))
            viewDistance++;
        if (arrayList.get(yPos)[xPos].equals("K"))
            keyNumber++;
        arrayList.get(yPos)[xPos] = ".";
        repaint(0, 0, frame.getWidth(), frame.getHeight());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
