package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class shortRed9Path {
    public PathChain shootPreload;
    public PathChain pickupRow2;
    public PathChain openGate;
    public PathChain shootRow2;
    public PathChain pickupRow1;
    public PathChain shootRow1;
    public PathChain park;

    public shortRed9Path(Follower follower) {
        shootPreload = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(120.325, 125.685),
                                new Pose(99.371, 105.442)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(-135))
                .build();

        pickupRow2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(99.371, 105.442),
                                new Pose(59.635, 54.558),
                                new Pose(127.249, 57)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        openGate = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(127.249, 57),
                                new Pose(112.470, 65.056),
                                new Pose(127.081, 71.127)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        shootRow2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(127.081, 71.127),
                                new Pose(89.487, 72.124),
                                new Pose(99.497, 105.132)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        pickupRow1 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(99.497, 105.132),
                                new Pose(83.396, 80.896),
                                new Pose(126.898, 83.462)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        shootRow1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(126.898, 83.462),
                                new Pose(99.000, 105.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                .build();

        park = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(99.000, 105.000),
                                new Pose(124.234, 104.462)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();
    }
}