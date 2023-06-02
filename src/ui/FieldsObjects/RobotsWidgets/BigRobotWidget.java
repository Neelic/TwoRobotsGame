package ui.FieldsObjects.RobotsWidgets;

import model.Events.RobotMoveEvent;
import model.Events.RobotMoveListener;
import model.Events.RobotStateChangeEvent;
import model.Events.RobotStateChangeListener;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.SmallRobot;
import org.jetbrains.annotations.NotNull;
import ui.Navigation.Cells.CellWidget;

import java.awt.*;

public class BigRobotWidget extends RobotWidget {
    private final BigRobot _robot;
    public BigRobotWidget(BigRobot robot) {
        super(robot);
        pathnameImg = "bigRobot.png";
        _robot = robot;
        setPreferredSize(new Dimension(CellWidget.CELL_SIZE - 5, CellWidget.CELL_SIZE - 5));
    }

    private final SmallRobotMoveList _listenerSmallRobotMove = new SmallRobotMoveList();

    private final SmallRobotStateList _listenerSmallRobotState = new SmallRobotStateList();

    public void addMoveListener(@NotNull SmallRobot robot) {
        robot.addListener(_listenerSmallRobotMove);
        robot.addListener(_listenerSmallRobotState);
    }

    private class SmallRobotMoveList implements RobotMoveListener {
        @Override
        public void robotMove(RobotMoveEvent event) {
            if (event.getSource() instanceof SmallRobot) {
                _robot.moveTo();
            }
        }
    }

    private class SmallRobotStateList implements RobotStateChangeListener {

        @Override
        public void robotStateChange(RobotStateChangeEvent event) {
            if (event.getSource() instanceof  SmallRobot
                    && event.getStatus() == RobotStateChangeEvent.StateStatus.DECREASE_MISS_COUNT) {
                _robot.moveTo();
            }
        }
    }
}
