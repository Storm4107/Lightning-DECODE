package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;

public class IndexCommand extends CommandBase {

    private final IndexerSub indexer;
    private final boolean rapidfire;
    private final boolean auto;

    public IndexCommand(IndexerSub indexerSub, boolean rapidFire, boolean auto){
        indexer = indexerSub;
        this.rapidfire = rapidFire;
        this.auto = auto;
    }

    @Override
    public void execute() {

        if (auto) {
            indexer.setState(IndexerSub.indexerStates.INTAKING);
        }

        if (rapidfire) {
            indexer.setState(IndexerSub.indexerStates.RAPIDFIRE);
        } else if (!auto && !rapidfire){
            indexer.setState(IndexerSub.indexerStates.ROTATE120);
        }
    }

    @Override
    public void end(boolean interrupted) {
        indexer.resetEncoder();
        indexer.setState(IndexerSub.indexerStates.IDLE);
    }
}
