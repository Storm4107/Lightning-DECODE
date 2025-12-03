package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class LightsSub extends SubsystemBase {

    private final RevBlinkinLedDriver lights;

    public enum lightStates {IDLE, SHOOTING, LOCKEDRED, LOCKEDBLUE}

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
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.HOT_PINK);
                break;

            case SHOOTING:
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.DARK_GREEN);
                break;

            case LOCKEDRED:
                lights.setPattern(RevBlinkinLedDriver.BlinkinPattern.DARK_RED);
                break;
        }
    }
}
