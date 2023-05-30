package model.Navigation.Algorithms;

import model.Navigation.Position;
import model.Navigation.PositionRange;

public class TargetInZone {
    private int zoneWidth;
    private int zoneHeight;
    public TargetInZone(int height, int width) {
        if (height > 0 && width > 0) {
            zoneHeight = height;
            zoneWidth = width;
        } else
            throw new RuntimeException("Incorrect params");
    }
    public boolean isTargetInSquareZone(Position currentPosition, Position targetPosition) {
        PositionRange widthRange = new PositionRange(
                currentPosition.col() - zoneWidth / 2,
                currentPosition.col() + zoneWidth / 2);
        PositionRange heightRange = new PositionRange(
                currentPosition.row() - zoneHeight / 2,
                currentPosition.row() + zoneHeight / 2);
        return widthRange.contains(targetPosition.col()) && heightRange.contains(targetPosition.row());
    }
}
