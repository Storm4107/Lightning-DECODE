package org.firstinspires.ftc.teamcode.tele;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.teamcode.Commands.autoShootCommand;
import org.firstinspires.ftc.teamcode.Commands.hoodCommand;
import org.firstinspires.ftc.teamcode.Commands.turretCommand;
import org.firstinspires.ftc.teamcode.robotContainer;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.targetingUtil;

@TeleOp(name = "redShortTele")
public class redShortTele extends CommandOpMode {

    private robotContainer robot;

    @Override
    public void initialize() {
        robot = new robotContainer(hardwareMap, gamepad1, Alliance.RED);

        robot.follower.setPose( new Pose(124.234,104.462,Math.toRadians(241.9084687)));

        robot.turret.setDefaultCommand(
                new turretCommand(robot.turret, robot.follower, ()-> Alliance.RED)
        );

        robot.hood.setDefaultCommand(
                new hoodCommand(robot.hood, robot.follower, () -> Alliance.RED)
        );

        robot.driver.getGamepadButton(GamepadKeys.Button.RIGHT_STICK_BUTTON).whileHeld(
                new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.RED)
        );

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        robot.follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        // Runs default commands automatically
        //CommandScheduler.getInstance().run();

        robot.follower.update();

        Pose pose = robot.follower.getPose();

        telemetry.addData("ROBOT X", pose.getX());
        telemetry.addData("ROBOT Y", pose.getY());
        telemetry.addData("ROBOT HEADING (deg)", Math.toDegrees(pose.getHeading()));

        double targetAngle = targetingUtil.getAngle(pose, Alliance.RED);

        telemetry.addData("TARGET ANGLE", targetAngle);
        telemetry.addData("TURRET ANGLE", robot.turret.getAngle());
        telemetry.addData("ERROR", targetAngle - robot.turret.getAngle());

        telemetry.addData("TURRET_ENCODER", robot.turret.getTurretEncoder());
        telemetry.addData("hypo distance:", robot.targetingUtil.getDistance(robot.follower.getPose(), Alliance.RED));
        telemetry.addData("fieldAngel:", robot.targetingUtil.getAngle(robot.follower.getPose(), Alliance.RED));
        telemetry.addData("Target RPM", robot.flywheel.calculateRPM(targetingUtil.getDistance(robot.follower.getPose(), Alliance.RED)));
        telemetry.addData("Current RPM", robot.flywheel.getVelocity());
        telemetry.addData("At Speed", robot.flywheel.atSpeed());
        telemetry.addData("Hood Actual", robot.hood.getPosition());
        telemetry.addData("rightShooterRPM", robot.flywheel.getRightVelocity());

        telemetry.update();
    }
}