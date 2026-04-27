package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class doorSub extends SubsystemBase {

    private final Servo door;

    // TUNE THESE
    private final double CLOSED = 0.45;
    private final double OPEN = 0.05;

    private boolean isOpen = false;

    public doorSub(HardwareMap hMap){

        door = hMap.get(Servo.class, "door");

        close();
    }

    public void open(){
        door.setPosition(OPEN);
        isOpen = true;
    }

    public void close(){
        door.setPosition(CLOSED);
        isOpen = false;
    }

    public void toggle(){
        if (isOpen) close();
        else open();
    }

    public boolean isOpen(){
        return isOpen;
    }

    public double getPosition(){
        return door.getPosition();
    }
}