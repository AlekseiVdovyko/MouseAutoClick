package gmail.avdovyko.MousePosition;

import java.awt.*;
import java.awt.event.MouseAdapter;

public class MousePosition extends MouseAdapter {

    public MousePosition() {
    }

    public int positionX() {
        return MouseInfo.getPointerInfo().getLocation().x;
    }

    public int positionY() {
        return MouseInfo.getPointerInfo().getLocation().y;
    }

}
