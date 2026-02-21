package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Commands.TurretCommand;
import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.FlywheelSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.TurretSub;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class RobotContainer {

    public GamepadEx driver;

    public final IntakeSub intakeSub;
    public final TurretSub turretSub;
    public final FlywheelSub flywheelSub;
    public final Follower follower ;
    public Telemetry telemetry;

    public RobotContainer(HardwareMap hardwareMap, Gamepad driver) {

        this.driver = new GamepadEx(driver);

        telemetry = null;

        intakeSub = new IntakeSub(hardwareMap);
        turretSub = new TurretSub(hardwareMap);
        flywheelSub = new FlywheelSub(hardwareMap);
        follower = Constants.createFollower(hardwareMap);

        configureSingleBindings();
    }


    public RobotContainer(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;

        intakeSub = new IntakeSub(hardwareMap);
        turretSub = new TurretSub(hardwareMap);
        flywheelSub = new FlywheelSub(hardwareMap);
        follower = Constants.createFollower(hardwareMap);
    }

    public void Periodic() {

        follower.update();

        if (telemetry != null)
            telemetry.addData("path", follower.getCurrentPath());
    }

    public void configureSingleBindings(){
        if (driver == null) return;

        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new IntakeCommand(intakeSub, flywheelSub, true, true));
        driver.getGamepadButton(GamepadKeys.Button.DPAD_DOWN)
                .whileHeld(new IntakeCommand(intakeSub, flywheelSub, false, false));
    }
}