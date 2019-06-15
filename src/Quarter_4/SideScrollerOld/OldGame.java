package Quarter_4.SideScrollerOld;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class OldGame extends Application {

    Group group;
//    Scene scene;

    char[][] level;

    int width, height;

    int blockIndex, screenPosition, playerPositionX, playerPositionY;

    static int blockSize;

    Block[][] blocks;

    Hero hero;

    double velocityX = 0, velocityY = 0;
    double accelerationY = 1;

//    int acceleration, velocity;

    @Override
    public void init() throws Exception {
        // READ IN LEVEL
        List<String> lines = Files.readAllLines(Paths.get("src/Quarter_4/SideScrollerOld/layout.txt"));
        level = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < level.length; i++)
            level[i] = lines.get(i).toCharArray();

        // SET ATTRIBUTES
        width = 800;
        height = 400;
        blockSize = height / level.length;
//        blockIndex = 0;

        hero = new Hero();
        playerPositionX = (width - blockSize) / 2;
//        playerPositionX = 600;
        hero.setLocation(playerPositionX, playerPositionY);
    }

//    boolean update = false;


    @Override
    public void start(Stage stage) {
        group = new Group();
        // CREATE AND ADD ALL BLOCKS
        blocks = new Block[level.length][level[0].length];
        for (int i = 0; i < level.length; i++)
            for (int j = 0; j < level[0].length; j++)
                if (level[i][j] == 'x') {
                    blocks[i][j] = new Block();
                    blocks[i][j].setLocation(j * blockSize, i * blockSize);
                    group.getChildren().add(blocks[i][j]);
                }

        group.getChildren().add(hero);

        Scene scene = new Scene(group, width, height);

        scene.addEventHandler(KeyEvent.ANY, event -> {
            if (event.getEventType().getName().equals("KEY_PRESSED")) {
                switch (event.getText()) {
                    case "d":
                        velocityX = 5;
                        break;
                    case "a":
                        velocityX = -5;
                        break;
                    case "w":
                        velocityY = -blockSize / 5;
                        break;
                }
            }
            else if (event.getEventType().getName().equals("KEY_RELEASED")) {
                if (event.getText().equals("d") || event.getText().equals("a")) {
                    velocityX = 0;
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Side Scroller");
        stage.show();

        drawLayout();

//        System.out.println(blocks.length + " " + blocks[0].length);

        Animation animation = new Animation();
        animation.start();
    }

    private void drawLayout() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].setX(j * blockSize - screenPosition);
                }
            }
        }
    }

    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {

//            System.out.println(hero.getX());

//            System.out.println(playerPositionX / blockSize + " " + (int) hero.getY() / blockSize);
//            if (hero.getX() / blockSize < blocks[0].length)
//            System.out.println(hero.getX() + " " + playerPositionX);








//            for (int i = 0; i < blocks.length; i++) {
//                if (playerPositionX > blocks[0].length * blockSize - (width - blockSize) / 2) {
//                    if (playerPositionX / blockSize - 2 > 0)
//                        System.out.print((blocks[i][playerPositionX / blockSize - 2] != null ? "X" : ".") + "\t");
//                    else
//                        System.out.print(" \t");
//                    System.out.print((blocks[i][playerPositionX / blockSize - 1] != null ? "X" : ".") + "\t");
//                    if (playerPositionX / blockSize < blocks[0].length)
//                        System.out.print(blocks[i][playerPositionX / blockSize] != null ? "X" : ".");
//                    else
//                        System.out.print(" \t");
//                    System.out.println();
//                }
//                else {
//                    if (playerPositionX / blockSize > 0)
//                        System.out.print((blocks[i][playerPositionX / blockSize - 1] != null ? "X" : ".") + "\t");
//                    else
//                        System.out.print(" \t");
//                    System.out.print((blocks[i][playerPositionX / blockSize] != null ? "X" : ".") + "\t");
//                    if (playerPositionX / blockSize + 1 < blocks[0].length)
//                        System.out.print(blocks[i][playerPositionX / blockSize + 1] != null ? "X" : ".");
//                    else
//                        System.out.print(" \t");
//                    System.out.println();
//                }
////                System.out.println((blocks[i][playerPositionX / blockSize - 1] != null ? "X" : ".") + "\t" + (blocks[i][playerPositionX / blockSize] != null ? "X" : ".") + "\t" + (blocks[i][playerPositionX / blockSize + 1] != null ? "X" : "."));
//
//            }
//            System.out.println();

//            System.out.println(playerPositionX);


//            if (bitch == 1)
//                System.out.println(hero.getX() + " " + playerPositionX + " " + screenPosition);

            collisionX:
            for (int j = 0; j < Math.abs(velocityX); j++) {

                for (int i = 0; i < (hero.getY() > 0 ? 1 + (hero.getY() % blockSize != 0 ? 1 : 0) : 0); i++) {
//            for (int i = 0; i < blocks.length; i++) {
                    Block block;
                    if (playerPositionX > blocks[0].length * blockSize - (width - blockSize) / 2) {
                        if (playerPositionX / blockSize - 2 > 0) {
                            block = blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize - 2];
                            if (block != null) {
                                System.out.print("X");
                                block.setFill(Color.BLUE);
                                if ((hero.getX() + blockSize == block.getX() && velocityX > 0) || (hero.getX() == block.getX() + blockSize && velocityX < 0)) {
                                    System.out.print("a");
                                    break collisionX;
                                }
                            }
                            else
                                System.out.print(".");
                            System.out.print("\t");
//                        System.out.print((block != null ? "X" : ".") + "\t");
                        }
                        else
                            System.out.print(" \t");
                        block = blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize - 1];
//                    System.out.print((block != null ? "X" : ".") + "\t");
                        if (block != null) {
                            System.out.print("X");
                            block.setFill(Color.BLUE);
                            if ((hero.getX() + blockSize == block.getX() && velocityX > 0) || (hero.getX() == block.getX() + blockSize && velocityX < 0)) {
                                System.out.print("b");
                                break collisionX;
                            }
                        }
                        else
                            System.out.print(".");
                        System.out.print("\t");
                        if (playerPositionX / blockSize < blocks[0].length) {
                            block = blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize];
//                        System.out.print(block != null ? "X" : ".");
                            if (block != null) {
                                System.out.print("X");
                                block.setFill(Color.BLUE);
                                if ((hero.getX() + blockSize == block.getX() && velocityX > 0) || (hero.getX() == block.getX() + blockSize && velocityX < 0)) {
                                    System.out.print("c");
                                    break collisionX;
                                }
                            }
                            else
                                System.out.print(".");
                            System.out.print("\t");
                        }
                        else
                            System.out.print(" \t");
                        System.out.println();
                    }
                    else {
                        if (playerPositionX / blockSize > 0) {
                            block = blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize - 1];
//                        System.out.print((block != null ? "X" : ".") + "\t");
                            if (block != null) {
                                System.out.print("X");
                                block.setFill(Color.BLUE);
                                if ((hero.getX() + blockSize == block.getX() && velocityX >= 0) || (hero.getX() == block.getX() + blockSize && velocityX <= 0)) {
                                    velocityX = 0;
                                    System.out.print("d");
                                    break collisionX;
                                }
                            }
                            else
                                System.out.print(".");
                            System.out.print("\t");
                        }
                        else
                            System.out.print(" \t");
                        block = blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize];
