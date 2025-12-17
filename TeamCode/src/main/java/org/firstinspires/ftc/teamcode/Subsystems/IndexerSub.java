package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class IndexerSub extends SubsystemBase {

    private final DcMotorEx indexer;

    private final Servo ramp;

    public enum indexerStates{ INTAKING, IDLE, RAPIDFIRE, ROTATE120, REVERSE}

    private indexerStates currentState = indexerStates.IDLE;

    public IndexerSub(HardwareMap hMap){
        indexer = hMap.get(DcMotorEx.class, "indexer");
        ramp = hMap.get(Servo.class, "ramp");

        indexer.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        indexer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        indexer.setTargetPosition(500);
    }

    public void setState(IndexerSub.indexerStates newState) {
        currentState = newState;
    }

    public indexerStates getCurrentState(){
        return currentState;
    }

    public double indexerEncoder(){
        return indexer.getCurrentPosition();
    }

    public void resetEncoder(){
        indexer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    @Override
    public void periodic() {

        switch (currentState) {
            case IDLE:
                indexer.setPower(0);
                ramp.setPosition(0.65);
                indexer.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
                break;

            case INTAKING:
                indexer.setPower(.4);
                break;

            case REVERSE:
                indexer.setPower(-.6);
                break;

            case RAPIDFIRE:
                indexer.setPower(.3);
                ramp.setPosition(1);
                break;

            case ROTATE120:
                if ((indexer.getCurrentPosition() > 495) && (indexer.getCurrentPosition() < 505)) {
                    indexer.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            } else if ((indexer.getCurrentPosition() == 500) == false) {
                    indexer.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                    indexer.setPower(1);
                    ramp.setPosition(.65);
                }
        }
    }
}
