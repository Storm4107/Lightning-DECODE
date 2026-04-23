package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Commands.shootCommand;
import org.firstinspires.ftc.teamcode.Commands.turretCommand;
import org.firstinspires.ftc.teamcode.Subsystems.intakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.shooterSub;
import org.firstinspires.ftc.teamcode.Subsystems.turretSub;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class robotContainer {

    public GamepadEx driver;

    public final intakeSub intakeSub;
    public final shooterSub shooterSub;
    public final turretSub turret;

    public final Follower follower;
    public Telemetry telemetry;

    public robotContainer(HardwareMap hardwareMap, Gamepad driver) {

        this.driver = new GamepadEx(driver);

        telemetry = null;

        intakeSub = new intakeSub(hardwareMap);
        shooterSub = new shooterSub(hardwareMap);
        turret = new turretSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);

        configureSingleBindings();
        follower.startTeleopDrive();
    }

    public robotContainer(HardwareMap hardwareMap, Telemetry telemetry) {

        telemetry = null;

        intakeSub = new intakeSub(hardwareMap);
        shooterSub = new shooterSub(hardwareMap);
        turret = new turretSub(hardwareMap);


        follower = Constants.createFollower(hardwareMap);

        configureSingleBindings();
        follower.startTeleopDrive();
    }

    public void configureSingleBindings(){
        if (driver == null) return;

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new intakeCommand(intakeSub, true));
        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new intakeCommand(intakeSub, false));

        driver.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new shootCommand(shooterSub, false));

        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whileHeld(new shootCommand(shooterSub, true));

        driver.getGamepadButton(GamepadKeys.Button.BACK)
                .whileHeld(new turretCommand(turret,follower, true, true));
    }

    public void Periodic() {

        follower.update();

        if (telemetry != null)
            telemetry.addData("path", follower.getCurrentPath());
    }
}