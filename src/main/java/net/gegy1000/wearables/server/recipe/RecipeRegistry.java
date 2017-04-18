package net.gegy1000.wearables.server.recipe;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class RecipeRegistry {
    public static void register() {
        GameRegistry.addRecipe(new ExtractArmourRecipe());
        GameRegistry.addRecipe(new ApplyArmourRecipe());
    }
}
