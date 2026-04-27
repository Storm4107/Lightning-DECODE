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
import org.firstinspires.ftc.teamcode.robotContainer;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.shortRed9Path;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous
public class shortRed9 extends CommandOpMode {

    robotContainer robot;

    @Override
    public void initialize(){

        robot = new robotContainer(hardwareMap, telemetry);

        robot.follower.setPose( new Pose(120.325,125.685,Math.toRadians(-135)));

        shortRed9Path paths = new shortRed9Path(robot.follower);

        robot.turret.setDefaultCommand(
                new turretCommand(robot.turret, robot.follower, ()-> Alliance.RED)
        );

        robot.hood.setDefaultCommand(
                new hoodCommand(robot.hood, robot.follower, () -> Alliance.RED)
        );

        schedule(
                new RunCommand( robot::Periodic ),
                new SequentialCommandGroup(
                        new FollowPathCommand(robot.follower, paths.shootPreload,true,1),
                        new WaitCommand(500),
                        new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.RED).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.pickupRow2)
                                .alongWith( new intakeCommand(robot.intake, robot.door, 1)).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.openGate),
                        new FollowPathCommand(robot.follower, paths.shootRow2),
                        new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.RED).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.pickupRow1)
                                .alongWith(new intakeCommand(robot.intake, robot.door, 1)).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.shootRow1),
                        new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.RED).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.park)
                        )
        );
    }
}
