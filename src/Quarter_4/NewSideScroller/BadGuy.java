package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class BadGuy extends MovingBlock {

    private Image[] idle;
    private Image[] walking;

    private State currentState;

    private long startingTime;

    public BadGuy(int minimum, int maximum, int constant, int width, int height, Orientation orientation) {
//        super(locationX, locationY, width, height, null);
        super(minimum, maximum, constant, width, height, null, orientation);

        Image idle = new Image("Quarter_4/NewSideScroller/Images/BadGuy/idle.png");
        PixelReader idleReader = idle.getPixelReader();
        this.idle = new Image[(int) (idle.getWidth() / idle.getHeight())];
        for (int i = 0; i < this.idle.length; i++)
            this.idle[i] = new WritableImage(idleReader, i * (int) idle.getHeight(), 0, (int) idle.getHeight(), (int) idle.getHeight());

        Image walking = new Image("Quarter_4/NewSideScroller/Images/BadGuy/fly.png");
        PixelReader walkingReader = walking.getPixelReader();
        this.walking = new Image[(int) (walking.getWidth() / walking.getHeight())];
        for (int i = 0; i < this.walking.length; i++)
            this.walking[i] = new WritableImage(walkingReader, i * (int) walking.getHeight(), 0, (int) walking.getHeight(), (int) walking.getHeight());

        currentState = State.IDLE;
    }

    public void setState(State state, long now) {
        this.currentState = state;
        this.startingTime = now;
    }

    public State getState() {
        return this.currentState;
    }

    public Image getImage(long now) {
        switch (currentState) {
            case IDLE:
                return idle[(int) ((now - this.startingTime) / ((1.0 / 8) * 1E9)) % idle.length];
            case WALKING:
                return walking[(int) ((now - this.startingTime) / ((1.0 / 11) * 1E9)) % walking.length];
            default:
                return null;
        }
    }

    @Override
    public boolean getDirection() { return !super.getDirection(); }

    public enum State {
        IDLE, WALKING
    }

}
