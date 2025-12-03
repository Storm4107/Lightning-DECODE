package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServo;
import com.seattlesolvers.solverslib.hardware.motors.CRServoGroup;

public class ShooterSub extends SubsystemBase {

    private final DcMotorEx rightShooter;
    private final DcMotorEx leftShooter;

    private final Servo hood;

    private final CRServoGroup turret;

    private final CRServo frontTurret, backTurret;

    public enum shooterStates{IDLE,SHOOT, CW, CCW, STOPTURRET} // cw means cloclwise and ccw means coutnerclockwise

    private shooterStates currentState = shooterStates.IDLE;


    public ShooterSub(HardwareMap hMap) {

        leftShooter = hMap.get(DcMotorEx.class,"leftShooter");
        rightShooter = hMap.get(DcMotorEx.class, "rightShooter");
        hood = hMap.get(Servo.class, "hood");

        frontTurret = new CRServo(hMap, "frontTurret");
        backTurret  = new CRServo(hMap, "backTurret");

        frontTurret.setInverted(true);

        turret = new CRServoGroup(frontTurret, backTurret);

        rightShooter.setDirection(DcMotorSimple.Direction.REVERSE);

        leftShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightShooter.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftShooter.setVelocityPIDFCoefficients(30,0,0.15,2.5);
        rightShooter.setVelocityPIDFCoefficients(30,0,0.15,2.5);

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
                break;

            case SHOOT:
                hood.setPosition(.7);
                break;

            case CCW:
                turret.set(1);
                break;

            case CW:
                turret.set(-1);
                break;

            case STOPTURRET:
                turret.set(0);
                break;
        }
    }

    public double variableShot(double distance){
        return distance;
    }
}
