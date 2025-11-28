package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class SensorSub extends SubsystemBase {

    private final ColorSensor frontSensor, backSensor;

    private enum sensorStates{ EMPTY, TWO, FULL, PPG, PGP, GPP}

    private sensorStates currentState = sensorStates.EMPTY;

    public SensorSub(HardwareMap hMap) {
        frontSensor = hMap.get(ColorSensor.class, "frontSensor");
        backSensor = hMap.get(ColorSensor.class, "backSensor");
    }

    public sensorStates getCurrentState() {
        return currentState;
    }

    private boolean seesArtifact(ColorSensor s) {
        return ((DistanceSensor)s).getDistance(DistanceUnit.MM) < 40;
    }

    private boolean isPurple(ColorSensor s) {
        return s.blue() > s.red() * 1.5 && s.blue() > s.green() * 1.5;
    }

    private boolean isGreen(ColorSensor s) {
        return s.green() > s.red() * 1.5 && s.green() > s.blue() * 1.5;
    }

    private void updateState() {

    }

    @Override
    public void periodic() {
        updateState();
    }
}