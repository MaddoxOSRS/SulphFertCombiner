package io.maddox.behaviour.bank;

import io.maddox.framework.Branch;
import org.powbot.api.rt4.Inventory;

public class ActivateBank extends Branch {
    @Override
    public boolean isValid() {
        return Inventory.isEmpty() || Inventory.stream().name("Saltpetre").isEmpty();
    }
}
