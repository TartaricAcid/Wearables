package net.gegy1000.wearables.client.model.component;

import net.gegy1000.wearables.client.model.block.DisplayMannequinModel;
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

    public void renderParented(ModelRenderer parent, ModelRenderer cuboid, float renderScale, float offsetX, float offsetY, float offsetZ, float scale) {
        GlStateManager.pushMatrix();
        parent.postRender(scale);
        GlStateManager.translate(offsetX, offsetY, offsetZ);
        GlStateManager.scale(renderScale, renderScale, renderScale);
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

    public void renderMannequin(float scale) {
        this.renderComponent(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, scale);
    }

    public abstract void renderComponent(Entity entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale);

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
        } else if (model instanceof DisplayMannequinModel) {
            DisplayMannequinModel mannequin = (DisplayMannequinModel) model;
            copyModelAngles(mannequin.rightArm, this.bipedRightArm);
            copyModelAngles(mannequin.leftArm, this.bipedLeftArm);
            copyModelAngles(mannequin.body, this.bipedBody);
            copyModelAngles(mannequin.head, this.bipedHead);
            copyModelAngles(mannequin.rightLeg, this.bipedRightLeg);
            copyModelAngles(mannequin.leftLeg, this.bipedLeftLeg);
        }
    }

    protected void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
