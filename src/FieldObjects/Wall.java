package FieldObjects;

import Navigation.MiddlePosition;
import Navigation.Position;

public class Wall {
    private final MiddlePosition _pos;
    public Wall(MiddlePosition pos) {
        _pos = pos;
    }

    public MiddlePosition position() {
        return _pos;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Wall) {
            return _pos.equals(((Wall) other)._pos);
        }
        return false;
    }
}
