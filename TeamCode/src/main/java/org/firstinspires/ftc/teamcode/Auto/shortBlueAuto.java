package org.firstinspires.ftc.teamcode.Auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.RunCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;

import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.pedroPathing.paths.blueShort;

@Autonomous
public class shortBlueAuto extends CommandOpMode {

    RobotContainer robot;

    @Override
    public void initialize(){

        robot = new RobotContainer(hardwareMap, telemetry);

        robot.follower.setPose(blueShort.startPose);

        schedule(
                new RunCommand(robot::Periodic),
                new SequentialCommandGroup(
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.follower, blueShort.shootPreload())
                                .setGlobalMaxPower(.75),
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.follower, blueShort.pickupMiddle1()),
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.follower, blueShort.pickupMiddle2()),
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.follower, blueShort.shootMiddle()),
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.follower, blueShort.openGateSetup1()),
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.follower, blueShort.openGateSetup2()),
                        new WaitCommand(1000),
                        new FollowPathCommand(robot.follower, blueShort.pickupGate())
                        ));
    }
}