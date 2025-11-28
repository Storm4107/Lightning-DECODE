package org.firstinspires.ftc.teamcode.TeleOp;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.RobotContainer;

@TeleOp(name = "GPPTele")
public class GPPTele extends CommandOpMode {

    private RobotContainer robot;

    @Override
    public void initialize() {
        robot = new RobotContainer(hardwareMap, gamepad2, gamepad1);

        telemetry.addData("Status", "RobotContainer Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        follower.update();
        super.run();

        telemetry.addData("Intake State", robot.getIntakeSub().getCurrentState());
        telemetry.addData("LED State", robot.getLightsSub().getCurrentState());
        telemetry.addData("Indexer State", robot.getIndexerSub().getCurrentState());
        telemetry.addData("Shooter State", robot.getShooterSub().getCurrentState());
        telemetry.addData("ShooterRPM", robot.getShooterSub().getShooterVelocity());
        telemetry.update();
    }
}
