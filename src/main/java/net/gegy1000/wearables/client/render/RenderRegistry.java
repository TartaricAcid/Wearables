package net.gegy1000.wearables.client.render;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.client.render.block.DisplayMannequinRenderer;
import net.gegy1000.wearables.client.render.component.ComponentRenderer;
import net.gegy1000.wearables.client.render.component.chest.PlainShirtRenderer;
import net.gegy1000.wearables.client.render.component.head.HatRenderer;
import net.gegy1000.wearables.client.render.item.WearableComponentRenderer;
import net.gegy1000.wearables.client.render.item.WearableItemRenderer;
import net.gegy1000.wearables.server.api.item.RegisterItemModel;
import net.gegy1000.wearables.server.block.BlockRegistry;
import net.gegy1000.wearables.server.block.DisplayMannequinBlock;
import net.gegy1000.wearables.server.block.entity.DisplayMannequinEntity;
import net.gegy1000.wearables.server.block.entity.WearableChestItemEntity;
import net.gegy1000.wearables.server.block.entity.WearableComponentEntity;
import net.gegy1000.wearables.server.block.entity.WearableFeetItemEntity;
import net.gegy1000.wearables.server.block.entity.WearableHeadItemEntity;
import net.gegy1000.wearables.server.block.entity.WearableLegsItemEntity;
import net.gegy1000.wearables.server.item.ItemRegistry;
import net.gegy1000.wearables.server.wearable.component.ComponentRegistry;
import net.gegy1000.wearables.server.wearable.component.WearableComponentType;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.item.Item;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import java.util.HashMap;
import java.util.Map;

public class RenderRegistry {
    private static final Minecraft MC = Minecraft.getMinecraft();
    private static final Map<String, ComponentRenderer> COMPONENT_RENDERERS = new HashMap<>();

    public static void onPreInit() {
        for (Block block : BlockRegistry.BLOCKS) {
            String name = block.getUnlocalizedName().substring("tile.".length());
            if (block instanceof RegisterItemModel) {
                RenderRegistry.register(block, ((RegisterItemModel) block).getResource(name), "inventory");
            }
        }

        for (Item item : ItemRegistry.ITEMS) {
            String name = item.getUnlocalizedName().substring("item.".length());
            if (item instanceof RegisterItemModel) {
                RenderRegistry.register(item, ((RegisterItemModel) item).getResource(name), "inventory");
            }
        }

        ClientRegistry.bindTileEntitySpecialRenderer(DisplayMannequinEntity.class, new DisplayMannequinRenderer());

        ClientRegistry.bindTileEntitySpecialRenderer(WearableComponentEntity.class, new WearableComponentRenderer());
        ForgeHooksClient.registerTESRItemStack(ItemRegistry.WEARABLE_COMPONENT, 0, WearableComponentEntity.class);

        ClientRegistry.bindTileEntitySpecialRenderer(WearableHeadItemEntity.class, new WearableItemRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(WearableChestItemEntity.class, new WearableItemRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(WearableLegsItemEntity.class, new WearableItemRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(WearableFeetItemEntity.class, new WearableItemRenderer());
        ForgeHooksClient.registerTESRItemStack(ItemRegistry.WEARABLE_HEAD, 0, WearableHeadItemEntity.class);
        ForgeHooksClient.registerTESRItemStack(ItemRegistry.WEARABLE_CHEST, 0, WearableChestItemEntity.class);
        ForgeHooksClient.registerTESRItemStack(ItemRegistry.WEARABLE_LEGS, 0, WearableLegsItemEntity.class);
        ForgeHooksClient.registerTESRItemStack(ItemRegistry.WEARABLE_FEET, 0, WearableFeetItemEntity.class);

        RenderRegistry.register(ComponentRegistry.PLAIN_SHIRT, new PlainShirtRenderer());
        RenderRegistry.register(ComponentRegistry.HAT, new HatRenderer());

        ModelLoader.setCustomStateMapper(BlockRegistry.DISPLAY_MANNEQUIN, new StateMap.Builder().ignore(DisplayMannequinBlock.FACING, DisplayMannequinBlock.HALF).build());
    }

    public static void register(Item item, String path, String type) {
        RenderRegistry.register(item, 0, path, type);
    }

    public static void register(Item item, int meta, String path, String type) {
        ModelResourceLocation resource = new ModelResourceLocation(Wearables.MODID + ":" + path, type);
        ModelLoader.setCustomModelResourceLocation(item, meta, resource);
    }

    public static void register(Block block, int meta, String path, String type) {
        RenderRegistry.register(Item.getItemFromBlock(block), meta, path, type);
    }

    public static void register(Block block, final String path, final String type) {
        RenderRegistry.register(block, 0, path, type);
    }

    public static void register(WearableComponentType type, ComponentRenderer renderer) {
        COMPONENT_RENDERERS.put(type.getIdentifier(), renderer);
    }

    public static ComponentRenderer getRenderer(String identifier) {
        return COMPONENT_RENDERERS.get(identifier);
    }
}