//                    System.out.print((block != null ? "X" : ".") + "\t");
                        if (block != null) {
                            System.out.print("X");
                            block.setFill(Color.BLUE);
                            if ((hero.getX() + blockSize == block.getX() && velocityX >= 0) || (hero.getX() == block.getX() + blockSize && velocityX <= 0)) {
                                velocityX = 0;
                                System.out.print("e");
                                break collisionX;
                            }
                        }
                        else
                            System.out.print(".");
                        System.out.print("\t");
                        if (playerPositionX / blockSize + 1 < blocks[0].length) {
                            block = blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize + 1];
//                        System.out.print(block != null ? "X" : ".");
                            if (block != null) {
                                System.out.print("X");
                                block.setFill(Color.BLUE);
//                                System.out.print((hero.getX() + blockSize) + " " + block.getX() + " " + (velocityX > 0));
                                System.out.print(hero.getX() + blockSize >= block.getX() && velocityX > 0);
                                System.out.print(hero.getX() <= block.getX() + blockSize && velocityX < 0);
                                if ((hero.getX() + blockSize >= block.getX() && velocityX > 0) || (hero.getX() <= block.getX() + blockSize && velocityX < 0)) {
                                    velocityX = 0;
//                                    System.out.println("here");
                                    System.out.print("f");
                                    break collisionX;
                                }
                            }
                            else
                                System.out.print(".");
                            System.out.print("\t");
                        }
                        else
                            System.out.print(" \t");
                        System.out.println();
                    }
