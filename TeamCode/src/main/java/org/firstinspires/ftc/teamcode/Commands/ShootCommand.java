package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class ShootCommand extends CommandBase {

    public final IntakeSub intake;
    public final IndexerSub indexer;

    public ShootCommand(IntakeSub intakeSub, IndexerSub indexerSub){

        intake = intakeSub;
        indexer = indexerSub;
        addRequirements(indexer, intake);
    }

    @Override
    public void initialize() {
        intake.setState(IntakeSub.intakeStates.INTAKE);
        indexer.setState(IndexerSub.indexerStates.RAPIDFIRE);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        indexer.setState(IndexerSub.indexerStates.IDLE);
    }
}
