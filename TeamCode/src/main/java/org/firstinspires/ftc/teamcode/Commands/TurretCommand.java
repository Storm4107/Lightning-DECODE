package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.FlywheelSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.TurretSub;

public class TurretCommand extends CommandBase {

    private final TurretSub turret;
    private final Follower follower;

    // Replace with your real target coordinates
    private static final double BLUE_TARGET_X = 21; //this is tuned using a cartesian cordinate plane   22 this went to far right
    private static final double BLUE_TARGET_Y = 144;  // NOT PEDRO PLANE    144
    private static final double RED_TARGET_X = 0;    // IT IS THE STANDARD CARTSIAN CORDS
    private static final double RED_TARGET_Y = 0;

    private boolean RED;

    public TurretCommand(TurretSub turret, Follower follower, boolean RED) {
        this.turret = turret;
        this.follower = follower;
        this.RED = RED;
        addRequirements(turret);
    }

    @Override
    public void execute() {

        if (RED = true){
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
        else if (RED != true) {
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
        }


    }

    @Override
    public boolean isFinished() {
        // Hold current position when command ends
        return false;
    }
}