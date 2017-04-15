package net.gegy1000.wearables.server.tab;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.server.block.BlockRegistry;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabRegistry {
    public static final CreativeTabs GENERAL = new CreativeTabs(Wearables.MODID + ".general") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(BlockRegistry.DISPLAY_MANNEQUIN);
        }
    };
    public static final CreativeTabs COMPONENTS = new CreativeTabs(Wearables.MODID + ".components") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.WEARABLE_COMPONENT);
        }
    };
    public static final CreativeTabs TEMPLATES = new CreativeTabs(Wearables.MODID + ".templates") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.WEARABLE_CHEST);
        }
    };
}
