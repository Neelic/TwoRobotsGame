package ui.FieldsObjects;

import ui.Navigation.Cells.CellWidget;

import java.awt.*;

public class ExitPointWidget extends FieldObjectWidget {
    public ExitPointWidget() {
        setPreferredSize(new Dimension(CellWidget.CELL_SIZE - 5, CellWidget.CELL_SIZE - 5));
        pathnameImg = "exit.png";
    }
}
