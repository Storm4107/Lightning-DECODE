package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;

public class intakeCommand extends CommandBase {

    private final IntakeSub intake;
    private final LightsSub lights;
    private final IndexerSub indexer;

    public intakeCommand(IntakeSub intakeSub, LightsSub lightsSub, IndexerSub indexerSub){
        intake = intakeSub;
        lights = lightsSub;
        indexer = indexerSub;
        addRequirements(intake, lights);
    }

    @Override
    public void initialize() {
        intake.setState(IntakeSub.intakeStates.INTAKE);
        lights.setState(LightsSub.lightStates.INTAKING);
        indexer.setState(IndexerSub.indexerStates.INTAKING);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        lights.setState(LightsSub.lightStates.IDLE);
        indexer.setState(IndexerSub.indexerStates.IDLE);
    }
}
