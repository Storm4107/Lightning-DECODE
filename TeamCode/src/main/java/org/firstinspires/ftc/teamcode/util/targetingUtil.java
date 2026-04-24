package org.firstinspires.ftc.teamcode.util;

import com.pedropathing.geometry.Pose;

public class targetingUtil {

    public static double getX(Alliance alliance){
        return (alliance == Alliance.RED) ? 144 : 0;
    }

    public static double getY(){
        return 144;
    }

    public static double getDistance(Pose pose, Alliance alliance){

        double x = getX(alliance);
        double y = getY();

        double xOffset = 0;
        double yOffset = 4;

        double heading = pose.getHeading();

        double turretx = pose.getX() + (xOffset * Math.cos(heading)) - (yOffset * Math.sin(heading));
        double turrety = pose.getY() + (xOffset * Math.sin(heading)) + (yOffset * Math.cos(heading));

        double dx = x - turretx;
        double dy = y - turrety;

        return Math.hypot(dx, dy);
    }

    public static double getAngle(Pose pose, Alliance alliance){

        double x = getX(alliance);
        double y = getY();

        double xOffset = 0;
        double yOffset = 4;

        double heading = pose.getHeading();

        double turretx = pose.getX() + (xOffset * Math.cos(heading)) - (yOffset * Math.sin(heading));
        double turrety = pose.getY() + (xOffset * Math.sin(heading)) + (yOffset * Math.cos(heading));

        double dx = x - turretx;
        double dy = y - turrety;

        double fieldAngle = Math.atan2(dy, dx);

        double targetAngle = fieldAngle - heading;

        targetAngle += Math.PI;

        return Math.toDegrees(
                Math.atan2(Math.sin(targetAngle), Math.cos(targetAngle))
        );
    }
}
