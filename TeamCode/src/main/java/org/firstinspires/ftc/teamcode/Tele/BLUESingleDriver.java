package org.firstinspires.ftc.teamcode.Tele;


import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Commands.TurretCommand;
import org.firstinspires.ftc.teamcode.RobotContainer;

@TeleOp(name = "BLUEsingle")
public class BLUESingleDriver extends CommandOpMode {

    private RobotContainer robot;

    @Override
    public void initialize() {
        robot = new RobotContainer(hardwareMap, gamepad1);

        robot.follower.startTeleopDrive();

        robot.follower.setPose( new Pose(30,138,-90));

        robot.shooterSub.setDefaultCommand(
                new TurretCommand(robot.shooterSub, robot.follower, false)
        );

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        robot.follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        robot.follower.update();

        // Runs default commands automatically
        CommandScheduler.getInstance().run();

        telemetry.addData("Robot Heading",
                Math.toDegrees(robot.follower.getPose().getHeading()));
        telemetry.addData("Turret Angle",
                robot.shooterSub.getTurretAngleDegrees());
        telemetry.addData("velocity", robot.shooterSub.getShoterVelocity());
        telemetry.update();
    }
}