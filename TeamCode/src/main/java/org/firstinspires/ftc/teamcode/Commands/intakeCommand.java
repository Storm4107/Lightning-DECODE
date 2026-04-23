package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.intakeSub;

public class intakeCommand extends CommandBase {

    private final intakeSub intake;

    private final boolean intaking;

    public intakeCommand(intakeSub intakeSub, boolean intaking){
        intake = intakeSub;
        this.intaking = intaking;
        addRequirements(intake);
    }

    @Override
    public void execute(){

        if (intaking) {
            intake.setState(intakeSub.intakeStates.INTAKE);
        } else {
            intake.setState(intakeSub.intakeStates.REVERSE);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(intakeSub.intakeStates.IDLE);
    }
}