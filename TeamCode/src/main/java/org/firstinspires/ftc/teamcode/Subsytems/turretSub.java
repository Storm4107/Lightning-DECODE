package org.firstinspires.ftc.teamcode.Subsytems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class turretSub extends SubsystemBase {

    private final DcMotorEx turret;

    private static final double TICKS_PER_DEGREE = 5.5; // adjust to turret
    private static final double MAX_ANGLE = 180;
    private static final double MIN_ANGLE = -90;

    // PID
    private final PIDFController turretPID =
            new PIDFController(0.02, 0.0, 0.0005, 0.0);


    private double turretTargetTicks = 0;

    public turretSub(HardwareMap hMap) {

        turret = hMap.get(DcMotorEx.class,"turret");

        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        turretPID.setTolerance(TICKS_PER_DEGREE * 0.15);
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

    public void turretOverride(){
        turret.setTargetPosition(0);

    }

    public double getTurretAngleDegrees() {
        return turret.getCurrentPosition() / TICKS_PER_DEGREE;
    }

    @Override
    public void periodic() {

        double currentTicks = turret.getCurrentPosition();

        double turretPower = turretPID.calculate(currentTicks);


        // Clamp power
        turretPower = Math.max(-1.0, Math.min(1.0, turretPower));

        turret.setPower(turretPower);
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