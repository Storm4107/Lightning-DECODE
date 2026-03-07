package org.firstinspires.ftc.teamcode.Auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Commands.shootCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.blueShortPath;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.redShortPath;
import org.firstinspires.ftc.teamcode.robotContainer;


@Autonomous
public class blueShortAuto extends CommandOpMode {

    robotContainer robot;

    @Override
    public void initialize() {

        robot = new robotContainer(hardwareMap, telemetry);

        robot.follower.setPose( new Pose(26,127,Math.toRadians(-45)));

        schedule(
                new RunCommand(robot::Periodic),
                new SequentialCommandGroup(
                        new shootCommand(robot.shooterSub, false).alongWith(
                                new FollowPathCommand( robot.follower, blueShortPath.shootPreload())).withTimeout(3000),
                        new shootCommand(robot.shooterSub, false).alongWith(
                new intakeCommand( robot.intakeSub, true)).withTimeout(2000),
                        new intakeCommand(robot.intakeSub, true).alongWith(
                                new FollowPathCommand(robot.follower, blueShortPath.pickupMiddle())).withTimeout(3000),
                        new FollowPathCommand(robot.follower, blueShortPath.openGate()),
                        new FollowPathCommand(robot.follower, blueShortPath.shootMiddle()).alongWith(
                                new shootCommand(robot.shooterSub, false)).withTimeout(3000),
                        new shootCommand(robot.shooterSub, false).alongWith(
                                new intakeCommand(robot.intakeSub, true)).withTimeout(2000),
                        new intakeCommand(robot.intakeSub, true).alongWith(
                                new FollowPathCommand(robot.follower, blueShortPath.pickupFront1())).withTimeout(2000),
                        new FollowPathCommand(robot.follower, blueShortPath.pickupFront()).alongWith(
                                new intakeCommand(robot.intakeSub, true)).withTimeout(3000),
                        new FollowPathCommand(robot.follower, blueShortPath.shootFront()).alongWith(
                                new shootCommand(robot.shooterSub, false)).withTimeout(3000),
                        new shootCommand(robot.shooterSub, false).alongWith(
                                new intakeCommand(robot.intakeSub, true)).withTimeout(3000),
                        new FollowPathCommand(robot.follower, blueShortPath.leave())
            )
        );
    }
}
