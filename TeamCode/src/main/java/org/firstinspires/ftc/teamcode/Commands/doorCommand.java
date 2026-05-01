package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.doorSub;

public class doorCommand extends CommandBase {

    private final doorSub door;
    private final boolean open;

    public doorCommand(doorSub door, boolean open){
        this.door = door;
        this.open = open;

        addRequirements(door);
    }

    @Override
    public void initialize(){
        if (open){
            door.open();
        } else {
            door.close();
        }
    }


}