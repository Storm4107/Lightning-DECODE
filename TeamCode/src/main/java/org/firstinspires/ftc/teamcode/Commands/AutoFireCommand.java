package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class AutoFireCommand extends CommandBase {

    private final IntakeSub intake;
    private final IndexerSub indexer;

    public AutoFireCommand(IntakeSub intakeSub, IndexerSub indexerSub){
        intake = intakeSub;
        indexer = indexerSub;

        addRequirements(intakeSub, indexerSub);
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
