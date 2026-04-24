package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDFController;

public class turretSub extends SubsystemBase {

    //to go 360 degrees the robot uses 2000 ticks or 5.6 ticks per degree

    private final DcMotor turret;

    private final PIDFController pidf = new PIDFController(0.01,0,0,0);
    private final double TICKS_PER_DEGREE = 5.6;
    private final double MAX_ANGLE = 180;
    private final double MIN_ANGLE = -180;

    public turretSub(HardwareMap hMap) {
        turret = hMap.get(DcMotor.class, "turret");

        turret.setDirection(DcMotorSimple.Direction.REVERSE);

        turret.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        turret.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        pidf.setTolerance(1 * TICKS_PER_DEGREE);  // this is equal to 5 degrees

    }

    public void setTargetAngle( double targetAngle) {

        targetAngle = Math.toDegrees(
                Math.atan2(
                        Math.sin(Math.toRadians(targetAngle)),
                        Math.cos(Math.toRadians(targetAngle))
                )
        );

        targetAngle = Math.max(MIN_ANGLE, Math.min(MAX_ANGLE, targetAngle));

        pidf.setSetPoint(targetAngle * TICKS_PER_DEGREE);
    }

    public double getAngle() {
        return turret.getCurrentPosition() / TICKS_PER_DEGREE;
    }

    public double getTurretEncoder(){
        return turret.getCurrentPosition();
    }

    @Override
    public void periodic() {

        double power = pidf.calculate(turret.getCurrentPosition());

        power = Math.max(-1, Math.min(1, power));

        turret.setPower(power);
    }
}
