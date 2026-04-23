package org.firstinspires.ftc.teamcode.Commands;

import android.annotation.SuppressLint;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.shooterSub;


public class shootCommand extends CommandBase {
    private final shooterSub shooter;

    private final boolean Long;

    public shootCommand(shooterSub shooterSub, boolean Long){
        shooter = shooterSub;
        this.Long = Long;
        addRequirements(shooter);
    }

    @SuppressLint("SuspiciousIndentation")
    @Override
    public void execute(){
        if (Long == true) {
            shooter.setState(shooterSub.ShooterState.LONGSHOT);
        } else
            shooter.setState(shooterSub.ShooterState.SHORTSHOT);
    }

    @Override
    public void end(boolean interrupted) {
        shooter.setState(shooterSub.ShooterState.IDLE);
    }
}