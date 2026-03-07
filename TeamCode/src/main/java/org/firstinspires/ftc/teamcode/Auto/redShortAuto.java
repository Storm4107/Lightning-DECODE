package org.firstinspires.ftc.teamcode.Auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Commands.shootCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.redShortPath;
import org.firstinspires.ftc.teamcode.robotContainer;


@Autonomous
public class redShortAuto extends CommandOpMode {

    robotContainer robot;

    @Override
    public void initialize() {

        robot = new robotContainer(hardwareMap, telemetry);

        robot.follower.setPose( new Pose(120.12182741116752,125.96954314720813,Math.toRadians(-135)));

        schedule(
                new RunCommand(robot::Periodic),
                new SequentialCommandGroup(
                        new shootCommand(robot.shooterSub, false).alongWith(
                                new FollowPathCommand( robot.follower, redShortPath.shootPreload())).withTimeout(3000),
                        new shootCommand(robot.shooterSub, false).alongWith(
                new intakeCommand( robot.intakeSub, true)).withTimeout(2000),
                        new intakeCommand(robot.intakeSub, true).alongWith(
                                new FollowPathCommand(robot.follower, redShortPath.pickupMiddle())).withTimeout(3000),
                        new FollowPathCommand(robot.follower, redShortPath.openGate()),
                        new FollowPathCommand(robot.follower, redShortPath.shootMiddle()).alongWith(
                                new shootCommand(robot.shooterSub, false)).withTimeout(3000),
                        new shootCommand(robot.shooterSub, false).alongWith(
                                new intakeCommand(robot.intakeSub, true)).withTimeout(2000),
                        new intakeCommand(robot.intakeSub, true).alongWith(
                                new FollowPathCommand(robot.follower, redShortPath.pickupFront1())).withTimeout(2000),
                        new FollowPathCommand(robot.follower, redShortPath.pickupFront()).alongWith(
                                new intakeCommand(robot.intakeSub, true)).withTimeout(3000),
                        new FollowPathCommand(robot.follower, redShortPath.shootFront()).alongWith(
                                new shootCommand(robot.shooterSub, false)).withTimeout(3000),
                        new shootCommand(robot.shooterSub, false).alongWith(
                                new intakeCommand(robot.intakeSub, true)).withTimeout(3000),
                        new FollowPathCommand(robot.follower, redShortPath.leave())
            )
        );
    }
}
