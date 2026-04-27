package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class intakeSub extends SubsystemBase {

    private final DcMotorEx intake;

    private final CRServo leftIntake, rightIntake;

    public intakeSub(HardwareMap hMap){

        intake = hMap.get(DcMotorEx.class, "intake");

        leftIntake = hMap.get(CRServo.class, "leftIntake");
        rightIntake = hMap.get(CRServo.class, "rightIntake");

        intake.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        leftIntake.setDirection(DcMotorSimple.Direction.REVERSE);

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setPower(double power){
        intake.setPower(power);
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

    public void setReverse(double power){
        leftIntake.setPower(power);
        rightIntake.setPower(power);
    }

    public void stop(){
        intake.setPower(0);
        leftIntake.setPower(0);
        rightIntake.setPower(0);
    }
}