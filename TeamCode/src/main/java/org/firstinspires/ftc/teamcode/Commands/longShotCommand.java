package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class longShotCommand extends CommandBase {

    private final LightsSub lights;
    private final ShooterSub shooter;
    private  final IndexerSub indexer;
    private final IntakeSub intake;

    public longShotCommand(LightsSub lightsSub, ShooterSub shooterSub, IndexerSub indexersub, IntakeSub intakeSub){
        lights = lightsSub;
        shooter = shooterSub;
        indexer = indexersub;
        intake = intakeSub;
        addRequirements(lights, shooter, indexer, intake);
    }

    public void initialize() {
        lights.setState(LightsSub.lightStates.SHOOTING);
        shooter.setState(ShooterSub.shooterStates.LONGSHOT);
        indexer.setState(IndexerSub.indexerStates.RAPIDFIRE);
        intake.setState(IntakeSub.intakeStates.INTAKE);
    }

    @Override
    public void end(boolean interrupted) {
        lights.setState(LightsSub.lightStates.IDLE);
        shooter.setState(ShooterSub.shooterStates.IDLE);
        indexer.setState(IndexerSub.indexerStates.IDLE);
        intake.setState(IntakeSub.intakeStates.IDLE);
    }
}
