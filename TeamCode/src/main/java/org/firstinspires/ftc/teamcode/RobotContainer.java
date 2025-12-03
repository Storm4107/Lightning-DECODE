package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.BLUEtrackCommand;
import org.firstinspires.ftc.teamcode.Commands.REDtrackCommand;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSub;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class RobotContainer {

    public final GamepadEx driver;
    public final GamepadEx operator;

    public final IntakeSub intakeSub;
    public final LightsSub lightsSub;
    public final IndexerSub indexerSub;
    public final ShooterSub shooterSub;
    public final VisionSub visionSub;

    public final Follower follower;

    public RobotContainer(HardwareMap hardwareMap, Gamepad gpDriver, Gamepad gpOperator) {

        this.driver = new GamepadEx(gpDriver);
        this.operator = new GamepadEx(gpOperator);

        intakeSub  = new IntakeSub(hardwareMap);
        lightsSub  = new LightsSub(hardwareMap);
        indexerSub = new IndexerSub(hardwareMap);
        shooterSub = new ShooterSub(hardwareMap);
        visionSub = new VisionSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);
        configureBindings();

        follower.startTeleopDrive();
    }

    private void configureBindings() {

        //OPERATOR

        operator.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new intakeCommand(intakeSub, indexerSub));

        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new REDtrackCommand(lightsSub, visionSub, shooterSub));

        operator.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new BLUEtrackCommand(lightsSub, visionSub, shooterSub));

        operator.getGamepadButton(GamepadKeys.Button.B)
                .whileHeld(new ShootCommand(intakeSub, indexerSub));

        //DRIVER

        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(() -> follower.setPose(new Pose(0,0,0)));
    }
}
