package net.minecraft.src;

public class ContainerSkills extends Container{
    private TileEntitySkills tileEntitySkills;

    public ContainerSkills(IInventory inventory, TileEntitySkills theTileEntitySkills){
        this.tileEntitySkills = theTileEntitySkills;
        IInventory skillbar = new InventoryBasic("Skills", 9);
        int i;
        int j;

        for (i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(skillbar, i, 8 + i * 18, 33));
        }

        for (i = 0; i < 3; ++i){
            for (j = 0; j < 9; ++j){
                this.addSlotToContainer(new Slot(inventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i){
            this.addSlotToContainer(new Slot(inventory, i, 8 + i * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer player){
        return this.tileEntitySkills.isUseableByPlayer(player);
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack transferStackInSlot(int slot){
        ItemStack newItem = null;
        Slot item = (Slot)this.inventorySlots.get(slot);

        if (item != null && item.getHasStack()){
            ItemStack var4 = item.getStack();
            newItem = var4.copy();

            if (slot < 9){
                if (!this.mergeItemStack(var4, 9, 45, true)){
                    return null;
                }
            }else if (!this.mergeItemStack(var4, 0, 9, false)){
                return null;
            }

            if (var4.stackSize == 0){
                item.putStack((ItemStack)null);
            }else{
                item.onSlotChanged();
            }

            if (var4.stackSize == newItem.stackSize){
                return null;
            }

            item.onPickupFromSlot(var4);
        }

        return newItem;
    }
}
