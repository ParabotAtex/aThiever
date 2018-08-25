package org.parabot.scriptwriter.aThiever.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

public class Picker implements Strategy {
    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && SceneObjects.getClosest(11730) != null
                && Interfaces.getOpenInterfaceId() == -1;
    }

    @Override
    public void execute() {
        SceneObject stall = SceneObjects.getClosest(11730);
        if(stall != null) {
            stall.interact(SceneObjects.Option.FIRST);
            Time.sleep(500,1000);
        }
    }
}
