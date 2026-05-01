package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.Commands.autoShootCommand;
import org.firstinspires.ftc.teamcode.Commands.hoodCommand;
import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.Commands.turretCommand;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.longBlue9Path;
import org.firstinspires.ftc.teamcode.robotContainer;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous
public class longBlue9 extends CommandOpMode {

    robotContainer robot;

    @Override
    public void initialize(){

        robot = new robotContainer(hardwareMap, telemetry);

        robot.follower.setPose( new Pose(65.746192893401,9.705583756345199,Math.toRadians(90)));

        longBlue9Path paths = new longBlue9Path(robot.follower);

        robot.turret.setDefaultCommand(
                new turretCommand(robot.turret, robot.follower, ()-> Alliance.BLUE)
        );

        robot.hood.setDefaultCommand(
                new hoodCommand(robot.hood, robot.follower, () -> Alliance.BLUE)
        );


        schedule(
                new RunCommand( robot::Periodic ),
                new SequentialCommandGroup(
                        new FollowPathCommand(robot.follower, paths.shootPreload,true,1),
                        new WaitCommand(500),
                        new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.BLUE).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.pickupRow3)
                                .alongWith( new intakeCommand(robot.intake, robot.door, 1)).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.shootRow3),
                        new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.BLUE).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.pickupWall)
                                .alongWith( new intakeCommand(robot.intake, robot.door, .75)).withTimeout(9000),
                        new FollowPathCommand(robot.follower, paths.shootWall),
                        new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.BLUE).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.park)
                        )
        );
    }
}
