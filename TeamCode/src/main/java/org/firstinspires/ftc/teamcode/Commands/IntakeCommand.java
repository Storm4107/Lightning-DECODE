package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.FlywheelSub;

public class IntakeCommand extends CommandBase {

    private final IntakeSub intake;
    private final FlywheelSub flywheelSub;

    private final boolean intaking;


    public IntakeCommand(IntakeSub intakeSub, FlywheelSub flywheelSub, boolean intaking){
        intake = intakeSub;
        this.flywheelSub = flywheelSub;
        this.intaking = intaking;
        addRequirements(intake);
    }

    @Override
    public void execute(){

        if (intaking) {
            intake.setState(IntakeSub.intakeStates.INTAKE);
            flywheelSub.setDoorClose();
        } else {
            intake.setState(IntakeSub.intakeStates.REVERSE);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        flywheelSub.setDoorClose();
    }
}