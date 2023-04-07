package Navigation;

public class MiddlePosition {
    private Position _pos;
    private Direction _dir;

    public MiddlePosition(Position pos, Direction dir) {
        if (!pos.isValid())
            throw new RuntimeException("Middle position not create");
        _dir = dir;
        _pos = pos;

        normalize();
    }

    public Position position() {
        return _pos;
    }

    public Direction direction() {
        return _dir;
    }

    private void normalize() {

        if(_dir.equals(Direction.south()) && _pos.hasNextPosition(_dir))
        {
            _pos = _pos.nextPosition(_dir);
            _dir = Direction.north();
        }

        if(_dir.equals(Direction.east()) && _pos.hasNextPosition(_dir))
        {
            _pos = _pos.nextPosition(_dir);
            _dir= Direction.west();
        }
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof MiddlePosition otherPosition) {
            return _pos.equals(otherPosition._pos) && _dir.equals(otherPosition._dir);
        }

        return false;
    }
}
