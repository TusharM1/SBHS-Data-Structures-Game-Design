package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;

public class BackgroundLayer {

    private Image image;
    private int position;
    private int width;
    private int height;

    public BackgroundLayer(Image image, int position, int width, int height) {
        this.image = image;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    public int getPosition() { return position; }
    public void setPosition(int position) { this.position = position; }

    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }

    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
}
