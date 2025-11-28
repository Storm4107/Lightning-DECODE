package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
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
    private final GamepadEx op2;
    private final GamepadEx op1;
    public final IntakeSub intakeSub;
    public final LightsSub lightsSub;
    public final IndexerSub indexerSub;
    public final ShooterSub shooterSub;

    Follower follower;

    public RobotContainer(HardwareMap hardwareMap, Gamepad gamepad2, Gamepad gamepad1) {
        op2 = new GamepadEx(gamepad2);
        op1 = new GamepadEx(gamepad1);

        intakeSub = new IntakeSub(hardwareMap);
        lightsSub = new LightsSub(hardwareMap);
        indexerSub = new IndexerSub(hardwareMap);
        shooterSub = new ShooterSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);

        configureBindings();

        follower.startTeleopDrive();
    }

    private void configureBindings() {
        op2.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new intakeCommand(intakeSub, lightsSub, indexerSub));

        op2.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new shootCommand(intakeSub,lightsSub,indexerSub,shooterSub));

        follower.setTeleOpDrive(
                -op1.getLeftY(),
                -op1.getLeftX(),
                -op1.getRightX(),
                true
        );
    }

    public IntakeSub getIntakeSub() {
        return intakeSub;
    }

    public LightsSub getLightsSub() {
        return lightsSub;
    }

    public IndexerSub getIndexerSub() {
        return indexerSub;
    }

    public ShooterSub getShooterSub() {
        return shooterSub;
    }

    public ShooterSub getShooterVelocity() {
        return shooterSub;
    }
}

