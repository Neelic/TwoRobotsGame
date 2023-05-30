package model.FieldObjects.Robots;

import model.Events.RobotMoveEvent;
import model.Events.RobotMoveListener;
import model.Events.RobotStateChangeEvent;
import model.Events.RobotStateChangeListener;
import model.FieldObjects.Devices.Device;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.Position;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Robot {
    protected Field _field;
    public abstract void setField(Field field);
    public Field field() {
        return _field;
    }

    public Robot(Field field) {
        _field = field;
    }
    protected Position _position;
    public Position position() {
        return _position;
    }

    public void  setPosition(Position pos) {
        _position = pos;
    }

    protected abstract boolean canMove(Direction dir);

    private final Map<Class, List<Device>> _devices = new HashMap<>();

    public void addDevice(@NotNull Device device, int count) {
        if (count < 1) {
            return;
        }

        if (!device.canAddToRobot(this)) {
            return;
        }

        List<Device> devices;

        if (!_devices.containsKey(device.getClass())) {
            devices = new ArrayList<>();
            _devices.put(device.getClass(), devices);
        } else {
            devices = _devices.get(device.getClass());
        }

        for (int i = 0; i < count; i++) {
            devices.add(device.clone());
        }
    }

    protected void putDevice(@NotNull Position pos, int chance) {
        if (chance < 0 || chance > 100) {
            return;
        }

        for (Map.Entry<Class, List<Device>> pair : _devices.entrySet()) {
            var size = pair.getValue().size();
            if (size < 1) {
                return;
            }

            var device = pair.getValue().get(size - 1);
            boolean isPutDevice = false;

            if (device != null && (chance == 100 || Math.random() * 100 < chance)) {
                device.setField(_field);
                isPutDevice = device.putDevice(pos);
            }

            if(isPutDevice) {
                pair.getValue().remove(device);
            }
        }
    }

    protected int countMissedMoves = 0;

    public void setCountMissedMoves(int count) {
        if (count < 0) {
            return;
        }

        countMissedMoves = count;
    }

    public int missedMoves() {
        return countMissedMoves;
    }

    private final ArrayList<RobotMoveListener> _listeners = new ArrayList<>();
    protected void robotMoveAction(Robot robot, Direction dir) {
        if (robot != null) {
            for (RobotMoveListener obj : _listeners) {
                obj.robotMove(new RobotMoveEvent(robot, dir));
            }
        }

    }

    public void addListener(RobotMoveListener listener) {
        if (!_listeners.contains(listener)) {
            _listeners.add(0, listener);
        }
    }

    public void removeListener(RobotMoveListener listener) {
        _listeners.remove(listener);
    }

    private final ArrayList<RobotStateChangeListener> _listenersState = new ArrayList<>();
    protected void robotStateChange(Robot robot) {
        if (robot != null) {
            for (RobotStateChangeListener obj : _listenersState) {
                obj.robotStateChange(new RobotStateChangeEvent(this));
            }
        }
    }

    public void addListener(RobotStateChangeListener listener) {
        if (!_listenersState.contains(listener))
            _listenersState.add(listener);
    }

    public void removeListener(RobotStateChangeListener listener) {
        _listenersState.remove(listener);
    }
}
