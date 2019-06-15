package Quarter_4.NewSideScroller;

import javafx.scene.image.Image;

public class BackgroundLayer {

    private Image image;
    private double position;
    private double width;
    private double height;

    public BackgroundLayer(Image image, double position, double width, double height) {
        this.image = image;
        this.position = position;
        this.width = width;
        this.height = height;
    }

    public Image getImage() { return image; }
    public void setImage(Image image) { this.image = image; }

    public double getPosition() { return position; }
    public void setPosition(double position) { this.position = position; }

    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }

    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
}
