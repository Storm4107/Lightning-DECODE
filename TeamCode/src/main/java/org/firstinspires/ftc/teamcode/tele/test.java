package org.firstinspires.ftc.teamcode.tele;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Commands.hoodCommand;
import org.firstinspires.ftc.teamcode.Commands.turretCommand;
import org.firstinspires.ftc.teamcode.robotContainer;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.targetingUtil;

@TeleOp(name = "test")
public class test extends CommandOpMode {

    private robotContainer robot;

    @Override
    public void initialize() {
        robot = new robotContainer(hardwareMap, gamepad1);

        robot.follower.setPose( new Pose(72,9,Math.toRadians(90)));

        robot.turret.setDefaultCommand(
                new turretCommand(robot.turret, robot.follower, ()-> Alliance.RED)
        );

        robot.hood.setDefaultCommand(
                new hoodCommand(robot.hood, robot.follower, () -> Alliance.RED)
        );

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        robot.follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        // Runs default commands automatically
        CommandScheduler.getInstance().run();

        robot.follower.update();

        telemetry.addData("TURRET_ENCODER", robot.turret.getTurretEncoder());
        telemetry.addData("hypo distance:", robot.targetingUtil.getDistance(robot.follower.getPose(), Alliance.RED));
        telemetry.addData("fieldAngel:", robot.targetingUtil.getAngle(robot.follower.getPose(), Alliance.RED));
        telemetry.addData("Angle:" , robot.turret.getAngle());
        telemetry.addData("Target RPM", robot.flywheel.calculateRPM(targetingUtil.getDistance(robot.follower.getPose(), Alliance.RED)));
        telemetry.addData("Current RPM", robot.flywheel.getVelocity());
        telemetry.addData("At Speed", robot.flywheel.atSpeed());
        telemetry.addData("Hood Actual", robot.hood.getPosition());

        telemetry.update();
    }
}