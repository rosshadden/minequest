package net.minecraftforge.common;

import java.util.*;

import net.minecraft.src.*;

public class ForgeHooks
{
    static class GrassEntry extends WeightedRandomItem
    {
        public final Block block;
        public final int metadata;
        public GrassEntry(Block block, int meta, int weight)
        {
            super(weight);
            this.block = block;
            this.metadata = meta;
        }
    }
    
    static class SeedEntry extends WeightedRandomItem
    {
        public final ItemStack seed;
        public SeedEntry(ItemStack seed, int weight)
        {
            super(weight);
            this.seed = seed;
        }
    }
    static final List<GrassEntry> grassList = new ArrayList<GrassEntry>();
    static final List<SeedEntry> seedList = new ArrayList<SeedEntry>();
    
    public static void plantGrass(World world, int x, int y, int z)
    {
        GrassEntry grass = (GrassEntry)WeightedRandom.getRandomItem(world.rand, grassList);
        if (grass == null || grass.block == null || !grass.block.canBlockStay(world, x, y, z))
        {
            return;
        }
        world.setBlockAndMetadataWithNotify(x, y, z, grass.block.blockID, grass.metadata);
    }

    public static ItemStack getGrassSeed(World world)
    {
        SeedEntry entry = (SeedEntry)WeightedRandom.getRandomItem(world.rand, seedList);
        if (entry == null || entry.seed == null)
        {
            return null;
        }
        return entry.seed.copy();
    }
    
    private static boolean toolInit = false;
    static HashMap<Item, List> toolClasses = new HashMap<Item, List>();
    static HashMap<List, Integer> toolHarvestLevels = new HashMap<List, Integer>();
    static HashSet<List> toolEffectiveness = new HashSet<List>();
    
    public static boolean canHarvestBlock(Block block, EntityPlayer player, int metadata)
    {
        if (block.blockMaterial.isHarvestable())
        {
            return true;
        }

        ItemStack stack = player.inventory.getCurrentItem();
        if (stack == null)
        {
            return player.canHarvestBlock(block);
        }

        List info = (List)toolClasses.get(stack.getItem());
        if (info == null)
        {
            return player.canHarvestBlock(block);
        }

        Object[] tmp = info.toArray();
        String toolClass = (String)tmp[0];
        int harvestLevel = (Integer)tmp[1];

        Integer blockHarvestLevel = (Integer)toolHarvestLevels.get(Arrays.asList(block, metadata, toolClass));
        if (blockHarvestLevel == null)
        {
            return player.canHarvestBlock(block);
        }

        if (blockHarvestLevel > harvestLevel)
        {
            return false;
        }
        return true;
    }

    public static float blockStrength(Block block, EntityPlayer player, World world, int x, int y, int z)
    {
        int metadata = world.getBlockMetadata(x, y, z);
        float hardness = block.getBlockHardness(world, x, y, z);
        if (hardness < 0.0F)
        {
            return 0.0F;
        }

        if (!canHarvestBlock(block, player, metadata))
        {
            return 1.0F / hardness / 100F;
        }
        else
        {
             return player.getCurrentPlayerStrVsBlock(block, metadata) / hardness / 30F;
        }
    }

    public static boolean isToolEffective(ItemStack stack, Block block, int metadata)
    {
        List toolClass = (List)toolClasses.get(stack.getItem());
        if (toolClass == null)
        {
            return false;
        }
        return toolEffectiveness.contains(Arrays.asList(block, metadata, (String)toolClass.get(0)));
    }
    
