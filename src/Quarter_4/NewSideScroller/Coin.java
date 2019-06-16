package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;

public class Coin extends Block {

    private boolean isConsumed;

    public Coin(int locationX, int locationY, int width, int height, Image image) {
        super(locationX, locationY, width, height, image);
        isConsumed = false;
    }

    public boolean isConsumed() { return isConsumed; }
    public void consume() { isConsumed = true; }

}
