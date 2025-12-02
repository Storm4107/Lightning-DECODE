package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class ShooterSub extends SubsystemBase {

    private final DcMotorEx rightShooter;
    private final DcMotorEx leftShooter;

    private final Servo hood;

    public enum shooterStates{ LONGSHOT, MIDSHOT, SHORTSHOT, IDLE}

    private shooterStates currentState = shooterStates.IDLE;


    public ShooterSub(HardwareMap hMap) {

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class, "rightShooter");
        hood = hMap.get(Servo.class, "hood");

        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        leftShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftShooter.setVelocityPIDFCoefficients(30,0,0.15,2.5);
        rightShooter.setVelocityPIDFCoefficients(30,0,0.15,2.5);

        hood.setDirection(Servo.Direction.REVERSE);
    }

    public void setState(ShooterSub.shooterStates newState) {
        currentState = newState;
    }

    public shooterStates getCurrentState(){
        return currentState;
    }

    public double getShooterVelocity() {
        return leftShooter.getVelocity();
    }

    @Override
    public void periodic() {
        switch (currentState) {
            case LONGSHOT:
                rightShooter.setVelocity(6000);
                leftShooter.setVelocity(6000);
                hood.setPosition(.7);
                break;

            case MIDSHOT:
                rightShooter.setVelocity(5000);
                leftShooter.setVelocity(5000);
                hood.setPosition(.7);
                break;

            case SHORTSHOT:
                rightShooter.setVelocity(4000);
                leftShooter.setVelocity(4000);
                hood.setPosition(.2);
                break;

            case IDLE:
                rightShooter.setVelocity(0);
                leftShooter.setVelocity(0);
                hood.setPosition(0);
                break;
        }
    }
}
