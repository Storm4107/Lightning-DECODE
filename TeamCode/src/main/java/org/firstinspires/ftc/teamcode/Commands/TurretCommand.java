package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;

public class TurretCommand extends CommandBase {

    private final ShooterSub shooter;
    private final Follower follower;

    // Replace with your real target coordinates
    private static final double TARGET_X = 144;
    private static final double TARGET_Y = 36;

    public TurretCommand(ShooterSub shooter, Follower follower) {
        this.shooter = shooter;
        this.follower = follower;
        addRequirements(shooter);
    }

    @Override
    public void execute() {

        Pose pose = follower.getPose();

        double robotX = pose.getX();
        double robotY = pose.getY();
        double robotHeading = pose.getHeading(); // radians

        // Field angle to target
        double targetFieldAngle = Math.atan2(
                TARGET_Y - robotY,
                TARGET_X - robotX
        );

        // Convert to robot-relative
        double turretAngleRad = targetFieldAngle - robotHeading;

        // Normalize to shortest path
        turretAngleRad = Math.atan2(
                Math.sin(turretAngleRad),
                Math.cos(turretAngleRad)
        );

        double turretAngleDeg = Math.toDegrees(turretAngleRad);

        shooter.setTurretTargetAngle(turretAngleDeg);
    }

    @Override
    public boolean isFinished() {
        // Hold current position when command ends
        return false;
    }
}