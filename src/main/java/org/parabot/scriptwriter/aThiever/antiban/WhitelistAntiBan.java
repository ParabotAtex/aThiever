package org.parabot.scriptwriter.aThiever.antiban;

import org.parabot.core.ui.Logger;
import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.wrappers.Player;

import java.util.ArrayList;
public class WhitelistAntiBan implements Strategy {
    private ArrayList<String> whitelist;
    private boolean enabled = true;
    public WhitelistAntiBan() {
        WhitelistForm whitelistForm = new WhitelistForm();
        while(whitelistForm.isVisible()) {
            Time.sleep(1000);
        }

        whitelist = whitelistForm.getNames();
        if(whitelist == null) {
            enabled = false;
        } else {
            whitelist.add(Players.getMyPlayer().getName().toLowerCase());
        }
    }

    @Override
    public boolean activate() {
        return enabled && playersClose();
    }

    @Override
    public void execute() {
        Time.sleep(1000);
    }

    private boolean playersClose() {
        for(Player p : Players.getPlayers()) {
            if(!whitelist.contains(p.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
