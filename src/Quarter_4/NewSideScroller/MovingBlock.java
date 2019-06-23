package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;

public class MovingBlock extends Block {

    private Orientation orientation;
    private int minimum, maximum, constant;
    private boolean direction;

    public MovingBlock(int minimum, int maximum, int constant, int width, int height, Image image, Orientation orientation) {
        super(orientation == Orientation.HORIZONTAL ? minimum : constant, orientation == Orientation.HORIZONTAL ? constant : minimum, width, height, image);
        this.minimum = minimum;
        this.maximum = maximum;
        this.constant = constant;
        this.orientation = orientation;
        this.direction = true;
    }

    public void move() {
        if (orientation == Orientation.HORIZONTAL) {
            if (getLocationX() < minimum || getLocationX() > maximum)
                changeDirection();
            setLocationX(getLocationX() + (direction ? 1 : -1));
        }
        else if (orientation == Orientation.VERTICAL) {
            if (getLocationY() < minimum || getLocationY() > maximum)
                changeDirection();
            setLocationY(getLocationY() + (direction ? 1 : -1));
        }
    }

    public Orientation getOrientation() { return orientation; }
    public void setOrientation(Orientation orientation) { this.orientation = orientation; }

    public boolean getDirection() { return direction; }
    public void changeDirection() { this.direction = !this.direction; }

    public int getMinimum() { return minimum; }
    public void setMinimum(int minimum) { this.minimum = minimum; }

    public int getMaximum() { return maximum; }
    public void setMaximum(int maximum) { this.maximum = maximum; }

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

}
