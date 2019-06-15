package Quarter_4.NewSideScroller;

public class Block {

    private int locationX, locationY, width, height;

    public Block(int locationX, int locationY, int width, int height) {
        this.locationX = locationX;
        this.locationY = locationY;
        this.width = width;
        this.height = height;
    }

    public int getLocationX() { return locationX; }
    public void setLocationX(int locationX) { this.locationX = locationX; }

    public int getLocationY() { return locationY; }
    public void setLocationY(int locationY) { this.locationY = locationY; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
