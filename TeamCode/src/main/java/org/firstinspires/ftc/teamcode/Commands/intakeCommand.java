package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.LightsSub;


public class intakeCommand extends CommandBase {

    private final IntakeSub intake;

    private final boolean intaking;

    private final LightsSub light;


    public intakeCommand(IntakeSub intakeSub, boolean intaking, LightsSub lightSub){
        intake = intakeSub;
        this.intaking = intaking;
        light = lightSub;
        addRequirements(intake);
    }

    @Override
    public void execute(){

        if (intaking) {
            intake.setState(IntakeSub.intakeStates.INTAKE);
        } else {
            intake.setState(IntakeSub.intakeStates.REVERSE);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        light.setState(LightsSub.lightStates.IDLE);
    }
}