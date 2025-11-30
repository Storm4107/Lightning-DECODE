package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Commands.shootCommand;
import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class RobotContainer {

    public final GamepadEx driver;
    public final GamepadEx operator;

    public final IntakeSub intakeSub;
    public final LightsSub lightsSub;
    public final IndexerSub indexerSub;
    public final ShooterSub shooterSub;

    public final Follower follower;

    public RobotContainer(HardwareMap hardwareMap, Gamepad gpDriver, Gamepad gpOperator) {

        this.driver = new GamepadEx(gpDriver);
        this.operator = new GamepadEx(gpOperator);

        intakeSub  = new IntakeSub(hardwareMap);
        lightsSub  = new LightsSub(hardwareMap);
        indexerSub = new IndexerSub(hardwareMap);
        shooterSub = new ShooterSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);
        configureBindings();

        follower.startTeleopDrive();
    }

    private void configureBindings() {

        operator.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new intakeCommand(intakeSub, lightsSub, indexerSub));

        operator.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new shootCommand(lightsSub,shooterSub));

        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whenPressed(() -> follower.setPose(new Pose(0,0,0)));
    }
}
