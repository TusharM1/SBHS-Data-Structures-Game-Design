package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;

public class Block {

    private int locationX;
    private int locationY;
    private int width;
    private int height;
    private Image image;
    private boolean isConsumed;

    public Block(int locationX, int locationY, int width, int height, Image image) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public int getLocationX() { return locationX; }
    public void setLocationX(int locationX) { this.locationX = locationX; }

    public int getLocationY() { return locationY; }
    public void setLocationY(int locationY) { this.locationY = locationY; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    public boolean isConsumed() { return isConsumed; }
    public void consume() { isConsumed = true; }
}
