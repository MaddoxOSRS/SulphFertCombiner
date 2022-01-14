package io.maddox.behaviour.combineitems;

import io.maddox.framework.Branch;
import org.powbot.api.rt4.Inventory;

public class ActivateCombine extends Branch {
    @Override
    public boolean isValid() {
        return Inventory.stream().name("Compost").isNotEmpty() && Inventory.stream().name("Saltpetre").isNotEmpty();
    }
}
