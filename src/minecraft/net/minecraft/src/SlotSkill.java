package net.minecraft.src;

public class SlotSkill extends Slot{

    public SlotSkill(IInventory inventory, int i, int j, int k){
        super(inventory, i, j, k);
    }

    public boolean isItemValid(ItemStack itemstack){
        return false;
    }

    public void onPickupFromSlot(ItemStack itemstack){
        super.onPickupFromSlot(itemstack);
    }
}
