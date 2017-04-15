package net.gegy1000.wearables.client.render.block;

import net.gegy1000.wearables.Wearables;
import net.gegy1000.wearables.client.model.block.DisplayMannequinModel;
import net.gegy1000.wearables.server.block.DisplayMannequinBlock;
import net.gegy1000.wearables.server.block.entity.DisplayMannequinEntity;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class DisplayMannequinRenderer extends TileEntitySpecialRenderer<DisplayMannequinEntity> {
    private static final Minecraft MC = Minecraft.getMinecraft();

    private static final DisplayMannequinModel MODEL = new DisplayMannequinModel();
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wearables.MODID, "textures/blocks/display_mannequin.png");
    private static final ResourceLocation TEXTURE_LIGHT = new ResourceLocation(Wearables.MODID, "textures/blocks/display_mannequin_light.png");

    @Override
    public void renderTileEntityAt(DisplayMannequinEntity entity, double x, double y, double z, float partialTicks, int destroyStage) {
        if (entity != null) {
            BlockPos pos = entity.getPos();
            IBlockState state = entity.getWorld().getBlockState(pos);
            EnumFacing facing = state.getValue(DisplayMannequinBlock.FACING);
            GlStateManager.pushMatrix();
            GlStateManager.translate(x + 0.5, y + 1.5F, z + 0.5);
            GlStateManager.scale(-1.0F, -1.0F, 1.0F);
            GlStateManager.rotate(facing.getHorizontalAngle(), 0.0F, 1.0F, 0.0F);

            float lastBrightnessX = OpenGlHelper.lastBrightnessX;
            float lastBrightnessY = OpenGlHelper.lastBrightnessY;

            MC.getTextureManager().bindTexture(TEXTURE);
            MODEL.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240, 240);
            MC.getTextureManager().bindTexture(TEXTURE_LIGHT);
            MODEL.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, lastBrightnessX, lastBrightnessY);

            GlStateManager.popMatrix();
        }
    }
}
