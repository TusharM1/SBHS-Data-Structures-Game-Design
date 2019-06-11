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

    int velocityX = 5;

//    int acceleration, velocity;

    @Override
    public void init() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get("src/Quarter_4/SideScroller/layout.txt"));
        level = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < level.length; i++)
            level[i] = lines.get(i).toCharArray();
        width = 800;
        height = 400;
        blockSize = height / level.length;
        blockIndex = 0;

        blocks = new Block[level.length][level[0].length];
        for (int i = 0; i < level.length; i++) {
            for (int j = 0; j < level[0].length; j++) {
                if (level[i][j] == 'x')
                    blocks[i][j] = new Block();
            }
        }

        hero = new Hero();

        playerPositionX = (width - blockSize) / 2;

        hero.setLocation(playerPositionX, playerPositionY);
    }

    @Override
    public void start(Stage stage) {
        group = new Group();

        Scene scene = new Scene(group, width, height);

        scene.addEventHandler(KeyEvent.ANY, event -> {
            if (event.getEventType().getName().equals("KEY_PRESSED")) {
                if (event.getText().equals("d")) {
                    screenPosition += velocityX;
                    System.out.println(screenPosition);
//                    nextPosition = Math.max(Math.min(blockIndex + 1, level[0].length - width / blockSize), 0);
                    int nextPosition = Math.max(Math.min(screenPosition / blockSize, level[0].length - width / blockSize), 0);
                    if (nextPosition != blockIndex) {
                        blockIndex = nextPosition;
                        createLayout();
                    }
                }
                if (event.getText().equals("a")) {
                    screenPosition -= velocityX;
//                    nextPosition = Math.max(Math.min(blockIndex - 1, level[0].length - width / blockSize), 0);
                    int nextPosition = Math.max(Math.min(screenPosition / blockSize, level[0].length - width / blockSize), 0);
                    if (nextPosition != blockIndex) {
                        blockIndex = nextPosition;
                        createLayout();
                    }
                }
            }
        });

        stage.setScene(scene);
        stage.setTitle("Side Scroller");
        stage.show();

        createLayout();

        Animation animation = new Animation();
        animation.start();
    }

    private void drawLayout() {

    }

    private void createLayout() {
        group.getChildren().retainAll(hero);
        for (int i = 0; i < level.length; i++) {
            for (int k = Math.max(Math.min(blockIndex, level[0].length - width / blockSize), 0), j = k; j < width / blockSize + blockIndex && j < level[0].length; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].setLocation(j * blockSize, i * blockSize);
                    group.getChildren().add(blocks[i][j]);
                }
            }
        }
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
    }

    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {
//            if (nextPosition != blockIndex) {
//                blockIndex = nextPosition;
//                createLayout();
//            }
        }
    }

}
