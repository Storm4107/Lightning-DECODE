package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.doorCommand;
import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.doorSub;
import org.firstinspires.ftc.teamcode.Subsystems.flywheelSub;
import org.firstinspires.ftc.teamcode.Subsystems.hoodSub;
import org.firstinspires.ftc.teamcode.Subsystems.intakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.turretSub;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.targetingUtil;

public class robotContainer {

    public GamepadEx driver;
    public turretSub turret;
    public flywheelSub flywheel;
    public intakeSub intake;
    public doorSub door;
    public  hoodSub hood;
    public Follower follower;
    public targetingUtil targetingUtil;
    public Telemetry telemetry;

    //this is for TElE
    public robotContainer(HardwareMap hardwareMap, Gamepad driver, Alliance alliance) {

        this.driver = new GamepadEx(driver);

        telemetry = null;

        turret = new turretSub(hardwareMap);
        hood = new hoodSub(hardwareMap);
        flywheel = new flywheelSub(hardwareMap);
        intake = new intakeSub(hardwareMap);
        door = new doorSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);

        configureSingleBindings();
        follower.startTeleopDrive();
    }

    //this is for auto
    public robotContainer(HardwareMap hardwareMap, Telemetry telemetry) {

        turret = new turretSub(hardwareMap);
        hood = new hoodSub(hardwareMap);
        flywheel = new flywheelSub(hardwareMap);
        intake = new intakeSub(hardwareMap);
        door = new doorSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);
    }

    public void configureSingleBindings(){
        if (driver == null) return;

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileHeld(
                new intakeCommand(intake, door,1)
        );

        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN).whileHeld(
                new intakeCommand(intake, door, -1)
        );
    }

    public void Periodic() {

        follower.update();

        if (telemetry != null)
            telemetry.addData("path", follower.getCurrentPath());
    }
}