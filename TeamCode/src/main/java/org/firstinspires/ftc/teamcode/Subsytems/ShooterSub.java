package org.firstinspires.ftc.teamcode.Subsytems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class ShooterSub extends SubsystemBase {

    private final DcMotorEx leftShooter, rightShooter;
    private final DcMotorEx turret;
    private final Servo door;
    private final Servo hood;

    public enum ShooterState {
        IDLE,
        OPEN,
        CLOSE
    }

    private ShooterState currentState = ShooterState.IDLE;

    private final PIDFController shooterPIDF =
            new PIDFController(0.019, 0.0000, 0.0000, 0);

    private final PIDFController turretPIDF =
            new PIDFController(0,0,0,0);

    private double shooterTargetVelocity = 0;

    public ShooterSub(HardwareMap hMap) {

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class,"rightShooter");
        turret = hMap.get(DcMotorEx.class,"turret");
        door = hMap.get(Servo.class,"door");
        hood = hMap.get(Servo.class,"hood");

        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        leftShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        shooterPIDF.setTolerance(50); // velocity tolerance
        turretPIDF.setTolerance(13);  //4,735 this many ticks per full revolution of the turret. the tolerance now will be set to 1 degree
    }

    public void setState(ShooterState newState) {
        if (newState != currentState) {
            shooterPIDF.reset();
        }

        currentState = newState;

        switch (newState) {
            case IDLE:
                shooterTargetVelocity = 0;
                break;
        }

        shooterPIDF.setSetPoint(shooterTargetVelocity);
    }

    public ShooterState getCurrentState() {
        return currentState;
    }

    public double getShooterVelocity() {
        return leftShooter.getVelocity();
    }

    public boolean atSpeed() {
        return shooterPIDF.atSetPoint();
    }

    @Override
    public void periodic() {

        double velocity = leftShooter.getVelocity();
        double power = shooterPIDF.calculate(velocity);

        power = Math.max(-1.0, Math.min(1.0, power));

        switch (currentState) {
            case IDLE:
                leftShooter.setPower(0);
                rightShooter.setPower(0);
                door.setPosition(1);
                hood.setPosition(0);
                break;

            case OPEN:
                door.setPosition(0);
                break;

            case CLOSE:
                door.setPosition(1);
                break;
        }
    }
}
