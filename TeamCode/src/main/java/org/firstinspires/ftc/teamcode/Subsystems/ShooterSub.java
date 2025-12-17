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

    private final Servo frontTurret, backTurret;

    public enum shooterStates{IDLE, CLOSESHOT, LEFTAIM, RIGHTAIM, SHORTRAMP, FARRAMP}

    private shooterStates currentState = shooterStates.IDLE;


    public ShooterSub(HardwareMap hMap) {

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class, "rightShooter");
        hood = hMap.get(Servo.class, "hood");

        frontTurret = hMap.get(Servo.class, "frontTurret");
        backTurret = hMap.get(Servo.class, "backTurret");

        frontTurret.setDirection(Servo.Direction.REVERSE);

        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        leftShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftShooter.setVelocityPIDFCoefficients(35,0,0.8,3);
        rightShooter.setVelocityPIDFCoefficients(35,0,0.8,3);

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

            case IDLE:
                rightShooter.setVelocity(0);
                leftShooter.setVelocity(0);
                hood.setPosition(0);
                frontTurret.setPosition(0.39);
                backTurret.setPosition(0.39);// this is "0" for our case
                break;

            case CLOSESHOT:
                rightShooter.setVelocity(2400);
                leftShooter.setVelocity(2400);
                frontTurret.setPosition(0.39);
                backTurret.setPosition(0.39);
                hood.setPosition(.45);
                break;

            case LEFTAIM:
                frontTurret.setPosition(.2);
                backTurret.setPosition(.2);
                rightShooter.setVelocity(5250);
                leftShooter.setVelocity(5250);
                hood.setPosition(.6);
                break;

            case RIGHTAIM:
                frontTurret.setPosition(.65);
                backTurret.setPosition(.65);
                rightShooter.setVelocity(5250);
                leftShooter.setVelocity(5250);
                hood.setPosition(.6);
                break;

            case FARRAMP:
                rightShooter.setVelocity(5500);
                leftShooter.setVelocity(5500);
                break;

            case SHORTRAMP:
                rightShooter.setVelocity(2400);
                leftShooter.setVelocity(2400);
                hood.setPosition(.45);
                break;
        }
    }
}
