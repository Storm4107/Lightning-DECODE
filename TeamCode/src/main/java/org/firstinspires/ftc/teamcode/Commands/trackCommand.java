package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSub;

public class trackCommand extends CommandBase {

    private final LightsSub lights;
    private final VisionSub vision;

    public trackCommand(LightsSub lightsSub, VisionSub visionSub){
        lights = lightsSub;
        vision = visionSub;
        addRequirements(lights, vision);
    }

    @Override
    public void execute() {
        if (vision.seesRedTarget()) {
            lights.setState(LightsSub.lightStates.LOCKED);
        } else lights.setState(LightsSub.lightStates.IDLE);
    }

    @Override
    public void end(boolean interrupted) {
        lights.setState(LightsSub.lightStates.IDLE);
    }
}
