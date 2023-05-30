package ui.Navigation.Cells;

import model.Navigation.Position;
import ui.Imagine;

import java.awt.*;

public class SwampCellWidget extends CellWidget {
    private final Color _col = Imagine.SWAMP_COLOR;
    public SwampCellWidget(Position pos) {
        super(pos);
        setBackground(_col);
    }
}
