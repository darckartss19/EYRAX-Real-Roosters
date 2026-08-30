package com.eyrax.realroosters.client;

import com.eyrax.realroosters.EyraxRealRoosters;
import com.eyrax.realroosters.entity.HeritageChicken;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public final class HeritageChickenRenderer extends MobRenderer<HeritageChicken, HeritageChickenModel> {
    public HeritageChickenRenderer(EntityRendererProvider.Context context) {
        super(context, new HeritageChickenModel(context.bakeLayer(ClientEvents.HERITAGE_CHICKEN_LAYER)), 0.40F);
    }

    @Override
    public ResourceLocation getTextureLocation(HeritageChicken chicken) {
        String age = chicken.isBaby() ? "chick" : chicken.getSex().serializedName();
        String file = chicken.getBreed().serializedName() + "_" + chicken.getPlumage().serializedName() + ".png";
        return ResourceLocation.fromNamespaceAndPath(EyraxRealRoosters.MOD_ID, "textures/entity/" + age + "/" + file);
    }

    @Override
    protected void scale(HeritageChicken chicken, PoseStack poseStack, float partialTickTime) {
        if (chicken.isBaby()) {
            float chickScale = switch (chicken.getBreed()) {
                case BRAHMA -> 0.90F;
                case SILKIE -> 0.76F;
                case LEGHORN -> 0.79F;
                default -> 0.83F;
            };
            poseStack.scale(chickScale, chickScale, chickScale);
            return;
        }

        float width;
        float height;
        float length;
        switch (chicken.getBreed()) {
            case CRIOLLO_DOMINICANO -> { width = 1.00F; height = 1.06F; length = 0.98F; }
            case RHODE_ISLAND_RED -> { width = 1.08F; height = 0.99F; length = 1.06F; }
            case LEGHORN -> { width = 0.91F; height = 1.08F; length = 0.93F; }
            case PLYMOUTH_ROCK -> { width = 1.09F; height = 0.98F; length = 1.07F; }
            case BRAHMA -> { width = 1.19F; height = 1.13F; length = 1.18F; }
            case SILKIE -> { width = 0.96F; height = 0.91F; length = 0.99F; }
            case AYAM_CEMANI -> { width = 0.96F; height = 1.06F; length = 0.95F; }
            case SUSSEX -> { width = 1.06F; height = 1.00F; length = 1.05F; }
            default -> { width = 1.0F; height = 1.0F; length = 1.0F; }
        }

        float sexWidth = chicken.isRooster() ? 1.03F : 0.98F;
        float sexHeight = chicken.isRooster() ? 1.05F : 0.97F;
        poseStack.scale(width * sexWidth, height * sexHeight, length * sexWidth);
    }
}
