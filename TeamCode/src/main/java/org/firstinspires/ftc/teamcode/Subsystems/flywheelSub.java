package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class flywheelSub extends SubsystemBase {

    private final DcMotorEx leftShooter, rightShooter;

    private final PIDFController pidf = new PIDFController(0.025, 0, 0, 0);

    private double targetVelocity = 0;

    private final double MAX_VELOCITY = 5400;
    private final double MIN_VELOCITY = 0;

    public flywheelSub(HardwareMap hMap){

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class,"rightShooter");

        rightShooter.setDirection(DcMotorEx.Direction.REVERSE);

        leftShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        pidf.setTolerance(200);
    }

    public void setVelocity(double velocity) {

        velocity = Math.max(MIN_VELOCITY, Math.min(MAX_VELOCITY, velocity));

        targetVelocity = velocity;
        pidf.setSetPoint(targetVelocity);
    }

    public double getVelocity() {
        return leftShooter.getVelocity();
    }

    public boolean atSpeed() {
        return pidf.atSetPoint();
    }

    // Distance → RPM
    public double calculateRPM(double distance) {

        if (distance < 70.75) {
            return 1250 + (distance - 40.75) * (1500 - 1250) / (70.75 - 40.75);
        } else {
            return 1500 + (distance - 70.75) * (2000 - 1500) / (112.5 - 70.75);
        }
    }

    @Override
    public void periodic() {

        double currentVelocity = leftShooter.getVelocity();
        double power = pidf.calculate(currentVelocity);

        power = Math.max(-1, Math.min(1, power));

        leftShooter.setPower(power);
        rightShooter.setPower(power);
    }
}