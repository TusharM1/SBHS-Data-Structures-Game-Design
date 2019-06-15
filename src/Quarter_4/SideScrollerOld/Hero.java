package Quarter_4.SideScrollerOld;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Hero extends Rectangle {

    public Hero() {
        super(OldGame.blockSize, OldGame.blockSize);
        setFill(Color.GREEN);
    }

    void setLocation(int x, int y) {
        setX(x);
        setY(y);
    }

}
