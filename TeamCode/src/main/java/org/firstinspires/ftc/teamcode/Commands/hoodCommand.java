package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.hoodSub;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.targetingUtil;

import java.util.function.Supplier;

public class hoodCommand extends CommandBase {

    private final hoodSub hood;
    private final Follower follower;
    private final Supplier<Alliance> allianceSupplier;

    public hoodCommand(hoodSub hood, Follower follower, Supplier<Alliance> allianceSupplier){

        this.hood = hood;
        this.follower = follower;
        this.allianceSupplier = allianceSupplier;

        addRequirements(hood);
    }

    @Override
    public void execute(){

        Pose pose = follower.getPose();

        double distance = targetingUtil.getDistance(
                pose,
                allianceSupplier.get()
        );

        double hoodPos = hood.calculateHood(distance);

        hood.setPosition(hoodPos);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}