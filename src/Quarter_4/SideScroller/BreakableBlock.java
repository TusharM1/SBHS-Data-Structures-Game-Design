package Quarter_4.SideScroller;

import javafx.scene.image.Image;

public class BreakableBlock extends Block {

    private int health;

    public BreakableBlock(int locationX, int locationY, int width, int height, Image image, int health) {
        super(locationX, locationY, width, height, image);
        this.health = health;
    }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
}
