package org.firstinspires.ftc.teamcode.pedroPathing.paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class redShortPath {

    public static Pose startPose = new Pose(120.12182741116752, 125.96954314720813, Math.toRadians(-135));
    public static Pose shootPose = new Pose(82.42131979695432, 80.9238578680203, Math.toRadians(-135));
    public static Pose middlePose = new Pose(138.0203045685279, 70.56852791878173);
    public static Pose middleControl = new Pose(81.13705583756345, 63.30964467005076);
    public static Pose gatePose = new Pose(132, 82 , Math.toRadians(0));
    public static Pose gateControlPose = new Pose(105, 68, Math.toRadians(0));
    public static Pose frontPose1 = new Pose(94.42131979695432, 90.9238578680203, Math.toRadians(0));
    public static Pose frontPose = new Pose(130, 98, Math.toRadians(0));
    public static Pose leavePose = new Pose(105, 92, Math.toRadians(-180));

    public static PathChain shootPreload() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                startPose,
                                shootPose
                        )
                )
                .setConstantHeadingInterpolation(startPose.getHeading())
                .build();
    }

    public static PathChain pickupMiddle() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierCurve(
                                shootPose,
                                middleControl,
                                middlePose
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
    }

    public static PathChain openGate() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierCurve(
                                middlePose,
                                gateControlPose,
                                gatePose
                        )
                )
                .setConstantHeadingInterpolation( Math.toRadians(0))
                .build();
    }

    public static PathChain shootMiddle() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                gatePose,
                                shootPose
                        )
                )
                .setLinearHeadingInterpolation( gatePose.getHeading(), shootPose.getHeading())
                .build();
    }

    public static PathChain pickupFront1() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                shootPose,
                                frontPose1
                        )
                )
                .setLinearHeadingInterpolation( shootPose.getHeading(), frontPose1.getHeading())
                .build();
    }

    public static PathChain pickupFront() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                frontPose1,
                                frontPose
                        )
                )
                .build();
    }

    public static PathChain shootFront() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                frontPose,
                                shootPose
                        )
                )
                .setLinearHeadingInterpolation( frontPose.getHeading(), shootPose.getHeading())
                .build();
    }

    public static PathChain leave() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                shootPose,
                                leavePose
                        )
                )
                .setLinearHeadingInterpolation( shootPose.getHeading(), leavePose.getHeading())
                .build();
    }
}