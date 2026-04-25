package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.flywheelSub;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.targetingUtil;

import java.util.function.Supplier;

public class flywheelCommand extends CommandBase {

    private final flywheelSub flywheel;
    private final Follower follower;
    private final Supplier<Alliance> allianceSupplier;

    public flywheelCommand(flywheelSub flywheel,
                           Follower follower,
                           Supplier<Alliance> allianceSupplier){

        this.flywheel = flywheel;
        this.follower = follower;
        this.allianceSupplier = allianceSupplier;

        addRequirements(flywheel);
    }

    @Override
    public void execute(){

        Pose pose = follower.getPose();

        double distance = targetingUtil.getDistance(
                pose,
                allianceSupplier.get()
        );

        double rpm = flywheel.calculateRPM(distance);

        flywheel.setVelocity(rpm);
    }

    @Override
    public void end(boolean interrupted){
        flywheel.setVelocity(0);
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}