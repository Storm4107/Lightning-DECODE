package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.LightsSub;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterSub;

public class trackCommand extends CommandBase {

    private final LightsSub lights;
    private final ShooterSub shooter;

    public trackCommand(LightsSub lightsSub, ShooterSub shooterSub){
        lights = lightsSub;
        shooter = shooterSub;
        addRequirements(lights, shooter);
    }

    public void initialize() {
        lights.setState(LightsSub.lightStates.SHOOTING);
        shooter.setState(ShooterSub.shooterStates.SHOOT);
    }

    @Override
    public void end(boolean interrupted) {
        lights.setState(LightsSub.lightStates.IDLE);
        shooter.setState(ShooterSub.shooterStates.IDLE);
    }
}