    static void initTools()
    {
        if (toolInit)
        {
            return;
        }
        toolInit = true;

        MinecraftForge.setToolClass(Item.pickaxeWood,    "pickaxe", 0);
        MinecraftForge.setToolClass(Item.pickaxeStone,   "pickaxe", 1);
        MinecraftForge.setToolClass(Item.pickaxeSteel,   "pickaxe", 2);
        MinecraftForge.setToolClass(Item.pickaxeGold,    "pickaxe", 0);
        MinecraftForge.setToolClass(Item.pickaxeDiamond, "pickaxe", 3);

        MinecraftForge.setToolClass(Item.axeWood,    "axe", 0);
        MinecraftForge.setToolClass(Item.axeStone,   "axe", 1);
        MinecraftForge.setToolClass(Item.axeSteel,   "axe", 2);
        MinecraftForge.setToolClass(Item.axeGold,    "axe", 0);
        MinecraftForge.setToolClass(Item.axeDiamond, "axe", 3);

        MinecraftForge.setToolClass(Item.shovelWood,    "shovel", 0);
        MinecraftForge.setToolClass(Item.shovelStone,   "shovel", 1);
        MinecraftForge.setToolClass(Item.shovelSteel,   "shovel", 2);
        MinecraftForge.setToolClass(Item.shovelGold,    "shovel", 0);
        MinecraftForge.setToolClass(Item.shovelDiamond, "shovel", 3);

        for (Block block : ItemPickaxe.blocksEffectiveAgainst)
        {
            MinecraftForge.setBlockHarvestLevel(block, "pickaxe", 0);
        }

        for (Block block : ItemSpade.blocksEffectiveAgainst)
        {
            MinecraftForge.setBlockHarvestLevel(block, "shovel", 0);
        }

        for (Block block : ItemAxe.blocksEffectiveAgainst)
        {
            MinecraftForge.setBlockHarvestLevel(block, "axe", 0);
        }

        MinecraftForge.setBlockHarvestLevel(Block.obsidian,     "pickaxe", 3);
        MinecraftForge.setBlockHarvestLevel(Block.oreEmerald,   "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(Block.oreDiamond,   "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(Block.blockDiamond, "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(Block.oreGold,      "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(Block.blockGold,    "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(Block.oreIron,      "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(Block.blockSteel,   "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(Block.oreLapis,     "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(Block.blockLapis,   "pickaxe", 1);
        MinecraftForge.setBlockHarvestLevel(Block.oreRedstone,  "pickaxe", 2);
        MinecraftForge.setBlockHarvestLevel(Block.oreRedstoneGlowing, "pickaxe", 2);
        MinecraftForge.removeBlockEffectiveness(Block.oreRedstone, "pickaxe");
        MinecraftForge.removeBlockEffectiveness(Block.obsidian,    "pickaxe");
        MinecraftForge.removeBlockEffectiveness(Block.oreRedstoneGlowing, "pickaxe");
    }

    public static String getTexture(String _default, Object obj)
    {
        if (obj instanceof Item)
        {
            return ((Item)obj).getTextureFile();
        }
        else if (obj instanceof Block)
        {
            return ((Block)obj).getTextureFile();
        }
        else
        {
            return _default;
        }
    }

    public static int getTotalArmorValue(EntityPlayer player)
    {
        int ret = 0;
        for (int x = 0; x < player.inventory.armorInventory.length; x++)
        {
            ItemStack stack = player.inventory.armorInventory[x];
            if (stack != null && stack.getItem() instanceof ISpecialArmor)
            {
                ret += ((ISpecialArmor)stack.getItem()).getArmorDisplay(player, stack, x);
            }
            else if (stack != null && stack.getItem() instanceof ItemArmor)
            {
                ret += ((ItemArmor)stack.getItem()).damageReduceAmount;
            }
        }
        return ret;
    }
    
    static
    {
        grassList.add(new GrassEntry(Block.plantYellow, 0, 20));
        grassList.add(new GrassEntry(Block.plantRed,    0, 10));
        seedList.add(new SeedEntry(new ItemStack(Item.seeds), 10));
        initTools();
        System.out.printf("MinecraftForge v%s Initialized\n", ForgeVersion.getVersion());
        ModLoader.getLogger().info(String.format("MinecraftForge v%s Initialized", ForgeVersion.getVersion()));
    }

    /**
     * Called when a player uses 'pick block', calls new Entity and Block hooks.
     */
    public static boolean onPickBlock(MovingObjectPosition target, EntityPlayer player, World world)
    {
        ItemStack result = null;
        boolean isCreative = player.capabilities.isCreativeMode;

        if (target.typeOfHit == EnumMovingObjectType.TILE)
        {
            int x = target.blockX;
            int y = target.blockY;
            int z = target.blockZ;
            Block var8 = Block.blocksList[world.getBlockId(x, y, z)];

            if (var8 == null)
            {
                return false;
            }

            result = var8.getPickBlock(target, world, x, y, z);
        }
        else
        {
            if (target.typeOfHit != EnumMovingObjectType.ENTITY || target.entityHit == null || !isCreative)
            {
                return false;
            }

            result = target.entityHit.getPickedResult(target);
        }

        if (result == null)
        {
            return false;
        }

        for (int x = 0; x < 9; x++)
        {
            ItemStack stack = player.inventory.getStackInSlot(x);
            if (stack != null && stack.isItemEqual(result))
            {
                player.inventory.currentItem = x;
                return true;
            }
        }

        if (!isCreative)
        {
            return false;
        }

        int slot = player.inventory.getFirstEmptyStack();
        if (slot < 0 || slot >= 9)
        {
            return false;
        }

        player.inventory.setInventorySlotContents(slot, result);
        player.inventory.currentItem = slot;
        return true;
    }
}
