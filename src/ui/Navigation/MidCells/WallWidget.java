package ui.Navigation.MidCells;

import model.Navigation.MiddlePosition;
import ui.Imagine;

import java.awt.*;

public class WallWidget extends MidCellWidget {
    private final Color _col = Imagine.WALL_COLOR;
    public WallWidget(MiddlePosition middlePosition) {
        super(middlePosition);
        setBackground(_col);
    }

    public WallWidget() {
        super();
        setBackground(_col);
    }
}
