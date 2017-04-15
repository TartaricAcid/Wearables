package net.gegy1000.wearables.client.model.component.chest;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlainShirtModel extends ModelBiped {
    public ModelRenderer rightArm;
    public ModelRenderer chest;
    public ModelRenderer leftArm;

    public PlainShirtModel(boolean smallArms) {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.chest = new ModelRenderer(this, 0, 16);
        this.chest.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.chest.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
        this.rightArm = new ModelRenderer(this, 14, 0);
        this.rightArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.rightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
        this.leftArm = new ModelRenderer(this, 0, 0);
        this.leftArm.mirror = true;
        this.leftArm.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.leftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.0F);
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, age, yaw, pitch, scale, entity);

        GlStateManager.pushMatrix();
        this.bipedBody.postRender(scale);
        this.chest.render(scale);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        this.bipedLeftArm.postRender(scale);
        this.leftArm.render(scale);
        GlStateManager.popMatrix();

        GlStateManager.pushMatrix();
        this.bipedRightArm.postRender(scale);
        this.rightArm.render(scale);
        GlStateManager.popMatrix();
    }
}
