package model.Navigation;

public class Position {
    private final int _row;
    private final int _col;
    public Position(int row, int col) {
        if (!isValid(row, col))
            throw new RuntimeException("Position not create");
        _row = row;
        _col = col;
    }
    public Position nextPosition(Direction dir) {
        if (Direction.north().equals(dir))
            return new Position(_row + 1, _col);
        else if (Direction.south().equals(dir))
            return new Position(_row - 1, _col);
        else if (Direction.east().equals(dir))
            return new Position(_row, _col + 1);
        else if (Direction.west().equals(dir))
            return new Position(_row, _col - 1);
        else
            throw new RuntimeException("Incorrect direction");
    }
    public boolean hasNextPosition(Direction dir) {
        if (Direction.north().equals(dir))
            return isValid(_row + 1, _col);
        else if (Direction.south().equals(dir))
            return isValid(_row - 1, _col);
        else if (Direction.east().equals(dir))
            return isValid(_row, _col + 1);
        else if (Direction.west().equals(dir))
            return isValid(_row, _col - 1);
        else
            throw new RuntimeException("Incorrect direction");
    }
    public int col() {
        return _col;
    }

    public int row() {
        return _row;
    }

    //-------------------------------------------------
    private static PositionRange _horizontalRange = new PositionRange(1, 10);
    private static PositionRange _verticalRange = new PositionRange(1, 10);

    public static void setHorizontalRange(int min, int max){
        if(PositionRange.isValidRange(min, max)) {
            _horizontalRange = new PositionRange(min, max);
        }
    }

    public static PositionRange horizontalRange(){
        return _horizontalRange;
    }

    public static void setVerticalRange(int min, int max){
        if(PositionRange.isValidRange(min, max)) {
            _verticalRange = new PositionRange(min, max);
        }
    }

    public static PositionRange verticalRange(){
        return _verticalRange;
    }

    public boolean isValid(){
        return isValid(_row, _col);
    }

    public static boolean isValid(int row, int col){
        return _horizontalRange.contains(col) && _verticalRange.contains(row);
    }

    @Override
    public boolean equals(Object other){
        if(!isValid()) {
            throw new RuntimeException("Incorrect");
        }

        if(other instanceof Position otherPosition) {
            return _row == otherPosition._row && _col == otherPosition._col;
        }

        return false;
    }

    public Position clone() {
        return new Position(_row, _col);
    }

    public Position getMirrorPositionRelativeToVertical() {
        return new Position(_row, _horizontalRange.max() - _col + 1);
    }

    public boolean isBorderPosition() {
        return (_horizontalRange.max() == _col || _horizontalRange.min() == _col)
                || (_verticalRange.max() == _row || _verticalRange.min() == _row);
    }
}
