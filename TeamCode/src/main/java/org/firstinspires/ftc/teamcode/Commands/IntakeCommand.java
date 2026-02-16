package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub;


public class IntakeCommand extends CommandBase {

    private final IntakeSub intake;
    private final ShooterSub shooter;

    private final boolean intaking;


    public IntakeCommand(IntakeSub intakeSub, boolean intaking, ShooterSub shooterSub){
        intake = intakeSub;
        shooter = shooterSub;
        this.intaking = intaking;
        addRequirements(intake);
    }

    @Override
    public void execute(){

        if (intaking) {
            intake.setState(IntakeSub.intakeStates.INTAKE);
            //shooter.setState(ShooterSub.ShooterState.CLOSE);
        } else {
            intake.setState(IntakeSub.intakeStates.REVERSE);
            //shooter.setState(ShooterSub.ShooterState.OPEN);
        }
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        //shooter.setState(ShooterSub.ShooterState.CLOSE);
    }
}