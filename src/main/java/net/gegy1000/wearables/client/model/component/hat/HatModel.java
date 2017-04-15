package net.gegy1000.wearables.client.model.component.hat;

import net.gegy1000.wearables.client.model.component.WearableComponentModel;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class HatModel extends WearableComponentModel {
    public ModelRenderer base;
    public ModelRenderer front;
    public ModelRenderer back;
    public ModelRenderer left;
    public ModelRenderer right;
    public ModelRenderer shape20;

    public HatModel() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.front = new ModelRenderer(this, 0, 0);
        this.front.setRotationPoint(-4.0F, 0.0F, -6.0F);
        this.front.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.shape20 = new ModelRenderer(this, 0, 0);
        this.shape20.setRotationPoint(-4.0F, -1.8F, -4.0F);
        this.shape20.addBox(0.0F, 0.0F, 0.0F, 8, 2, 8, 0.0F);
        this.left = new ModelRenderer(this, 0, 0);
        this.left.setRotationPoint(5.0F, 0.0F, -4.0F);
        this.left.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8, 0.0F);
        this.back = new ModelRenderer(this, 0, 0);
        this.back.setRotationPoint(-4.0F, 0.0F, 5.0F);
        this.back.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, 0.0F);
        this.base = new ModelRenderer(this, 0, 0);
        this.base.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.base.addBox(-5.0F, 0.0F, -5.0F, 10, 1, 10, 0.0F);
        this.right = new ModelRenderer(this, 0, 0);
        this.right.setRotationPoint(-6.0F, 0.0F, -4.0F);
        this.right.addBox(0.0F, 0.0F, 0.0F, 1, 1, 8, 0.0F);
        this.base.addChild(this.front);
        this.base.addChild(this.shape20);
        this.base.addChild(this.left);
        this.base.addChild(this.back);
        this.base.addChild(this.right);
    }

    @Override
    public void renderComponent(Entity entity, float limbSwing, float limbSwingAmount, float age, float yaw, float pitch, float scale) {
        this.renderParented(this.bipedHead, this.base, scale);
    }
}
