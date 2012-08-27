package net.minecraft.src;

public class ContainerSkills extends Container{
    private TileEntitySkills tileEntitySkills;

    public ContainerSkills(IInventory inventory, TileEntitySkills theTileEntitySkills){
        this.tileEntitySkills = theTileEntitySkills;
        int var3;
        int var4;

        for (var3 = 0; var3 < 3; ++var3){
            for (var4 = 0; var4 < 3; ++var4){
                this.addSlotToContainer(new Slot(theTileEntitySkills, var4 + var3 * 3, 62 + var4 * 18, 17 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 3; ++var3){
            for (var4 = 0; var4 < 9; ++var4){
                this.addSlotToContainer(new Slot(inventory, var4 + var3 * 9 + 9, 8 + var4 * 18, 84 + var3 * 18));
            }
        }

        for (var3 = 0; var3 < 9; ++var3){
            this.addSlotToContainer(new Slot(inventory, var3, 8 + var3 * 18, 142));
        }
    }

    public boolean canInteractWith(EntityPlayer par1EntityPlayer){
        return this.tileEntitySkills.isUseableByPlayer(par1EntityPlayer);
    }

    /**
     * Called to transfer a stack from one inventory to the other eg. when shift clicking.
     */
    public ItemStack transferStackInSlot(int par1){
        ItemStack var2 = null;
        Slot var3 = (Slot)this.inventorySlots.get(par1);

        if (var3 != null && var3.getHasStack()){
            ItemStack var4 = var3.getStack();
            var2 = var4.copy();

            if (par1 < 9){
                if (!this.mergeItemStack(var4, 9, 45, true)){
                    return null;
                }
            }
            else if (!this.mergeItemStack(var4, 0, 9, false)){
                return null;
            }

            if (var4.stackSize == 0){
                var3.putStack((ItemStack)null);
            }
            else{
                var3.onSlotChanged();
            }

            if (var4.stackSize == var2.stackSize){
                return null;
            }

            var3.onPickupFromSlot(var4);
        }

        return var2;
    }
}
