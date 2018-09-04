package org.parabot.scriptwriter.aThiever.strategies;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.input.Keyboard;
import org.parabot.environment.scripts.framework.SleepCondition;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.*;
import org.rev317.min.api.wrappers.Item;
import org.rev317.min.api.wrappers.Npc;

public class Seller implements Strategy {
    @Override
    public boolean activate() {
        return Inventory.isFull() && Npcs.getClosest(22)  != null;
    }

    @Override
    public void execute() {
        Npc trader = Npcs.getClosest(22);
        if(trader != null && Interfaces.getOpenInterfaceId() == -1) {
            trader.interact(Npcs.Option.TALK_TO);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return Interfaces.getOpenInterfaceId() == 3824;
                }
            }, 3000);
        }
        if(Interfaces.getOpenInterfaceId() == 3824) {
            Item cake = Inventory.getItem(1892);
            Menu.sendAction(53, cake.getId() - 1, cake.getSlot(), 3823);
            Time.sleep(1500);
            Keyboard.getInstance().sendKeys("28");
            Time.sleep(500);
            Time.sleep(new SleepCondition() {
                @Override
                public boolean isValid() {
                    return !Inventory.isFull();
                }
            }, 3000);
            if(!Inventory.isFull()) {
                Menu.clickButton(3902);
                Time.sleep(new SleepCondition() {
                    @Override
                    public boolean isValid() {
                        return Interfaces.getOpenInterfaceId() == -1;
                    }
                }, 2000);
                Picker.selectNewStall();
            }
        }
    }
}
