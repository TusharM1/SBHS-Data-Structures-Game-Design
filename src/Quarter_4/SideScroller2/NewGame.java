package Quarter_4.SideScroller2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NewGame extends Application {

    boolean[] keys;

    char[][] level;

    int width, height;

    static int blockSize;

    Block[][] blocks;

    Hero hero;

    int heroX, heroY, heroVelocityX, heroVelocityY, gravity = 1, screenPosition;

    boolean canJump = false;

    @Override
    public void start(Stage primaryStage) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/Quarter_4/SideScroller2/layout.txt"));
        level = new char[lines.size()][lines.get(0).length()];
        for (int i = 0; i < level.length; i++)
            level[i] = lines.get(i).toCharArray();

        Group group = new Group();

        width = 800;
        height = 400;
        blockSize = height / level.length;

        heroX = (width - blockSize) / 2;

        hero = new Hero();
        heroX = (width - blockSize) / 2;
        hero.setLocation(heroX, heroY);

        group.getChildren().add(hero);

        keys = new boolean[3];

        blocks = new Block[level.length][level[0].length];
        for (int i = 0; i < level.length; i++)
            for (int j = 0; j < level[0].length; j++)
                if (level[i][j] == 'x') {
                    blocks[i][j] = new Block();
                    blocks[i][j].setLocation(j * blockSize, i * blockSize);
                    group.getChildren().add(blocks[i][j]);
                }

        Scene scene = new Scene(group, width, height);

        scene.addEventHandler(KeyEvent.ANY, event -> {
            System.out.println(event);
            if (event.getEventType().getName().equals("KEY_PRESSED")) {
                if (event.getText().equals("d"))
                    keys[0] = true;
                if (event.getText().equals("a"))
                    keys[1] = true;
                if (event.getText().equals("w"))
                    keys[2] = true;
            }
            else if (event.getEventType().getName().equals("KEY_RELEASED")) {
                if (event.getText().equals("d"))
                    keys[0] = false;
                if (event.getText().equals("a"))
                    keys[1] = false;
                if (event.getText().equals("w"))
                    keys[2] = false;
            }
        });

//        scene.addEventHandler(KeyEvent.ANY, event -> {
//            if (event.getEventType().getName().equals("KEY_PRESSED")) {
//                switch (event.getText()) {
//                    case "d":
//                        heroVelocityX = 5;
//                        break;
//                    case "a":
//                        heroVelocityX = -5;
//                        break;
//                    case "w":
//                        heroVelocityY = -blockSize / 5;
//                        break;
//                }
//            }
//            else if (event.getEventType().getName().equals("KEY_RELEASED")) {
//                if (event.getText().equals("d") || event.getText().equals("a")) {
//                    heroVelocityX = 0;
//                }
//            }
//        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Side Scroller");
        primaryStage.show();

        drawLayout();

        Animation animation = new Animation();
        animation.start();
    }

    void drawLayout() {
        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].setX(j * blockSize - screenPosition);
                }
            }
        }
