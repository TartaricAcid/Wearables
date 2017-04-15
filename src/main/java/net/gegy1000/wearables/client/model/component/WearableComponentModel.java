package net.gegy1000.wearables.client.model.component;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class WearableComponentModel extends ModelBiped {
    public void renderParented(ModelRenderer parent, ModelRenderer cuboid, float scale) {
        GlStateManager.pushMatrix();
        parent.postRender(scale);
        cuboid.render(scale);
        GlStateManager.popMatrix();
    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale) {
        this.setRotationAngles(limbSwing, limbSwingAmount, age, yaw, pitch, scale, entity);

        GlStateManager.pushMatrix();
        if (entity != null) {
            if (entity.isSneaking()) {
                GlStateManager.translate(0.0F, 0.2F, 0.0F);
            }
        }

        this.renderComponent(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
        GlStateManager.popMatrix();
    }

    @Override
    public void setModelAttributes(ModelBase model) {
        super.setModelAttributes(model);
        if (model instanceof ModelBiped) {
            ModelBiped biped = (ModelBiped) model;
            copyModelAngles(biped.bipedRightArm, this.bipedRightArm);
            copyModelAngles(biped.bipedLeftArm, this.bipedLeftArm);
            copyModelAngles(biped.bipedBody, this.bipedBody);
            copyModelAngles(biped.bipedHead, this.bipedHead);
            copyModelAngles(biped.bipedRightLeg, this.bipedRightLeg);
            copyModelAngles(biped.bipedLeftLeg, this.bipedLeftLeg);
        }
    }

    public abstract void renderComponent(Entity entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale);
}
