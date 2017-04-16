package net.gegy1000.wearables.server.patcher;

import net.gegy1000.wearables.server.core.WearablesClientHooks;
import net.ilexiconn.llibrary.server.asm.RuntimePatcher;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.ForgeHooksClient;

public class WearablesRuntimePatcher extends RuntimePatcher {
    @Override
    public void onInit() {
        this.patchClass(TileEntityItemStackRenderer.class)
                .patchMethod("renderByItem", ItemStack.class, void.class)
                .apply(Patch.BEFORE, data -> data.node.getPrevious() == null, method -> method
                        .var(ALOAD, 1)
                        .method(INVOKESTATIC, WearablesClientHooks.class, "updateRenderedStack", ItemStack.class, void.class));
        this.patchClass(ForgeHooksClient.class)
                .patchMethod("handleCameraTransforms", IBakedModel.class, ItemCameraTransforms.TransformType.class, boolean.class, IBakedModel.class)
                .apply(Patch.BEFORE, data -> data.node.getPrevious() == null, method -> method
                        .var(ALOAD, 1)
                        .method(INVOKESTATIC, WearablesClientHooks.class, "updateCameraTransform", ItemCameraTransforms.TransformType.class, void.class));
    }
}
