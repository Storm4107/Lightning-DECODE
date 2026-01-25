package org.firstinspires.ftc.teamcode.Commands;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.follower.Follower;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;

public class TurretCommand extends CommandBase {

    private final ShooterSub shooter;

    private final Follower follower;



    public TurretCommand(ShooterSub shooterSub, Follower follower){
        shooter = shooterSub;
        this.follower = follower;
    }

    @Override
    public void execute() {

        double error = ((shooter.getTurretAngle() - follower.getHeading()) * shooter.turretDegreeConversion);
    }
}
