package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;

public class TurretCommand extends CommandBase {

    private final ShooterSub shooter;

    public TurretCommand(ShooterSub shooterSub){
        shooter = shooterSub;
    }

    @Override
    public void execute() {

    }
}
