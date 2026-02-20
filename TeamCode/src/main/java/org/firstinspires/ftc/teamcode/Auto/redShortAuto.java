package org.firstinspires.ftc.teamcode.Auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.Commands.HoodCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.ShootCommand;
import org.firstinspires.ftc.teamcode.Commands.TurretCommand;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.FlywheelSub;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.blueShort;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.redShort;

@Autonomous
public class redShortAuto extends CommandOpMode {

    RobotContainer robot;

    @Override
    public void initialize(){

        robot = new RobotContainer(hardwareMap, telemetry);

        robot.follower.setPose( new Pose(120.12182741116752,125.96954314720813,Math.toRadians(-135)));

        schedule(
                new RunCommand(robot::Periodic),
                new SequentialCommandGroup(
                        new HoodCommand( robot.flywheelSub).withTimeout(1000),
                        new FollowPathCommand( robot.follower, redShort.shootPreload(), true, .5)
                                .alongWith( new ShootCommand(robot.flywheelSub, robot.follower, robot.intakeSub, true)).withTimeout(1500),
                                ( new ShootCommand( robot.flywheelSub, robot.follower, robot.intakeSub, true))
                                        .alongWith(new TurretCommand(robot.turretSub, robot.follower, true))
                                        .alongWith(new IntakeCommand(robot.intakeSub, robot.flywheelSub, true, false)).withTimeout(2500),
                        new IntakeCommand( robot.intakeSub, robot.flywheelSub, true, true)
                                .alongWith( new FollowPathCommand( robot.follower, redShort.pickupMiddle(), true, .1).setGlobalMaxPower(.5)).withTimeout(2750),
                        new FollowPathCommand( robot.follower, redShort.openGate(), true).setGlobalMaxPower(1),
                        new FollowPathCommand( robot.follower, redShort.shootMiddle(), true, .5),
                        new IntakeCommand( robot.intakeSub, robot.flywheelSub, true, false)
                                .alongWith( new TurretCommand( robot.turretSub, robot.follower, true))
                                .alongWith( new ShootCommand( robot.flywheelSub, robot.follower, robot.intakeSub, true)).withTimeout(2000),
                        new FollowPathCommand( robot.follower, redShort.pickupFront1(), true, .5),
                        new IntakeCommand( robot.intakeSub, robot.flywheelSub, true, true)
                                .alongWith( new FollowPathCommand( robot.follower, redShort.pickupFront())).withTimeout(2700),
                        new ShootCommand( robot.flywheelSub, robot.follower, robot.intakeSub, true)
                                .alongWith( new FollowPathCommand( robot.follower, redShort.shootFront())).withTimeout(2500),
                        new ShootCommand( robot.flywheelSub, robot.follower, robot.intakeSub, true)
                                .alongWith(new TurretCommand(robot.turretSub, robot.follower, true))
                                .alongWith(new IntakeCommand(robot.intakeSub, robot.flywheelSub, true, false)).withTimeout(2500)
                )
        );
    }
}