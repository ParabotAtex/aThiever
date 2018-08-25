package org.parabot.scriptwriter.aThiever.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.wrappers.Tile;

public class UnknownLocation implements Strategy {

    @Override
    public boolean activate() {
        return new Tile(3307,2662).distanceTo() > 10;
    }

    @Override
    public void execute() {
        Time.sleep(1000);
    }
}
