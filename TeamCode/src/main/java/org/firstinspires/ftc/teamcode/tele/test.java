package org.firstinspires.ftc.teamcode.tele;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import org.firstinspires.ftc.teamcode.robotContainer;

@TeleOp(name = "test")
public class test extends CommandOpMode {

    private robotContainer robot;

    @Override
    public void initialize() {
        robot = new robotContainer(hardwareMap, gamepad1);

        robot.follower.setPose( new Pose(72,9,Math.toRadians(90)));

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        robot.follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        // Runs default commands automatically
        CommandScheduler.getInstance().run();

        robot.follower.update();

        telemetry.addData("TURRET_ENCODER", robot.turret.getTurretEncoder());

        telemetry.update();
    }
}