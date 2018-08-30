package org.parabot.scriptwriter.aThiever.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Interfaces;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.wrappers.SceneObject;

import java.util.concurrent.ThreadLocalRandom;

public class Picker implements Strategy {
    private static SceneObject currentStall;

    @Override
    public boolean activate() {
        return !Inventory.isFull()
                && SceneObjects.getClosest(11730) != null
                && Interfaces.getOpenInterfaceId() == -1;
    }

    @Override
    public void execute() {
        if(currentStall != null) {
            if(currentStall.distanceTo() > 10) {
                selectNewStall();
            }
            currentStall.interact(SceneObjects.Option.FIRST);
            Time.sleep(500,1000);
        } else {
            selectNewStall();
        }
    }

    public static void selectNewStall() {
        SceneObject[] stalls = SceneObjects.getNearest(11730);
        int r = ThreadLocalRandom.current().nextInt(0, stalls.length);
        currentStall = stalls[r];
    }
}
