package Quarter_4.SideScroller;

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

    private boolean isOnHorizontalBlock;
    private boolean canJump;
    private boolean debug;
    private boolean inDeath;
    private boolean antiGravity;

    private double lastLocation;
    private double velocityX, velocityY, acceleration;
    private int heroPosition, blockSize;
    private int screenWidth, screenHeight, screenCenter, screenPosition;

    private long currentTime, accelerationTime;

    private GraphicsContext graphicsContext;
    private Hero hero;
    private BadGuy badGuy;
    private Block antigravity;
    private Block coin;
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

        strings = Files.readString(Paths.get("src/Quarter_4/SideScroller/level.txt")).split("\n");

        screenHeight = 400;
        screenWidth = 800;

        blockSize = screenHeight / strings.length;
        screenHeight = strings.length * blockSize;
        screenWidth = screenWidth / blockSize * blockSize;

        screenCenter = (screenWidth - blockSize) / 2;
        screenCenter = (int) (2.5 * blockSize);

        acceleration = 1;

        canJump = true;
        debug = false;

        hero = new Hero(screenCenter, 0, blockSize, blockSize);
        heroPosition = hero.getLocationX();

        Image blockImage = new Image("Quarter_4/SideScroller/Images/Items/floor.png");
        Image coinImage = new Image("Quarter_4/SideScroller/Images/Items/coin.png");

        for (int i = 0; i < strings.length; i++)
            for (int j = 0; j < strings[i].length(); j++)
                if (strings[i].charAt(j) == 'x')
                    blocks.add(new Block(j * blockSize, i * blockSize, blockSize, blockSize, blockImage));
                else if (strings[i].charAt(j) == 'e')
                    antigravity = new Block(j * blockSize, i * blockSize, blockSize, blockSize, null);
                else if (strings[i].charAt(j) == 'b')
                    blocks.add(new BreakableBlock(j * blockSize, i * blockSize, blockSize, blockSize, coinImage, 5));
                else if (strings[i].charAt(j) == 'p') {
                    coin = new Block(j * blockSize, i * blockSize, blockSize, blockSize, coinImage);
                }

        veritcalBlock = new MovingBlock(blockSize / 2, 2 * blockSize, 39 * blockSize, blockSize, 3 * blockSize, blockImage, MovingBlock.Orientation.VERTICAL);
        horizontalBlock = new MovingBlock(12 * blockSize, 15 * blockSize, 3 * blockSize, 2 * blockSize, blockSize, blockImage, MovingBlock.Orientation.HORIZONTAL);

        blocks.add(veritcalBlock);
        blocks.add(horizontalBlock);

        badGuy = new BadGuy(26 * blockSize, 31 * blockSize, 4 * blockSize, blockSize, blockSize, MovingBlock.Orientation.HORIZONTAL);

        background1 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/background.png"), 0, screenWidth, screenHeight);
        background2 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/background.png"), screenWidth, screenWidth, screenHeight);

        clouds1 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/clouds.png"), 0, screenWidth, screenHeight);
        clouds2 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/clouds.png"), screenWidth, screenWidth, screenHeight);

        mountains1 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/mountains.png"), 0, screenWidth, screenHeight);
        mountains2 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/mountains.png"), screenWidth, screenWidth, screenHeight);

        trees1 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/trees.png"), 0, screenWidth, screenHeight);
        trees2 = new BackgroundLayer(new Image("Quarter_4/SideScroller/Images/Background/trees.png"), screenWidth, screenWidth, screenHeight);

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

    private Block collisionRight() {
        for (Block block : blocks)
            if (hero.getLocationX() + hero.getWidth() == block.getLocationX() - screenPosition &&
                    hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                    hero.getLocationY() + hero.getHeight() > block.getLocationY() ||
                    hero.getLocationX() + hero.getWidth() >= screenWidth)
                return block;
        return null;
    }

    private Block collisionLeft() {
        for (Block block : blocks)
            if (hero.getLocationX() == block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationY() < block.getLocationY() + block.getHeight() &&
                    hero.getLocationY() + hero.getHeight() > block.getLocationY())
                return block;
        return null;
    }

    private Block collisionUp() {
        for (Block block : blocks)
            if (hero.getLocationY() == block.getLocationY() + block.getHeight() &&
                    hero.getLocationX() < block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationX() + hero.getWidth() > block.getLocationX() - screenPosition)
                return block;
        return null;
    }

    private Block collisionDown() {
        for (Block block : blocks) {
            if (hero.getLocationY() + hero.getHeight() == block.getLocationY() &&
                    hero.getLocationX() < block.getLocationX() - screenPosition + block.getWidth() &&
                    hero.getLocationX() + hero.getWidth() > block.getLocationX() - screenPosition) {
                isOnHorizontalBlock = block == horizontalBlock;
                return block;
            }
        }
        return null;
    }

    private void adjustLocationX(int direction) {
        if (heroPosition <= screenCenter || heroPosition >= strings[0].length() * blockSize - screenWidth + screenCenter)
            hero.setLocationX(hero.getLocationX() + direction);
        else
            hero.setLocationX(screenCenter);
        heroPosition += direction;
        screenPosition = Math.max(Math.min(heroPosition - screenCenter, strings[0].length() * blockSize - screenWidth), 0);
    }

    private void resetHero() {
        hero.setLocationX(screenCenter);
        hero.setLocationY(0);
        heroPosition = screenCenter;
        screenPosition = 0;
        velocityY = 0;
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
                    if (collisionLeft() != null) {
                        velocityX = 0;
                        x = iterationX;
                        break collisionX;
                    }
                    adjustLocationX(-1);
                }
                // RIGHT
                if (keyboard[KeyCode.D.getCode()] && !keyboard[KeyCode.A.getCode()]) {
                    if (collisionRight() != null) {
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
                    if (collisionUp() != null || hero.getLocationY() <= 0) {
                        velocityY = 0;
                        y = iterationY;
                        break collisionY;
                    }
                    hero.setLocationY(hero.getLocationY() - 1);
                }
                // DOWN
                if (velocityY > 0) {
                    if (collisionDown() != null) {
                        canJump = true;
                        velocityY = 0;
                        y = iterationY;
                        break collisionY;
                    }
                    isOnHorizontalBlock = false;
                    hero.setLocationY(hero.getLocationY() + 1);
                    canJump = false;
                }
                y++;
            }

        } while (x != iterationX || y != iterationY);

        // CAP TOP
        if (hero.getLocationY() < 0)
            while (hero.getLocationY() < 0)
                hero.setLocationY(hero.getLocationY() + 1);

        // CAP LEFT
        if (hero.getLocationX() < 0)
            while (hero.getLocationX() < 0)
                hero.setLocationX(hero.getLocationX() + 1);

        // CAP RIGHT
        if (hero.getLocationX() + hero.getWidth() > screenWidth)
            while (hero.getLocationX() + hero.getWidth() > screenWidth)
                hero.setLocationX(hero.getLocationX() - 1);

        // JUMP
        if (keyboard[KeyCode.W.getCode()] && canJump) {
            velocityY = (int) -Math.sqrt(2.5 * hero.getHeight());
            canJump = false;
        }

    }

    private void moveBadGuy() {
//        if (heroPosition > badGuy.getLocationX())
//            badGuy.setDirection(false);
//        else if (heroPosition < badGuy.getLocationX())
//            badGuy.setDirection(true);
        if ((badGuy.getLocationX() < badGuy.getMinimum() && !badGuy.getDirection()) || (badGuy.getLocationX() > badGuy.getMaximum() && badGuy.getDirection()))
            badGuy.changeDirection();
        else if (badGuy.getLocationX() <= badGuy.getMaximum() && badGuy.getDirection())
            badGuy.setLocationX(badGuy.getLocationX() + 1);
        else if (badGuy.getLocationX() >= badGuy.getMinimum() && !badGuy.getDirection())
            badGuy.setLocationX(badGuy.getLocationX() - 1);
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

    private void draw() {
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
            if (block instanceof MovingBlock) {
                MovingBlock movingBlock = (MovingBlock) block;
                if (movingBlock.getOrientation() == MovingBlock.Orientation.HORIZONTAL)
                    for (int i = 0; i < movingBlock.getWidth() / movingBlock.getHeight(); i++)
                        graphicsContext.drawImage(block.getImage(), block.getLocationX() - screenPosition + i * block.getHeight(), block.getLocationY(), block.getHeight(), block.getHeight());
                else
                    for (int i = 0; i < movingBlock.getHeight() / movingBlock.getWidth(); i++)
                        graphicsContext.drawImage(block.getImage(), block.getLocationX() - screenPosition, block.getLocationY() + i * block.getWidth(), block.getWidth(), block.getWidth());
            }
            else if (block.getImage() != null)
                graphicsContext.drawImage(block.getImage(), block.getLocationX() - screenPosition, block.getLocationY(), block.getWidth(), block.getHeight());
            else
                graphicsContext.fillRect(block.getLocationX() - screenPosition, block.getLocationY(), block.getWidth(), block.getHeight());

        if (!coin.isConsumed())
            graphicsContext.drawImage(coin.getImage(), coin.getLocationX() - screenPosition, coin.getLocationY(), coin.getWidth(), coin.getHeight());

        if (!antigravity.isConsumed())
            graphicsContext.fillRect(antigravity.getLocationX() - screenPosition, antigravity.getLocationY(), antigravity.getWidth(), antigravity.getHeight());

        if (hero.getDirection())
            graphicsContext.drawImage(hero.getImage(currentTime), hero.getLocationX(), hero.getLocationY(), hero.getWidth(), hero.getHeight());
        else
            graphicsContext.drawImage(hero.getImage(currentTime), hero.getLocationX() + hero.getWidth(), hero.getLocationY(), -hero.getWidth(), hero.getHeight());

        if (!badGuy.getDirection())
            graphicsContext.drawImage(badGuy.getImage(currentTime), badGuy.getLocationX() - screenPosition, badGuy.getLocationY(), badGuy.getWidth(), badGuy.getHeight());
        else
            graphicsContext.drawImage(badGuy.getImage(currentTime), badGuy.getLocationX() - screenPosition + badGuy.getWidth(), badGuy.getLocationY(), -badGuy.getWidth(), badGuy.getHeight());

        if (debug)
            graphicsContext.strokeRect(hero.getLocationX(), hero.getLocationY(), hero.getWidth(), hero.getHeight());

    }

    private boolean intersects(Block block1, Block block2) {
        return ((block1.getLocationY() < block2.getLocationY() + block2.getHeight() &&
                block1.getLocationY() + block1.getHeight() > block2.getLocationY()) &&
                (block1.getLocationX() < block2.getLocationX() - screenPosition + block2.getWidth() &&
                 block1.getLocationX() + block1.getWidth() > block2.getLocationX() - screenPosition));
    }

    private boolean intersectsVertical(Block block1, Block block2) {
        return block1.getLocationY() < block2.getLocationY() + block2.getHeight() &&
                block1.getLocationY() + block1.getHeight() > block2.getLocationY();
    }

    private boolean intersectsHorizontal(int positionX, Block block1, Block block2) {
        return positionX < block2.getLocationX() - screenPosition + block2.getWidth() &&
                block1.getLocationX() + block1.getWidth() > block2.getLocationX() - screenPosition;
    }

    private class Animation extends AnimationTimer {
        @Override
        public void handle(long now) {
            currentTime = now;

            // Gravity
            if (intersects(hero, antigravity) && !antigravity.isConsumed()) {
                antiGravity = true;
                accelerationTime = now;
                antigravity.consume();
                blocks.remove(antigravity);
            }

            if (now - accelerationTime > 6 * 1E9) {
                acceleration = 1;
                antiGravity = false;
            }
            else if (antiGravity) {
                acceleration = -1;
                if (keyboard[KeyCode.S.getCode()])
                    velocityY = 10;
            }

            // DEATH
            if (hero.getLocationY() >= screenHeight)
                resetHero();

            if (keyboard[KeyCode.SPACE.getCode()]) {
                hero.setTemporaryState(Hero.State.DEATH, currentTime);
                draw();
                return;
            }

            if (intersects(hero, badGuy)) {
                if (hero.getState() != Hero.State.DEATH && !inDeath) {
                    hero.setTemporaryState(Hero.State.DEATH, currentTime);
                    inDeath = true;
                }
                if (!hero.inTemporaryState())
                    inDeath = false;
                if (inDeath) {
                    draw();
                    return;
                }
                else
                    resetHero();
            }

            if (hero.getState() == Hero.State.DEATH) {
                if (hero.inTemporaryState()) {
                    draw();
                    return;
                }
                resetHero();
            }

            // Parallax
            moveBackground(velocityX);

            if (hero.getState() != Hero.State.SLASHING) {
                velocityX = (keyboard[KeyCode.A.getCode()] ? -5 : 0) + (keyboard[KeyCode.D.getCode()] ? 5 : 0);

                if (keyboard[KeyCode.D.getCode()] && !keyboard[KeyCode.A.getCode()])
                    hero.setDirection(true);
                else if (keyboard[KeyCode.A.getCode()] && !keyboard[KeyCode.D.getCode()])
                    hero.setDirection(false);
            }
            else
                velocityX = 0;

            // Hero GIF
            if (mouse != null && !mouse.isConsumed() && mouse.getButton() == MouseButton.PRIMARY && mouse.getEventType() == MouseEvent.MOUSE_CLICKED) {
                hero.setTemporaryState(Hero.State.SLASHING, now);
                if (hero.getDirection()) {
                    adjustLocationX(1);
                    Block block = collisionRight();
                    if (block instanceof BreakableBlock) {
                        BreakableBlock breakableBlock = ((BreakableBlock) block);
                        breakableBlock.setHealth(breakableBlock.getHealth() - 1);
                        if (breakableBlock.getHealth() < 0)
                            blocks.remove(breakableBlock);
                    }
                    hero.consumeState();
                    adjustLocationX(-1);
                }
                else {
                    Block block = collisionLeft();
                    if (block instanceof BreakableBlock) {
                        BreakableBlock breakableBlock = ((BreakableBlock) block);
                        breakableBlock.setHealth(breakableBlock.getHealth() - 1);
                        if (breakableBlock.getHealth() < 0)
                            blocks.remove(breakableBlock);
                    }
                    hero.consumeState();
                }
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

            velocityY += acceleration;

            // Moving Block collision
            boolean collisionRight = collisionRight() != null, collisionLeft = collisionLeft() != null;
            if (collisionRight && !collisionLeft || hero.getLocationY() > screenWidth) {
                lastLocation = heroPosition;
                adjustLocationX(-1);
            }
            if (collisionLeft && !collisionRight || hero.getLocationY() < 0) {
                lastLocation = heroPosition;
                adjustLocationX(1);
            }

            boolean collisionUp = collisionUp() != null, collisionDown = collisionDown() != null;
            if (collisionUp && !collisionDown)
                hero.setLocationY(hero.getLocationY() + 1);
            if (collisionDown && !collisionUp)
                hero.setLocationY(hero.getLocationY() - 1);

            // Moving Blocks Parallax
            if (isOnHorizontalBlock)
                if (collisionLeft() == null && !horizontalBlock.getDirection() || collisionRight() == null && horizontalBlock.getDirection()) {
                    adjustLocationX(horizontalBlock.getDirection() ? 1 : -1);
                    if (lastLocation != heroPosition)
                        moveBackground(horizontalBlock.getDirection() ? 1 : -1);
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
            draw();
        }
    }

}