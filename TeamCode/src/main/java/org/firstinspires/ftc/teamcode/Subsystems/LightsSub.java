package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class LightsSub extends SubsystemBase {

    private final RevBlinkinLedDriver lights;

    public enum lightStates {IDLE, INTAKING, SHOOTING, INDEXING, ERROR}

    private lightStates currentState = lightStates.IDLE;

    public LightsSub(HardwareMap hMap) {
        lights = hMap.get(RevBlinkinLedDriver.class, "lights");
    }

    public void setState(lightStates newState) {
        currentState = newState;
    }

    public lightStates getCurrentState(){
        return currentState;
    }

    public void periodic(){
        switch (currentState) {
            case IDLE:
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.BREATH_BLUE);
                break;

            case INTAKING:
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.CP1_2_COLOR_GRADIENT);
                break;

            case SHOOTING:
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.STROBE_RED);
        }
    }
}
