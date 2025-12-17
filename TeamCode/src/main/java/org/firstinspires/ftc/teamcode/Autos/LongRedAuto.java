package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.Commands.AimCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoFireCommand;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.LongRed;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.ShortRed;

@Autonomous
public class LongRedAuto extends CommandOpMode {

    RobotContainer robot;

    @Override
    public void initialize() {

        robot = new RobotContainer(hardwareMap, telemetry);

        robot.getFollower().setPose(ShortRed.startPose);

        schedule(
                new RunCommand(robot::Periodic),
                new SequentialCommandGroup(
                        new WaitCommand(1000),
                        new AimCommand(robot.shooterSub,true, false).alongWith(
                                new SequentialCommandGroup(
                                        new WaitCommand(3000),
                                        new AutoFireCommand(robot.intakeSub, robot.indexerSub)
                                )
                        ).withTimeout(9000)
                        //new FollowPathCommand(robot.getFollower(), LongRed.shootPreload(), true, .5)
                )
        );
    }
}