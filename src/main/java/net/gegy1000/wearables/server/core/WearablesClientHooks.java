package net.gegy1000.wearables.server.core;

import net.minecraft.item.ItemStack;

public class WearablesClientHooks {
    private static final ThreadLocal<ItemStack> RENDERED_STACK = new ThreadLocal<>();

    public static void updateRenderedStack(ItemStack stack) {
        RENDERED_STACK.set(stack);
    }

    public static ItemStack getRenderedStack() {
        return RENDERED_STACK.get();
    }
}
