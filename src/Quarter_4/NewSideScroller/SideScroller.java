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
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SideScroller extends Application {

    private boolean isOnMovingBlock;
    private boolean canJump;
    private boolean debug;

    private double velocityX, velocityY, acceleration;
    private int heroPosition, blockSize;
    private int screenWidth, screenHeight, screenCenter, screenPosition;

    private long accelerationTime;

    private GraphicsContext graphicsContext;
    private Hero hero;
    private BadGuy badGuy;
    private Coin coin;
    private MovingBlock veritcalBlock, horizontalBlock;
    private ArrayList<Block> blocks;
    private String[] strings;
    private MouseEvent mouse;
    private boolean[] keyboard;

    private BackgroundLayer background1, background2,
                                clouds1, clouds2,
                                mountains1, mountains2,
                                trees1, trees2;

    public static void main(String[] args) {
        launch(args);
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
        screenCenter = (int) (2.5 * blockSize);

        acceleration = 1;

        canJump = true;
        debug = true;

        hero = new Hero(screenCenter, 0, blockSize, blockSize);
        heroPosition = hero.getLocationX();

        Image blockImage = new Image("Quarter_4/NewSideScroller/Images/Items/floor.png");
        Image coinImage = new Image("Quarter_4/NewSideScroller/Images/Items/coin.png");

        for (int i = 0; i < strings.length; i++)
            for (int j = 0; j < strings[i].length(); j++)
                if (strings[i].charAt(j) == 'x')
                    blocks.add(new Block(j * blockSize, i * blockSize, blockSize, blockSize, blockImage));
                else if (strings[i].charAt(j) == 'o')
                    badGuy = new BadGuy(j * blockSize, i * blockSize, blockSize, blockSize);
                else if (strings[i].charAt(j) == 'p')
                    coin = new Coin(j * blockSize, i * blockSize, blockSize, blockSize, coinImage);

        veritcalBlock = new MovingBlock(12 * blockSize, 15 * blockSize - 300, 4 * blockSize, 3 * blockSize, blockSize, MovingBlock.Orientation.VERTICAL);
        horizontalBlock = new MovingBlock(12 * blockSize, 15 * blockSize, 3 * blockSize, 2 * blockSize, blockSize, MovingBlock.Orientation.HORIZONTAL);

        blocks.add(veritcalBlock);
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
        scene.addEventHandler(MouseEvent.ANY, event -> mouse = event);
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

    private boolean collisionRight() {
        for (Block block : blocks)
            if (hero.getLocationX() + hero.getWidth() == block.getLocationX() - screenPosition &&
                    hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                    hero.getLocationY() + hero.getHeight() > block.getLocationY() ||
                    hero.getLocationX() + hero.getWidth() >= screenWidth)
                return true;
        return false;
    }

    private boolean collisionLeft() {
        for (Block block : blocks)
            if (hero.getLocationX() == block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                    hero.getLocationY() + hero.getHeight() > block.getLocationY() ||
                    hero.getLocationX() <= 0)
                return true;
        return false;
    }

    private boolean collisionUp() {
        for (Block block : blocks)
            if (hero.getLocationY() == block.getLocationY() + block.getHeight() &&
                    hero.getLocationX() < block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationX() + hero.getWidth() > block.getLocationX() - screenPosition ||
                    hero.getLocationY() <= 0)
                return true;
        return false;
    }

    private boolean collisionDown() {
        for (Block block : blocks) {
            if (hero.getLocationY() + hero.getHeight() == block.getLocationY() &&
                    hero.getLocationX() < block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationX() + hero.getWidth() > block.getLocationX() - screenPosition ||
                    hero.getLocationY() + hero.getHeight() >= screenHeight) {
                if (block == horizontalBlock)
                    this.isOnMovingBlock = true;
                else
                    this.isOnMovingBlock = false;
                return true;
            }
        }
        return false;
    }

    private void adjustLocationX(int direction) {
        if (heroPosition <= screenCenter || heroPosition >= strings[0].length() * blockSize - screenWidth + screenCenter)
            hero.setLocationX(hero.getLocationX() + direction);
        else
            hero.setLocationX(screenCenter);
        heroPosition += direction;
        screenPosition = Math.max(Math.min(heroPosition - screenCenter, strings[0].length() * blockSize - screenWidth), 0);
    }

    private void moveHero() {
        int iterationX = (int) Math.floor(Math.abs(velocityX));
        int iterationY = (int) Math.floor(Math.abs(velocityY));

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

    private void moveBadGuy() {
        if (heroPosition > badGuy.getLocationX())
            badGuy.setDirection(false);
        else if (heroPosition < badGuy.getLocationX())
            badGuy.setDirection(true);
    }

    private void moveBackground(double parallax) {
        int overlap = (int) Math.floor(Math.abs(parallax));
        if (parallax != 0 && heroPosition > screenCenter && heroPosition < strings[0].length() * blockSize - screenWidth + screenCenter) {
            background1.setPosition(background1.getPosition() - parallax * .25);
            background1.setPosition(background1.getPosition() > screenWidth ? -screenWidth + overlap : (background1.getPosition() < -screenWidth ? screenWidth - overlap : background1.getPosition()));
            background2.setPosition(background2.getPosition() - parallax * .25);
            background2.setPosition(background2.getPosition() > screenWidth ? -screenWidth + overlap : (background2.getPosition() < -screenWidth ? screenWidth - overlap : background2.getPosition()));

            clouds1.setPosition(clouds1.getPosition() - parallax * .5);
            clouds1.setPosition(clouds1.getPosition() > screenWidth ? -screenWidth + overlap : (clouds1.getPosition() < -screenWidth ? screenWidth - overlap : clouds1.getPosition()));
            clouds2.setPosition(clouds2.getPosition() - parallax * .5);
            clouds2.setPosition(clouds2.getPosition() > screenWidth ? -screenWidth + overlap : (clouds2.getPosition() < -screenWidth ? screenWidth - overlap : clouds2.getPosition()));

            mountains1.setPosition(mountains1.getPosition() - parallax * .75);
            mountains1.setPosition(mountains1.getPosition() > screenWidth ? -screenWidth + overlap : (mountains1.getPosition() < -screenWidth ? screenWidth - overlap : mountains1.getPosition()));
            mountains2.setPosition(mountains2.getPosition() - parallax * .75);
            mountains2.setPosition(mountains2.getPosition() > screenWidth ? -screenWidth + overlap : (mountains2.getPosition() < -screenWidth ? screenWidth - overlap : mountains2.getPosition()));

            trees1.setPosition(trees1.getPosition() - parallax);
            trees1.setPosition(trees1.getPosition() > screenWidth ? -screenWidth + overlap : (trees1.getPosition() < -screenWidth ? screenWidth - overlap : trees1.getPosition()));
            trees2.setPosition(trees2.getPosition() - parallax);
            trees2.setPosition(trees2.getPosition() > screenWidth ? -screenWidth + overlap : (trees2.getPosition() < -screenWidth ? screenWidth - overlap : trees2.getPosition()));
        }
    }

    private void draw(long now) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.clearRect(0, 0, screenWidth, screenHeight);

        graphicsContext.drawImage(background1.getImage(), background1.getPosition(), 0, background1.getWidth(), background1.getHeight());
        graphicsContext.drawImage(background2.getImage(), background2.getPosition(), 0, background2.getWidth(), background2.getHeight());

        graphicsContext.drawImage(clouds1.getImage(), clouds1.getPosition(), 0, clouds1.getWidth(), clouds1.getHeight());
        graphicsContext.drawImage(clouds2.getImage(), clouds2.getPosition(), 0, clouds2.getWidth(), clouds2.getHeight());

        graphicsContext.drawImage(mountains1.getImage(), mountains1.getPosition(), 0, mountains1.getWidth(), mountains1.getHeight());
        graphicsContext.drawImage(mountains2.getImage(), mountains2.getPosition(), 0, mountains2.getWidth(), mountains2.getHeight());

        graphicsContext.drawImage(trees1.getImage(), trees1.getPosition(), 0, trees1.getWidth(), trees1.getHeight());
        graphicsContext.drawImage(trees2.getImage(), trees2.getPosition(), 0, trees2.getWidth(), trees2.getHeight());

        graphicsContext.setFill(Color.LIGHTGREEN);
        for (Block block : blocks)
            if (block.getImage() == null)
                graphicsContext.fillRect(block.getLocationX() - screenPosition, block.getLocationY(), block.getWidth(), block.getHeight());
            else
                graphicsContext.drawImage(block.getImage(), block.getLocationX() - screenPosition, block.getLocationY(), block.getWidth(), block.getHeight());

        if (!coin.isConsumed())
            graphicsContext.drawImage(coin.getImage(), coin.getLocationX() - screenPosition, coin.getLocationY(), coin.getWidth(), coin.getHeight());

        if (hero.getDirection())
            graphicsContext.drawImage(hero.getImage(now), hero.getLocationX(), hero.getLocationY(), hero.getWidth(), hero.getHeight());
        else
            graphicsContext.drawImage(hero.getImage(now), hero.getLocationX() + hero.getWidth(), hero.getLocationY(), -hero.getWidth(), hero.getHeight());

        if (badGuy.getDirection())
            graphicsContext.drawImage(badGuy.getImage(now), badGuy.getLocationX() - screenPosition, badGuy.getLocationY(), badGuy.getWidth(), badGuy.getHeight());
        else
            graphicsContext.drawImage(badGuy.getImage(now), badGuy.getLocationX() - screenPosition + badGuy.getWidth(), badGuy.getLocationY(), -badGuy.getWidth(), badGuy.getHeight());

        if (debug)
            graphicsContext.strokeRect(hero.getLocationX(), hero.getLocationY(), hero.getWidth(), hero.getHeight());

    }

    private boolean intersects(Block block1, Block block2) {
        return ((block1.getLocationY() < block2.getLocationY() + block2.getHeight() &&
                block1.getLocationY() + block1.getHeight() > block2.getLocationY()) &&
                (block1.getLocationX() < block2.getLocationX() - screenPosition + block2.getWidth() &&
                 block1.getLocationX() + block1.getWidth() > block2.getLocationX() - screenPosition));
    }

    double lastLocation;

    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {
            // Parallax
            moveBackground(velocityX);

            velocityX = (keyboard[KeyCode.A.getCode()] ? -5 : 0) + (keyboard[KeyCode.D.getCode()] ? 5 : 0);

            if (keyboard[KeyCode.D.getCode()] && !keyboard[KeyCode.A.getCode()])
                hero.setDirection(true);
            else if (keyboard[KeyCode.A.getCode()] && !keyboard[KeyCode.D.getCode()])
                hero.setDirection(false);

            // Hero GIF

            if (mouse != null && !mouse.isConsumed() && mouse.getButton() == MouseButton.PRIMARY && mouse.getEventType() == MouseEvent.MOUSE_CLICKED) {
                hero.setTemporaryState(Hero.State.SLASHING, now);
                mouse.consume();
            }
            else
                if (velocityX != 0 && hero.getState() != Hero.State.WALKING)
                hero.setState(Hero.State.WALKING, now);
            else if (velocityX == 0 && hero.getState() != Hero.State.IDLE)
                hero.setState(Hero.State.IDLE, now);

            // Consume Coin
            if (!coin.isConsumed() && intersects(hero, coin)) {
                coin.consume();
                acceleration /= 2;
                accelerationTime = now;
            }

            // Gravity
            if (now - accelerationTime > 6 * 1E9)
                acceleration = 1;
            velocityY += acceleration;

            // Moving Block collision
            boolean collisionRight = collisionRight(), collisionLeft = collisionLeft();
            if (collisionRight && !collisionLeft) {
                lastLocation = heroPosition;
                adjustLocationX(-1);
            }
            if (collisionLeft && !collisionRight) {
                lastLocation = heroPosition;
                adjustLocationX(1);
            }

            // Moving Blocks Parallax
            if (isOnMovingBlock)
                if (!collisionLeft() && horizontalBlock.getDirection() < 0 || !collisionRight() && horizontalBlock.getDirection() > 0) {
                    adjustLocationX(horizontalBlock.getDirection());
                    if (lastLocation != heroPosition)
                        moveBackground(horizontalBlock.getDirection());
                }

            // Moving Blocks
            veritcalBlock.move();
            horizontalBlock.move();

            // Hero Move
            moveHero();

            // Bad Guy Move
            moveBadGuy();

            // Parallax
            screenPosition = Math.max(Math.min(heroPosition - screenCenter, strings[0].length() * blockSize - screenWidth), 0);

            // Final Draw
            draw(now);
        }
    }

}