//                System.out.println((blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize - 1] != null ? "X" : ".") + "\t" + (blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize] != null ? "X" : ".") + "\t" + (blocks[(int) (hero.getY() / blockSize) + i][playerPositionX / blockSize + 1] != null ? "X" : "."));

                }
                System.out.println();


//                Block block;

//                for (int j = 0; j < blocks.length; j++) {
//                    Block block;
//                    if (playerPositionX > blocks[0].length * blockSize - (width - blockSize) / 2) {
//                        block = blocks[j][playerPositionX / blockSize - 2];
//                        if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize))
//                            block.setFill(Color.BLUE);
//                        if (playerPositionX / blockSize < blocks[0].length) {
//                            block = blocks[j][playerPositionX / blockSize];
//                            if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize))
//                                block.setFill(Color.BLUE);
//                        }
//                    }
//                    else {
//                        if (playerPositionX / blockSize > 0) {
//                            block = blocks[j][playerPositionX / blockSize - 1];
//                            if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize))
//                                block.setFill(Color.BLUE);
//                        }
//                        if (playerPositionX / blockSize + 1 < blocks[0].length) {
//                            block = blocks[j][playerPositionX / blockSize + 1];
//                            if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize))
//                                block.setFill(Color.BLUE);
//                        }
//                    }
//                }

                // Check Left Side
//                if (playerPositionX / blockSize != 0) {
//                    // Left Top
//                    block = blocks[(int) (hero.getY() / blockSize)][playerPositionX / blockSize - 1];
////                    System.out.println((int) (hero.getY() / blockSize) + " " + (playerPositionX / blockSize - 1));
//                    if (block != null && hero.getX() == block.getX() + blockSize)
//                        block.setFill(Color.BLUE);
//                    block = blocks[(int) (hero.getY() / blockSize)][playerPositionX / blockSize];
////                    System.out.println((int) (hero.getY() / blockSize) + " " + (playerPositionX / blockSize - 1));
//                    if (block != null && hero.getX() == block.getX() + blockSize)
//                        block.setFill(Color.BLUE);
//                    if (playerPositionX / blockSize + 1 < blocks[0].length - 2)
//                    block = blocks[(int) (hero.getY() / blockSize)][playerPositionX / blockSize + 1];
////                    System.out.println((int) (hero.getY() / blockSize) + " " + (playerPositionX / blockSize - 1));
//                    if (block != null && hero.getX() == block.getX() + blockSize)
//                        block.setFill(Color.BLUE);
//                }

