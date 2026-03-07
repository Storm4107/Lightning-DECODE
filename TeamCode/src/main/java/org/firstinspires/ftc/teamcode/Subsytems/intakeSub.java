package org.firstinspires.ftc.teamcode.Subsytems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class intakeSub extends SubsystemBase {

    private final DcMotorEx intake; // plugged into Expansion Hub Port (1)

    //decelaring the Intake States
    public enum intakeStates {INTAKE, IDLE, REVERSE, RAPIDFIRE}
    // sets the current (default) state of the intake
    private intakeStates currentState = intakeStates.IDLE;

    //Constructor
    public intakeSub(final HardwareMap hMap) {
        intake = hMap.get(DcMotorEx.class, "intake");

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setState(intakeStates newState) {
        currentState = newState;
    }

    public intakeStates getCurrentState(){
        return currentState;
    }

    @Override
    public void periodic() {
        switch (currentState) {
            case INTAKE:
                intake.setPower(1);
                break;

            case REVERSE:
                intake.setPower(-1);
                break;

            case RAPIDFIRE:
                intake.setPower(1);
                break;

            case IDLE:
                intake.setPower(0);
                break;
        }
    }
}
