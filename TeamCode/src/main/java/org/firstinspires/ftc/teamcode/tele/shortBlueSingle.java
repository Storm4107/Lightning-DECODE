package org.firstinspires.ftc.teamcode.tele;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;

import org.firstinspires.ftc.teamcode.Commands.turretCommand;
import org.firstinspires.ftc.teamcode.robotContainer;

@TeleOp(name = "shortBlueSingle")
public class shortBlueSingle extends CommandOpMode {

    private robotContainer robot;

    @Override
    public void initialize() {
        robot = new robotContainer(hardwareMap, gamepad1);

        robot.turret.setDefaultCommand(
              new turretCommand(robot.turret, robot.follower, false, false)
        );

        robot.follower.setPose( new Pose(8,92,Math.toRadians(180)));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        robot.follower.setTeleOpDrive(gamepad1.left_stick_y, gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        // Runs default commands automatically
        //CommandScheduler.getInstance().run();

        if (gamepad1.startWasPressed()){
            robot.follower.setPose( new Pose(0,0,0));
        }

        robot.follower.update();

        telemetry.addData("Intake State",   robot.intakeSub.getCurrentState());
        telemetry.addData("Shooter State",   robot.shooterSub.getCurrentState());
        telemetry.addData("ShooterRPM",   robot.shooterSub.getShooterVelocity());
        telemetry.update();
    }
}