//                Block block;
//                if (playerPositionX / blockSize != 0) {
//                    block = blocks[(int) hero.getY() / blockSize][playerPositionX / blockSize - 1];
//                    if (block != null && ((hero.getX() + blockSize == block.getX() && velocityX > 0) || (hero.getX() == block.getX() + blockSize && velocityX < 0)))
//                        break;
//                    if ((int) hero.getY() / blockSize < blocks.length - 1 && hero.getY() % blockSize != 0) {
//                        block = blocks[(int) hero.getY() / blockSize + 1][playerPositionX / blockSize - 1];
//                        if (block != null && ((hero.getX() + blockSize == block.getX() && velocityX > 0) || (hero.getX() == block.getX() + blockSize && velocityX < 0)))
//                            break;
//                    }
//                }
//                if (playerPositionX / blockSize < blocks[0].length - 1) {
//                    block = blocks[(int) hero.getY() / blockSize][playerPositionX / blockSize + 1];
//                    if (block != null && ((hero.getX() + blockSize == block.getX() && velocityX > 0) || (hero.getX() == block.getX() + blockSize && velocityX < 0)))
//                        break;
//                    if ((int) hero.getY() / blockSize < blocks.length - 1 && hero.getY() % blockSize != 0) {
//                        block = blocks[(int) hero.getY() / blockSize + 1][playerPositionX / blockSize + 1];
//                        if (block != null && ((hero.getX() + blockSize == block.getX() && velocityX > 0) || (hero.getX() == block.getX() + blockSize && velocityX < 0)))
//                            break;
//                    }
//                }

                playerPositionX = (int) Math.max(Math.min(playerPositionX + Math.copySign(1, velocityX), blocks[0].length * blockSize), 0);
                if (playerPositionX >= (width - blockSize) / 2 && playerPositionX <= blocks[0].length * blockSize - (width - blockSize) / 2) {
                    hero.setX((width - blockSize) / 2);
                    screenPosition = Math.max(Math.min((int) (screenPosition + Math.copySign(1, velocityX)), level[0].length * blockSize - width), 0);
                }
                else if (playerPositionX < (width - blockSize) / 2)
                    hero.setX(playerPositionX);
                                                // full length - center start
                else if (playerPositionX > blocks[0].length * blockSize - (width - blockSize) / 2) //{
//                    hero.setX(playerPositionX - (blocks[0].length * blockSize - width));
//                    System.out.println("kjbefkw");
//                }
                    hero.setX((playerPositionX - (blocks[0].length * blockSize - width + blockSize)));

            }


//                Block block;
//                if (playerPositionX % blockSize == 0) {
//                if (playerPositionX / blockSize - 1 > 0) {
//                    block = blocks[(int) hero.getY() / blockSize][playerPositionX / blockSize - 1];
//                    if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize)) {
//                        block.setFill(Color.BLUE);
//                        System.out.println("a");
//                        velocityX = 0;
//                        break;
//                    }
//                    if (hero.getY() / blockSize % 1 != 0) {
//                        block = blocks[(int) hero.getY() / blockSize + 1][playerPositionX / blockSize - 1];
//                        if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize)) {
//                            block.setFill(Color.BLUE);
//                            System.out.println("b");
//                            velocityX = 0;
//                            break;
//                        }
//                    }
//                }
//                if (playerPositionX / blockSize + 1 < blocks[0].length) {
//                    block = blocks[(int) hero.getY() / blockSize][playerPositionX / blockSize + 1];
//                    if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize)) {
//                        block.setFill(Color.BLUE);
//                        System.out.println("c");
//                        velocityX = 0;
//                        break;
//                    }
//                    if (hero.getY() / blockSize % 1 != 0) {
//                        block = blocks[(int) hero.getY() / blockSize + 1][playerPositionX / blockSize + 1];
//                        if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize)) {
//                            block.setFill(Color.BLUE);
//                            System.out.println("d");
//                            velocityX = 0;
//                            break;
//                        }
//                    }
//                }





