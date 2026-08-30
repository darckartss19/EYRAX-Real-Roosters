package com.eyrax.realroosters.registry;

import com.eyrax.realroosters.EyraxRealRoosters;
import com.eyrax.realroosters.config.RoostersConfig;
import com.eyrax.realroosters.entity.HeritageChicken;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, EyraxRealRoosters.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<HeritageChicken>> HERITAGE_CHICKEN =
            ENTITY_TYPES.register("heritage_chicken", () -> EntityType.Builder
                    .of(HeritageChicken::new, MobCategory.CREATURE)
                    .sized(0.55F, 0.85F)
                    .clientTrackingRange(10)
                    .build("heritage_chicken"));

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(HERITAGE_CHICKEN.get(), HeritageChicken.createAttributes().build());
    }

    public static void registerSpawnPlacements(RegisterSpawnPlacementsEvent event) {
        event.register(
                HERITAGE_CHICKEN.get(),
                SpawnPlacementTypes.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (type, level, spawnType, pos, random) ->
                        RoostersConfig.NATURAL_SPAWNING.get() && Animal.checkAnimalSpawnRules(type, level, spawnType, pos, random),
                RegisterSpawnPlacementsEvent.Operation.REPLACE);
    }

    private ModEntities() {}
}
