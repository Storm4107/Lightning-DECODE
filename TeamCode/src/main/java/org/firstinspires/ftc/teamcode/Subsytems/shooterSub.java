package org.firstinspires.ftc.teamcode.Subsytems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class shooterSub extends SubsystemBase {

    private final DcMotorEx leftShooter;
    private final DcMotorEx rightShooter;
    private final Servo door;
    private final Servo hood;

    public enum ShooterState {
        IDLE,
        SHORTSHOT,
        LONGSHOT
    }

    private ShooterState currentState = ShooterState.IDLE;

    private final PIDFController pidf =
            new PIDFController(0.019, 0.0000, 0.0000, 0);

    private double targetVelocity = 0;

    public shooterSub(HardwareMap hMap) {

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class,"rightShooter");
        door = hMap.get(Servo.class,"door");
        hood = hMap.get(Servo.class,"hood");

        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        leftShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        pidf.setTolerance(50); // velocity tolerance
    }

    public void setState(ShooterState newState) {
        if (newState != currentState) {
            pidf.reset();
        }

        currentState = newState;

        switch (newState) {
            case IDLE:
                targetVelocity = 0;
                break;

            case SHORTSHOT:
                targetVelocity = 1500;
                break;

            case LONGSHOT:
                targetVelocity = 1850;
                break;
        }

        pidf.setSetPoint(targetVelocity);
    }

    public ShooterState getCurrentState() {
        return currentState;
    }

    public double getShooterVelocity() {
        return leftShooter.getVelocity();
    }

    public boolean atSpeed() {
        return pidf.atSetPoint();
    }

    @Override
    public void periodic() {

        double velocity = leftShooter.getVelocity();
        double power = pidf.calculate(velocity);

        power = Math.max(-1.0, Math.min(1.0, power));

        switch (currentState) {
            case IDLE:
                leftShooter.setPower(0);
                rightShooter.setPower(0);
                door.setPosition(1);
                hood.setPosition(0);
                break;

            case SHORTSHOT:
                leftShooter.setPower(power);
                rightShooter.setPower(power);
                door.setPosition(0);
                hood.setPosition(0.9);
                break;

            case LONGSHOT:
                leftShooter.setPower(power);
                rightShooter.setPower(power);
                door.setPosition(0);
                hood.setPosition(0.95);
                break;
        }
    }
}