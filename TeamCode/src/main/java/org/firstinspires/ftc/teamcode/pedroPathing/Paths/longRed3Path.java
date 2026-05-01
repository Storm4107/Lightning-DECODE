package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;

public  class longRed3Path {
    public PathChain shootPreload;
    public PathChain pickupRow3;
    public PathChain shootRow3;
    public PathChain pickupWall;
    public PathChain shootWall;
    public PathChain park;

    public longRed3Path(Follower follower) {
        shootPreload = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(90.38071065989847, 9.24365482233503),
                                new Pose(108, 12)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(78))
                .build();

        pickupRow3 = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(59.345, 21.426),
                                new Pose(44.761, 35.901),
                                new Pose(12.107, 34.975)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootRow3 = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(12.107, 34.975),
                                new Pose(59.548, 21.137)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(180))
                .build();

        pickupWall = follower.pathBuilder()
                .addPath(
                        new BezierCurve(
                                new Pose(59.548, 21.137),
                                new Pose(7.769, 70.541),
                                new Pose(6.619, 11.518)
                        )
                )
                .setTangentHeadingInterpolation()
                .build();

        shootWall = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(6.619, 11.518),
                                new Pose(59.569, 21.132)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-90))
                .build();

        park = follower.pathBuilder()
                .addPath(
                        new BezierLine(
                                new Pose(90, 9),
                                new Pose(112, 12)
                        )
                )
                .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(80))
                .build();
    }
}