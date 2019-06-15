package Quarter_4.SideScroller;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {

    public Block() {
        super(Game.blockSize, Game.blockSize);
        setFill(Color.RED);
    }

    public Block(double x, double y) {
        super(x, y, Game.blockSize, Game.blockSize);
        setFill(Color.RED);
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

//    @Override
//    public String toString() {
////        return "(" + x + ", " + y + ")";
//    }
}
