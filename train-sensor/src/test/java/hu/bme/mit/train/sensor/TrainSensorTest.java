package hu.bme.mit.train.sensor;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
public class TrainSensorTest {

    TrainController mockController;
    TrainUser mockUser;
    TrainSensor sensor;

    @Before public void init() {
        mockController = mock(TrainController.class);
        mockUser = mock(TrainUser.class);
        sensor = new TrainSensorImpl(mockController, mockUser);
    }

    @Test
    public void alarmTrueIfRelativeMarginSurpassed() {
        // Arrange
        when(mockController.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(50);
        // Act
        sensor.overrideSpeedLimit(10);
        // Assert
        verify(mockUser).setAlarmState(true);
    }

    @Test
    public void alarmTrueIfAbsoluteMarginSurpassed() {
        // Arrange
        when(mockController.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(50);
        // Act
        sensor.overrideSpeedLimit(501);
        // Assert
        verify(mockUser).setAlarmState(true);
    }

    @Test
    public void alarmTrueIfBothMarginsSurpassed() {
        // Arrange
        when(mockController.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(50);
        // Act
        sensor.overrideSpeedLimit(-1);
        // Assert
        verify(mockUser).setAlarmState(true);
    }

    @Test
    public void alarmFalseIfMarginsAreNotSurpassed() {
        // Arrange
        when(mockController.getReferenceSpeed()).thenReturn(50);
        sensor.overrideSpeedLimit(50);
        // Act
        sensor.overrideSpeedLimit(60);
        // Assert
        // called twice beacuse of the first overrideSpeedLimit method
        verify(mockUser, times(2)).setAlarmState(false);
    }
}
