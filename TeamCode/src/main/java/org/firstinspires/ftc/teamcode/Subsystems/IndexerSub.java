package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class IndexerSub extends SubsystemBase {

    private final DcMotorEx indexer;

    private final Servo ramp;

    public enum indexerStates{ INTAKING, IDLE, RAPIDFIRE}

    private indexerStates currentState = indexerStates.IDLE;

    public IndexerSub(HardwareMap hMap){
        indexer = hMap.get(DcMotorEx.class, "indexer");
        ramp = hMap.get(Servo.class, "ramp");
    }

    public void setState(IndexerSub.indexerStates newState) {
        currentState = newState;
    }

    public indexerStates getCurrentState(){
        return currentState;
    }

    @Override
    public void periodic() {
        switch (currentState) {
            case IDLE:
                indexer.setPower(0);
                ramp.setPosition(0.55);
                break;

            case INTAKING:
                indexer.setPower(.25);
                break;

            case RAPIDFIRE:
                indexer.setPower(.5);
                ramp.setPosition(1);
                break;
        }
    }
}
