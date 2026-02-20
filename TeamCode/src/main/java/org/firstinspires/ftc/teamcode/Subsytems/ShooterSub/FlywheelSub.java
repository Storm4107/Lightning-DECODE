package org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class FlywheelSub extends SubsystemBase {

    private final DcMotorEx leftShooter, rightShooter;
    private final Servo door;
    private final Servo hood;

    // PID

    private final PIDFController flywheelPIDF =
            new PIDFController(0.02, 0.0000, 0.0000, 0);

    private double shooterTargetVelocity = 0;

    public FlywheelSub(HardwareMap hMap) {

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class,"rightShooter");
        door = hMap.get(Servo.class,"door");
        hood = hMap.get(Servo.class,"hood");

        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        leftShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        flywheelPIDF.setTolerance(50); // velocity tolerance
    }

    public void setShoterVelocity(double distance) {

        shooterTargetVelocity = (Math.pow(distance, 2) * .0213) + (6.07 * distance) + 810; // 0.26x^2−1.25x+1500

        flywheelPIDF.setSetPoint(shooterTargetVelocity);
    }

    public double getShoterVelocity() {
        return shooterTargetVelocity;
    }

    public void setDoorOpen() {
        door.setPosition(0);
    }

    public void setDoorClose() {
        door.setPosition(1);
    }

    public void setHoodUp() {
        hood.setPosition(1);
    }

    public boolean atSpeed() {
        return flywheelPIDF.atSetPoint();
    }

    @Override
    public void periodic() {

        double velocity = leftShooter.getVelocity();
        double shooterPower = flywheelPIDF.calculate(velocity);

        // Clamp power
        shooterPower = Math.max(-1.0, Math.min(1.0, shooterPower));

        leftShooter.setPower(shooterPower);
        rightShooter.setPower(shooterPower);
    }
}