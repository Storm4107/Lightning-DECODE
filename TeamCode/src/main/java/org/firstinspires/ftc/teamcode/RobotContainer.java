package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AimCommand;
import org.firstinspires.ftc.teamcode.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.*;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class RobotContainer {

    public GamepadEx driver;
    public GamepadEx operator;

    public final IntakeSub intakeSub;
    public final LightsSub lightsSub;
    public final IndexerSub indexerSub;
    public final ShooterSub shooterSub;
    public final VisionSub visionSub;
    public final SensorSub sensorSub;

    public final Follower follower;
    public Telemetry telemetry;
    public RobotContainer(HardwareMap hardwareMap, Gamepad driver, Gamepad operator) {

        this.driver = new GamepadEx(driver);
        this.operator = new GamepadEx(operator);

        telemetry = null;

        intakeSub = new IntakeSub(hardwareMap);
        lightsSub = new LightsSub(hardwareMap);
        indexerSub = new IndexerSub(hardwareMap);
        shooterSub = new ShooterSub(hardwareMap);
        visionSub = new VisionSub(hardwareMap);
        sensorSub = new SensorSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);

        configureBindings();
        follower.startTeleopDrive();
    }

    public RobotContainer(HardwareMap hardwareMap, Telemetry telemetry) {

        this.telemetry = telemetry;

        intakeSub = new IntakeSub(hardwareMap);
        lightsSub = new LightsSub(hardwareMap);
        indexerSub = new IndexerSub(hardwareMap);
        shooterSub = new ShooterSub(hardwareMap);
        visionSub = new VisionSub(hardwareMap);
        sensorSub = new SensorSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);
    }

    private void configureBindings() {
        if (driver == null || operator == null) return;

        operator.getGamepadButton(GamepadKeys.Button.DPAD_RIGHT)
                .whileHeld(new AimCommand(shooterSub, true, false));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_LEFT)
                .whileHeld(new AimCommand(shooterSub, false, false));

        operator.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new intakeCommand(intakeSub,sensorSub, true));

        operator.getGamepadButton(GamepadKeys.Button.Y)
                .whileHeld(new intakeCommand(intakeSub,sensorSub, false));

        operator.getGamepadButton(GamepadKeys.Button.DPAD_UP)
                .whileHeld(new AimCommand(shooterSub, false, true));

        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld (new IndexCommand(indexerSub, false,false));

        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new IndexCommand( indexerSub, true, false));
    }
    
    public void Periodic() {

        follower.update();

        if (telemetry != null)
            telemetry.addData("path", follower.getCurrentPath());

        indexerSub.periodic();
        lightsSub.periodic();
        sensorSub.periodic();
        shooterSub.periodic();
        visionSub.periodic();

        if (telemetry != null) telemetry.update();
    }

    public Follower getFollower() {
        return follower;
    }
}
