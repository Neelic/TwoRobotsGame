package model.FieldObjects.Devices;

import model.Events.DeviceActionEvent;
import model.Events.DeviceActionListener;
import model.Events.DevicePutEvent;
import model.Events.DevicePutListener;
import model.FieldObjects.Robots.Robot;
import model.Navigation.Field;
import model.Navigation.Position;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public abstract class Device {

    protected Field _field;

    public void setField(Field field) {
        _field = field;
    }

    private Position _position;

    public Position position() {
        return _position;
    }

    public boolean putDevice(Position pos) {
        if (_field != null && canReplace(pos)) {
            _position = pos.clone();
            _field.addDevice(this);
            putDeviceEvent(_position);
            return true;
        } else {
            return false;
        }
    }

    public abstract boolean canAddToRobot(Robot robot);

    protected abstract boolean canReplace(Position position);

    public abstract int startAction(Robot robot);

    public abstract Device clone();

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        return this.getClass() == object.getClass();
    }

    public abstract boolean canMoveOnSwamp();

    private final static List<DevicePutListener> _PutDeviceListeners = new ArrayList<>();

    private final static List<DeviceActionListener> _ActionDeviceListeners = new ArrayList<>();

    public static void addListener(@NotNull DevicePutListener listener) {
        if (!_PutDeviceListeners.contains(listener)) {
            _PutDeviceListeners.add(listener);
        }
    }

    public static void removeListener(@NotNull DevicePutListener listener) {
        _PutDeviceListeners.remove(listener);
    }

    public static void addListener(@NotNull DeviceActionListener listener) {
        if (!_ActionDeviceListeners.contains(listener)) {
            _ActionDeviceListeners.add(listener);
        }
    }

    public static void removeListener(@NotNull DeviceActionListener listener) {
        _ActionDeviceListeners.remove(listener);
    }

    protected void putDeviceEvent(Position position) {
        for (DevicePutListener obj: _PutDeviceListeners) {
            obj.deviceIsPut(new DevicePutEvent(this, position));
        }
    }

    protected void actionDeviceEvent() {
        for (DeviceActionListener obj: _ActionDeviceListeners) {
            obj.startDeviceAction(new DeviceActionEvent(this));
        }
    }
}
