package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;
import org.firstinspires.ftc.teamcode.Subsystems.VisionSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class REDtrackCommand extends CommandBase {

    private final LightsSub lights;
    private final VisionSub vision;
    private final ShooterSub shooter;

    public REDtrackCommand(LightsSub lightsSub, VisionSub visionSub, ShooterSub shooterSub){
        lights = lightsSub;
        vision = visionSub;
        shooter = shooterSub;
        addRequirements(lights, vision, shooter);
    }

    @Override
    public void execute() {

        shooter.variableShot((100- (vision.redDistanceFromTarget)) * 60);
        shooter.setState(ShooterSub.shooterStates.SHOOT);

        if (vision.redAngleOfTarget > 0){
            shooter.setState(ShooterSub.shooterStates.CW);
        } else if (vision.redAngleOfTarget < 0) {
            shooter.setState(ShooterSub.shooterStates.CCW);
        } else {
            shooter.setState(ShooterSub.shooterStates.STOPTURRET);
            lights.setState(LightsSub.lightStates.LOCKEDRED);
        }
    }

    @Override
    public void end(boolean interrupted) {
        lights.setState(LightsSub.lightStates.IDLE);
    }
}
