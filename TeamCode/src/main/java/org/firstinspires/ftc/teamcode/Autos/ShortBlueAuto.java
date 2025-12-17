package org.firstinspires.ftc.teamcode.Autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.ShortBlue;
import org.firstinspires.ftc.teamcode.pedroPathing.Paths.ShortRed;

@Autonomous
public class ShortBlueAuto extends CommandOpMode {
    RobotContainer robot;

    @Override
    public void initialize() {

        robot = new RobotContainer(hardwareMap, telemetry);

        robot.getFollower().setPose(ShortRed.startPose);

        schedule(
                new RunCommand(robot::Periodic),
                new SequentialCommandGroup(
                        new FollowPathCommand
                                (robot.getFollower(), ShortBlue.shootPreload(), true, 1),
                        new FollowPathCommand(robot.getFollower(), ShortBlue.lineUp1(), true, 1),
                        new FollowPathCommand(robot.getFollower(), ShortBlue.shoot6(), true, 1),
                        new FollowPathCommand(robot.getFollower(), ShortBlue.lineUp2(), true, 1),
                        new FollowPathCommand(robot.getFollower(), ShortBlue.shoot9(), true, 1)
                )
        );
    }
}