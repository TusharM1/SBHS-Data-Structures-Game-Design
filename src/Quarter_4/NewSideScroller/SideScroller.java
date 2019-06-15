package Quarter_4.NewSideScroller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SideScroller extends Application {

    private static int screenWidth, screenHeight;

    private static GraphicsContext graphicsContext;

    private boolean[] keyboard;

    private Block hero;

    private ArrayList<Block> blocks;

    private int blockSize;

    private String[] strings;

    int velocityX = 0, velocityY = 0, accelerationY = 1;

    boolean canJump = true;

    // Change this to just be draw function, it doesn't need to clear to draw the background
    private void clearCanvas() {
        graphicsContext.save();
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, screenWidth, screenHeight);
        graphicsContext.restore();
    }

    public void draw() {
        System.out.println(hero.getLocationX() + " " + hero.getLocationY());
        graphicsContext.save();
        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(hero.getLocationX(), hero.getLocationY(), hero.getWidth(), hero.getHeight());
        for (Block block : blocks) {
            graphicsContext.setFill(Color.LIGHTGREEN);
            graphicsContext.fillRect(block.getLocationX(), block.getLocationY(), block.getWidth(), block.getHeight());
        }
        graphicsContext.restore();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        blocks = new ArrayList<>();

        strings = Files.readString(Paths.get("src/Quarter_4/NewSideScroller/level.txt")).split("\n");

        screenHeight = 400;
        screenWidth = 800;

        blockSize = screenHeight / strings.length;
        screenHeight = strings.length * blockSize;
        screenWidth = screenWidth / blockSize * blockSize;

        hero = new Block(0, 0, blockSize, blockSize);

        for (int i = 0; i < strings.length; i++)
            for (int j = 0; j < strings[i].length(); j++)
                if (strings[i].charAt(j) == 'x')
                    blocks.add(new Block(j * blockSize, i * blockSize, blockSize, blockSize));



        Canvas canvas = new Canvas(screenWidth, screenHeight);
        graphicsContext = canvas.getGraphicsContext2D();
        clearCanvas();

        Group group = new Group(canvas);
        Scene scene = new Scene(group);

        keyboard = new boolean[1024];

        scene.addEventHandler(KeyEvent.ANY, event -> keyboard[event.getCode().getCode()] = event.getEventType() != KeyEvent.KEY_RELEASED);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            screenWidth = newValue.intValue();
            canvas.setWidth(screenWidth);
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            screenHeight = newValue.intValue();
            canvas.setHeight(screenHeight);
        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Collision Detection");
        primaryStage.show();

        Animation animation = new Animation();
        animation.start();
    }

    private void moveBlock() {
        int iterationX = Math.abs(velocityX);
        int iterationY = Math.abs(velocityY);

        int x = 0, y = 0;
        do {
            collisionX:
            if (x < iterationX) {
                // LEFT
                if (keyboard[KeyCode.A.getCode()] && !keyboard[KeyCode.D.getCode()]) {
                    for (Block block : blocks)
                        if (hero.getLocationX() == block.getLocationX() + block.getWidth() &&
                                hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                                hero.getLocationY() + hero.getHeight() > block.getLocationY() ||
                                hero.getLocationX() <= 0) {
                            velocityX = 0;
                            x = iterationX;
                            break collisionX;
                        }
                    hero.setLocationX(hero.getLocationX() - 1);
                }
                // RIGHT
                if (keyboard[KeyCode.D.getCode()] && !keyboard[KeyCode.A.getCode()]) {
                    for (Block block : blocks)
                        if (hero.getLocationX() + hero.getWidth() == block.getLocationX() &&
                                hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                                hero.getLocationY() + hero.getHeight() > block.getLocationY() ||
                                hero.getLocationX() + hero.getWidth() >= screenWidth) {
                            velocityX = 0;
                            x = iterationX;
                            break collisionX;
                        }
                    hero.setLocationX(hero.getLocationX() + 1);
                }
                x++;
            }

            collisionY:
            if (y < iterationY) {
                // UP
                if (velocityY < 0) {
                    for (Block block : blocks)
                        if (hero.getLocationY() == block.getLocationY() + block.getHeight() &&
                                hero.getLocationX() < block.getLocationX() + block.getWidth() &&
                                hero.getLocationX() + hero.getWidth() > block.getLocationX() ||
                                hero.getLocationY() <= 0) {
                            velocityY = 0;
                            y = iterationY;
                            break collisionY;
                        }
                    hero.setLocationY(hero.getLocationY() - 1);
                }
                // DOWN
                if (velocityY > 0) {
                    for (Block block : blocks)
                        if (hero.getLocationY() + hero.getHeight() == block.getLocationY() &&
                                hero.getLocationX() < block.getLocationX() + block.getWidth() &&
                                hero.getLocationX() + hero.getWidth() > block.getLocationX() ||
                                hero.getLocationY() + hero.getHeight() >= screenHeight) {
                            canJump = true;
                            velocityY = 0;
                            y = iterationY;
                            break collisionY;
                        }
                    hero.setLocationY(hero.getLocationY() + 1);
                    canJump = false;
                }
                y++;
            }

        } while (x != iterationX || y != iterationY);

        // CAP RIGHT
        if (hero.getLocationX() + hero.getWidth() > screenWidth)
            while (hero.getLocationX() + hero.getWidth() > screenWidth)
                hero.setLocationX(hero.getLocationX() - 1);

        // CAP BOTTOM
        if (hero.getLocationY() + hero.getHeight() > screenHeight)
            while (hero.getLocationY() + hero.getHeight() > screenHeight)
                hero.setLocationY(hero.getLocationY() - 1);

        // RESET
        if (keyboard[KeyCode.SPACE.getCode()]) {
            hero.setLocationX((screenWidth - hero.getWidth()) / 2);
            hero.setLocationY(0);
            velocityY = 0;
        }

        // JUMP
        if (keyboard[KeyCode.W.getCode()] && canJump) {
            velocityY = (int) -Math.sqrt(2.5 * hero.getHeight());
            canJump = false;
        }

        velocityX = (keyboard[KeyCode.A.getCode()] ? -5 : 0) + (keyboard[KeyCode.D.getCode()] ? 5 : 0);
        velocityY += accelerationY;

    }

    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {
            clearCanvas();

            moveBlock();
            draw();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
