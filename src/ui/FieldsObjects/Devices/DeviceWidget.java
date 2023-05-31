package ui.FieldsObjects.Devices;

import model.FieldObjects.Devices.Device;
import org.jetbrains.annotations.NotNull;
import ui.FieldsObjects.FieldObjectWidget;

public abstract class DeviceWidget extends FieldObjectWidget {

    private final Device _device;

    public DeviceWidget(@NotNull Device device) {
        _device = device;
    }

    public Device device() {
        return _device;
    }
}
