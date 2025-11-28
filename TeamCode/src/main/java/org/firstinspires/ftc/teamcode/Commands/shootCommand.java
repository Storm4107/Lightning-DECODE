package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class shootCommand extends CommandBase {

    private final IntakeSub intake;
    private final LightsSub lights;
    private final IndexerSub indexer;
    private final ShooterSub shooter;

    public shootCommand(IntakeSub intakeSub, LightsSub lightsSub, IndexerSub indexerSub, ShooterSub shooterSub){
        intake = intakeSub;
        lights = lightsSub;
        indexer = indexerSub;
        shooter = shooterSub;
        addRequirements(intake, lights, indexer, shooter);
    }

    public void initialize() {
        intake.setState(IntakeSub.intakeStates.INTAKE);
        lights.setState(LightsSub.lightStates.SHOOTING);
        indexer.setState(IndexerSub.indexerStates.RAPIDFIRE);
        shooter.setState(ShooterSub.shooterStates.SHOOT);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        lights.setState(LightsSub.lightStates.IDLE);
        indexer.setState(IndexerSub.indexerStates.IDLE);
        shooter.setState(ShooterSub.shooterStates.IDLE);
    }
}
