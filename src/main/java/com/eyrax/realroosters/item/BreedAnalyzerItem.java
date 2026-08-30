package com.eyrax.realroosters.item;

import com.eyrax.realroosters.entity.HeritageChicken;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BreedAnalyzerItem extends Item {
    public BreedAnalyzerItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand hand) {
        if (target instanceof HeritageChicken chicken) {
            return analyze(player, chicken);
        }
        return InteractionResult.PASS;
    }

    public InteractionResult analyze(Player player, HeritageChicken chicken) {
        if (!player.level().isClientSide) {
            String ageKey = chicken.isBaby() ? "chick" : chicken.getSex().serializedName();
            player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.analysis_title")
                    .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD), false);
            player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.identity",
                    Component.translatable("entity.eyrax_real_roosters." + ageKey),
                    Component.translatable("breed.eyrax_real_roosters." + chicken.getBreed().serializedName()),
                    Component.translatable("plumage.eyrax_real_roosters." + chicken.getPlumage().serializedName())), false);
            player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.traits",
                    chicken.getVitality(), chicken.getAgility(), chicken.getPower(),
                    chicken.getFertility(), chicken.getTemperament()), false);
            player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.specialty",
                    Component.translatable(chicken.getBreed().specialtyTranslationKey())), false);
            if (chicken.isRooster() && !chicken.isBaby()) {
                player.displayClientMessage(Component.translatable("message.eyrax_real_roosters.stance",
                        Component.translatable("stance.eyrax_real_roosters." + chicken.getStance().serializedName())), false);
            }
        }
        return InteractionResult.sidedSuccess(player.level().isClientSide);
    }
}
