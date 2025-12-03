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
        robot = new RobotContainer(hardwareMap, gamepad1, gamepad2);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
    }

    @Override
    public void run() {
        super.run();

        robot.follower.setTeleOpDrive(-gamepad1.left_stick_y, -gamepad1.left_stick_x, -gamepad1.right_stick_x, false);

        robot.follower.update();

        telemetry.addData("Intake State",   robot.intakeSub.getCurrentState());
        telemetry.addData("LED State",      robot.lightsSub.getCurrentState());
        telemetry.addData("Indexer State",  robot.indexerSub.getCurrentState());
        telemetry.addData("Shooter State",  robot.shooterSub.getCurrentState());
        telemetry.addData("Shooter RPM",    robot.shooterSub.getShooterVelocity());
        telemetry.addData("Heading", follower.getPose().getHeading());
        telemetry.update();
    }
}
