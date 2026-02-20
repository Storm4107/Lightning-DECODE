package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.FlywheelSub;

public class HoodCommand extends CommandBase {

    private final FlywheelSub flywheelSub;

    public HoodCommand( FlywheelSub flywheelSub){
        this.flywheelSub = flywheelSub;
        addRequirements(flywheelSub);
    }

    @Override
    public void execute(){
        flywheelSub.setHoodUp();
    }

    @Override
    public void end(boolean interrupted) {
    }
}