package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class hoodSub extends SubsystemBase {

    private final Servo hood;

    private double targetPosition = 0;

    public hoodSub(HardwareMap hMap){

        hood = hMap.get(Servo.class, "hood");

        // Invert if needed (you said earlier it was backwards)
        hood.setDirection(Servo.Direction.REVERSE);
    }

    public void setPosition(double position){

        // Clamp to valid servo range
        position = Math.max(0.0, Math.min(1.0, position));

        targetPosition = position;
        hood.setPosition(targetPosition);
    }

    public double getPosition(){
        return hood.getPosition();
    }

    // 🔥 CONTINUOUS distance → hood mapping
    public double calculateHood(double distance){

        // --- YOUR TUNABLE POINTS ---
        double d1 = 40.75, h1 = 0.15;   // close
        double d2 = 70.75, h2 = 0.35;   // mid
        double d3 = 152, h3 = 0.75;   // far

        double hoodPos;

        if (distance <= d2) {
            // interpolate between (d1,h1) and (d2,h2)
            hoodPos = h1 + (distance - d1) * (h2 - h1) / (d2 - d1);
        } else {
            // interpolate between (d2,h2) and (d3,h3)
            hoodPos = h2 + (distance - d2) * (h3 - h2) / (d3 - d2);
        }

        // Clamp again just to be safe
        return Math.max(0.0, Math.min(1.0, hoodPos));
    }
}