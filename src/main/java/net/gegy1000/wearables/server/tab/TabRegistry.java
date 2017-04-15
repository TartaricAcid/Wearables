package net.gegy1000.wearables.server.tab;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class TabRegistry {
    public static final CreativeTabs BLOCKS = new CreativeTabs(Wearables.MODID + ".blocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.CRAFTING_TABLE);
        }
    };
    public static final CreativeTabs ITEMS = new CreativeTabs(Wearables.MODID + ".items") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.WEARABLE_COMPONENT);
        }
    };
}
