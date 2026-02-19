package org.firstinspires.ftc.teamcode.pedroPathing.paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class redShort {

    public static Pose startPose = new Pose(120.12182741116752, 125.96954314720813, Math.toRadians(-135));
    public static Pose shootPose = new Pose(89.42131979695432, 98.9238578680203, Math.toRadians(0));
    public static Pose middlePose = new Pose(131.5736040609137, 83.81725888324873);
    public static Pose middleControl = new Pose(85.5228426395939, 79.67512690355329);


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
}
