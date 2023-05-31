package ui.Navigation;

import model.Events.*;
import model.FieldObjects.Devices.Device;
import model.FieldObjects.ExitPoint;
import model.FieldObjects.Robots.BigRobot;
import model.FieldObjects.Robots.Robot;
import model.FieldObjects.Robots.SmallRobot;
import model.Navigation.Direction;
import model.Navigation.Field;
import model.Navigation.MiddlePosition;
import model.Navigation.Position;
import org.jetbrains.annotations.NotNull;
import ui.EndGameWindowWidget;
import ui.FieldsObjects.Devices.DeviceWidget;
import ui.FieldsObjects.RobotsWidgets.RobotWidget;
import ui.Navigation.Cells.CellWidget;
import ui.Navigation.MidCells.MidCellWidget;
import ui.WidgetFactory;

import javax.swing.*;
import java.util.Objects;

public class FieldWidget extends JPanel {
    private final Field _field;
    private final WidgetFactory _factory;

    public FieldWidget(@NotNull Field field, @NotNull WidgetFactory factory) {
        _field = field;
        _factory = factory;
        _factory.setFieldWidget(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        showField();
        _factory.createFieldObjectWidgets();

        setListeners();
    }

    public Field field() {
        return _field;
    }

    private void showField() {

        add(createRowWalls(1, Direction.south()));

        for (int i = 1; i <= _field.height(); ++i) {
            add(createRow(i));
            add(createRowWalls(i, Direction.north()));
        }

    }

    private JPanel createRow(int rowIndex) {
        JPanel row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for(int i = 1; i <= _field.width(); ++i) {
            if (i == 1) {
                row.add(_factory.createMidCellWidget(new MiddlePosition(new Position(rowIndex,i), Direction.west())));
            }

            row.add(_factory.createCellWidget(new Position(rowIndex, i)));

            row.add(_factory.createMidCellWidget(new MiddlePosition(new Position(rowIndex, i), Direction.east())));
        }
        return row;
    }

    private JPanel createRowWalls(int rowIndex, Direction direction) {
        if(Objects.equals(direction, Direction.east()) || Objects.equals(direction, Direction.west())) {
            throw new IllegalArgumentException();
        }
        var row = new JPanel();
        row.setLayout(new BoxLayout(row, BoxLayout.X_AXIS));

        for(int i = 1; i <= _field.width(); ++i)
        {
            row.add(new MidCellWidget());
            row.add(_factory.createMidCellWidget(new MiddlePosition(new Position(rowIndex, i), direction)));
        }

        row.add(new MidCellWidget());
        return row;
    }

    private final RobotController _robotController = new RobotController();

    private class RobotController implements RobotMoveListener {
        @Override
        public void robotMove(RobotMoveEvent event) {
            Robot source = (Robot) event.getSource();
            Position sourcePosition = source.position();
            RobotWidget sourceWidget = (RobotWidget) _factory.cellWidget(
                    sourcePosition.nextPosition(event.getDirection().opposite())
            ).removeObjectWidget();

            if (sourceWidget != null) {
                _factory.cellWidget(sourcePosition).setObjectWidget(sourceWidget);
                if (source instanceof SmallRobot) {
                    sourceWidget.requestFocus();
                }
            }

            repaint();
        }
    }

    private final GameOverController _gameOverController = new GameOverController();

    private class GameOverController implements GameOverActionListener {

        @Override
        public void gameOverEvent(GameOverActionEvent event) {
            var tmp = _factory.cellWidget(event.getRobot().position()).removeObjectWidget();
            tmp.setVisible(false);

            if (event.getSource() instanceof BigRobot) {
                EndGameWindowWidget.playerLoseWindow();
            } else if (event.getSource() instanceof ExitPoint) {
                EndGameWindowWidget.playerWinWindow();
            }
        }
    }

    private final DevicePutController _devicePutController = new DevicePutController();

    private class DevicePutController implements DevicePutListener {

        @Override
        public void deviceIsPut(DevicePutEvent event) {
            DeviceWidget deviceWidget = _factory.createDeviceWidget((Device) event.getSource());

            CellWidget cellWidget = _factory.cellWidget(event.getPosition());

            if (deviceWidget != null) {
                cellWidget.add(deviceWidget);
                cellWidget.repaint();
            }
        }
    }

    private final DeviceActionController _deviceActionController = new DeviceActionController();

    private class DeviceActionController implements DeviceActionListener {

        @Override
        public void startDeviceAction(DeviceActionEvent event) {
            Device source = (Device) event.getSource();

            _factory.cellWidget(source.position());
        }
    }

    private void setListeners() {
        _field.smallRobot().addListener(_robotController);
        _field.bigRobot().addListener(_robotController);
        _field.bigRobot().addListener(_gameOverController);
        _field.exitPoint().addListener(_gameOverController);
        Device.addListener(_deviceActionController);
        Device.addListener(_devicePutController);
    }
}
