package io.maddox;

import io.maddox.behaviour.bank.ActivateBank;
import io.maddox.behaviour.bank.leaves.StartBank;
import io.maddox.behaviour.combineitems.ActivateCombine;
import io.maddox.behaviour.combineitems.leaves.Combine;
import io.maddox.behaviour.fallback.FallbackLeaf;
import io.maddox.behaviour.firstrun.FirsRunBranch;
import io.maddox.behaviour.firstrun.Leaves.StartLeaf;
import io.maddox.behaviour.timeout.TimeoutLeaf;
import io.maddox.data.Configs;
import io.maddox.framework.Tree;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.walking.model.Skill;
import org.powbot.api.script.AbstractScript;
import org.powbot.api.script.OptionType;
import org.powbot.api.script.ScriptConfiguration;
import org.powbot.api.script.ScriptManifest;
import org.powbot.api.script.paint.Paint;
import org.powbot.api.script.paint.PaintBuilder;
import org.powbot.mobile.script.ScriptManager;
import org.powbot.mobile.service.ScriptUploader;

import static io.maddox.data.Configs.*;

/**
 * Credits to @Proto && @Dan for Guidance, and information, Credits to Powbot Development Discord Section
 */



@ScriptManifest(
        name = "SaltpetreCombiner",
        description = "Combines Compost and Saltpetre.",
        version = "0.01",
        author = "Maddox"
)


public class Main extends AbstractScript {

    public static void main(String[] args) {
        new ScriptUploader().uploadAndStart("SaltpetreCombiner", "gimcrowdser", "127.0.0.1:5595", true, false);
    }

    private final Tree tree = new Tree();

    @Override
    public void onStart() {
        timeIdle = System.currentTimeMillis();
        Paint p = new PaintBuilder()
                .addString("Branch:", () -> Configs.currentBranch)
                .addString("Leaf:", () -> Configs.currentLeaf)
                .trackInventoryItems(13419)
                .trackSkill(Skill.Farming)
                .x(30)
                .y(50)
                .build();
        addPaint(p);
        instantiateTree();
    }

    private void instantiateTree() {
        tree.addBranches(
                new TimeoutLeaf(),
                new FirsRunBranch().addLeafs(new StartLeaf()),
                new ActivateBank().addLeafs(new StartBank()),
                new ActivateCombine().addLeafs(new Combine()),
                new FallbackLeaf());
    }

    @Override
    public void poll() {
        tree.onLoop();
    }
}

