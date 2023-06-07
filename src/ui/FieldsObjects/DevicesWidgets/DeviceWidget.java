package ui.FieldsObjects.DevicesWidgets;

import model.FieldObjects.Devices.Device;
import org.jetbrains.annotations.NotNull;
import ui.FieldsObjects.FieldObjectWidget;
import ui.Navigation.Cells.CellWidget;

import java.awt.*;

public abstract class DeviceWidget extends FieldObjectWidget {

    private final Device _device;

    public DeviceWidget(@NotNull Device device) {
        _device = device;
        setPreferredSize(new Dimension(CellWidget.CELL_SIZE - 5, CellWidget.CELL_SIZE - 5));
    }

    public Device device() {
        return _device;
    }
}
