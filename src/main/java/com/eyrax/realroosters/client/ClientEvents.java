package com.eyrax.realroosters.client;

import com.eyrax.realroosters.EyraxRealRoosters;
import com.eyrax.realroosters.registry.ModEntities;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = EyraxRealRoosters.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientEvents {
    public static final ModelLayerLocation HERITAGE_CHICKEN_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(EyraxRealRoosters.MOD_ID, "heritage_chicken"), "main");

    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(HERITAGE_CHICKEN_LAYER, HeritageChickenModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.HERITAGE_CHICKEN.get(), HeritageChickenRenderer::new);
    }

    private ClientEvents() {}
}
