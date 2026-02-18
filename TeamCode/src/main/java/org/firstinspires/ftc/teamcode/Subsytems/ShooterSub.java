package org.firstinspires.ftc.teamcode.Subsytems;

import android.health.connect.datatypes.units.Velocity;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class ShooterSub extends SubsystemBase {

    private final DcMotorEx turret;
    private final DcMotorEx leftShooter, rightShooter;
    private final Servo door;
    private final Servo hood;

    private static final double TICKS_PER_DEGREE = 6; // adjust to turret
    private static final double MAX_ANGLE = 180;
    private static final double MIN_ANGLE = -90;

    // PID
    private final PIDFController turretPID =
            new PIDFController(0.01, 0.0, 0.000, 0.0);

    private final PIDFController shooterPID =
            new PIDFController(0.019, 0.0000, 0.0000, 0);

    private double turretTargetTicks = 0;

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

        shooterPID.setTolerance(50); // velocity tolerance

        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        turretPID.setTolerance(TICKS_PER_DEGREE * 1);
        turret.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setTurretTargetAngle(double angleDegrees) {

        // Normalize to [-180, 180]
        angleDegrees = normalizeDegrees(angleDegrees);

        // Clamp to mechanical limits
        angleDegrees = Math.max(MIN_ANGLE,
                Math.min(MAX_ANGLE, angleDegrees));

        turretTargetTicks = angleDegrees * TICKS_PER_DEGREE;

        turretPID.setSetPoint(turretTargetTicks);
    }

    public void setShoterVelocity(double distance) {

        shooterTargetVelocity = (Math.pow(distance, 2) * .26) - (1.25 * distance); // 0.26x^2−1.25x+1500

        shooterPID.setSetPoint(shooterTargetVelocity);
    }

    public double getShoterVelocity() {
        return shooterTargetVelocity;
    }

    public double getTurretAngleDegrees() {
        return turret.getCurrentPosition() / TICKS_PER_DEGREE;
    }

    public void zeroPower() {
        leftShooter.setPower(0);
        rightShooter.setPower(0);
    }

    @Override
    public void periodic() {

        double currentTicks = turret.getCurrentPosition();

        double turretPower = turretPID.calculate(currentTicks);

        double velocity = leftShooter.getVelocity();
        double shooterPower = shooterPID.calculate(velocity);

        // Clamp power
        turretPower = Math.max(-1.0, Math.min(1.0, turretPower));
        shooterPower = Math.max(-1.0, Math.min(1.0, shooterPower));

        turret.setPower(turretPower);

        leftShooter.setPower(shooterPower);
        rightShooter.setPower(shooterPower);
    }


    private double normalizeDegrees(double angle) {
        return Math.toDegrees(
                Math.atan2(
                        Math.sin(Math.toRadians(angle)),
                        Math.cos(Math.toRadians(angle))
                )
        );
    }
}