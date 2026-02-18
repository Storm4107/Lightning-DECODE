package org.firstinspires.ftc.teamcode.pedroPathing.paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class blueShort {
    public static Pose startPose = new Pose(23.634517766497463, 126.21319796954315, Math.toRadians(-45));
    public static Pose shootPose = new Pose(55.50253807106599, 75.89340101522843, Math.toRadians(-75));
    public static Pose middleRow = new Pose(12.913705583756345, 58.964467005076145, Math.toRadians(-180));
    public static Pose middleRowSetup = new Pose(52.629441624365484, 57.45177664974619, Math.toRadians(-180));
    public static Pose gate = new Pose(5.644670050761421, 68.59390862944162, Math.toRadians(90));
    public static Pose pickupGate = new Pose(-3, 49.705583756345185, Math.toRadians(90));


    public static PathChain shootPreload() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                startPose,
                                shootPose
                        )
                )
                .setLinearHeadingInterpolation(startPose.getHeading(), shootPose.getHeading())
                .build();
    }

    public static PathChain pickupMiddle1() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                shootPose,
                                middleRowSetup
                        )
                )
                .setLinearHeadingInterpolation(shootPose.getHeading(), middleRowSetup.getHeading())
                .build();
    }

    public static PathChain pickupMiddle2() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                middleRowSetup,
                                middleRow
                        )
                )
                .setLinearHeadingInterpolation(middleRowSetup.getHeading(), middleRow.getHeading())
                .build();
    }

    public static PathChain shootMiddle() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                middleRow,
                                shootPose
                        )
                )
                .setConstantHeadingInterpolation(middleRow.getHeading())
                .build();
    }

    public static PathChain openGateSetup1() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                shootPose,
                                middleRow
                        )
                )
                .setLinearHeadingInterpolation(shootPose.getHeading(), Math.toRadians(90))
                .build();
    }

    public static PathChain openGateSetup2() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                middleRow,
                                gate
                        )
                )
                .setConstantHeadingInterpolation(gate.getHeading())
                .build();
    }

    public static PathChain pickupGate() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                gate,
                                pickupGate
                        )
                )
                .setConstantHeadingInterpolation(gate.getHeading())
                .build();
    }
}