//        for (int i = 0; i < blocks.length; i++) {
//            int screenStart = Math.max(Math.min(heroX - (width - blockSize) / 2, blocks[0].length * blockSize - width), 0);
//            for (int j = screenStart / blockSize; j < (screenStart + width) / blockSize; j++)
//                System.out.print((blocks[i][j] == null ? "." : "x") + " ");
//            System.out.println();
//        }
//        System.out.println();
    }

    private boolean collisionX() {
        for (int j = 0; j < blocks.length; j++) {
            for (int k = 0; k < blocks[0].length; k++) {
                Block block = blocks[j][k], next = new Block(hero.getX() + 1, hero.getY());
                if (block != null) {
                    if (Shape.intersect(hero, block).getBoundsInParent().isEmpty() && !Shape.intersect(next, block).getBoundsInParent().isEmpty() && heroVelocityX > 0) {
                        heroVelocityX = 0;
//                        System.out.println(j + " " + k);
                        return false;
                    }
                    next = new Block(hero.getX() - 1, hero.getY());
                    if (Shape.intersect(hero, block).getBoundsInParent().isEmpty() && !Shape.intersect(next, block).getBoundsInParent().isEmpty() && heroVelocityX < 0) {
                        heroVelocityX = 0;
//                        System.out.println(j + " " + k);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean collisionY() {
        for (int j = 0; j < blocks.length; j++) {
            int currentPosition = (int) ((hero.getX() + screenPosition) / blockSize);
            for (int k = 0; k < 1 + (heroX % blockSize != 0 && heroX < ((blocks[0].length - 1) * blockSize) ? 1 : 0); k++) {
//                        System.out.println(j + " " + (currentPosition + k));
                if (blocks[j][currentPosition + k] != null) {
                    if ((hero.getY() + blockSize == blocks[j][currentPosition + k].getY() && heroVelocityY >= 0) ||
                            (hero.getY() == blocks[j][currentPosition + k].getY() + blockSize && heroVelocityY <= 0)) {
                        heroVelocityY = 0;
//                        System.out.println(true);
                        canJump = true;
                        return false;
                    }
                }
            }
        }
        canJump = false;
        return true;
    }


    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {
            heroVelocityX = (keys[0] ? 5 : 0) + (keys[1] ? -5 : 0);
            if (keys[2] && canJump) {
                heroVelocityY += -blockSize / 5;
            }
            heroVelocityY += gravity;
            int x = 0, y = 0;
            do {
                if (x < Math.abs(heroVelocityX) && collisionX()) {
                    heroX = Math.max(Math.min(heroX + (int) Math.copySign(1, heroVelocityX), (blocks[0].length - 1) * blockSize), 0);
                    if (heroX < (width - blockSize) / 2) {
                        hero.setX(heroX);
                        screenPosition = 0;
                    }
                    else if (heroX > blocks[0].length * blockSize - width + (width - blockSize) / 2) {
                        hero.setX(heroX - (blocks[0].length * blockSize - width));
                        screenPosition = blocks[0].length * blockSize - width;
                    }
                    else {
                        hero.setX((width - blockSize) / 2);
                        screenPosition = heroX - (width - blockSize) / 2;
                    }
                }

                if (y < Math.abs(heroVelocityY) && collisionY()) {
                    hero.setY(hero.getY() + Math.copySign(1, heroVelocityY));
                }

                if (x < Math.abs(heroVelocityX))
                    x++;
                if (y < Math.abs(heroVelocityY))
                    y++;
            } while (x < Math.abs(heroVelocityX) || y < Math.abs(heroVelocityY));

//            heroVelocityY += gravity;
//            collisionY:
//            for (int i = 0; i < Math.abs(heroVelocityY); i++) {
//                for (int j = 0; j < blocks.length; j++) {
//                    int currentPosition = (int) ((hero.getX() + screenPosition) / blockSize);
//                    for (int k = 0; k < 1 + (heroX % blockSize != 0 && heroX < ((blocks[0].length - 1) * blockSize) ? 1 : 0); k++) {
////                        System.out.println(j + " " + (currentPosition + k));
//                        if (blocks[j][currentPosition + k] != null) {
//                            if ((hero.getY() + blockSize == blocks[j][currentPosition + k].getY() && heroVelocityY >= 0) ||
//                                    (hero.getY() == blocks[j][currentPosition + k].getY() + blockSize && heroVelocityY <= 0)) {
//                                heroVelocityY = 0;
//                                break collisionY;
//                            }
//                        }
//                    }
////                    System.out.println();
//                }
//                hero.setY(hero.getY() + Math.copySign(1, heroVelocityY));
//            }
//
//            collisionX:
//            for (int i = 0; i < Math.abs(heroVelocityX); i++) {
//                for (int j = 0; j < blocks.length; j++) {
//                    for (int k = 0; k < blocks[0].length; k++) {
//                        Block block = blocks[j][k], next = new Block(hero.getX() + 1, hero.getY());
//                        if (block != null) {
//                            if (Shape.intersect(hero, block).getBoundsInParent().isEmpty() && !Shape.intersect(next, block).getBoundsInParent().isEmpty() && heroVelocityX > 0) {
//                                heroVelocityX = 0;
//                                break collisionX;
//                            }
//                            next = new Block(hero.getX() - 1, hero.getY());
//                            if (Shape.intersect(hero, block).getBoundsInParent().isEmpty() && !Shape.intersect(next, block).getBoundsInParent().isEmpty() && heroVelocityX < 0) {
//                                heroVelocityX = 0;
//                                break collisionX;
//                            }
//                        }
//                    }
//                }
//
//                heroX = Math.max(Math.min(heroX + (int) Math.copySign(1, heroVelocityX), (blocks[0].length - 1) * blockSize), 0);
//                if (heroX < (width - blockSize) / 2) {
//                    hero.setX(heroX);
//                    screenPosition = 0;
//                }
//                else if (heroX > blocks[0].length * blockSize - width + (width - blockSize) / 2) {
//                    hero.setX(heroX - (blocks[0].length * blockSize - width));
//                    screenPosition = blocks[0].length * blockSize - width;
//                }
//                else {
//                    hero.setX((width - blockSize) / 2);
//                    screenPosition = heroX - (width - blockSize) / 2;
//                }
//            }
            
            drawLayout();
        }
    }

}