//                }
//                else {
//                    if (playerPositionX / blockSize < blocks[0].length - 1) {
//                        block = blocks[(int) hero.getY() / blockSize][playerPositionX / blockSize + 1];
//                        if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize)) {
//                            block.setFill(Color.BLUE);
//                            velocityX = 0;
//                            break;
//                        }
//                        if (hero.getY() / blockSize < blocks.length - 1) {
//                            block = blocks[(int) hero.getY() / blockSize + 1][playerPositionX / blockSize + 1];
//                            if (block != null && (hero.getX() + blockSize == block.getX() || hero.getX() == block.getX() + blockSize)) {
//                                block.setFill(Color.BLUE);
//                                velocityX = 0;
//                                break;
//                            }
//                        }
//                    }
////                }
//                playerPositionX = (int) Math.max(Math.min(playerPositionX + Math.copySign(1, velocityX), blocks[0].length * blockSize), 0);
//                if (playerPositionX >= (width - blockSize) / 2 && playerPositionX <= blocks[0].length * blockSize - (width - blockSize) / 2) {
//                    hero.setX((width - blockSize) / 2);
//                    screenPosition = Math.max(Math.min((int) (screenPosition + Math.copySign(1, velocityX)), level[0].length * blockSize - width), 0);
//                } else if (playerPositionX < (width - blockSize) / 2)
//                    hero.setX(playerPositionX);
//                else if (playerPositionX > blocks[0].length * blockSize - (width - blockSize) / 2)
//                    hero.setX((playerPositionX - (blocks[0].length * blockSize - width + blockSize)));
//            }

//            if (velocityX != 0) {
//                playerPositionX = (int) Math.max(Math.min(playerPositionX + velocityX, blocks[0].length * blockSize), 0);
//                if (playerPositionX >= (width - blockSize) / 2 && playerPositionX <= blocks[0].length * blockSize - (width - blockSize) / 2) {
//                    hero.setX((width - blockSize) / 2);
//                    screenPosition = Math.max(Math.min((int) (screenPosition + velocityX), level[0].length * blockSize - width), 0);
//                } else if (playerPositionX < (width - blockSize) / 2)
//                    hero.setX(playerPositionX);
//                else if (playerPositionX > blocks[0].length * blockSize - (width - blockSize) / 2)
//                    hero.setX((playerPositionX - (blocks[0].length * blockSize - width + blockSize)));
//            }
//                else {d
//                    System.out.println(hero.getX());
//                    hero.setX(playerPositionX - (blocks[0].length * blockSize - width));
//                }
//                System.out.println(playerPositionX);
//                if (screenPosition <= 0 && hero.getX() <= (width - blockSize) / 2.0)
//                    playerPositionX += velocityX;
//                else
//                    screenPosition += velocityX;
//                if (screenPosition == 0) {
//                    if (playerPositionX <= (width - blockSize) / 2) {
//                        playerPositionX += velocityX;
//                        hero.setX(playerPositionX);
//                    }
////                    hero.setX(hero.getX() + velocityX);
////                    playerPositionX += velocityX;
//                    else
//                        screenPosition = Math.max(Math.min((int) (screenPosition + velocityX), level[0].length * blockSize - width), 0);
////                        screenPosition += velocityX;
//                }
//                screenPosition = Math.max(Math.min((int) (screenPosition + velocityX), level[0].length * blockSize - width), 0);
////                    screenPosition += velocityX;
//                System.out.println(hero.getX() + " " + (width - blockSize) / 2.0);
//                System.out.println(hero.getX());
//                System.out.println((width - blockSize) / 2.0);
//                System.out.println(hero.getX() <= (width - blockSize) / 2.0 || hero.getX() >= blocks[0].length * blockSize - (width - blockSize) / 2.0);
//                if (!(hero.getX() > (width - blockSize) / 2.0 && hero.getX() < blocks[0].length * blockSize - (width - blockSize) / 2.0))
//                if (hero.getX() <= (width - blockSize) / 2.0 || hero.getX() >= blocks[0].length * blockSize - (width - blockSize) / 2.0)
//                    hero.setX(hero.getX() + velocityX);
////            }
//            if (velocityY != 0)
//                hero.setY(hero.getY() + velocityY);



