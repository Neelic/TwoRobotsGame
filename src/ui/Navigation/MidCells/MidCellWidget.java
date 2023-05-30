package ui.Navigation.MidCells;

import model.Navigation.Direction;
import model.Navigation.MiddlePosition;
import ui.Imagine;
import ui.Navigation.Cells.CellWidget;

import javax.swing.*;
import java.awt.*;

public class MidCellWidget extends JPanel {
    public static final int WALL_WIDTH = 5;
    private final MiddlePosition _midPosition;
    private final Color _col = Imagine.BACKGROUND_COLOR;
    public MidCellWidget(MiddlePosition middlePosition) {
        _midPosition = middlePosition;
        setPreferredSize(getDimensionByOrientation());
        setBackground(_col);
    }

    public MidCellWidget() {
        _midPosition = null;
        setPreferredSize(new Dimension(WALL_WIDTH, WALL_WIDTH));
        setBackground(_col);
    }

    private Dimension getDimensionByOrientation() {
        return (_midPosition.direction().equals(Direction.west()) || _midPosition.direction().equals(Direction.east())) ?
                new Dimension(WALL_WIDTH, CellWidget.CELL_SIZE) :
                new Dimension(CellWidget.CELL_SIZE , WALL_WIDTH);
    }
}
