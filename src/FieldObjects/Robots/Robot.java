package FieldObjects.Robots;

import Navigation.Direction;
import Navigation.Field;
import Navigation.Position;

abstract class Robot {
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
}
