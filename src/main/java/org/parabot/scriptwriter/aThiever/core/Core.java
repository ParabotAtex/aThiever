package org.parabot.scriptwriter.aThiever.core;

import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;
import org.parabot.scriptwriter.aThiever.randoms.IRandomEvent;
import org.parabot.scriptwriter.aThiever.strategies.Relog;
import org.rev317.min.script.ScriptEngine;
import org.parabot.scriptwriter.aThiever.randoms.Trivia;
import org.parabot.scriptwriter.aThiever.antiban.WhitelistAntiBan;
import org.parabot.scriptwriter.aThiever.strategies.Picker;
import org.parabot.scriptwriter.aThiever.strategies.Seller;
import org.parabot.scriptwriter.aThiever.strategies.UnknownLocation;

import java.util.ArrayList;

@ScriptManifest(author = "Atex",
        category = Category.THIEVING,
        description = "THIEVING MONEY",
        name = "aThiever", servers = { "Revival" },
        version = 0.3)
public class Core extends Script {
    ArrayList<Strategy> strategies = new ArrayList<>();
    Trivia trivia;

    @Override
    public boolean onExecute() {
        trivia = new Trivia("http://parabot.abielen.be/data/revival/trivia/", 4000);
        strategies.add(new Relog());
        strategies.add(new WhitelistAntiBan(new IRandomEvent[]{trivia}));
        strategies.add(new Seller());
        strategies.add(new Picker());
        strategies.add(new UnknownLocation());
        provide(strategies);

        ScriptEngine.getInstance().addMessageListener(trivia);

        return true;
    }
}
