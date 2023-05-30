package ui.FieldsObjects.RobotsWidgets;

import model.FieldObjects.Robots.SmallRobot;
import model.Navigation.Direction;
import ui.Navigation.Cells.CellWidget;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SmallRobotWidget extends RobotWidget implements KeyListener {
    private final SmallRobot _robot;
    public SmallRobotWidget(SmallRobot robot) {
        super(robot);

        pathnameImg = "smallRobot.png";
        _robot = robot;
        setPreferredSize(new Dimension(CellWidget.CELL_SIZE - 5, CellWidget.CELL_SIZE - 5));

        addKeyListener(this);
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            _robot.moveTo(Direction.south());
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            _robot.moveTo(Direction.north());
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            _robot.moveTo(Direction.west());
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            _robot.moveTo(Direction.east());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
