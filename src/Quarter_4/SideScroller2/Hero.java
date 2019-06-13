package Quarter_4.SideScroller2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hero extends Rectangle {

    public Hero() {
        super(NewGame.blockSize, NewGame.blockSize);
        setFill(Color.GREEN);
    }

    void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

}
