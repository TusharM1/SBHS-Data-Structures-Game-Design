package Quarter_4.SideScroller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Game extends Application {

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
        List<String> lines = Files.readAllLines(Paths.get("src/Quarter_4/SideScroller/layout.txt"));
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
                if (event.getText().equals("d")) {
//                    update = true;
//                    screenPosition += velocityX;
                    velocityX = 5;
//                    System.out.println(screenPosition);
//                    drawLayout();
//                    System.out.println(screenPosition);
////                    nextPosition = Math.max(Math.min(blockIndex + 1, level[0].length - width / blockSize), 0);
//                    int nextPosition = Math.max(Math.min(screenPosition / blockSize, level[0].length - width / blockSize), 0);
//                    if (nextPosition != blockIndex) {
//                        blockIndex = nextPosition;
////                        createLayout();
//                    }
                }
                if (event.getText().equals("a")) {
//                    screenPosition -= velocityX;
                    velocityX = -5;
//                    drawLayout();
////                    nextPosition = Math.max(Math.min(blockIndex - 1, level[0].length - width / blockSize), 0);
//                    int nextPosition = Math.max(Math.min(screenPosition / blockSize, level[0].length - width / blockSize), 0);
//                    if (nextPosition != blockIndex) {
//                        blockIndex = nextPosition;
////                        createLayout();
//                    }
                }
                if (event.getText().equals(" "))
                    velocityY = -10;
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

//        createLayout();

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

//    private void createLayout() {
//        group.getChildren().retainAll(hero);
//        for (int i = 0; i < level.length; i++) {
//            for (int k = Math.max(Math.min(blockIndex, level[0].length - width / blockSize), 0), j = k; j < width / blockSize + blockIndex && j < level[0].length; j++) {
//                if (blocks[i][j] != null) {
//                    blocks[i][j].setLocation(j * blockSize, i * blockSize);
//                    group.getChildren().add(blocks[i][j]);
//                }
//            }
//        }
//        System.out.println(screenPosition);
//        group.getChildren().clear();
//        group.getChildren().add(hero);
//        for (int i = 0; i < level.length; i++) {
//            for (int k = Math.max(Math.min(blockIndex, level[0].length - width / blockSize), 0), j = k; j < width / blockSize + blockIndex && j < level[0].length; j++) {
//                if (blocks[i][j] != null) {
//                    blocks[i][j].setLocation((screenPosition + width) % width + j * blockSize, i * blockSize);
//                    group.getChildren().add(blocks[i][j]);
//                }
//            }
//        }
//    }

    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {
            screenPosition = Math.max(Math.min((int) (screenPosition + velocityX), level[0].length * blockSize - width), 0);
            // COLLISION FOR Y
            velocityY += accelerationY;
            System.out.println(velocityY);
            draw:
            for (int i = 0; i < Math.abs(velocityY); i++) {
                for (int j = 0; j < blocks.length; j++) {
                    int currentPosition = (int) ((hero.getX() + screenPosition) / blockSize);
                    for (int k = 0; k < 1 + (screenPosition % blockSize != 0 && screenPosition < ((blocks[0].length - 1) * blockSize) ? 1 : 0); k++) {
                        if (blocks[j][currentPosition + k] != null) {
                            if ((hero.getY() + blockSize == blocks[j][currentPosition + k].getY() && velocityY >= 0) ||
                                    (hero.getY() == blocks[j][currentPosition + k].getY() + blockSize && velocityY <= 0)) {
                                velocityY = 0;
                                break draw;
                            }
                        }
                    }
//                        if (hero.getY() + blockSize + 1 < blocks[j][currentPosition].getY() && hero.getY() + 1 > blocks[j][currentPosition].getY())
//                            hero.setY(blocks[j][currentPosition].getY() - blockSize);
//                        if (hero.getY() + blockSize + 1 < blocks[j][currentPosition].getY() && hero.getY() + 1 > blocks[j][currentPosition].getY())
//                            hero.setY(blocks[j][currentPosition].getY() - blockSize);
//                        else
//                            hero.setY(hero.getY() + Math.copySign(1, velocityX));

                }
                hero.setY(hero.getY() + Math.copySign(1, velocityY));
            }

//            hero.setY(hero.getY() + velocityY);
            drawLayout();
//            if (update)
//                screenPosition += 1;
//            if (nextPosition != blockIndex) {
//                blockIndex = nextPosition;
//                createLayout();
//            }
        }
    }

}
