package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.IndexerSub;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsystems.SensorSub;

public class intakeCommand extends CommandBase {

    private final IntakeSub intake;
    private final SensorSub sensor;

    private final boolean intaking;


    public intakeCommand(IntakeSub intakeSub, SensorSub sensorSub, boolean intaking){
        intake = intakeSub;
        sensor = sensorSub;
        this.intaking = intaking;
        addRequirements(intake);
    }

    @Override
    public void execute(){

        if (intaking) {
            intake.setState(IntakeSub.intakeStates.INTAKE);
        } else {
            intake.setState(IntakeSub.intakeStates.REVERSE);
        }

        /*if (intaking) {


            if (sensor.artifactDistance() < 10) {
                indexer.setState(IndexerSub.indexerStates.ROTATE120);
            }
        }else {
            intake.setState(IntakeSub.intakeStates.REVERSE);
            indexer.setState(IndexerSub.indexerStates.REVERSE);
        }*/
    }

    @Override
    public void end(boolean interrupted) {
        intake.setState(IntakeSub.intakeStates.IDLE);
        //indexer.setState(IndexerSub.indexerStates.IDLE);
    }
}
