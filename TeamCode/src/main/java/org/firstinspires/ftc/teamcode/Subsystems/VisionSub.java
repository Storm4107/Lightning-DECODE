package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class VisionSub extends SubsystemBase {

    private final Limelight3A limelight;

    private static final int RED_TARGET = 24;
    private static final int BLUE_TARGET = 20;

    public double redAngleOfTarget;
    public double redDistanceFromTarget;

    public double blueAngleOfTarget;
    public double blueDistanceFromTarget;

    public VisionSub(HardwareMap hMap) {
        limelight = hMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();
    }

    @Override
    public void periodic() {
        LLResult result = limelight.getLatestResult();


        if (result != null && result.isValid()) {

            for (LLResultTypes.FiducialResult redTag : result.getFiducialResults()) {

                if (redTag.getFiducialId() == RED_TARGET) {
                    redAngleOfTarget = redTag.getTargetXDegrees();
                    redDistanceFromTarget = redTag.getTargetArea();
                    return;
                }
            }

            for (LLResultTypes.FiducialResult blueTag : result.getFiducialResults()) {

                if (blueTag.getFiducialId() == BLUE_TARGET) {
                    blueAngleOfTarget = blueTag.getTargetXDegrees();
                    blueDistanceFromTarget = blueTag.getTargetArea();
                    return;
                }
            }
        }
    }
}
