package Quarter_4.SideScrollerOld;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block extends Rectangle {

    public Block() {
        super(OldGame.blockSize, OldGame.blockSize);
        setFill(Color.RED);
    }

    public void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

//    @Override
//    public String toString() {
////        return "(" + x + ", " + y + ")";
//    }
}
