package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class ShortBlue {
    public static Pose startPose = new Pose(32.162436548223354, 135.47208121827413, 180);
    public static Pose shootPose = new Pose(56.04060913705584, 84.06091370558376, 180);
    public static Pose set1Pose = new Pose( 19.97969543147208, 83.81725888324873, 180);
    public static Pose set2LineUpPose = new Pose( 43.85786802030457, 59.45177664974619, 180);
    public static Pose set2Pose = new Pose( 23.878172588832488, 59.69543147208121, 180);


    public static PathChain shootPreload() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                startPose,
                                shootPose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }

    public static PathChain lineUp1() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                shootPose,
                                set1Pose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }

    public static PathChain shoot6() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                set1Pose,
                                shootPose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }

    public static PathChain lineUp2() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                shootPose,
                                set2LineUpPose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))

                .addPath(
                        new BezierLine(
                                set2LineUpPose,
                                set2Pose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }

    public static PathChain shoot9() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                set1Pose,
                                shootPose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }
}