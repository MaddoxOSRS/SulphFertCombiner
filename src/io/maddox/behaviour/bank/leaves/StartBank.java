package io.maddox.behaviour.bank.leaves;

import io.maddox.framework.Leaf;
import org.powbot.api.Condition;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Inventory;

public class StartBank extends Leaf {
    @Override
    public boolean isValid() {
        return Inventory.isEmpty() || Inventory.stream().name("Saltpetre").isEmpty();
    }

    @Override
    public int onLoop() {
        if (!Bank.opened()) {
            Bank.open();
            Condition.wait(() -> Bank.opened(), 50, 50);
        }
            if (Inventory.isNotEmpty() && Bank.opened()) {
                Bank.depositInventory();
                Condition.wait(Inventory::isEmpty, 500, 5);
        }
            if(Inventory.isEmpty()) {
                Bank.withdraw("Compost", 14);
                Bank.withdraw("Saltpetre", 14);
            }
        return 0;
    }
}
