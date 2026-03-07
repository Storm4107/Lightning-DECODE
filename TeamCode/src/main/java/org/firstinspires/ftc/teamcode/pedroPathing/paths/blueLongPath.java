package org.firstinspires.ftc.teamcode.pedroPathing.paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class blueLongPath {

    public static Pose startPose = new Pose(26, 127, Math.toRadians(315));
    public static Pose shootPose = new Pose(65, 80.9238578680203, Math.toRadians(305));
    public static Pose middlePose = new Pose(2, 52.56852791878173);
    public static Pose middleControl = new Pose(40, 52.30964467005076);
    public static Pose gatePose = new Pose(8, 62 , Math.toRadians(0));
    public static Pose gateControlPose = new Pose(40, 68, Math.toRadians(0));
    public static Pose frontPose1 = new Pose(50, 90.9238578680203, Math.toRadians(0));
    public static Pose frontPose = new Pose(2, 98, Math.toRadians(0));
    public static Pose leavePose = new Pose(25, 8, Math.toRadians(180));

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
                .setConstantHeadingInterpolation( Math.toRadians(180))
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
                                startPose,
                                leavePose
                        )
                )
                .setConstantHeadingInterpolation(startPose.getHeading())
                .build();
    }
}