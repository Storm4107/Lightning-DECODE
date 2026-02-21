package org.firstinspires.ftc.teamcode.Commands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.Pose;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsytems.IntakeSub;
import org.firstinspires.ftc.teamcode.Subsytems.ShooterSub.FlywheelSub;

public class ShootCommand extends CommandBase {

    private final FlywheelSub flywheelSub;
    private final IntakeSub intakeSub;
    private final Follower follower;

    // Replace with your real target coordinates
    private static final double BLUE_TARGET_X = 20;
    private static final double BLUE_TARGET_Y = 144;
    private static final double RED_TARGET_X = 140;
    private static final double RED_TARGET_Y = 145;

    private final boolean RED;

    public ShootCommand(FlywheelSub flywheelSub, Follower follower, IntakeSub intakeSub, boolean RED) {
        this.flywheelSub = flywheelSub;
        this.follower = follower;
        this.RED = RED;
        this.intakeSub = intakeSub;
        addRequirements(flywheelSub);
    }

    @Override
    public void execute() {

        flywheelSub.setDoorOpen();
        flywheelSub.setHoodUp();

        if (RED){
            Pose pose = follower.getPose();

            double robotX = pose.getX();
            double robotY = pose.getY();

            // Field distance to target
            double distanceToTarget =
                    Math.sqrt((Math.pow((RED_TARGET_X - robotX), 2) + Math.pow((RED_TARGET_Y - robotY), 2)));

            //tuned value

            flywheelSub.setShoterVelocity(distanceToTarget);

            /*if (flywheelSub.atSpeed()) {
                intakeSub.setState(IntakeSub.intakeStates.RAPIDFIRE);
                flywheelSub.setDoorOpen();
            }

            else if (!flywheelSub.atSpeed()) {
                intakeSub.setState(IntakeSub.intakeStates.IDLE);
                //flywheelSub.setDoorClose();        May want the intake to the heavy lifting to
                                                    // limit the ball flow during this period
            }*/
        }


        if (!RED){
            Pose pose = follower.getPose();

            double robotX = pose.getX();
            double robotY = pose.getY();

            // Field distance to target
            double distanceToTarget =
                    Math.sqrt(Math.pow((BLUE_TARGET_X - robotX), 2) + Math.pow((BLUE_TARGET_Y - robotY), 2));

            flywheelSub.setShoterVelocity((distanceToTarget));

            /*if (flywheelSub.atSpeed()) {
                intakeSub.setState(IntakeSub.intakeStates.RAPIDFIRE);
            }

            else if (!flywheelSub.atSpeed()) {
                intakeSub.setState(IntakeSub.intakeStates.IDLE);
                //flywheelSub.setDoorClose();
            }*/
        }
    }

    @Override
    public void end(boolean interrupted) {
        intakeSub.setState(IntakeSub.intakeStates.IDLE);
        //flywheelSub.setDoorClose();
        //flywheelSub.setShoterVelocity(750);
        //flywheelSub.setShoterVelocity(Math.abs((flywheelSub.getShoterVelocity()) - 712));
        //712 is the lowest speed the shooter can possibly go based on this formula 0.0213x^2−6.07x+712
        // this value can never dip into the negatives but it uses the Math.abs just in case
    }
}