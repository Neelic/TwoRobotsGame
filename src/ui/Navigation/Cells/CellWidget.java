package ui.Navigation.Cells;

import model.Navigation.Position;
import org.jetbrains.annotations.NotNull;
import ui.FieldsObjects.FieldObjectWidget;
import ui.Imagine;

import javax.swing.*;
import java.awt.*;

public class CellWidget extends JPanel {
    private final Position _position;
    public static final int CELL_SIZE = 75;
    private final Color _col = Imagine.CELL_COLOR;
    public CellWidget(Position pos) {
        _position = pos;
        setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
        setBackground(_col);
    }

    public Position position() {
        return _position;
    }

    private FieldObjectWidget _objectWidget;

    public void setObjectWidget(@NotNull FieldObjectWidget objectWidget) {
        _objectWidget = objectWidget;
        add(_objectWidget, 0);
    }

    public FieldObjectWidget removeObjectWidget() {
        FieldObjectWidget obj = _objectWidget;
        _objectWidget = null;
        remove(0);
        return obj;
    }
}
