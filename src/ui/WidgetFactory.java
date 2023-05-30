package ui;

import model.Navigation.MiddlePosition;
import model.Navigation.Position;
import org.jetbrains.annotations.NotNull;
import ui.FieldsObjects.ExitPointWidget;
import ui.FieldsObjects.RobotsWidgets.BigRobotWidget;
import ui.FieldsObjects.RobotsWidgets.SmallRobotWidget;
import ui.Navigation.MidCells.WallWidget;
import ui.Navigation.Cells.CellWidget;
import ui.Navigation.Cells.SwampCellWidget;
import ui.Navigation.FieldWidget;
import ui.Navigation.MidCells.MidCellWidget;

import java.util.LinkedList;
import java.util.List;

public class WidgetFactory {
    private FieldWidget _fieldWidget;

    private final List <CellWidget> _cellWidgets = new LinkedList<>();

    public void createFieldObjectWidgets() {
        cellWidget(_fieldWidget.field().exitPoint().position()).setObjectWidget(new ExitPointWidget());

        cellWidget(_fieldWidget.field().smallRobot().position())
                .setObjectWidget(new SmallRobotWidget(_fieldWidget.field().smallRobot()));

        var widget = new BigRobotWidget(_fieldWidget.field().bigRobot());
        widget.addMoveListener(_fieldWidget.field().smallRobot());
        cellWidget(_fieldWidget.field().bigRobot().position())
                .setObjectWidget(widget);
    }

    public void setFieldWidget(FieldWidget fieldWidget) {
        this._fieldWidget = fieldWidget;
    }

    public CellWidget createCellWidget(Position pos) {
        CellWidget cellWidget;
        if (_fieldWidget.field().swamp(pos) != null) {
            cellWidget = new SwampCellWidget(pos);
        } else {
            cellWidget = new CellWidget(pos);
        }
        _cellWidgets.add(cellWidget);
        return cellWidget;
    }

    public MidCellWidget createMidCellWidget(MiddlePosition midPos) {
        MidCellWidget midCellWidget;
        if (_fieldWidget.field().wall(midPos) != null) {
            midCellWidget = new WallWidget(midPos);
        } else {
            midCellWidget = new MidCellWidget(midPos);
        }
        return midCellWidget;
    }

    public CellWidget cellWidget(@NotNull Position pos) {
        for (CellWidget obj : _cellWidgets) {
            if (obj.position().equals(pos)) {
                return obj;
            }
        }
        return null;
    }
}
