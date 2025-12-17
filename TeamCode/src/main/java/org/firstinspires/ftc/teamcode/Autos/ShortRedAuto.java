package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.Commands.AimCommand;
import org.firstinspires.ftc.teamcode.Commands.AutoFireCommand;
import org.firstinspires.ftc.teamcode.Commands.IndexCommand;
import org.firstinspires.ftc.teamcode.Commands.RampCommand;
import org.firstinspires.ftc.teamcode.Commands.intakeCommand;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.ShortRed;

@Autonomous
public class ShortRedAuto extends CommandOpMode {

    RobotContainer robot;

    @Override
    public void initialize() {

        robot = new RobotContainer(hardwareMap, telemetry);

        robot.getFollower().setPose(ShortRed.startPose);

        schedule(
                new RunCommand(robot::Periodic),
                new SequentialCommandGroup(
                        new FollowPathCommand(robot.getFollower(), ShortRed.shootPreload(), true, 1),
                        new AimCommand(robot.shooterSub, false,true).alongWith(
                                new AutoFireCommand(robot.intakeSub, robot.indexerSub)
                        ).withTimeout(5000),
                        new FollowPathCommand(robot.getFollower(), ShortRed.pickup6Turn(), true, 1),
                        new intakeCommand(robot.intakeSub,robot.sensorSub,true).alongWith(
                                new ParallelCommandGroup(
                                new IndexCommand(robot.indexerSub, false, true),
                                new FollowPathCommand(robot.getFollower(), ShortRed.lineUp1ball1(), true, .1),
                                new WaitCommand(1000),
                                        new FollowPathCommand(robot.getFollower(), ShortRed.lineUp1ball3(), true, .1)))
                                .withTimeout(3000),
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.getFollower(), ShortRed.shoot6(), true, .01),
                        new AimCommand(robot.shooterSub, false,true).alongWith(
                                new AutoFireCommand(robot.intakeSub, robot.indexerSub)
                        ).withTimeout(6000)
                )
        );
    }
}