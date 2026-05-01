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
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.longRed3Path;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.shortRed9Path;
import org.firstinspires.ftc.teamcode.robotContainer;
import org.firstinspires.ftc.teamcode.util.Alliance;

@Autonomous
public class longRed9 extends CommandOpMode {

    robotContainer robot;

    @Override
    public void initialize(){

        robot = new robotContainer(hardwareMap, telemetry);

        robot.follower.setPose( new Pose(112,12,Math.toRadians(90)));

        longRed3Path paths = new longRed3Path(robot.follower);

        robot.turret.setDefaultCommand(
                new turretCommand(robot.turret, robot.follower, ()-> Alliance.RED)
        );

        robot.hood.setDefaultCommand(
                new hoodCommand(robot.hood, robot.follower, () -> Alliance.RED)
        );

        schedule(
                new RunCommand( robot::Periodic ),
                new SequentialCommandGroup(
                        new autoShootCommand(robot.intake, robot.flywheel, robot.door, robot.follower, ()-> Alliance.RED).withTimeout(3000),
                        new FollowPathCommand(robot.follower, paths.park)
                        )
        );
        robot.turret.reset();
    }
}
