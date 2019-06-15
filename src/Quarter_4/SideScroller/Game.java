package Quarter_4.SideScroller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Game extends Application {

    boolean[] keys;

    char[][] level;

    int width, height;

    static int blockSize;

    Block[][] blocks;

    Hero hero;

    int heroX, heroY, heroVelocityX, heroVelocityY, gravity = 1, screenPosition;

    boolean canJump = false;

    ImageView background1, background2;
    ImageView clouds1, clouds2;
    ImageView mountains1, mountains2;
    ImageView trees1, trees2;

    int lastX;

    @Override
    public void start(Stage primaryStage) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/Quarter_4/SideScroller/layout.txt"));
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
        lastX = heroX;
        hero.setLocation(heroX, heroY);

        background1 = new ImageView(new Image("Quarter_4/SideScroller/Background/background.png"));
        background2 = new ImageView(new Image("Quarter_4/SideScroller/Background/background.png"));
        background1.setFitWidth(width);
        background2.setFitWidth(width);
        background1.setFitHeight(height);
        background2.setFitHeight(height);
        background2.setX(width);

        clouds1 = new ImageView(new Image("Quarter_4/SideScroller/Background/clouds.png"));
        clouds2 = new ImageView(new Image("Quarter_4/SideScroller/Background/clouds.png"));
        clouds1.setFitWidth(width);
        clouds2.setFitWidth(width);
        clouds1.setFitHeight(height);
        clouds2.setFitHeight(height);
        clouds2.setX(width);

        mountains1 = new ImageView(new Image("Quarter_4/SideScroller/Background/mountains.png"));
        mountains2 = new ImageView(new Image("Quarter_4/SideScroller/Background/mountains.png"));
        mountains1.setFitWidth(width);
        mountains2.setFitWidth(width);
        mountains1.setFitHeight(height);
        mountains2.setFitHeight(height);
        mountains2.setX(width);

        trees1 = new ImageView(new Image("Quarter_4/SideScroller/Background/trees.png"));
        trees2 = new ImageView(new Image("Quarter_4/SideScroller/Background/trees.png"));
        trees1.setFitWidth(width);
        trees2.setFitWidth(width);
        trees1.setFitHeight(height);
        trees2.setFitHeight(height);
        trees2.setX(width);

        group.getChildren().addAll(background1, background2, clouds1, clouds2, mountains1, mountains2, trees1, trees2);
//        group.getChildren().addAll(clouds1, clouds2, mountains1, mountains2, trees1, trees2);
//        group.getChildren().addAll(trees1, trees2);
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
            if (event.getCode() == KeyCode.RIGHT)
                keys[0] = event.getEventType() != KeyEvent.KEY_RELEASED;
            if (event.getCode() == KeyCode.LEFT)
                keys[1] = event.getEventType() != KeyEvent.KEY_RELEASED;
            if (event.getCode() == KeyCode.UP)
                keys[2] = event.getEventType() != KeyEvent.KEY_RELEASED;
            if (event.getCode() == KeyCode.SPACE) {
                heroX = lastX = (width - blockSize) / 2;
                hero.setLocation(heroX, heroY);
                background1.setX(0);
                background2.setX(width);
                clouds1.setX(0);
                clouds2.setX(width);
                mountains1.setX(0);
                mountains2.setX(width);
                trees1.setX(0);
                trees2.setX(width);
                drawLayout();
            }
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Side Scroller");
        primaryStage.show();

        drawLayout();

        Animation animation = new Animation();
        animation.start();
    }

    void drawLayout() {
        int parallax = (keys[0] ? 1 : 0) + (keys[1] ? -1 : 0);

        if (Math.abs(lastX - heroX) > 0.5 && heroX > (width - blockSize) / 2 && heroX < blocks[0].length * blockSize - width + (width - blockSize) / 2) {
            background1.setX(background1.getX() - parallax);
            background2.setX(background2.getX() - parallax);
            if (background1.getX() < -width)
                background1.setX(width);
            if (background1.getX() > width)
                background1.setX(-width);
            if (background2.getX() < -width)
                background2.setX(width);
            if (background2.getX() > width)
                background2.setX(-width);

            clouds1.setX(clouds1.getX() - 2 * parallax);
            clouds2.setX(clouds2.getX() - 2 * parallax);
            if (clouds1.getX() < -width)
                clouds1.setX(width);
            if (clouds1.getX() > width)
                clouds1.setX(-width);
            if (clouds2.getX() < -width)
                clouds2.setX(width);
            if (clouds2.getX() > width)
                clouds2.setX(-width);

            mountains1.setX(mountains1.getX() - 3 * parallax);
            mountains2.setX(mountains2.getX() - 3 * parallax);
            if (mountains1.getX() < -width)
                mountains1.setX(width);
            if (mountains1.getX() > width)
                mountains1.setX(-width);
            if (mountains2.getX() < -width)
                mountains2.setX(width);
            if (mountains2.getX() > width)
                mountains2.setX(-width);

            trees1.setX(trees1.getX() - 4 * parallax);
            trees2.setX(trees2.getX() - 4 * parallax);
            if (trees1.getX() < -width)
                trees1.setX(width);
            if (trees1.getX() > width)
                trees1.setX(-width);
            if (trees2.getX() < -width)
                trees2.setX(width);
            if (trees2.getX() > width)
                trees2.setX(-width);
        }

        for (int i = 0; i < blocks.length; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                if (blocks[i][j] != null) {
                    blocks[i][j].setX(j * blockSize - screenPosition);
                }
            }
        }
    }

    boolean canMoveLeft = true, canMoveRight = true;

    private boolean collisionX() {
        System.out.println(hero.getY());
        for (int j = 0; j < blocks.length; j++) {
            for (int k = 0; k < blocks[0].length; k++) {
                Block block = blocks[j][k], next = new Block(hero.getX() + 1, hero.getY());
                if (block != null) {
                    if (!Shape.intersect(next, block).getBoundsInParent().isEmpty() && heroVelocityX > 0) {
                        heroVelocityX = 0;
                        keys[0] = false;
                        while (canMoveRight && !Shape.intersect(hero, block).getBoundsInParent().isEmpty()) {
                            heroX--;
                            hero.setX(hero.getX() - 1);
                        }
                        canMoveRight = false;
                        return false;
                    }
                    next = new Block(hero.getX() - 1, hero.getY());
                    if (!Shape.intersect(next, block).getBoundsInParent().isEmpty() && heroVelocityX < 0) {
                        heroVelocityX = 0;
                        keys[1] = false;
                        while (canMoveLeft && !Shape.intersect(hero, block).getBoundsInParent().isEmpty()) {
                            heroX++;
                            hero.setX(hero.getX() + 1);
                        }
                        canMoveLeft = false;
                        return false;
                    }
                }
            }
        }
        canMoveLeft = canMoveRight = true;
        return true;
    }

    private boolean collisionY() {
        for (int j = 0; j < blocks.length; j++) {
            int currentPosition = (int) ((hero.getX() + screenPosition) / blockSize);
            for (int k = 0; k < 1 + (heroX % blockSize != 0 && heroX < ((blocks[0].length - 1) * blockSize) ? 1 : 0); k++) {
                if (blocks[j][currentPosition + k] != null) {
                    if (hero.getY() + blockSize == blocks[j][currentPosition + k].getY() && heroVelocityY >= 0) {
                        heroVelocityY = 0;
                        canJump = true;
                        return false;
                    }
                    else if (hero.getY() == blocks[j][currentPosition + k].getY() + blockSize && heroVelocityY <= 0) {
                        heroVelocityY = 0;
                        canJump = false;
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
            if (keys[2] && canJump)
                heroVelocityY -= Math.sqrt(3 * blockSize);
            heroVelocityY += gravity;
            int x = 0, y = 0;
            do {
                if (x < Math.abs(heroVelocityX) || keys[0]) {
                    if (collisionX()) {
                        lastX = heroX;
                        heroX = Math.max(Math.min(heroX + (int) Math.copySign(1, heroVelocityX), (blocks[0].length - 1) * blockSize), 0);
                        if (heroX < (width - blockSize) / 2) {
                            hero.setX(heroX);
                            screenPosition = 0;
                        } else if (heroX > blocks[0].length * blockSize - width + (width - blockSize) / 2) {
                            hero.setX(heroX - (blocks[0].length * blockSize - width));
                            screenPosition = blocks[0].length * blockSize - width;
                        } else {
                            hero.setX((width - blockSize) / 2);
                            screenPosition = heroX - (width - blockSize) / 2;
                        }
                    }
                    else
                        heroVelocityX = 0;
                }

                if (y < Math.abs(heroVelocityY) && collisionY())
                    hero.setY(hero.getY() + Math.copySign(1, heroVelocityY));

                if (x < Math.abs(heroVelocityX))
                    x++;
                if (y < Math.abs(heroVelocityY))
                    y++;
            } while (x < Math.abs(heroVelocityX) || y < Math.abs(heroVelocityY));

            drawLayout();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
