package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class VisionSub extends SubsystemBase {

    private final Limelight3A limelight;

    private static final int RED_TARGET = 24;

    private boolean seesRedTarget = false;

    public VisionSub(HardwareMap hMap) {
        limelight = hMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);
        limelight.start();
    }

    @Override
    public void periodic() {
        LLResult result = limelight.getLatestResult();

        seesRedTarget = false;

        if (result != null && result.isValid()) {

            for (LLResultTypes.FiducialResult tag : result.getFiducialResults()) {

                if (tag.getFiducialId() == RED_TARGET) {

                    if (Math.abs(result.getTx()) <= 10) {
                        seesRedTarget = true;
                    }
                }
            }
        }
    }

    public boolean seesRedTarget() {
        return seesRedTarget;
    }
}
