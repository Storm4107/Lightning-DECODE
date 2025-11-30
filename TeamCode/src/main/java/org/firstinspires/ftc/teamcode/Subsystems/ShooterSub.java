package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.AnalogSensor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorGroup;

public class ShooterSub extends SubsystemBase {

    private final DcMotorEx rightShooter;
    private final DcMotorEx leftShooter;

    private final Servo hood;

    public enum shooterStates{ SHOOT, IDLE}

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
            case SHOOT:
                rightShooter.setVelocity(6000);
                leftShooter.setVelocity(6000);
                break;

            case IDLE:
                rightShooter.setVelocity(0);
                leftShooter.setVelocity(0);
                break;
        }
    }
}
