package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;

public class MovingBlock extends Block {

    private Orientation orientation;
    private int minimum, maximum, constant;
    private int direction;

    public MovingBlock(int minimum, int maximum, int constant, int width, int height, Image image, Orientation orientation) {
        super(orientation == Orientation.HORIZONTAL ? minimum : constant, orientation == Orientation.HORIZONTAL ? constant : minimum, width, height, image);
        this.minimum = minimum;
        this.maximum = maximum;
        this.constant = constant;
        this.orientation = orientation;
        this.direction = 1;
    }

    public void move() {
        if (orientation == Orientation.HORIZONTAL) {
            if (getLocationX() < minimum || getLocationX() > maximum)
                changeDirection();
            setLocationX(getLocationX() + direction);
        }
        else if (orientation == Orientation.VERTICAL) {
            if (getLocationY() < minimum || getLocationY() > maximum)
                changeDirection();
            setLocationY(getLocationY() + direction);
        }
    }

    public Orientation getOrientation() { return orientation; }
    public void setOrientation(Orientation orientation) { this.orientation = orientation; }

    public int getDirection() { return direction; }
    public void changeDirection() { this.direction *= -1; }

    public int getMinimum() { return minimum; }
    public void setMinimum(int minimum) { this.minimum = minimum; }

    public int getMaximum() { return maximum; }
    public void setMaximum(int maximum) { this.maximum = maximum; }

    public enum Orientation {
        HORIZONTAL, VERTICAL
    }

}
