package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.turretSub;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.targetingUtil;

import java.util.function.Supplier;


public class turretCommand extends CommandBase {

    private final turretSub turret;
    private final Follower follower;
    private final Supplier<Alliance> allianceSupplier;

    public turretCommand(turretSub turret, Follower follower, Supplier<Alliance> allianceSupplier){
        this.turret = turret;
        this.follower = follower;
        this.allianceSupplier = allianceSupplier;

        addRequirements(turret);
    }

    @Override
    public void execute(){

        Pose pose = follower.getPose();

        double targetAngle = targetingUtil.getAngle(
                pose,
                allianceSupplier.get()
        );

        turret.setTargetAngle(targetAngle);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
