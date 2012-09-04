package net.minecraft.src;

public class TileEntitySkills extends TileEntity implements IInventory{
    private ItemStack[] skillsContents = new ItemStack[9];

    /**
     * Returns the number of slots in the inventory.
     */
    public int getSizeInventory(){
        return 9;
    }

    /**
     * Returns the stack in slot i
     */
    public ItemStack getStackInSlot(int slot){
        return this.skillsContents[slot];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    public ItemStack decrStackSize(int slot, int stackSize){
        if (this.skillsContents[slot] != null){
            ItemStack item;

            if (this.skillsContents[slot].stackSize <= stackSize){
                item = this.skillsContents[slot];
                this.skillsContents[slot] = null;
                this.onInventoryChanged();
                return item;
            }else{
                item = this.skillsContents[slot].splitStack(stackSize);

                if (this.skillsContents[slot].stackSize == 0){
                    this.skillsContents[slot] = null;
                }

                this.onInventoryChanged();
                return item;
            }
        }else{
            return null;
        }
    }

    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    public ItemStack getStackInSlotOnClosing(int par1){
        if (this.skillsContents[par1] != null){
            ItemStack var2 = this.skillsContents[par1];
            this.skillsContents[par1] = null;
            return var2;
        }else{
            return null;
        }
    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    public void setInventorySlotContents(int slot, ItemStack item){
        this.skillsContents[slot] = item;

        if (item != null && item.stackSize > this.getInventoryStackLimit()){
            item.stackSize = this.getInventoryStackLimit();
        }

        // this.onInventoryChanged();
    }

    /**
     * Returns the name of the inventory.
     */
    public String getInvName(){
        return "container.skills";
    }

    /**
     * Reads a tile entity from NBT.
     */
    public void readFromNBT(NBTTagCompound par1NBTTagCompound){
        super.readFromNBT(par1NBTTagCompound);
        NBTTagList tagList = par1NBTTagCompound.getTagList("Items");
        this.skillsContents = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); i++){
            NBTTagCompound var4 = (NBTTagCompound)tagList.tagAt(i);
            int var5 = var4.getByte("Slot") & 255;

            if (var5 >= 0 && var5 < this.skillsContents.length){
                this.skillsContents[var5] = ItemStack.loadItemStackFromNBT(var4);
            }
        }
    }

    /**
     * Writes a tile entity to NBT.
     */
    public void writeToNBT(NBTTagCompound par1NBTTagCompound){
        super.writeToNBT(par1NBTTagCompound);
        NBTTagList tagList = new NBTTagList();

        for (int i = 0; i < this.skillsContents.length; i++){
            if (this.skillsContents[i] != null){
                NBTTagCompound var4 = new NBTTagCompound();
                var4.setByte("Slot", (byte)i);
                this.skillsContents[i].writeToNBT(var4);
                tagList.appendTag(var4);
            }
        }

        par1NBTTagCompound.setTag("Items", tagList);
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    public int getInventoryStackLimit(){
        return 64;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player){
        return this.worldObj.getBlockTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    public void openChest(){}

    public void closeChest(){}
}
