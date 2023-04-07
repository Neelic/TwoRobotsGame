package FieldObjects;

import Navigation.Position;

public class Swamp {
    private final Position _pos;

    public Swamp(Position pos) {
        _pos = pos;
    }

    public Position position() {
        return _pos;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Swamp) {
            return _pos.equals(((Swamp) other)._pos);
        }
        return false;
    }
}
