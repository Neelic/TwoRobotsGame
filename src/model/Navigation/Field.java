package model.Navigation;

import model.FieldObjects.Devices.Device;
import model.FieldObjects.ExitPoint;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.SmallRobot;
import model.FieldObjects.Swamp;
import model.FieldObjects.Wall;

import java.util.ArrayList;

public class Field {
    private static int _width = 10;
    public void setWidth(int width) {
        if (width > 1) {
            _width = width;
            Position.setHorizontalRange(1, width);
        }
    }

    public int width() {
        return _width;
    }

    private static int _height = 10;

    public void setHeight(int height) {
        if (height > 1) {
            _height = height;
            Position.setVerticalRange(1, height);
        }
    }

    public int height() {
        return _height;
    }
    //-----------------------------Small robot
    private SmallRobot _smallRobot;
    public SmallRobot smallRobot() {
        return _smallRobot;
    }

    public void setSmallRobot(SmallRobot smallRobot) {
        if (_smallRobot != null && _smallRobot != smallRobot)
            _smallRobot.setField(null);

        _smallRobot = smallRobot;

        if (smallRobot != null && smallRobot.field() != this)
            smallRobot.setField(this);
    }
    //-----------------------------Big Robot
    private BigRobot _bigRobot;
    public BigRobot bigRobot() {
        return _bigRobot;
    }

    public void setBigRobot(BigRobot bigRobot) {
        if (_bigRobot != null && _bigRobot != bigRobot)
            _bigRobot.setField(null);

        _bigRobot = bigRobot;

        if (bigRobot != null && bigRobot.field() != this)
            bigRobot.setField(this);
    }
    //-------------------------------Exit Point
    private ExitPoint _exitPoint;
    public void setExitPoint(ExitPoint point) {
        if (_exitPoint != null && _exitPoint != point)
            _exitPoint.setField(null);

        _exitPoint = point;

        if (point != null && point.field() != this)
            point.setField(this);
    }

    public ExitPoint exitPoint() {
        return _exitPoint;
    }
    //---------------------------------Swamps
    private final ArrayList<Swamp> _swamps = new ArrayList<>();
    public void addSwamp(Swamp swamp) {
        if (swamp != null && !_swamps.contains(swamp)) {
            boolean isCanAdd = true;
            for (int i = 0; i < _swamps.size() && isCanAdd; i++) {
                Swamp obj = _swamps.get(i);
                if (obj.position().equals(swamp.position()))
                    isCanAdd = false;
            }
            if (isCanAdd) {
                _swamps.add(swamp);
                return;
            }
        }
        throw new RuntimeException("Can't add object");
    }

    public Swamp swamp(Position position) {
        if (position != null) {
            for (Swamp obj : _swamps) {
                if (obj.position().equals(position))
                    return obj;
            }
        }
        return null;
    }

    public ArrayList<Swamp> swamps() {
        return _swamps;
    }

    //-----------------------------Walls
    private final ArrayList<Wall>_walls = new ArrayList<>();
    public void addWall(Wall wall){
        if (wall != null && !_walls.contains(wall)) {
            boolean isCanAdd = true;
            for (int i = 0; i < _walls.size() && isCanAdd; i++) {
                Wall obj = _walls.get(i);
                if (obj.position().equals(wall.position()))
                    isCanAdd = false;
            }
            if (isCanAdd) {
                _walls.add(wall);
                return;
            }
        }
        throw new RuntimeException("Can't add object");
    }

    public Wall wall(MiddlePosition midPosition) {
        if (midPosition != null) {
            for (Wall obj : _walls) {
                if (obj.position().equals(midPosition))
                    return obj;
            }
        }
        return null;
    }

    public ArrayList<Wall> walls() {
        return _walls;
    }

    //-------------------------------Devices

    private final ArrayList<Device> _devices = new ArrayList<>();

    public void addDevice(Device device) {
        if (device.position() != null && deviceByPosition(device.position()) == null) {
            _devices.add(device);
            System.out.println("Add Device to field on " + device.position().col() + " " + device.position().row());
        }
    }

    public Device deviceByPosition(Position pos) {

        for (Device obj : _devices) {
            if (pos.equals(obj.position())) {
                return  obj;
            }
        }

        return null;
    }

    public Device deviceByTypeAndPosition(Position position, Class deviceClass) {
        for (Device obj : _devices) {
            if (obj.getClass() == deviceClass && obj.position().equals(position)) {
                return obj;
            }
        }

        return null;
    }

    public void deleteDevice(Device device) {
        device.setField(null);
        _devices.remove(device);
        System.out.println("Remove " + device.getClass() + " on " + device.position().col() + " " + device.position().row());
    }
}
