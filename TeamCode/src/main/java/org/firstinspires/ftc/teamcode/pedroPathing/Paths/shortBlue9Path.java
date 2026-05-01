package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public class shortBlue9Path {

    public PathChain shootPreload;
    public PathChain pickupRow2;
    public PathChain openGate;
    public PathChain shootRow2;
    public PathChain pickupRow1;
    public PathChain shootRow1;
    public PathChain park;

    public shortBlue9Path(Follower follower) {
        shootPreload = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(23.838, 125.929),
                                new Pose(43.264, 105.000)
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(-45))
                .build();

        pickupRow2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(43.264, 105.000),
                                new Pose(80.769, 55.954),
                                new Pose(10.000, 56.000)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        openGate = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(16.000, 60.000),
                                new Pose(40.525, 67.142),
                                new Pose(17.168, 70.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

        shootRow2 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(16.168, 70.000),
                                new Pose(73.312, 71.520),
                                new Pose(43.330, 105.640)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

        pickupRow1 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(43.330, 105.640),
                                new Pose(63.566, 80.023),
                                new Pose(14.000, 78.500)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

        shootRow1 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(19.000, 84.000),
                                new Pose(43.462, 105.533)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

        park = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(43.462, 105.533),
                                new Pose(19.000, 106.000)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(-81))
                .build();
    }
}