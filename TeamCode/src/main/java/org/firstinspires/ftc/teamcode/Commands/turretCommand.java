package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.turretSub;

public class turretCommand extends CommandBase {

    private final turretSub turret;
    private final Follower follower;

    // Replace with your real target coordinates
    private static final double BLUE_TARGET_X = 0;
    private static final double BLUE_TARGET_Y = 144;
    private static final double RED_TARGET_X = 140;
    private static final double RED_TARGET_Y = 145;

    private boolean RED;
    private boolean OVERRIDE;

    public turretCommand(turretSub turret, Follower follower, boolean RED, boolean OVERRIDE) {
        this.turret = turret;
        this.follower = follower;
        this.RED = RED;
        this.OVERRIDE = OVERRIDE;
        addRequirements(turret);
    }

    @Override
    public void execute() {

        if (RED){
            Pose pose = follower.getPose();

            double robotX = pose.getX();
            double robotY = pose.getY();
            double robotHeading = pose.getHeading(); // radians

            // Field angle to target
            double targetFieldAngle = Math.atan2(
                    robotY - RED_TARGET_Y,
                    robotX - RED_TARGET_X
            );

            // Convert to robot-relative
            double turretAngleRad = targetFieldAngle - robotHeading;

            // Normalize to shortest path
            turretAngleRad = Math.atan2(
                    Math.sin(turretAngleRad),
                    Math.cos(turretAngleRad)
            );

            double turretAngleDeg = Math.toDegrees(turretAngleRad);

            turret.setTurretTargetAngle(turretAngleDeg);
        } else {
            Pose pose = follower.getPose();

            double robotX = pose.getX();
            double robotY = pose.getY();
            double robotHeading = pose.getHeading(); // radians

            // Field angle to target
            double targetFieldAngle = Math.atan2(
                    robotY - BLUE_TARGET_Y,
                    robotX - BLUE_TARGET_X
            );



            // Convert to robot-relative
            double turretAngleRad = targetFieldAngle - robotHeading;

            // Normalize to shortest path
            turretAngleRad = Math.atan2(
                    Math.sin(turretAngleRad),
                    Math.cos(turretAngleRad)
            );

            double turretAngleDeg = Math.toDegrees(turretAngleRad);

            turret.setTurretTargetAngle(turretAngleDeg);
        }


        if (OVERRIDE) {
            turret.turretOverride();
        }


    }

    @Override
    public boolean isFinished() {
        // Hold current position when command ends
        return false;
    }
}