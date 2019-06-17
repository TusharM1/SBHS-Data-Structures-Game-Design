package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class Hero extends Block {

    private static Image[] idle, walking, slashing, death;

    private State currentState, lastState;

    private long startingTime;

    private boolean direction;

    private boolean stateConsumed;
    
    static {
        Image idle = new Image("Quarter_4/NewSideScroller/Images/Hero/idle.png");
        PixelReader idleReader = idle.getPixelReader();
        Hero.idle = new Image[(int) (idle.getWidth() / idle.getHeight())];
        for (int i = 0; i < Hero.idle.length; i++)
            Hero.idle[i] = new WritableImage(idleReader, i * (int) idle.getHeight(), 0, (int) idle.getHeight(), (int) idle.getHeight());

        Image walking = new Image("Quarter_4/NewSideScroller/Images/Hero/walk.png");
        PixelReader walkingReader = walking.getPixelReader();
        Hero.walking = new Image[(int) (walking.getWidth() / walking.getHeight())];
        for (int i = 0; i < Hero.walking.length; i++)
            Hero.walking[i] = new WritableImage(walkingReader, i * (int) walking.getHeight(), 0, (int) walking.getHeight(), (int) walking.getHeight());

        Image slashing = new Image("Quarter_4/NewSideScroller/Images/Hero/slash.png");
        PixelReader slashingReader = slashing.getPixelReader();
        Hero.slashing = new Image[(int) (slashing.getWidth() / slashing.getHeight())];
        for (int i = 0; i < Hero.slashing.length; i++)
            Hero.slashing[i] = new WritableImage(slashingReader, i * (int) slashing.getHeight(), 0, (int) slashing.getHeight(), (int) slashing.getHeight());

        Image death = new Image("Quarter_4/NewSideScroller/Images/Hero/death.png");
        PixelReader deathReader = death.getPixelReader();
        Hero.death = new Image[(int) (death.getWidth() / death.getHeight())];
        for (int i = 0; i < Hero.death.length; i++)
            Hero.death[i] = new WritableImage(deathReader, i * (int) death.getHeight(), 0, (int) death.getHeight(), (int) death.getHeight());
    }

    public Hero(int locationX, int locationY, int width, int height) {
        super(locationX, locationY, width, height, null);
        
        currentState = State.IDLE;
        direction = true;
        stateConsumed = false;
    }

    public boolean inTemporaryState() {
        return lastState != null;
    }

    public void setState(State state, long now) {
        if (lastState == null) {
            stateConsumed = false;
            this.currentState = state;
            this.startingTime = now;
            this.lastState = null;
        }
    }

    public void setTemporaryState(State state, long now) {
        this.lastState = this.currentState;
        this.currentState = state;
        this.startingTime = now;
        stateConsumed = false;
    }

    public State getState() {
        return this.currentState;
    }

    public Image getImage(long now) {
        if (lastState != null && (int) ((now - this.startingTime) / currentState.animationSpeed) % currentState.images.length >= currentState.images.length - 1) {
            this.currentState = this.lastState;
            this.lastState = null;
            this.startingTime = now;
        }
        return currentState.images[(int) ((now - this.startingTime) / currentState.animationSpeed) % currentState.images.length];
    }

    public boolean getDirection() { return direction; }
    public void setDirection(boolean direction) { this.direction = direction; }

    public boolean isStateConsumed() { return stateConsumed; }
    public void consumeState() { this.stateConsumed = true; }

    public enum State {

        IDLE (idle, (1.0 / 8) * 1E9),
        WALKING (walking, (1.0 / 11) * 1E9),
        SLASHING (slashing, (1.0 / 11) * 1E9),
        DEATH (death, (1.0 / 8) * 1E9);

        private double animationSpeed;
        private Image[] images;

        State(Image[] images, double animationSpeed) {
            this.images = images;
            this.animationSpeed = animationSpeed;
        }

    }

}
