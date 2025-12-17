package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class LongRed {
    public static Pose startPose = new Pose(111.83756345177665, 135.47208121827413, 0);
    public static Pose shootPose = new Pose(89.17766497461929, 87.71573604060914, 0);
    public static Pose set1Pose = new Pose(121.58375634517768, 82.84263959390863, 0);
    public static Pose set2LineUpPose = new Pose(89.42131979695432, 59.45177664974619, 0);
    public static Pose set2Pose = new Pose(128.89340101522842, 58.964467005076145, 0);

    public static PathChain shootPreload() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                startPose,
                                shootPose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(50))
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
                .setConstantHeadingInterpolation(Math.toRadians(0))
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
                .setConstantHeadingInterpolation(Math.toRadians(0))
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
                .setConstantHeadingInterpolation(Math.toRadians(0))

                .addPath(
                        new BezierLine(
                                set2LineUpPose,
                                set2Pose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
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
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
    }
}