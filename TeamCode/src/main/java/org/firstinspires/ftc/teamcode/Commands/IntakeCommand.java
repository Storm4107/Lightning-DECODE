package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.FlywheelSub;

public class IntakeCommand extends CommandBase {

    private final IntakeSub intake;
    private final FlywheelSub flywheelSub;

    private final boolean intaking;
    private final boolean close;


    public IntakeCommand(IntakeSub intakeSub, FlywheelSub flywheelSub, boolean intaking, boolean close){
        intake = intakeSub;
        this.flywheelSub = flywheelSub;
        this.intaking = intaking;
        this.close = close;
        addRequirements(intake);
    }

    @Override
    public void execute(){

        if (intaking && close) {
            intake.setState(IntakeSub.intakeStates.INTAKE);
            flywheelSub.setDoorClose();
        }

        if (intaking && !close) {
            intake.setState(IntakeSub.intakeStates.INTAKE);
            flywheelSub.setDoorOpen();
        }

        if (!intaking && !close) {
            intake.setState(IntakeSub.intakeStates.REVERSE);
            flywheelSub.setDoorOpen();
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        flywheelSub.setDoorClose();
    }
}