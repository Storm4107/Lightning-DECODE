package org.firstinspires.ftc.teamcode.pedroPathing.Paths;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathBuilder;
import com.pedropathing.paths.PathChain;

public class LongBlue {

    public static Pose startPose = new Pose(58.72081218274112, 84.30456852791879, 90);
    public static Pose setUp1Pose = new Pose(19.248730964467004, 35.329949238578685, 90);
    public static Pose setUp2Pose = new Pose(18.517766497461928, 59.93908629441625, 180);

    public static PathChain lineUp1() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierCurve(
                                startPose,
                                //new Pose(48,39.71573604060914),
                                setUp1Pose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(0))
                .build();
    }

    public static PathChain shoot6() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                setUp1Pose,
                                startPose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }

    public  static PathChain lineUp2() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierCurve(
                                startPose,
                                new Pose(50.19289340101523,68.95431472081218),
                                setUp2Pose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }

    public static PathChain shoot9() {
        return new PathBuilder(follower)
                .addPath(
                        new BezierLine(
                                setUp2Pose,
                                startPose
                        )
                )
                .setConstantHeadingInterpolation(Math.toRadians(180))
                .build();
    }
}
