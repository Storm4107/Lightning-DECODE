package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class flywheelSub extends SubsystemBase {

    private final DcMotorEx leftShooter, rightShooter;

    private final PIDFController pidf = new PIDFController(1, 0, 0.01, 0);

    private final double MAX_VELOCITY = 5400;
    private final double MIN_VELOCITY = 0;

    private double targetVelocity = 0;

    private final double[] distances = {40.75,50.00, 81.68, 112.5, 152};
    private final double[] rpms = {1150, 1350, 1450, 1650, 2250};

    public flywheelSub(HardwareMap hMap){

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class,"rightShooter");

        rightShooter.setDirection(DcMotorEx.Direction.REVERSE);

        leftShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        rightShooter.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        rightShooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        pidf.setTolerance(150);
    }

    public void setVelocity(double velocity) {
        targetVelocity = Math.max(MIN_VELOCITY, Math.min(MAX_VELOCITY, velocity));
        pidf.setSetPoint(targetVelocity);
    }

    public void stop() {
        targetVelocity = 0;
        pidf.reset();

        leftShooter.setPower(0);
        rightShooter.setPower(0);
    }

    public double getVelocity() {
        return leftShooter.getVelocity();
    }

    public double getRightVelocity() {
        return rightShooter.getVelocity();
    }

    public boolean atSpeed() {
        return targetVelocity > 0 && pidf.atSetPoint();
    }

    public double calculateRPM(double distance){

        // clamp low
        if (distance <= distances[0]) {
            return rpms[0];
        }

        // clamp high
        if (distance >= distances[distances.length - 1]) {
            return rpms[rpms.length - 1];
        }

        // find segment
        for (int i = 0; i < distances.length - 1; i++) {

            if (distance <= distances[i + 1]) {

                double d1 = distances[i];
                double d2 = distances[i + 1];

                double r1 = rpms[i];
                double r2 = rpms[i + 1];

                // linear interpolation
                return r1 + (distance - d1) * (r2 - r1) / (d2 - d1);
            }
        }

        return rpms[rpms.length - 1]; // fallback
    }

    @Override
    public void periodic() {

        if (targetVelocity < 50) {
            leftShooter.setPower(0);
            rightShooter.setPower(0);
            return;
        }

        double currentVelocity =
                (leftShooter.getVelocity() + rightShooter.getVelocity()) / 2.0;

        double power = pidf.calculate(currentVelocity);

        // clamp power
        power = Math.max(-1, Math.min(1, power));

        // deadband to stop oscillation
        if (Math.abs(power) < 0.05) power = 0;

        leftShooter.setPower(power);
        rightShooter.setPower(power);
    }
}