//            screenPosition = Math.max(Math.min((int) (screenPosition + velocityX), level[0].length * blockSize - width), 0);
//            // COLLISION FOR Y
            velocityY += accelerationY;
            collisionY:
            for (int i = 0; i < Math.abs(velocityY); i++) {
                for (int j = 0; j < blocks.length; j++) {
                    int currentPosition = (int) ((hero.getX() + screenPosition) / blockSize);
                    for (int k = 0; k < 1 + (playerPositionX % blockSize != 0 && playerPositionX < ((blocks[0].length - 1) * blockSize) ? 1 : 0); k++) {
//                        System.out.println(j + " " + (currentPosition + k));
                        if (blocks[j][currentPosition + k] != null) {
                            if ((hero.getY() + blockSize == blocks[j][currentPosition + k].getY() && velocityY >= 0) ||
                                    (hero.getY() == blocks[j][currentPosition + k].getY() + blockSize && velocityY <= 0)) {
                                velocityY = 0;
                                break collisionY;
                            }
                        }
                    }
//                    System.out.println();
                }
                hero.setY(hero.getY() + Math.copySign(1, velocityY));
            }

//            collisionX:
//            for (int i = 0; i < Math.abs(velocityX); i++) {
//                for (int j = 0; j < blocks.length; j++) {
//                    int currentPosition = Math.min((int) ((hero.getX() + screenPosition) / blockSize), blocks[0].length);
//                    for (int k = 0; k < 1 + (playerPositionX % blockSize != 0 && playerPositionX < ((blocks[0].length - 1) * blockSize) ? 1 : 0); k++) {
//                        if (blocks[j][currentPosition + k] != null) {
////                            System.out.println(j + " " + (currentPosition + k));
//                            if ((hero.getX() + blockSize == blocks[j][currentPosition + k].getX() && velocityX >= 0) ||
//                                    (hero.getX() == blocks[j][currentPosition + k].getX() + blockSize && velocityX <= 0)) {
//                                velocityX = 0;
//                                System.out.println("collisionX");
//                                break collisionX;
//                            }
//                        }
//                    }
////                    System.out.println();
//                }
//                playerPositionX = (int) Math.max(Math.min(playerPositionX + Math.copySign(1, velocityX), blocks[0].length * blockSize), 0);
//                if (playerPositionX >= (width - blockSize) / 2 && playerPositionX <= blocks[0].length * blockSize - (width - blockSize) / 2) {
//                    hero.setX((width - blockSize) / 2);
//                    screenPosition = Math.max(Math.min((int) (screenPosition + Math.copySign(1, velocityX)), level[0].length * blockSize - width), 0);
//                }
//                else if (playerPositionX < (width - blockSize) / 2)
//                    hero.setX(playerPositionX);
//                else if (playerPositionX > blocks[0].length * blockSize - (width - blockSize) / 2)
//                    hero.setX((playerPositionX - (blocks[0].length * blockSize - width + blockSize)));
//            }



//
//            if (hero.getX() > (width - blockSize) / 2.0 && hero.getX() < blocks[0].length * blockSize - (width - blockSize) / 2.0)
//                System.out.println(true);


//            collisionX:
//            for (int i = 0; i < Math.abs(velocityX); i++) {
//                for (int j = 0; j < blocks[0].length - 1 ; j++) {
//                    int currentPosition = (int) (hero.getY() / blockSize);
//                    for (int k = 0; k < 1 + (hero.getY() % blockSize != 0 && hero.getY() < ((blocks.length - 1) * blockSize) ? 1 : 0); k++) {
//                        if (blocks[currentPosition][j + k] != null) {
//                            if ((hero.getX() + blockSize == blocks[currentPosition][j + k].getX() && velocityX >= 0) ||
//                                    (hero.getX() == blocks[currentPosition][j + k].getX() + blockSize && velocityX <= 0)) {
//                                velocityX = 0;
//                                break collisionX;
//                            }
//                        }
//                    }
//                }
////                screenPosition = Math.max(Math.min((int) (screenPosition + Math.copySign(1, velocityX)), (level[0].length - 1) * blockSize), 0);
//                hero.setX(hero.getX() + Math.copySign(1, velocityX));
//            }

//            screenPosition = Math.max(Math.min((int) (screenPosition + Math.copySign(1, velocityX)), level[0].length * blockSize - width), 0);

            drawLayout();
        }
    }

}
