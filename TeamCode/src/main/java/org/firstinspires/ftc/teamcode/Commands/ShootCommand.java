package org.firstinspires.ftc.teamcode.Commands;

import android.annotation.SuppressLint;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;


public class ShootCommand extends CommandBase {
    private final ShooterSub shooter;

    private final boolean Long;
    public ShootCommand(ShooterSub shooterSub, boolean Long){
        shooter = shooterSub;
        this.Long = Long;
        addRequirements(shooter);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void execute(){
        if (Long == true) {
            shooter.setState(ShooterSub.ShooterState.LONGSHOT);
        } else
        shooter.setState(ShooterSub.ShooterState.SHORTSHOT);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setState(ShooterSub.ShooterState.IDLE);
    }
}