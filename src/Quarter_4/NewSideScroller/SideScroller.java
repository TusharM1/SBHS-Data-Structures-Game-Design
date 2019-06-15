package Quarter_4.NewSideScroller;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SideScroller extends Application {

    private int screenWidth, screenHeight;

    private GraphicsContext graphicsContext;

    private boolean[] keyboard;

    private Block hero;
    private MovingBlock veritcalBlock, horizontalBlock;
//    private int verticalBlockDirection, horizontalBlockDirection = 1;

    private ArrayList<Block> blocks;

    private int blockSize;

    private String[] strings;

    private int velocityX = 0, velocityY = 0, accelerationY = 1;

    private boolean canJump = true;

    private int screenCenter, screenPosition = 0, heroPosition;

    BackgroundLayer background1, background2;
    BackgroundLayer clouds1, clouds2;
    BackgroundLayer mountains1, mountains2;
    BackgroundLayer trees1, trees2;

    public void draw() {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillRect(0, 0, screenWidth, screenHeight);

        graphicsContext.drawImage(background1.getImage(), background1.getPosition(), 0, background1.getWidth(), background1.getHeight());
        graphicsContext.drawImage(background2.getImage(), background2.getPosition(), 0, background2.getWidth(), background2.getHeight());

        graphicsContext.drawImage(clouds1.getImage(), clouds1.getPosition(), 0, clouds1.getWidth(), clouds1.getHeight());
        graphicsContext.drawImage(clouds2.getImage(), clouds2.getPosition(), 0, clouds2.getWidth(), clouds2.getHeight());

        graphicsContext.drawImage(mountains1.getImage(), mountains1.getPosition(), 0, mountains1.getWidth(), mountains1.getHeight());
        graphicsContext.drawImage(mountains2.getImage(), mountains2.getPosition(), 0, mountains2.getWidth(), mountains2.getHeight());

        graphicsContext.drawImage(trees1.getImage(), trees1.getPosition(), 0, trees1.getWidth(), trees1.getHeight());
        graphicsContext.drawImage(trees2.getImage(), trees2.getPosition(), 0, trees2.getWidth(), trees2.getHeight());

        graphicsContext.setFill(Color.LIGHTBLUE);
        graphicsContext.fillRect(hero.getLocationX(), hero.getLocationY(), hero.getWidth(), hero.getHeight());
        for (Block block : blocks) {
            graphicsContext.setFill(Color.LIGHTGREEN);
            graphicsContext.fillRect(block.getLocationX() - screenPosition, block.getLocationY(), block.getWidth(), block.getHeight());
        }
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

//        screenCenter = (screenWidth - blockSize) / 2;
        screenCenter = 200;

        hero = new Block(screenCenter, 0, blockSize, blockSize);
        heroPosition = hero.getLocationX();

        for (int i = 0; i < strings.length; i++)
            for (int j = 0; j < strings[i].length(); j++)
                if (strings[i].charAt(j) == 'x')
                    blocks.add(new Block(j * blockSize, i * blockSize, blockSize, blockSize));

        veritcalBlock = new MovingBlock(20, 100, 500, 50, 150, MovingBlock.Orientation.VERTICAL);
        blocks.add(veritcalBlock);

        horizontalBlock = new MovingBlock(0, 300, 200, 250, 50, MovingBlock.Orientation.HORIZONTAL);
        blocks.add(horizontalBlock);

        background1 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/background.png"), 0, screenWidth, screenHeight);
        background2 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/background.png"), screenWidth, screenWidth, screenHeight);

        clouds1 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/clouds.png"), 0, screenWidth, screenHeight);
        clouds2 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/clouds.png"), screenWidth, screenWidth, screenHeight);

        mountains1 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/mountains.png"), 0, screenWidth, screenHeight);
        mountains2 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/mountains.png"), screenWidth, screenWidth, screenHeight);

        trees1 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/trees.png"), 0, screenWidth, screenHeight);
        trees2 = new BackgroundLayer(new Image("Quarter_4/NewSideScroller/Background/trees.png"), screenWidth, screenWidth, screenHeight);

        Canvas canvas = new Canvas(screenWidth, screenHeight);
        graphicsContext = canvas.getGraphicsContext2D();

        Group group = new Group(canvas);
        Scene scene = new Scene(group);

        keyboard = new boolean[1024];

        scene.addEventHandler(KeyEvent.ANY, event -> keyboard[event.getCode().getCode()] = event.getEventType() != KeyEvent.KEY_RELEASED);
        scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            screenWidth = newValue.intValue();
            canvas.setWidth(screenWidth);

            if (oldValue.intValue() != 0) {
                background1.setWidth(newValue.intValue());
                if (background1.getPosition() > background2.getPosition())
                    background1.setPosition(background1.getPosition() - oldValue.intValue() + newValue.intValue());
                background2.setWidth(newValue.intValue());
                if (background2.getPosition() > background1.getPosition())
                    background2.setPosition(background2.getPosition() - oldValue.intValue() + newValue.intValue());

                clouds1.setWidth(newValue.intValue());
                if (clouds1.getPosition() > clouds2.getPosition())
                    clouds1.setPosition(clouds1.getPosition() - oldValue.intValue() + newValue.intValue());
                clouds2.setWidth(newValue.intValue());
                if (clouds2.getPosition() > clouds1.getPosition())
                    clouds2.setPosition(clouds2.getPosition() - oldValue.intValue() + newValue.intValue());

                mountains1.setWidth(newValue.intValue());
                if (mountains1.getPosition() > mountains2.getPosition())
                    mountains1.setPosition(mountains1.getPosition() - oldValue.intValue() + newValue.intValue());
                mountains2.setWidth(newValue.intValue());
                if (mountains2.getPosition() > mountains1.getPosition())
                    mountains2.setPosition(mountains2.getPosition() - oldValue.intValue() + newValue.intValue());

                trees1.setWidth(newValue.intValue());
                if (trees1.getPosition() > trees2.getPosition())
                    trees1.setPosition(trees1.getPosition() - oldValue.intValue() + newValue.intValue());
                trees2.setWidth(newValue.intValue());
                if (trees2.getPosition() > trees1.getPosition())
                    trees2.setPosition(trees2.getPosition() - oldValue.intValue() + newValue.intValue());
            }
        });
        scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            screenHeight = newValue.intValue();
            canvas.setHeight(screenHeight);

            background1.setHeight(newValue.intValue());
            background2.setHeight(newValue.intValue());

            clouds1.setHeight(newValue.intValue());
            clouds2.setHeight(newValue.intValue());

            mountains1.setHeight(newValue.intValue());
            mountains2.setHeight(newValue.intValue());

            trees1.setHeight(newValue.intValue());
            trees2.setHeight(newValue.intValue());

        });

        primaryStage.setScene(scene);
        primaryStage.setTitle("Collision Detection");
        primaryStage.show();

        Animation animation = new Animation();
        animation.start();
    }

    boolean isOnMovingBlock;

    private boolean collisionRight() {
        for (Block block : blocks)
            if (hero.getLocationX() + hero.getWidth() == block.getLocationX() - screenPosition &&
                    hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                    hero.getLocationY() + hero.getHeight() > block.getLocationY() ||
                    hero.getLocationX() + hero.getWidth() >= screenWidth)
                return true;
        return false;
    }

    public boolean collisionLeft() {
        for (Block block : blocks)
            if (hero.getLocationX() == block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                    hero.getLocationY() + hero.getHeight() > block.getLocationY() ||
                    hero.getLocationX() <= 0)
                return true;
        return false;
    }

    public boolean collisionUp() {
        for (Block block : blocks)
            if (hero.getLocationY() == block.getLocationY() + block.getHeight() &&
                    hero.getLocationX() < block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationX() + hero.getWidth() > block.getLocationX() - screenPosition ||
                    hero.getLocationY() <= 0)
                return true;
        return false;
    }

    public boolean collisionDown() {
        for (Block block : blocks) {
            if (hero.getLocationY() + hero.getHeight() == block.getLocationY() &&
                    hero.getLocationX() < block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationX() + hero.getWidth() > block.getLocationX() - screenPosition ||
                    hero.getLocationY() + hero.getHeight() >= screenHeight) {
                if (block == horizontalBlock)
                    this.isOnMovingBlock = true;
                return true;
            }
        }
        return false;
    }

    public void adjustLocationX(int direction) {
        if (heroPosition <= screenCenter  || heroPosition >= strings[0].length() * blockSize - screenWidth + screenCenter)
            hero.setLocationX(hero.getLocationX() + direction);
        else
            hero.setLocationX(screenCenter);
        heroPosition += direction;
        screenPosition = Math.max(Math.min(heroPosition - screenCenter, strings[0].length() * blockSize - screenWidth), 0);
    }

    private void moveBlock() {
        int iterationX = Math.abs(velocityX);
        int iterationY = Math.abs(velocityY);

        int x = 0, y = 0;
        do {
            // Collision Detection and Resolution for Left and Right
            collisionX:
            if (x < iterationX) {
                // LEFT
                if (keyboard[KeyCode.A.getCode()] && !keyboard[KeyCode.D.getCode()]) {
                    if (collisionLeft()) {
                        velocityX = 0;
                        x = iterationX;
                        break collisionX;
                    }
                    adjustLocationX(-1);
                }
                // RIGHT
                if (keyboard[KeyCode.D.getCode()] && !keyboard[KeyCode.A.getCode()]) {
                    if (collisionRight()) {
                        velocityX = 0;
                        x = iterationX;
                        break collisionX;
                    }
                    adjustLocationX(1);
                }
                x++;
            }

            // Collision Detection and Resolution for Up and Down
            collisionY:
            if (y < iterationY) {
                // UP
                if (velocityY < 0) {
                    if (collisionUp()) {
                        velocityY = 0;
                        y = iterationY;
                        break collisionY;
                    }
                    hero.setLocationY(hero.getLocationY() - 1);
                }
                // DOWN
                if (velocityY > 0) {
                    if (collisionDown()) {
                        canJump = true;
                        velocityY = 0;
                        y = iterationY;
                        break collisionY;
                    }
                    this.isOnMovingBlock = false;
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
            hero.setLocationX(screenCenter);
            hero.setLocationY(0);
            heroPosition = screenCenter;
            screenPosition = 0;
            velocityY = 0;
        }

        // JUMP
        if (keyboard[KeyCode.W.getCode()] && canJump) {
            velocityY = (int) -Math.sqrt(2.5 * hero.getHeight());
            canJump = false;
        }

    }

    private void moveBackground() {
        int parallax = (int) Math.copySign(1, velocityX), overlap = Math.abs(velocityX);
        if (velocityX != 0 && heroPosition > screenCenter && heroPosition < strings[0].length() * blockSize - screenWidth + screenCenter) {
            background1.setPosition(background1.getPosition() - parallax);
            background1.setPosition(background1.getPosition() > screenWidth ? -screenWidth + overlap : (background1.getPosition() < -screenWidth ? screenWidth - overlap: background1.getPosition()));
            background2.setPosition(background2.getPosition() - parallax);
            background2.setPosition(background2.getPosition() > screenWidth ? -screenWidth + overlap: (background2.getPosition() < -screenWidth ? screenWidth - overlap: background2.getPosition()));

            clouds1.setPosition(clouds1.getPosition() - 2 * parallax);
            clouds1.setPosition(clouds1.getPosition() > screenWidth ? -screenWidth + overlap: (clouds1.getPosition() < -screenWidth ? screenWidth - overlap: clouds1.getPosition()));
            clouds2.setPosition(clouds2.getPosition() - 2 * parallax);
            clouds2.setPosition(clouds2.getPosition() > screenWidth ? -screenWidth + overlap: (clouds2.getPosition() < -screenWidth ? screenWidth - overlap: clouds2.getPosition()));

            mountains1.setPosition(mountains1.getPosition() - 3 * parallax);
            mountains1.setPosition(mountains1.getPosition() > screenWidth ? -screenWidth + overlap: (mountains1.getPosition() < -screenWidth ? screenWidth - overlap: mountains1.getPosition()));
            mountains2.setPosition(mountains2.getPosition() - 3 * parallax);
            mountains2.setPosition(mountains2.getPosition() > screenWidth ? -screenWidth + overlap: (mountains2.getPosition() < -screenWidth ? screenWidth - overlap: mountains2.getPosition()));

            trees1.setPosition(trees1.getPosition() - 4 * parallax);
            trees1.setPosition(trees1.getPosition() > screenWidth ? -screenWidth + overlap: (trees1.getPosition() < -screenWidth ? screenWidth - overlap: trees1.getPosition()));
            trees2.setPosition(trees2.getPosition() - 4 * parallax);
            trees2.setPosition(trees2.getPosition() > screenWidth ? -screenWidth + overlap: (trees2.getPosition() < -screenWidth ? screenWidth - overlap: trees2.getPosition()));
        }
    }

    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {
            moveBackground();

            velocityX = (keyboard[KeyCode.A.getCode()] ? -5 : 0) + (keyboard[KeyCode.D.getCode()] ? 5 : 0);

            if (collisionRight())
                adjustLocationX(-1);

            if (collisionLeft())
                adjustLocationX(1);

            if (isOnMovingBlock)
                if (!collisionLeft() && horizontalBlock.getDirection() < 0 || !collisionRight() && horizontalBlock.getDirection() > 0)
                    adjustLocationX(horizontalBlock.getDirection());
            System.out.println(velocityX);
            velocityY += accelerationY;

            veritcalBlock.move();
            horizontalBlock.move();

            moveBlock();

            screenPosition = Math.max(Math.min(heroPosition - screenCenter, strings[0].length() * blockSize - screenWidth), 0);

            draw();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
