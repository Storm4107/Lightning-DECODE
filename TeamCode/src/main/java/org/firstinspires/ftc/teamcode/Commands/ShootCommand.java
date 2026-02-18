package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.robocol.Command;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;

public class ShootCommand extends CommandBase {

    private final ShooterSub shooter;
    private final Follower follower;

    // Replace with your real target coordinates
    private static final double BLUE_TARGET_X = 0;
    private static final double BLUE_TARGET_Y = 144;
    private static final double RED_TARGET_X = 144;
    private static final double RED_TARGET_Y = 144;

    private boolean RED;

    public ShootCommand(ShooterSub shooter, Follower follower, boolean RED) {
        this.shooter = shooter;
        this.follower = follower;
        this.RED = RED;
        addRequirements(shooter);
    }

    @Override
    public void execute() {

        if (RED = true){
            Pose pose = follower.getPose();

            double robotX = pose.getX();
            double robotY = pose.getY();

            // Field distance to target
            double distanceToTarget =
                    Math.sqrt((Math.pow((RED_TARGET_X - robotX), 2) + Math.pow((RED_TARGET_Y - robotY), 2)));

            double velocity =
                    distanceToTarget; //tuned value

            shooter.setShoterVelocity(velocity);
        }
        if (RED = false){
            Pose pose = follower.getPose();

            double robotX = pose.getX();
            double robotY = pose.getY();

            // Field distance to target
            double distanceToTarget =
                    Math.sqrt(Math.pow((RED_TARGET_X - robotX), 2) + Math.pow((RED_TARGET_Y - robotY), 2));

            double distance =
                    (distanceToTarget);

            shooter.setShoterVelocity(distance);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.zeroPower();
    }
}