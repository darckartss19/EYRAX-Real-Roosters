package com.eyrax.realroosters.client;

import com.eyrax.realroosters.EyraxRealRoosters;
import com.eyrax.realroosters.entity.HeritageChicken;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class HeritageChickenRenderer extends MobRenderer<HeritageChicken, HeritageChickenModel> {
    public HeritageChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new HeritageChickenModel(context.bakeLayer(ClientEvents.HERITAGE_CHICKEN_LAYER)), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(HeritageChicken chicken) {
        String age = chicken.isBaby() ? "chick" : chicken.getSex().serializedName();
        String file = chicken.getBreed().serializedName() + "_" + chicken.getPlumage().serializedName() + ".png";
        return ResourceLocation.fromNamespaceAndPath(EyraxRealRoosters.MOD_ID, "textures/entity/" + age + "/" + file);
    }

    @Override
    protected void scale(HeritageChicken chicken, PoseStack poseStack, float partialTickTime) {
        float scale = chicken.isBaby() ? 0.55F : switch (chicken.getBreed()) {
            case BRAHMA -> 1.16F;
            case SILKIE -> 0.88F;
            case LEGHORN -> 0.94F;
            default -> 1.0F;
        };
        poseStack.scale(scale, scale, scale);
    }
}
