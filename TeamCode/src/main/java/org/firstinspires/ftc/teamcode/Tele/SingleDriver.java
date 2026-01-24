package org.firstinspires.ftc.teamcode.Tele;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import org.firstinspires.ftc.teamcode.RobotContainer;

@TeleOp(name = "single")
public class SingleDriver extends CommandOpMode {

    private RobotContainer robot;

    @Override
    public void initialize() {
        robot = new RobotContainer(hardwareMap, gamepad1);

        robot.follower.setPose( new Pose(0,0,0));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        robot.follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        robot.follower.update();

        telemetry.addData("Intake State",   robot.intakeSub.getCurrentState());
        telemetry.addData("Shooter State",   robot.shooterSub.getCurrentState());
        telemetry.addData("ShooterRPM",   robot.shooterSub.getShooterVelocity());
        telemetry.update();
    }
}