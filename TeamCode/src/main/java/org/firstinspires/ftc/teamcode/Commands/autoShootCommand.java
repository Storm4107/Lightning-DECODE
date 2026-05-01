package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.*;
import org.firstinspires.ftc.teamcode.util.Alliance;
import org.firstinspires.ftc.teamcode.util.targetingUtil;

import java.util.function.Supplier;

public class autoShootCommand extends CommandBase {

    private final intakeSub intake;
    private final flywheelSub flywheel;
    private final doorSub door;
    private final Follower follower;

    private final Supplier<Alliance> allianceSupplier;

    public autoShootCommand(
            intakeSub intake,
            flywheelSub flywheel,
            doorSub door,
            Follower follower,
            Supplier<Alliance> allianceSupplier
    ){
        this.intake = intake;
        this.flywheel = flywheel;
        this.door = door;
        this.follower = follower;
        this.allianceSupplier = allianceSupplier;

        addRequirements(intake, flywheel, door);
    }

    @Override
    public void initialize(){
        door.close(); // always start closed
    }

    @Override
    public void execute(){

        Pose pose = follower.getPose();

        // --- DISTANCE ---
        double distance = targetingUtil.getDistance(
                pose,
                allianceSupplier.get()
        );

        // --- FLYWHEEL (RPM curve) ---
        double rpm = flywheel.calculateRPM(distance);
        flywheel.setVelocity(rpm);

        // --- FEED LOGIC (prevents early shots) ---
        if (flywheel.atSpeed()) {
            intake.setPower(1);
            intake.setGearbox(-1);
            door.open();
        } else {
            door.close();
            intake.setGearbox(1);
        }
    }

    @Override
    public void end(boolean interrupted){
        intake.stop();
        flywheel.stop();
        door.close();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}