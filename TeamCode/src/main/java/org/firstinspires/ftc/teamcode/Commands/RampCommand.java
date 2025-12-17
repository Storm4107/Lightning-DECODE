package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class RampCommand extends CommandBase {

    private final ShooterSub shooter;

    private final boolean close;

    public RampCommand(ShooterSub shooterSub, boolean Close){
        shooter = shooterSub;
        this.close = Close;
    }

    @Override
    public void execute() {
        if (close) {
            shooter.setState(ShooterSub.shooterStates.SHORTRAMP);
        } else {
            shooter.setState(ShooterSub.shooterStates.FARRAMP);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setState(ShooterSub.shooterStates.IDLE);
    }
}
