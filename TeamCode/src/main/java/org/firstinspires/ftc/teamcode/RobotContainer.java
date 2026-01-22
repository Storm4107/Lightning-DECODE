package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;
import org.firstinspires.ftc.teamcode.Subsytems.LightsSub;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

public class RobotContainer {

    public GamepadEx driver;
    public GamepadEx operator;

    public final IntakeSub intakeSub;
    public final ShooterSub shooterSub;
    public  final LightsSub lightSub;

    public final Follower follower;
    public Telemetry telemetry;

    public RobotContainer(HardwareMap hardwareMap, Gamepad driver) {

        this.driver = new GamepadEx(driver);

        telemetry = null;

        intakeSub = new IntakeSub(hardwareMap);
        shooterSub = new ShooterSub(hardwareMap);
        lightSub = new LightsSub(hardwareMap);

        follower = Constants.createFollower(hardwareMap);

        configureSingleBindings();
        follower.startTeleopDrive();
    }

    public void configureSingleBindings(){
        if (driver == null) return;

        driver.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER)
                .whileHeld(new intakeCommand(intakeSub, true, lightSub));
        driver.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER)
                .whileHeld(new intakeCommand(intakeSub, false, lightSub));

        driver.getGamepadButton(GamepadKeys.Button.A)
                .whileHeld(new ShootCommand(shooterSub, false));

        driver.getGamepadButton(GamepadKeys.Button.Y)
                .whileHeld(new ShootCommand(shooterSub, true));

    }
}