package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class AimCommand extends CommandBase {

    public final ShooterSub shooter;

    private final boolean right;
    private final boolean close;

    public AimCommand(ShooterSub shooterSub, boolean right, boolean close){
        shooter = shooterSub;
        this.right = right;
        this.close = close;
    }

    @Override
    public void execute() {
        if (right && !close) {
            shooter.setState(ShooterSub.shooterStates.RIGHTAIM);
        } else if (!right && !close){
            shooter.setState(ShooterSub.shooterStates.LEFTAIM);
        } else if (!right && close) {
            shooter.setState(ShooterSub.shooterStates.CLOSESHOT);
        }
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setState(ShooterSub.shooterStates.IDLE);
    }
}
