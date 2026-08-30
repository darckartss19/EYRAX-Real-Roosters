package com.eyrax.realroosters;

import com.eyrax.realroosters.config.RoostersConfig;
import com.eyrax.realroosters.registry.ModEntities;
import com.eyrax.realroosters.registry.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.slf4j.Logger;

@Mod(EyraxRealRoosters.MOD_ID)
public final class EyraxRealRoosters {
    public static final String MOD_ID = "eyrax_real_roosters";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = TABS.register(
            "main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.eyrax_real_roosters"))
                    .withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
                    .icon(() -> ModItems.BREED_ANALYZER.get().getDefaultInstance())
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.BREED_ANALYZER.get());
                        output.accept(ModItems.TRAINING_WHISTLE.get());
                        output.accept(ModItems.HERITAGE_CHICKEN_SPAWN_EGG.get());
                    })
                    .build());

    public EyraxRealRoosters(IEventBus modBus, ModContainer container) {
        ModEntities.ENTITY_TYPES.register(modBus);
        ModItems.ITEMS.register(modBus);
        TABS.register(modBus);

        modBus.addListener(ModEntities::registerAttributes);
        modBus.addListener(ModEntities::registerSpawnPlacements);
        container.registerConfig(ModConfig.Type.COMMON, RoostersConfig.SPEC);

        LOGGER.info("EYRAX Real Roosters 0.1.0 is loading");
    }
}
