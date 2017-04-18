package net.gegy1000.wearables.server.plugin.jei;

import mezz.jei.api.BlankModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import net.gegy1000.wearables.server.block.BlockRegistry;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.gegy1000.wearables.server.item.WearableComponentItem;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class WearableJEIPlugin extends BlankModPlugin {
    @Override
    public void register(IModRegistry registry) {
        registry.getJeiHelpers().getIngredientBlacklist().addIngredientToBlacklist(new ItemStack(BlockRegistry.DISPLAY_MANNEQUIN));

        registry.addRecipeCategories(new FabricatorRecipeCategory(registry.getJeiHelpers().getGuiHelper()));
        registry.addRecipeCategoryCraftingItem(new ItemStack(BlockRegistry.WEARABLE_FABRICATOR), "wearables.fabricator");
        registry.handleRecipes(WearableComponentType.class, FabricatorRecipeWrapper::new, "wearables.fabricator");
        registry.addRecipes(ComponentRegistry.COMPONENTS, "wearables.fabricator");
    }

    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {
        subtypeRegistry.registerSubtypeInterpreter(ItemRegistry.WEARABLE_COMPONENT, stack -> WearableComponentItem.getComponent(stack).getType().getIdentifier());
    }
}
