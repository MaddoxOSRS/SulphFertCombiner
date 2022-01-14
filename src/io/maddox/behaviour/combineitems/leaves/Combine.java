package io.maddox.behaviour.combineitems.leaves;

import io.maddox.data.Configs;
import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.Preferences;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Game;
import org.powbot.api.rt4.Inventory;
import org.powbot.api.rt4.Item;

import static io.maddox.data.Configs.compost;
import static io.maddox.data.Configs.saltpetre;

public class Combine extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.stream().name("Compost").isNotEmpty() && Inventory.stream().name("Saltpetre").isNotEmpty();
    }

    @Override
    public int onLoop() {
        if (Bank.opened()) {
            Bank.close();
            Condition.wait(() -> !Bank.opened(), 50, 50);
        }
        if (!Bank.opened()) {
            if (Inventory.selectedItem().id() == -1) {
                for (int i = 0; i < 28; i++) {
                    if (Inventory.itemAt(i).id() == Configs.compost) {
                        Inventory.itemAt(i).click();
                        Condition.wait(() -> Inventory.selectedItem().id() == Configs.compost, 700, 10);
                        Inventory.stream().name("Saltpetre").last().click();
                    }
                }
            }
        }
        return 0;
    }
}
