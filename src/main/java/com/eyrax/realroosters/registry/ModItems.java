package com.eyrax.realroosters.registry;

import com.eyrax.realroosters.EyraxRealRoosters;
import com.eyrax.realroosters.item.BreedAnalyzerItem;
import com.eyrax.realroosters.item.TrainingWhistleItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EyraxRealRoosters.MOD_ID);

    public static final DeferredItem<Item> BREED_ANALYZER = ITEMS.register(
            "breed_analyzer", () -> new BreedAnalyzerItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> TRAINING_WHISTLE = ITEMS.register(
            "training_whistle", () -> new TrainingWhistleItem(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<DeferredSpawnEggItem> HERITAGE_CHICKEN_SPAWN_EGG = ITEMS.register(
            "heritage_chicken_spawn_egg",
            () -> new DeferredSpawnEggItem(ModEntities.HERITAGE_CHICKEN, 0x8A3E22, 0xD9B45B, new Item.Properties()));

    private ModItems() {}
}
