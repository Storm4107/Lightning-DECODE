package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.doorSub;
import org.firstinspires.ftc.teamcode.Subsystems.intakeSub;

public class intakeCommand extends CommandBase {

    private final intakeSub intake;
    private final doorSub door;
    private final double power;

    public intakeCommand(intakeSub intake, doorSub door, double power){
        this.intake = intake;
        this.door = door;
        this.power = power;

        addRequirements(intake);
    }

    @Override
    public void execute(){
        intake.setPower(power);

        if (power >= 0){
            door.close();
        } else {
            door.open();
        }
    }

    @Override
    public void end(boolean interrupted){
        intake.stop();
        door.close();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}